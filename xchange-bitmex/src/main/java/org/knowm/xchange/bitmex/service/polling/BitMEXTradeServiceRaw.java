package org.knowm.xchange.bitmex.service.polling;

import java.io.IOException;
import java.math.BigDecimal;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitmex.dto.marketdata.BitMEXCancel;
import org.knowm.xchange.bitmex.dto.marketdata.BitMEXFills;
import org.knowm.xchange.bitmex.dto.marketdata.BitMEXOpenOrders;
import org.knowm.xchange.bitmex.dto.marketdata.BitMEXOrder;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.trade.LimitOrder;

/**
 * @author Samuel Reed
 */

public class BitMEXTradeServiceRaw extends BitMEXBasePollingService {

  /**
   * Constructor
   *
   * @param exchange
   * @param nonceFactory
   */
  public BitMEXTradeServiceRaw(Exchange exchange) {

    super(exchange);
  }

  public BitMEXOrder placeBitMEXLimitOrder(LimitOrder order) throws IOException {
    String type = "LMT";
    String tradeable = order.getCurrencyPair().base.toString();
    String unit = order.getCurrencyPair().counter.toString();
    String dir = "Buy";
    if (order.getType().equals(OrderType.ASK)) {
      dir = "Sell";
    }
    BigDecimal qty = order.getTradableAmount();
    BigDecimal price = order.getLimitPrice();

    BitMEXOrder ord = bitmex.placeOrder(exchange.getExchangeSpecification().getApiKey(), signatureCreator,
        exchange.getNonceFactory(), type, tradeable, unit, dir, qty, price);

    return ord;
  }

  public BitMEXCancel cancelBitMEXOrder(String uid, CurrencyPair currencyPair) throws IOException {
    BitMEXCancel res = bitmex.cancelOrder(exchange.getExchangeSpecification().getApiKey(), signatureCreator,
        exchange.getNonceFactory(), uid, currencyPair.base.toString(), currencyPair.counter.toString());

    return res;
  }

  public BitMEXOpenOrders getBitMEXOpenOrders() throws IOException {
    BitMEXOpenOrders openOrders = null;
    try {
      openOrders = bitmex.openOrders(exchange.getExchangeSpecification().getApiKey(), signatureCreator, exchange.getNonceFactory());
    } catch (Exception e) {
      return null;
    }

    return openOrders;
  }

  public BitMEXFills getBitMEXFills() throws IOException {
    BitMEXFills fills = null;

    try {
      fills = bitmex.fills(exchange.getExchangeSpecification().getApiKey(), signatureCreator, exchange.getNonceFactory());
    } catch (Exception e) {
      return null;
    }

    return fills;
  }
}
