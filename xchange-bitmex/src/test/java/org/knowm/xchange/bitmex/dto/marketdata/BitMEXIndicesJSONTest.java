package org.knowm.xchange.bitmex.dto.marketdata;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Samuel Reed
 */

@Deprecated
public class BitMEXIndexJSONTest {

  @Test
  public void testUnmarshal() throws IOException {

    // Read in the JSON from the example resources
    InputStream is = BitMEXIndexJSONTest.class.getResourceAsStream("/marketdata/example-indices-data.json");

    // Use Jackson to parse it
    ObjectMapper mapper = new ObjectMapper();
    BitMEXIndex cryptoFacilitiesIndex = mapper.readValue(is, BitMEXIndex.class);

    // Verify that the example data was unmarshalled correctly
    assertThat(cryptoFacilitiesIndex.isSuccess()).isTrue();
    assertThat(cryptoFacilitiesIndex.getCfbpi()).isEqualTo(new BigDecimal("322.48"));
  }

}
