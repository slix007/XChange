package org.knowm.xchange.bitmex.dto.marketdata;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.bitmex.dto.BitMEXResult;

/**
 * @author Samuel Reed
 */

@Deprecated
public class BitMEXTrades extends BitMEXResult {

  private final List<BitMEXTrade> trades;

  public BitMEXTrades(@JsonProperty("result") String result, @JsonProperty("error") String error,
      @JsonProperty("orders") List<BitMEXTrade> trades) {

    super(result, error);

    this.trades = trades;
  }

  public List<BitMEXTrade> getTrades() {
    return trades;
  }

  @Override
  public String toString() {

    String res = "BitMEXTrades [trades=";
    for (BitMEXTrade trd : trades)
      res = res + trd.toString() + ", ";
    res = res + " ]";

    return res;
  }

}
