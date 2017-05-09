package org.knowm.xchange.bitmex;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.knowm.xchange.bitmex.dto.account.BitMEXAccount;
import org.knowm.xchange.bitmex.dto.marketdata.BitMEXCancel;
import org.knowm.xchange.bitmex.dto.marketdata.BitMEXCumulatedBidAsk;
import org.knowm.xchange.bitmex.dto.marketdata.BitMEXCumulativeBidAsk;
import org.knowm.xchange.bitmex.dto.marketdata.BitMEXFill;
import org.knowm.xchange.bitmex.dto.marketdata.BitMEXFills;
import org.knowm.xchange.bitmex.dto.marketdata.BitMEXOpenOrder;
import org.knowm.xchange.bitmex.dto.marketdata.BitMEXOpenOrders;
import org.knowm.xchange.bitmex.dto.marketdata.BitMEXOrder;
import org.knowm.xchange.bitmex.dto.marketdata.BitMEXTicker;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.account.AccountInfo;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.dto.marketdata.OrderBook;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades.TradeSortType;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrade;
import org.knowm.xchange.dto.trade.UserTrades;

/**
 * @author Samuel Reed
 */

public class BitMEXAdapters {

  public static Ticker adaptTicker(BitMEXTicker bitmexTicker, CurrencyPair currencyPair) {

    if (bitmexTicker != null) {
      Ticker.Builder builder = new Ticker.Builder();

      builder.ask(bitmexTicker.getAsk());
      builder.bid(bitmexTicker.getBid());
      builder.last(bitmexTicker.getLast());
      builder.currencyPair(currencyPair);
      builder.low(bitmexTicker.getLow24H());
      builder.high(bitmexTicker.getHigh24H());
      builder.volume(bitmexTicker.getVol24H());
      builder.timestamp(bitmexTicker.getLastTime());

      return builder.build();
    }
    return null;
  }

  public static Currency adaptCurrency(String code) {
    return new Currency(code);
  }

  public static AccountInfo adaptAccount(BitMEXAccount bitmexAccount, String username) {

    List<Balance> balances = new ArrayList<Balance>(bitmexAccount.getBalances().size());
    Balance balance;

    for (Entry<String, BigDecimal> balancePair : bitmexAccount.getBalances().entrySet()) {
      if (balancePair.getKey().equalsIgnoreCase("xbt")) {
        // For xbt balance we construct both total=deposited xbt and available=total - margin balances
        balance = new Balance(Currency.BTC, balancePair.getValue(), bitmexAccount.getAuxiliary().get("af"));
      } else {
        Currency currency = adaptCurrency(balancePair.getKey());
        balance = new Balance(currency, balancePair.getValue());
      }
      balances.add(balance);
    }
    return new AccountInfo(username, new Wallet(balances));
  }

  public static String adaptOrderId(BitMEXOrder order) {

    return order.getOrderId();

  }

  public static OrderType adaptOrderType(String bitmexOrderType) {

    return bitmexOrderType.equalsIgnoreCase("buy") ? OrderType.BID : OrderType.ASK;
  }

  public static LimitOrder adaptLimitOrder(BitMEXOpenOrder ord) {
    return new LimitOrder(adaptOrderType(ord.getDirection()), ord.getQuantity(),
        new CurrencyPair(new Currency(ord.getTradeable()), new Currency(ord.getUnit())), ord.getUid(), ord.getTimestamp(), ord.getLimitPrice());
  }

  public static OpenOrders adaptOpenOrders(BitMEXOpenOrders orders) {
    List<LimitOrder> limitOrders = new ArrayList<LimitOrder>();

    if (orders != null && orders.isSuccess()) {
      for (BitMEXOpenOrder ord : orders.getOrders()) {
        // how to handle stop-loss orders?
        // ignore anything but a plain limit order for now
        if (ord.getType().equals("LMT")) {
          limitOrders.add(adaptLimitOrder(ord));
        }
      }
    }

    return new OpenOrders(limitOrders);

  }

  public static UserTrade adaptFill(BitMEXFill fill) {
    return new UserTrade(adaptOrderType(fill.getSide()), fill.getSize(), new CurrencyPair(fill.getSymbol(), "USD"), fill.getPrice(),
        fill.getFillTime(), fill.getFillId(), fill.getOrderId(), null, (Currency) null);
  }

  public static UserTrades adaptFills(BitMEXFills bitmexFills) {
    List<UserTrade> trades = new ArrayList<UserTrade>();

    if (bitmexFills != null && bitmexFills.isSuccess()) {
      for (BitMEXFill fill : bitmexFills.getFills()) {
        trades.add(adaptFill(fill));
      }
    }

    return new UserTrades(trades, TradeSortType.SortByTimestamp);
  }

  public static LimitOrder adaptOrderBookOrder(BitMEXCumulatedBidAsk cumulBidAsk, String direction, String tradeable, String unit) {
    LimitOrder order = new LimitOrder(adaptOrderType(direction), cumulBidAsk.getQuantity(),
        new CurrencyPair(new Currency(tradeable), new Currency(unit)), null, null, cumulBidAsk.getPrice());

    return order;
  }

  public static List<LimitOrder> adaptOrderBookSide(List<BitMEXCumulatedBidAsk> cumulBidAsks, String direction, String tradeable,
      String unit) {
    List<LimitOrder> limitOrders = new ArrayList<LimitOrder>();

    for (BitMEXCumulatedBidAsk cumulBidAsk : cumulBidAsks) {
      limitOrders.add(adaptOrderBookOrder(cumulBidAsk, direction, tradeable, unit));
    }

    return limitOrders;
  }

  public static OrderBook adaptOrderBook(BitMEXCumulativeBidAsk cumul) throws IOException {
    List<BitMEXCumulatedBidAsk> cumulBids = cumul.getCumulatedBids();
    List<BitMEXCumulatedBidAsk> cumulAsks = cumul.getCumulatedAsks();

    return new OrderBook(null, adaptOrderBookSide(cumulAsks, "Sell", "Forward", "USD"), adaptOrderBookSide(cumulBids, "Buy", "Forward", "USD"));
  }

  public static boolean adaptBitMEXCancel(BitMEXCancel cancel) {
    return cancel.isSuccess();
  }

}
