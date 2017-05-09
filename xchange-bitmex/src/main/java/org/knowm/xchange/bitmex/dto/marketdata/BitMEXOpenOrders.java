package org.knowm.xchange.bitmex.dto.marketdata;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.bitmex.dto.BitMEXResult;

/**
 * @author Samuel Reed
 */

public class BitMEXOpenOrders extends BitMEXResult {

  private final List<BitMEXOpenOrder> orders;

  public BitMEXOpenOrders(@JsonProperty("result") String result, @JsonProperty("error") String error,
      @JsonProperty("orders") List<BitMEXOpenOrder> orders) {

    super(result, error);

    this.orders = orders;
  }

  public List<BitMEXOpenOrder> getOrders() {
    return orders;
  }

  @Override
  public String toString() {

    String res = "BitMEXOrders [orders=";
    for (BitMEXOpenOrder ord : orders)
      res = res + ord.toString() + ", ";
    res = res + " ]";

    return res;
  }

}
