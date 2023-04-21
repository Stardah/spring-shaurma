package com.kpo.springshaurma.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class SimpleResponse extends Response<Boolean> {

  @JsonCreator
  public SimpleResponse(
      @JsonProperty("status") ResponseStatus status,
      @JsonProperty("errorCode") String errorCode,
      @JsonProperty("errorMessage") String errorMessage,
      @JsonProperty("displayedMessage") String displayedMessage,
      @JsonProperty("body") Boolean body) {
    super(status, errorCode, errorMessage, displayedMessage, body);
  }
}
