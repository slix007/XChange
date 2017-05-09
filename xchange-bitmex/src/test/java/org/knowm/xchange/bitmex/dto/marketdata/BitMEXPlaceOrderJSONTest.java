package org.knowm.xchange.bitmex.dto.marketdata;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Samuel Reed
 */

public class BitMEXPlaceOrderJSONTest {

  @Test
  public void testUnmarshal() throws IOException {

    // Read in the JSON from the example resources
    InputStream is = BitMEXPlaceOrderJSONTest.class.getResourceAsStream("/marketdata/example-placeOrder-data.json");

    // Use Jackson to parse it
    ObjectMapper mapper = new ObjectMapper();
    BitMEXOrder cryptoFacilitiesOrder = mapper.readValue(is, BitMEXOrder.class);

    // Verify that the example data was unmarshalled correctly
    assertThat(cryptoFacilitiesOrder.isSuccess()).isTrue();

    String orderId = cryptoFacilitiesOrder.getOrderId();

    assertThat(orderId).isEqualTo("25c3521c-979e-4b78-a4a1-a807f0597c28");
  }

}
