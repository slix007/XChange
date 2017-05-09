package org.knowm.xchange.bitmex.service.polling;

import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitmex.dto.marketdata.BitMEXCumulativeBidAsk;
import org.knowm.xchange.bitmex.dto.marketdata.BitMEXInstruments;
import org.knowm.xchange.bitmex.dto.marketdata.BitMEXTicker;
import org.knowm.xchange.bitmex.dto.marketdata.BitMEXTickers;
import org.knowm.xchange.currency.CurrencyPair;

/**
 * @author Samuel Reed
 */

public class BitMEXMarketDataServiceRaw extends BitMEXBasePollingService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public BitMEXMarketDataServiceRaw(Exchange exchange) {

    super(exchange);
  }

  public BitMEXTicker getBitMEXTicker(CurrencyPair currencyPair) throws IOException {

    BitMEXTicker ticker = bitmex.getTickers().getTicker(currencyPair.base.toString());

    return ticker;
  }

  public BitMEXTickers getBitMEXTickers() throws IOException {

    BitMEXTickers tickers = bitmex.getTickers();

    return tickers;
  }

  public BitMEXInstruments getBitMEXInstruments() throws IOException {

    BitMEXInstruments instruments = bitmex.getInstruments();

    return instruments;
  }

  public BitMEXCumulativeBidAsk getBitMEXCumulativeBidAsk(CurrencyPair currencyPair) {
    BitMEXCumulativeBidAsk cfcbidask = bitmex.getCumulativeBidAsk(currencyPair.base.toString(), currencyPair.counter.toString());

    return cfcbidask;
  }

}
