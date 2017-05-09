package org.knowm.xchange.bitmex.dto.marketdata;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.knowm.xchange.bitmex.dto.account.BitMEXAccount;

/**
 * @author Panchen
 */

public class BitMEXAccountJSONTest {

  @Test
  public void testUnmarshal() throws IOException {

    // Read in the JSON from the example resources
    InputStream is = BitMEXAccountJSONTest.class.getResourceAsStream("/marketdata/example-account-data.json");

    // Use Jackson to parse it
    ObjectMapper mapper = new ObjectMapper();
    BitMEXAccount cryptoFacilitiesAccount = mapper.readValue(is, BitMEXAccount.class);

    // Verify that the example data was unmarshalled correctly
    assertThat(cryptoFacilitiesAccount.isSuccess()).isTrue();

    assertThat(cryptoFacilitiesAccount.getBalances().get("f-xbt:usd-feb16-w4")).isEqualTo(new BigDecimal("50"));
    assertThat(cryptoFacilitiesAccount.getBalances().get("f-xbt:usd-mar16-w1")).isEqualTo(new BigDecimal("-15"));
    assertThat(cryptoFacilitiesAccount.getBalances().get("xbt")).isEqualTo(new BigDecimal("141.31756797"));

    assertThat(cryptoFacilitiesAccount.getAuxiliary().get("af")).isEqualTo(new BigDecimal("100.73891563"));
    assertThat(cryptoFacilitiesAccount.getAuxiliary().get("pnl")).isEqualTo(new BigDecimal("12.42134766"));
    assertThat(cryptoFacilitiesAccount.getAuxiliary().get("pv")).isEqualTo(new BigDecimal("153.73891563"));
    assertThat(cryptoFacilitiesAccount.getAuxiliary().get("usd")).isEqualTo(new BigDecimal("-119012.92"));

    assertThat(cryptoFacilitiesAccount.getMarginRequirements().get("im")).isEqualTo(new BigDecimal("52.8"));
    assertThat(cryptoFacilitiesAccount.getMarginRequirements().get("mm")).isEqualTo(new BigDecimal("23.76"));
    assertThat(cryptoFacilitiesAccount.getMarginRequirements().get("lt")).isEqualTo(new BigDecimal("39.6"));
    assertThat(cryptoFacilitiesAccount.getMarginRequirements().get("tt")).isEqualTo(new BigDecimal("15.84"));

    assertThat(cryptoFacilitiesAccount.getTriggerEstimates().get("im")).isEqualTo(new BigDecimal("311"));
    assertThat(cryptoFacilitiesAccount.getTriggerEstimates().get("mm")).isEqualTo(new BigDecimal("300"));
    assertThat(cryptoFacilitiesAccount.getTriggerEstimates().get("lt")).isEqualTo(new BigDecimal("289"));
    assertThat(cryptoFacilitiesAccount.getTriggerEstimates().get("tt")).isEqualTo(new BigDecimal("283"));

  }

}
