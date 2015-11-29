package com.edlink.utils;

import java.lang.reflect.Field;

import com.fasterxml.jackson.annotation.JsonProperty;
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
}
