package com.edlink.filters;

import java.util.Arrays;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;

public class JWTFeature implements DynamicFeature {
  private static final String REGISTER = "register";
  private static final String LOGIN = "login";

  @Override
  public void configure(final ResourceInfo resourceInfo,
      final FeatureContext context) {
    if (!Arrays.asList(REGISTER, LOGIN).contains(
        resourceInfo.getResourceMethod().getName())) {
      context.register(JWTFilter.class);
    }
  }
}
