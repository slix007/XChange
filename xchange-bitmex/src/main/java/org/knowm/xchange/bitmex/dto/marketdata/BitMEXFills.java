package org.knowm.xchange.bitmex.dto.marketdata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.bitmex.dto.BitMEXResult;

/**
 * @author Panchen
 */

public class BitMEXFills extends BitMEXResult {

  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

  private final Date serverTime;
  private final List<BitMEXFill> fills;

  public BitMEXFills(@JsonProperty("result") String result, @JsonProperty("serverTime") String strServerTime,
      @JsonProperty("error") String error, @JsonProperty("fills") List<BitMEXFill> fills) throws ParseException {

    super(result, error);

    this.serverTime = DATE_FORMAT.parse(strServerTime);
    this.fills = fills;
  }

  public List<BitMEXFill> getFills() {
    return fills;
  }

  public Date getServerTime() {
    return serverTime;
  }

  @Override
  public String toString() {

    if (isSuccess()) {
      String res = "BitMEXFills [serverTime=" + DATE_FORMAT.format(serverTime) + ", fills=";
      for (BitMEXFill fill : fills)
        res = res + fill.toString() + ", ";
      res = res + " ]";

      return res;
    } else {
      return super.toString();
    }
  }

}
