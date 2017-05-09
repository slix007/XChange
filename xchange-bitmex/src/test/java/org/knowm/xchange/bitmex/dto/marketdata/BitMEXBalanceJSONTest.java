package org.knowm.xchange.bitmex.dto.marketdata;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.knowm.xchange.bitmex.dto.account.BitMEXBalance;

/**
 * @author Samuel Reed
 */

@Deprecated
public class BitMEXBalanceJSONTest {

  @Test
  public void testUnmarshal() throws IOException {

    // Read in the JSON from the example resources
    InputStream is = BitMEXBalanceJSONTest.class.getResourceAsStream("/marketdata/example-balance-data.json");

    // Use Jackson to parse it
    ObjectMapper mapper = new ObjectMapper();
    BitMEXBalance cryptoFacilitiesBalance = mapper.readValue(is, BitMEXBalance.class);

    // Verify that the example data was unmarshalled correctly
    assertThat(cryptoFacilitiesBalance.isSuccess()).isTrue();

    Map<String, BigDecimal> balances = cryptoFacilitiesBalance.getBalances();

    assertThat(balances.size()).isEqualTo(4);

    Iterator<Entry<String, BigDecimal>> it = balances.entrySet().iterator();
    Entry<String, BigDecimal> bal = it.next();

    assertThat(bal.getKey()).isEqualTo("F-XBT:USD-Dec15");
    assertThat(bal.getValue()).isEqualTo(new BigDecimal("10.0"));
  }

}
