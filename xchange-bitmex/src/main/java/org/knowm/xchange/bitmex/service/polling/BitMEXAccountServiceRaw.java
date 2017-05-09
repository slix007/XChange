package org.knowm.xchange.bitmex.service.polling;

import java.io.IOException;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.bitmex.dto.account.BitMEXAccount;

/**
 * @author Samuel Reed
 */

public class BitMEXAccountServiceRaw extends BitMEXBasePollingService {

  /**
   * Constructor
   *
   * @param exchange
   */
  public BitMEXAccountServiceRaw(Exchange exchange) {

    super(exchange);
  }

  public BitMEXAccount getBitMEXAccount() throws IOException {

    return bitmex.account(exchange.getExchangeSpecification().getApiKey(), signatureCreator, exchange.getNonceFactory());
  }

}
