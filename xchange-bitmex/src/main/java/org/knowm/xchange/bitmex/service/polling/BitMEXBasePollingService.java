package org.knowm.xchange.bitmex.service.polling;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitmex.BitMEXAuthenticated;
import org.knowm.xchange.bitmex.service.BitMEXDigest;
import org.knowm.xchange.service.BaseExchangeService;
import org.knowm.xchange.service.polling.BasePollingService;

import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.RestProxyFactory;

/**
 * @author Samuel Reed
 */

public class BitMEXBasePollingService extends BaseExchangeService implements BasePollingService {

  protected BitMEXAuthenticated bitmex;
  protected ParamsDigest signatureCreator;

  /**
   * Constructor
   *
   * @param exchange
   */
  public BitMEXBasePollingService(Exchange exchange) {

    super(exchange);

    bitmex = RestProxyFactory.createProxy(BitMEXAuthenticated.class, exchange.getExchangeSpecification().getSslUri());
    signatureCreator = BitMEXDigest.createInstance(exchange.getExchangeSpecification().getSecretKey());
  }

}
