package org.knowm.xchange.bitmex.dto.marketdata;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.bitmex.dto.BitMEXResult;

/**
 * @author Samuel Reed
 */

@Deprecated
public class BitMEXContracts extends BitMEXResult {

  private final List<BitMEXContract> contracts;

  public BitMEXContracts(@JsonProperty("result") String result, @JsonProperty("error") String error,
      @JsonProperty("contracts") List<BitMEXContract> contracts) {

    super(result, error);

    this.contracts = contracts;
  }

  public List<BitMEXContract> getContracts() {
    return contracts;
  }

  @Override
  public String toString() {

    String res = "BitMEXContracts [contracts=";
    for (BitMEXContract ct : contracts)
      res = res + ct.toString() + ", ";
    res = res + " ]";

    return res;
  }

}
