package org.knowm.xchange.bitmex.dto.marketdata;

import java.math.BigDecimal;

/**
 * @author Samuel Reed
 */

public class BitMEXCumulatedBidAsk {

  private final BigDecimal price;
  private final BigDecimal quantity;

  public BitMEXCumulatedBidAsk(BigDecimal price, BigDecimal quantity) {
    super();
    this.price = price;
    this.quantity = quantity;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public BigDecimal getQuantity() {
    return quantity;
  }

}
