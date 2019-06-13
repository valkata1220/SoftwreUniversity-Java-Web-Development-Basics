package org.softuin.exam.service;

import org.softuin.exam.domain.models.service.UserServiceModel;

public interface UserService {

    UserServiceModel getUserByUsername(String username);

    UserServiceModel createUser(UserServiceModel userServiceModel);
}
