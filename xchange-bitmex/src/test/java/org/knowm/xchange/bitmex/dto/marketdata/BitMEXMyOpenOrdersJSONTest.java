package org.knowm.xchange.bitmex.dto.marketdata;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Samuel Reed
 */

public class BitMEXOpenOrdersJSONTest {

  @Test
  public void testUnmarshal() throws IOException {

    // Read in the JSON from the example resources
    InputStream is = BitMEXOpenOrdersJSONTest.class.getResourceAsStream("/marketdata/example-myOpenOrders-data.json");

    // Use Jackson to parse it
    ObjectMapper mapper = new ObjectMapper();
    BitMEXOpenOrders cryptoFacilitiesOpenOrders = mapper.readValue(is, BitMEXOpenOrders.class);

    // Verify that the example data was unmarshalled correctly
    assertThat(cryptoFacilitiesOpenOrders.isSuccess()).isTrue();

    List<BitMEXOpenOrder> orders = cryptoFacilitiesOpenOrders.getOrders();

    assertThat(orders.size()).isEqualTo(1);

    Iterator<BitMEXOpenOrder> it = orders.iterator();
    BitMEXOpenOrder ord = it.next();

    assertThat(ord.getUid()).isEqualTo("25c3521c-979e-4b78-a4a1-a807f0597c28");
    assertThat(ord.getTradeable()).isEqualTo("F-XBT:USD-Dec15");
    assertThat(ord.getUnit()).isEqualTo("USD");
    assertThat(ord.getDirection()).isEqualTo("Buy");
    assertThat(ord.getQuantity()).isEqualTo(new BigDecimal("1"));
    assertThat(ord.getType()).isEqualTo("LMT");
    assertThat(ord.getLimitPrice()).isEqualTo(new BigDecimal("300.07"));
  }

}
