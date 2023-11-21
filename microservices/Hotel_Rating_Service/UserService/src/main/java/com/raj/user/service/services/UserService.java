package com.raj.user.service.services;

import com.raj.user.service.entities.User;

import java.util.List;

public interface UserService {
    // user opeations

    // create
    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    // get single user of given userId
    User getUser(String userId);

    //TODO: delete



    // TODO : Update


}
