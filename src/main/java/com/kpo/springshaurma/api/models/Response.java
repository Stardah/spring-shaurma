package com.kpo.springshaurma.api.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

  private ResponseStatus status;

  private String errorCode;

  private String errorMessage;

  String displayedMessage;

  private T body;

  @JsonCreator
  public Response(
      @JsonProperty("status") ResponseStatus status,
      @JsonProperty("errorCode") String errorCode,
      @JsonProperty("errorMessage") String errorMessage,
      @JsonProperty("displayedMessage") String displayedMessage,
      @JsonProperty("body") T body) {
    this.status = status;
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.displayedMessage = displayedMessage;
    this.body = body;
  }
}
