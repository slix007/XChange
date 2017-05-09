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

@Deprecated
public class BitMEXTradesJSONTest {

  @Test
  public void testUnmarshal() throws IOException {

    // Read in the JSON from the example resources
    InputStream is = BitMEXTradesJSONTest.class.getResourceAsStream("/marketdata/example-trades-data.json");

    // Use Jackson to parse it
    ObjectMapper mapper = new ObjectMapper();
    BitMEXTrades cryptoFacilitiesTrades = mapper.readValue(is, BitMEXTrades.class);

    // Verify that the example data was unmarshalled correctly
    assertThat(cryptoFacilitiesTrades.isSuccess()).isTrue();

    List<BitMEXTrade> trades = cryptoFacilitiesTrades.getTrades();

    assertThat(trades.size()).isEqualTo(1);

    Iterator<BitMEXTrade> it = trades.iterator();
    BitMEXTrade trd = it.next();

    assertThat(trd.getUid()).isEqualTo("7c229c4a-3be9-40ab-8c13-89afc81d3adc");
    assertThat(trd.getTradeable()).isEqualTo("F-XBT:USD-Dec15");
    assertThat(trd.getUnit()).isEqualTo("USD");
    assertThat(trd.getDirection()).isEqualTo("Sell");
    assertThat(trd.getQuantity()).isEqualTo(new BigDecimal("1"));
    assertThat(trd.getPrice()).isEqualTo(new BigDecimal("346.3"));
  }

}
