package com.edlink.services.auth;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.edlink.dto.auth.LoginDTO;
import com.edlink.dto.response.ErrorDetails;
import com.edlink.dto.response.ResponseDTO;
import com.edlink.utils.AppConstants;
import com.edlink.utils.AppUtils;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginService {
  private final AppUtils appUtils;

  public LoginService() {
    appUtils = new AppUtils();
  }

  @POST
  public ResponseDTO login(final LoginDTO loginDTO) throws Exception {
    final MongoClient mongoClient = new MongoClient(
        AppConstants.MONGO_HOSTNAME);
    final MongoDatabase db = mongoClient.getDatabase(
        AppConstants.MONGO_DATABASE);
    final MongoCollection<Document> userCredsCollection = db.getCollection(
        AppConstants.USER_CREDS_COLLECTION);
    final ResponseDTO<String> responseDTO = new ResponseDTO<String>();

    if (userCredsCollection.find(
        and(
            eq(AppConstants.USERNAME, loginDTO.getUserName()),
            eq(AppConstants.PASSWORD, loginDTO.getPassword())
        ))
        .first() == null) {
      // Login failed
      final ErrorDetails errorDetails = new ErrorDetails();
      errorDetails.setErrorMessage(
          "The username and password provided do not match");
      responseDTO.setErrorDetails(errorDetails);
    } else {
      // Login successful, generate JWT.
      responseDTO.setResponse(appUtils.generateJWT(loginDTO.getUserName()));
    }

    mongoClient.close();

    return responseDTO;
  }
}