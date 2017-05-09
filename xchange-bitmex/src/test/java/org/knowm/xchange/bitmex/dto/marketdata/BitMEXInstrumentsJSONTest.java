package org.knowm.xchange.bitmex.dto.marketdata;

import static org.fest.assertions.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Neil Panchen
 */

public class BitMEXInstrumentsJSONTest {

  @Test
  public void testUnmarshal() throws IOException, ParseException {

    // Read in the JSON from the example resources
    InputStream is = BitMEXInstrumentsJSONTest.class.getResourceAsStream("/marketdata/example-instruments-data.json");

    // Use Jackson to parse it
    ObjectMapper mapper = new ObjectMapper();
    BitMEXInstruments cryptoFacilitiesInstruments = mapper.readValue(is, BitMEXInstruments.class);

    // Verify that the example data was unmarshalled correctly
    assertThat(cryptoFacilitiesInstruments.isSuccess()).isTrue();

    List<BitMEXInstrument> instruments = cryptoFacilitiesInstruments.getInstruments();
    assertThat(instruments.size()).isEqualTo(10);

    Iterator<BitMEXInstrument> it = instruments.iterator();
    BitMEXInstrument ct = it.next();

    assertThat(ct.getTradeable()).isTrue();
    assertThat(ct.getSymbol()).isEqualTo("f-xbt:usd-apr16-w5");
    assertThat(ct.getUnderlying()).isEqualTo("cf-hbpi");
    assertThat(ct.getContractSize()).isEqualTo(new BigDecimal("1"));
    assertThat(ct.getType()).isEqualTo("futures");
    assertThat(ct.getTickSize()).isEqualTo(new BigDecimal("0.01"));

    //2016-04-29 17:00:00
    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
    assertThat(ct.getLastTradingTime()).isEqualTo(DATE_FORMAT.parse("2016-04-29T16:00:00.000Z"));

  }

}
