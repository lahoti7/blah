package com.edlink.dto.base;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PrimitiveDTO<T> {
  private T value;

  public PrimitiveDTO() {}

  private PrimitiveDTO(T value) {
    this.value = value;
  }

  public static PrimitiveDTO<Integer> wrap(int intValue) {
    return new PrimitiveDTO<Integer>(intValue);
  }

  public static PrimitiveDTO<Long> wrap(long longValue) {
    return new PrimitiveDTO<Long>(longValue);
  }

  public static PrimitiveDTO<Boolean> wrap(boolean boolValue) {
    return new PrimitiveDTO<Boolean>(boolValue);
  }

  public static PrimitiveDTO<Integer> wrap(Integer intValue) {
    return new PrimitiveDTO<Integer>(intValue);
  }

  public static PrimitiveDTO<Long> wrap(Long longValue) {
    return new PrimitiveDTO<Long>(longValue);
  }

  public static PrimitiveDTO<Boolean> wrap(Boolean boolValue) {
    return new PrimitiveDTO<Boolean>(boolValue);
  }

  public static PrimitiveDTO<String> wrap(String string) {
    return new PrimitiveDTO<String>(string);
  }
}
