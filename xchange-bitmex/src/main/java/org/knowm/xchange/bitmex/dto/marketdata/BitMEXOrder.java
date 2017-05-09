package org.knowm.xchange.bitmex.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.bitmex.dto.BitMEXResult;

/**
 * @author Samuel Reed
 */

public class BitMEXOrder extends BitMEXResult {

  private final String orderId;

  public BitMEXOrder(@JsonProperty("result") String result, @JsonProperty("error") String error, @JsonProperty("orderId") String orderId) {

    super(result, error);

    this.orderId = orderId;
  }

  public String getOrderId() {
    return orderId;
  }

  @Override
  public String toString() {

    if (isSuccess())
      return "BitMEXOrder [orderId=" + orderId + "]";
    else
      return super.toString();
  }

}
