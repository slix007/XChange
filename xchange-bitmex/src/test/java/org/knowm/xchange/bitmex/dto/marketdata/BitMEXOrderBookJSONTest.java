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

public class BitMEXCumulativeBidAskJSONTest {

  @Test
  public void testUnmarshal() throws IOException {

    // Read in the JSON from the example resources
    InputStream is = BitMEXCumulativeBidAskJSONTest.class.getResourceAsStream("/marketdata/example-orderBook-data.json");

    // Use Jackson to parse it
    ObjectMapper mapper = new ObjectMapper();
    BitMEXCumulativeBidAsk cryptoFacilitiesCumulativeBidAsk = mapper.readValue(is, BitMEXCumulativeBidAsk.class);

    // Verify that the example data was unmarshalled correctly
    assertThat(cryptoFacilitiesCumulativeBidAsk.isSuccess()).isTrue();

    assertThat(cryptoFacilitiesCumulativeBidAsk.getCumulatedBids().get(0).getPrice()).isEqualTo(new BigDecimal("325.20"));
    assertThat(cryptoFacilitiesCumulativeBidAsk.getCumulatedBids().get(0).getQuantity()).isEqualTo(new BigDecimal("1"));

    assertThat(cryptoFacilitiesCumulativeBidAsk.getCumulatedAsks().get(0).getPrice()).isEqualTo(new BigDecimal("327.52"));
    assertThat(cryptoFacilitiesCumulativeBidAsk.getCumulatedAsks().get(0).getQuantity()).isEqualTo(new BigDecimal("7"));
  }

}
