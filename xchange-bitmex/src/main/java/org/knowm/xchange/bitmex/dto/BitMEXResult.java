package org.knowm.xchange.bitmex.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * @author Samuel Reed
 */

public class BitMEXResult {

  private final String result;
  private final String error;

  /**
   * Constructor
   *
   * @param result
   * @param error
   */
  @JsonCreator
  public BitMEXResult(String result, String error) {

    this.result = result;
    this.error = error;
  }

  public boolean isSuccess() {

    return result.equalsIgnoreCase("success");
  }

  public String getResult() {

    return result;
  }

  public String getError() {
    return error;
  }

  @Override
  public String toString() {

    if (isSuccess())
      return result;
    else
      return result + " : " + error;
  }
}
