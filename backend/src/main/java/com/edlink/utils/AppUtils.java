package com.edlink.utils;

import java.lang.reflect.Field;
import java.text.ParseException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;

@Slf4j
public class AppUtils {

  public Document convertToDocument(Object classObj) {
    final Document document = new Document();

    try {
      for (final Field field : classObj.getClass().getDeclaredFields()) {
        field.setAccessible(true);
        String fieldName;
        if (field.isAnnotationPresent(JsonProperty.class)) {
          fieldName = field.getAnnotation(JsonProperty.class).value();
        } else {
          fieldName = field.getName();
        }
        document.append(fieldName, field.get(classObj));
      }
    } catch (final IllegalAccessException ex) {
      log.debug("Exception while retrieving field value from class");
    }
    return document;
  }

  public String generateJWT(final String subject) throws JOSEException {
    final JWSSigner jwsSigner = new MACSigner(AppConstants.sharedSecret);
    final JWTClaimsSet.Builder jwtClaimsSet = new JWTClaimsSet.Builder();
    jwtClaimsSet.subject(subject);
    final SignedJWT signedJWT = new SignedJWT(
        new JWSHeader(JWSAlgorithm.HS256), jwtClaimsSet.build());
    signedJWT.sign(jwsSigner);
    return signedJWT.serialize();
  }

  public JWTClaimsSet parseJWT(final String jwtToken) throws ParseException,
      JOSEException {
    final SignedJWT signedJWT = SignedJWT.parse(jwtToken);
    final JWSVerifier verifier = new MACVerifier(AppConstants.sharedSecret);
    signedJWT.verify(verifier);
    return signedJWT.getJWTClaimsSet();
  }
}
