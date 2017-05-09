package org.knowm.xchange.bitmex;

import java.io.IOException;
import java.math.BigDecimal;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.knowm.xchange.bitmex.dto.account.BitMEXAccount;
import org.knowm.xchange.bitmex.dto.marketdata.BitMEXCancel;
import org.knowm.xchange.bitmex.dto.marketdata.BitMEXFills;
import org.knowm.xchange.bitmex.dto.marketdata.BitMEXOpenOrders;
import org.knowm.xchange.bitmex.dto.marketdata.BitMEXOrder;

import si.mazi.rescu.ParamsDigest;
import si.mazi.rescu.SynchronizedValueFactory;

/**
 * @author Samuel Reed
 */

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
public interface BitMEXAuthenticated extends BitMEX {

  @POST
  @Path("/v2/account")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public BitMEXAccount account(@HeaderParam("APIKey") String apiKey, @HeaderParam("Authent") ParamsDigest signer,
      @HeaderParam("Nonce") SynchronizedValueFactory<Long> nonce) throws IOException;

  @POST
  @Path("/placeOrder")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public BitMEXOrder placeOrder(@HeaderParam("APIKey") String apiKey, @HeaderParam("Authent") ParamsDigest signer,
      @HeaderParam("Nonce") SynchronizedValueFactory<Long> nonce, @QueryParam("type") String type, @QueryParam("tradeable") String tradeable,
      @QueryParam("unit") String unit, @QueryParam("dir") String dir, @QueryParam("qty") BigDecimal qty, @QueryParam("price") BigDecimal price)
      throws IOException;

  @POST
  @Path("/cancelOrder")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public BitMEXCancel cancelOrder(@HeaderParam("APIKey") String apiKey, @HeaderParam("Authent") ParamsDigest signer,
      @HeaderParam("Nonce") SynchronizedValueFactory<Long> nonce, @QueryParam("uid") String uid, @QueryParam("tradeable") String tradeable,
      @QueryParam("unit") String unit) throws IOException;

  @POST
  @Path("/openOrders")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public BitMEXOpenOrders openOrders(@HeaderParam("APIKey") String apiKey, @HeaderParam("Authent") ParamsDigest signer,
      @HeaderParam("Nonce") SynchronizedValueFactory<Long> nonce) throws IOException;

  @POST
  @Path("/v2/fills")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public BitMEXFills fills(@HeaderParam("APIKey") String apiKey, @HeaderParam("Authent") ParamsDigest signer,
      @HeaderParam("Nonce") SynchronizedValueFactory<Long> nonce) throws IOException;

}
