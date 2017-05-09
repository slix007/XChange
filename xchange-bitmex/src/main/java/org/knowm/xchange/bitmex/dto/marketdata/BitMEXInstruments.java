package org.knowm.xchange.bitmex.dto.marketdata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.bitmex.dto.BitMEXResult;

/**
 * @author Neil Panchen
 */

public class BitMEXInstruments extends BitMEXResult {

  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

  private final Date serverTime;
  private final List<BitMEXInstrument> instruments;

  public BitMEXInstruments(@JsonProperty("result") String result, @JsonProperty("serverTime") String strServerTime,
      @JsonProperty("error") String error, @JsonProperty("instruments") List<BitMEXInstrument> instruments) throws ParseException {

    super(result, error);

    this.serverTime = DATE_FORMAT.parse(strServerTime);
    this.instruments = instruments;
  }

  public List<BitMEXInstrument> getInstruments() {
    return instruments;
  }

  @Override
  public String toString() {

    if (isSuccess()) {
      String res = "BitMEXInstruments [serverTime=" + DATE_FORMAT.format(serverTime) + ",instruments=";
      for (BitMEXInstrument ct : instruments)
        res = res + ct.toString() + ", ";
      res = res + " ]";

      return res;
    } else {
      return super.toString();
    }
  }

}
