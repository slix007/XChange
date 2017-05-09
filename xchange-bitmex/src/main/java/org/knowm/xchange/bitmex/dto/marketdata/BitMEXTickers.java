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

public class BitMEXTickers extends BitMEXResult {

  private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");

  private final Date serverTime;
  private final List<BitMEXTicker> tickers;

  public BitMEXTickers(@JsonProperty("result") String result, @JsonProperty("serverTime") String strServerTime,
      @JsonProperty("error") String error, @JsonProperty("tickers") List<BitMEXTicker> tickers) throws ParseException {

    super(result, error);

    this.serverTime = strServerTime == null ? null : DATE_FORMAT.parse(strServerTime);
    this.tickers = tickers;
  }

  public Date getServerTime() {
    return serverTime;
  }

  public List<BitMEXTicker> getTickers() {
    return tickers;
  }

  public BitMEXTicker getTicker(String symbol) {
    if (isSuccess() && tickers != null) {
      for (BitMEXTicker ticker : tickers) {
        if (ticker != null && ticker.getSymbol().equalsIgnoreCase(symbol)) {
          return ticker;
        }
      }
    }
    return null;
  }

  @Override
  public String toString() {

    if (isSuccess()) {
      String res = "BitMEXTickers [serverTime=" + DATE_FORMAT.format(serverTime) + ", tickers=";
      for (BitMEXTicker ticker : tickers) {
        res = res + ticker.toString() + ", ";
      }
      res = res + " ]";

      return res;
    } else {
      return super.toString();
    }
  }

}
