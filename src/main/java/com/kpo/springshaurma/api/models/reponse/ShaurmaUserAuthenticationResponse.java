package com.kpo.springshaurma.api.models.reponse;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kpo.springshaurma.api.models.Response;
import com.kpo.springshaurma.api.models.ResponseStatus;
import com.kpo.springshaurma.api.models.ShaurmaUserObject;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class ShaurmaUserAuthenticationResponse extends
        Response<ShaurmaUserAuthenticationResponse.MobileUserAuthenticationResponseBody> {

  @JsonCreator
  public ShaurmaUserAuthenticationResponse(
      @JsonProperty("status") ResponseStatus status,
      @JsonProperty("errorCode") String errorCode,
      @JsonProperty("errorMessage") String errorMessage,
      @JsonProperty("displayedMessage") String displayedMessage,
      @JsonProperty("body") MobileUserAuthenticationResponseBody body) {
    super(status, errorCode, errorMessage, displayedMessage, body);
  }

  @Data
  @Builder
  public static class MobileUserAuthenticationResponseBody {

    private String accessToken;

    private String refreshToken;

    private ShaurmaUserObject mobileUser;

    public MobileUserAuthenticationResponseBody(
        @JsonProperty("accessToken") String accessToken,
        @JsonProperty("refreshToken") String refreshToken,
        @JsonProperty("mobileUser") ShaurmaUserObject mobileUser) {
      this.accessToken = accessToken;
      this.refreshToken = refreshToken;
      this.mobileUser = mobileUser;
    }
  }

}
