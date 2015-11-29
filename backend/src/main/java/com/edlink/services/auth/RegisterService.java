package com.edlink.services.auth;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.edlink.dto.auth.RegisterDTO;
import com.edlink.dto.response.ErrorDetails;
import com.edlink.dto.response.ResponseDTO;
import com.edlink.utils.AppConstants;
import com.edlink.utils.AppUtils;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

@Path("/register")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegisterService {
  private final AppUtils appUtils;

  public RegisterService() {
    appUtils = new AppUtils();
  }

  @POST
  public ResponseDTO register(final RegisterDTO registerDTO) {
    final MongoClient mongoClient = new MongoClient(
        AppConstants.MONGO_HOSTNAME);
    final MongoDatabase db = mongoClient.getDatabase(
        AppConstants.MONGO_DATABASE);
    final MongoCollection<Document> userCredsCollection = db.getCollection(
        AppConstants.USER_CREDS_COLLECTION);
    final ResponseDTO responseDTO = new ResponseDTO();

    if (userCredsCollection.find(eq(AppConstants.USERNAME,
        registerDTO.getUserName())).first() == null) {
      userCredsCollection.insertOne(appUtils.convertToDocument(registerDTO));
    } else {
      final ErrorDetails errorDetails = new ErrorDetails();
      errorDetails.setErrorMessage("The username has been already taken.");
      responseDTO.setErrorDetails(errorDetails);
    }

    mongoClient.close();

    return responseDTO;
  }
}
