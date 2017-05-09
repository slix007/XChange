package org.knowm.xchange.bitmex.dto.marketdata;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.bitmex.dto.BitMEXResult;

/**
 * @author Samuel Reed
 */

@Deprecated
public class BitMEXIndex extends BitMEXResult {

  private final BigDecimal cfbpi;

  public BitMEXIndex(@JsonProperty("result") String result, @JsonProperty("error") String error, @JsonProperty("cf-bpi") BigDecimal cfbpi) {

    super(result, error);

    this.cfbpi = cfbpi;
  }

  public BigDecimal getCfbpi() {
    return cfbpi;
  }

  @Override
  public String toString() {

    if (isSuccess())
      return "BitMEXIndex [cfbpi=" + cfbpi + "]";
    else
      return super.toString();
  }

}
