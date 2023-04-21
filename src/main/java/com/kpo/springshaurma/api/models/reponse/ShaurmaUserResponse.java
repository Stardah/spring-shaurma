package com.kpo.springshaurma.api.models.reponse;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kpo.springshaurma.api.models.Response;
import com.kpo.springshaurma.api.models.ResponseStatus;
import com.kpo.springshaurma.api.models.ShaurmaUserObject;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class ShaurmaUserResponse extends Response<ShaurmaUserObject> {

  @JsonCreator
  public ShaurmaUserResponse(
      @JsonProperty("status") ResponseStatus responseStatus,
      @JsonProperty("errorCode") String errorCode,
      @JsonProperty("errorMessage") String errorMessage,
      @JsonProperty("displayedMessage") String displayedMessage,
      @JsonProperty("body") ShaurmaUserObject body) {
    super(responseStatus, errorCode, errorMessage, displayedMessage, body);
  }

}
