package com.kpo.springshaurma.api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

import static com.kpo.springshaurma.util.SerializationConstants.DATE_TIME_FORMAT;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShaurmaUserObject {

  public enum Role {
    @JsonProperty("role")
    USER,
  }

  public enum MobileAccountType {
    GOOGLE,
    FACEBOOK,
    VK,
    APPLE,
    MANUAL,
  }

  @JsonIgnore
  private UUID id;

  private String email;

  @JsonProperty(access = Access.WRITE_ONLY)
  private String password;

  private String displayName;

  private String pictureUrl;

  @JsonIgnore
  private Role role;

  private MobileAccountType mainAccount;

  @JsonIgnore
  private Boolean active;

  @JsonIgnore
  private Boolean ban;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
  @JsonIgnore
  private OffsetDateTime banUntilDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
  @JsonIgnore
  private OffsetDateTime registrationDate;

}
