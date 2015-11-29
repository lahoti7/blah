package com.edlink.dto.auth;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class LoginDTO {

  @NotNull
  @JsonProperty("user_name")
  private String userName;

  @NotNull
  private String password;
}
