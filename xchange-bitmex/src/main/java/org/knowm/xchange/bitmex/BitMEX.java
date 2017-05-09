package org.knowm.xchange.bitmex;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.knowm.xchange.bitmex.dto.marketdata.BitMEXCumulativeBidAsk;
import org.knowm.xchange.bitmex.dto.marketdata.BitMEXInstruments;
import org.knowm.xchange.bitmex.dto.marketdata.BitMEXTickers;

/**
 * @author Samuel Reed
 */

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public interface BitMEX {

  @GET
  @Path("/v2/tickers")
  BitMEXTickers getTickers();

  @GET
  @Path("/cumulativebidask")
  BitMEXCumulativeBidAsk getCumulativeBidAsk(@QueryParam("tradeable") String tradeable, @QueryParam("unit") String unit);

  @GET
  @Path("/v2/instruments")
  BitMEXInstruments getInstruments();

}
