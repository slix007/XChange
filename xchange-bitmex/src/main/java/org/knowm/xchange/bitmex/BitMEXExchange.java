package org.knowm.xchange.bitmex;

import org.knowm.xchange.BaseExchange;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeSpecification;
import org.knowm.xchange.bitmex.service.polling.BitMEXAccountService;
import org.knowm.xchange.bitmex.service.polling.BitMEXMarketDataService;
import org.knowm.xchange.bitmex.service.polling.BitMEXTradeService;
import org.knowm.xchange.utils.nonce.CurrentTimeNonceFactory;

import si.mazi.rescu.SynchronizedValueFactory;

/**
 * @author Samuel Reed
 */

public class BitMEXExchange extends BaseExchange implements Exchange {

  private final SynchronizedValueFactory<Long> nonceFactory = new CurrentTimeNonceFactory();

  @Override
  protected void initServices() {
    this.pollingMarketDataService = new BitMEXMarketDataService(this);
    this.pollingAccountService = new BitMEXAccountService(this);
    this.pollingTradeService = new BitMEXTradeService(this);
  }

  @Override
  public ExchangeSpecification getDefaultExchangeSpecification() {

    ExchangeSpecification exchangeSpecification = new ExchangeSpecification(this.getClass().getCanonicalName());
    exchangeSpecification.setSslUri("https://www.bitmex.com/api/v1");
    exchangeSpecification.setHost("www.bitmex.com");
    exchangeSpecification.setPort(443);
    exchangeSpecification.setExchangeName("BitMEX");
    exchangeSpecification.setExchangeDescription("BitMEX is a bitcoin derivatives exchange.");
    return exchangeSpecification;
  }

  @Override
  public SynchronizedValueFactory<Long> getNonceFactory() {

    return nonceFactory;
  }
}
