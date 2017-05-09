package org.knowm.xchange.bitmex.dto.account;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.knowm.xchange.bitmex.dto.BitMEXResult;

/**
 * @author Samuel Reed
 */

public class BitMEXBalance extends BitMEXResult {

  private final Map<String, BigDecimal> balances;

  @JsonCreator
  public BitMEXBalance(Map<String, Object> data) {

    super(data.get("result").toString(), data.get("error") != null ? ((String) data.get("error")) : null);

    this.balances = new LinkedHashMap<>();
    for (Entry<String, Object> dt : data.entrySet()) {
      if (!dt.getKey().equals("result") && !dt.getKey().equals("error")) {
        balances.put(dt.getKey(), new BigDecimal(dt.getValue().toString()));
      }
    }
  }

  public Map<String, BigDecimal> getBalances() {
    return balances;
  }

}
