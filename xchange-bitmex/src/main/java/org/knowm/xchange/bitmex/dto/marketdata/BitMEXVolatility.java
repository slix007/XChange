package org.knowm.xchange.bitmex.dto.marketdata;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.bitmex.dto.BitMEXResult;

/**
 * @author Samuel Reed
 */

@Deprecated
public class BitMEXVolatility extends BitMEXResult {

  private final BigDecimal volatility;

  public BitMEXVolatility(@JsonProperty("result") String result, @JsonProperty("error") String error,
      @JsonProperty("volatility") BigDecimal volatility) {

    super(result, error);

    this.volatility = volatility;
  }

  public BigDecimal getVolatility() {
    return volatility;
  }

  @Override
  public String toString() {

    if (isSuccess())
      return "BitMEXVolatility [volatility=" + volatility + "]";
    else
      return super.toString();
  }

}
