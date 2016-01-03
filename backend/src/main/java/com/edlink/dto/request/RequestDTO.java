package com.edlink.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDTO<T> {
  @JsonProperty("request")
  private T request;

  @JsonProperty("user_id")
  private String userId;
}
