package org.knowm.xchange.bitmex.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.bitmex.dto.BitMEXResult;

/**
 * @author Samuel Reed
 */

public class BitMEXCancel extends BitMEXResult {

  public BitMEXCancel(@JsonProperty("result") String result, @JsonProperty("error") String error) {

    super(result, error);
  }

}
