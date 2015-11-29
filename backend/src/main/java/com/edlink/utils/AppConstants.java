package com.edlink.utils;

public class AppConstants {

  // Mongo-related constants
  public static final String MONGO_HOSTNAME = "localhost";
  public static final String MONGO_DATABASE = "edlink";
  public static final String USER_CREDS_COLLECTION = "user_creds";

  // Field names within documents in USER_CREDS_COLLECTION
  public static final String USERNAME = "userName";
  public static final String PASSWORD = "password";
}
