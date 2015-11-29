package com.edlink.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
public class ErrorDetails {
  @JsonProperty("error_code")
  private int errorCode;

  @JsonProperty("error_message")
  private String errorMessage;
}
