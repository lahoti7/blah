package com.edlink.utils;

public class AppConstants {

  // Shared secret
  public static byte[] sharedSecret = new byte[32];

  // Mongo-related constants
  public static final String MONGO_HOSTNAME = "localhost";
  public static final String MONGO_DATABASE = "edlink";
  public static final String USER_CREDS_COLLECTION = "user_creds";

  // Field names within documents in USER_CREDS_COLLECTION
  public static final String USERNAME = "user_name";
  public static final String PASSWORD = "password";

  public static final String UID_HEADER = "User ID";
  public static final String REGISTER_PATH = "/register";
  public static final String LOGIN_PATH = "/login";
}
