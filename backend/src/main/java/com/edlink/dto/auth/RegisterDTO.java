package com.edlink.dto.auth;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class RegisterDTO {

  @NotNull
  @JsonProperty("user_name")
  private String userName;

  @NotNull
  private String password;

  @NotNull
  @JsonProperty("first_name")
  private String firstName;

  @NotNull
  @JsonProperty("last_name")
  private String lastName;
}
