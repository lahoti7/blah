package com.edlink.filters;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import com.edlink.exceptions.NotAuthorizedException;
import com.edlink.utils.AppUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@Provider
public class JWTFilter implements ContainerRequestFilter {
  private static final String UTF_8 = "UTF-8";
  private static final String REQUEST = "request";
  private static final String USER_ID = "user_id";
  private static final String AUTHORIZATION_HEADER = "Authorization";

  private final AppUtils appUtils;

  public JWTFilter() {
    appUtils = new AppUtils();
  }

  @Override
  public void filter(final ContainerRequestContext request)
      throws IOException {
    final InputStream inputStream = request.getEntityStream();
    try {
      final String jsonInput = IOUtils.toString(inputStream, UTF_8);
      final String updatedInput = getUpdatedInput(request, jsonInput);
      request.setEntityStream(new ByteArrayInputStream(
          updatedInput.getBytes()));
    } catch (final Exception ex) {
      throw new NotAuthorizedException(ex.getMessage());
    }
  }

  private String getUpdatedInput(final ContainerRequestContext request,
      final String inputString) throws IOException {
    final ObjectMapper mapper = new ObjectMapper();
    final Map<String, Object> updatedMapFormat = new HashMap<>();
    Map<String, Object> mapFormat = new HashMap<>();
    if (!StringUtils.isEmpty(inputString)) {
      mapFormat = mapper.readValue(inputString,
          TypeFactory.defaultInstance().constructMapType(
              Map.class, String.class, Object.class));
    }

    final String authHeader = request.getHeaderString(AUTHORIZATION_HEADER);
    if (!StringUtils.isEmpty(authHeader)) {
      final JWTClaimsSet jwtClaimsSet;
      try {
        jwtClaimsSet = appUtils.parseJWT(
            authHeader.split(" ")[1]);
      } catch (final Exception ex) {
        throw new NotAuthorizedException("Incorrect credentials passed");
      }
      updatedMapFormat.put(REQUEST, mapFormat);
      updatedMapFormat.put(USER_ID, jwtClaimsSet.getSubject());
      return mapper.writeValueAsString(updatedMapFormat);
    }
    return null;
  }
}
