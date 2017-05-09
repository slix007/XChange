package org.knowm.xchange.bitmex.dto.account;

import java.math.BigDecimal;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.bitmex.dto.BitMEXResult;

/**
 * @author Panchen
 */

public class BitMEXAccount extends BitMEXResult {

  private final BitMEXAccountInfo accountInfo;

  public BitMEXAccount(@JsonProperty("result") String result, @JsonProperty("serverTime") String strServerTime,
      @JsonProperty("error") String error, @JsonProperty("account") BitMEXAccountInfo accountInfo) {

    super(result, error);

    this.accountInfo = accountInfo;
  }

  public Map<String, BigDecimal> getBalances() {
    return accountInfo.getBalances();
  }

  public Map<String, BigDecimal> getAuxiliary() {
    return accountInfo.getAuxiliary();
  }

  public Map<String, BigDecimal> getMarginRequirements() {
    return accountInfo.getMarginRequirements();
  }

  public Map<String, BigDecimal> getTriggerEstimates() {
    return accountInfo.getTriggerEstimates();
  }
}
