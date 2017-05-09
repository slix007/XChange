package org.knowm.xchange.bitmex.dto.marketdata;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.knowm.xchange.bitmex.dto.BitMEXResult;

/**
 * @author Samuel Reed
 */

public class BitMEXCumulativeBidAsk extends BitMEXResult {

  private final String cumulatedBids;
  private final String cumulatedAsks;

  public BitMEXCumulativeBidAsk(@JsonProperty("result") String result, @JsonProperty("error") String error,
      @JsonProperty("cumulatedBids") String cumulatedBids, @JsonProperty("cumulatedAsks") String cumulatedAsks) throws ParseException {

    super(result, error);

    this.cumulatedBids = cumulatedBids;
    this.cumulatedAsks = cumulatedAsks;
  }

  @Override
  public String toString() {
    return "BitMEXCumulativeBidAsk [cumulatedBids=" + cumulatedBids + ", cumulatedAsks=" + cumulatedAsks + " ]";
  }

  private List<BitMEXCumulatedBidAsk> parseCumulatedString(String strCumulated) throws JsonProcessingException, IOException {
    List<BitMEXCumulatedBidAsk> res = new LinkedList<BitMEXCumulatedBidAsk>();

    String jsonString = "{ \"table\" : " + strCumulated + " }";

    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode root = objectMapper.readTree(jsonString);
    JsonNode tableNode = root.get("table");
    int size = tableNode.size();
    for (int i = 0; i < size; i++) {
      BigDecimal price = new BigDecimal(tableNode.get(i).get(0).asText()).setScale(2, RoundingMode.HALF_EVEN);
      BigDecimal qty = new BigDecimal(tableNode.get(i).get(1).asText()).setScale(0, RoundingMode.HALF_EVEN);

      res.add(new BitMEXCumulatedBidAsk(price, qty));
    }

    return res;
  }

  private List<BitMEXCumulatedBidAsk> parseCumulatedReverseString(String strCumulated) throws JsonProcessingException, IOException {
    List<BitMEXCumulatedBidAsk> res = new LinkedList<BitMEXCumulatedBidAsk>();

    String jsonString = "{ \"table\" : " + strCumulated + " }";

    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode root = objectMapper.readTree(jsonString);
    JsonNode tableNode = root.get("table");
    int size = tableNode.size();
    for (int i = size - 1; i >= 0; i--) {
      BigDecimal price = new BigDecimal(tableNode.get(i).get(0).asText()).setScale(2, RoundingMode.HALF_EVEN);
      BigDecimal qty = new BigDecimal(tableNode.get(i).get(1).asText()).setScale(0, RoundingMode.HALF_EVEN);

      res.add(new BitMEXCumulatedBidAsk(price, qty));
    }

    return res;
  }

  public List<BitMEXCumulatedBidAsk> getCumulatedBids() throws JsonProcessingException, IOException {
    // CryptoFacilites https api returns order book bids in reverse order.
    // I.e. this.cumulatedBids[0] is not best bid!
    return parseCumulatedReverseString(this.cumulatedBids);
  }

  public List<BitMEXCumulatedBidAsk> getCumulatedAsks() throws JsonProcessingException, IOException {
    return parseCumulatedString(this.cumulatedAsks);
  }

}
