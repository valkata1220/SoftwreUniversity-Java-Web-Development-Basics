package org.softuin.exam.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.softuin.exam.domain.entities.User;
import org.softuin.exam.domain.models.service.UserServiceModel;
import org.softuin.exam.repository.UserRepository;

import javax.inject.Inject;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserServiceModel getUserByUsername(String username) {
        return this.modelMapper.map(this.userRepository.findByUsername(username), UserServiceModel.class);

    }

    @Override
    public UserServiceModel createUser(UserServiceModel userServiceModel) {
        userServiceModel.setPassword(DigestUtils.sha256Hex(userServiceModel.getPassword()));

        return this.modelMapper.map(
                this.userRepository
                        .save(this.modelMapper.map(userServiceModel, User.class))
                , UserServiceModel.class);
    }
}