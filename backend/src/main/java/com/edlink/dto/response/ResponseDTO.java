package com.edlink.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

@Setter
public class ResponseDTO<T> {
  private T response;

  @JsonProperty("error_details")
  private ErrorDetails errorDetails;

  @JsonProperty("status_message")
  private String statusMessage;
}
