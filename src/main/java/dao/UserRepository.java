/*
 * Copyright (c) 2015 Edlink Inc. All rights reserved.
 *
 * Author: lahotipritesh@gmail.com
 */

package dao;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import utils.AppConstants;

public class UserRepository {

    public UserDTO find(final String username) {
        final DBObject user = getUsers().findOne(
            new BasicDBObject("username", username));

        if (user == null)
            return null;

        return new UserDTO(username, (String) user.get("password"));
    }

    public void create(final UserDTO user) {
        final BasicDBObject dbObject = new BasicDBObject(
            "username", user.getUsername())
            .append("password", user.getPassword());
        getUsers().insert(dbObject);
    }

    public boolean authenticate(final UserDTO user) {
        final UserDTO userDTO = find(user.getUsername());
        if (userDTO == null) {
            return false;
        }
        return userDTO.getPassword().equals(user.getPassword());
    }

    private DBCollection getUsers() {
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient(AppConstants.DB_HOST);
        } catch (final UnknownHostException e) {}
        return mongoClient.getDB(AppConstants.DB_NAME)
            .getCollection(AppConstants.USERS_COLLECTION);
    }
}
