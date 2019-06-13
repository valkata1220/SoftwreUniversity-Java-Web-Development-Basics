package org.softuin.exam.web.beans;

import org.modelmapper.ModelMapper;
import org.softuin.exam.domain.models.binding.UserRegisterBindingModel;
import org.softuin.exam.domain.models.service.UserServiceModel;
import org.softuin.exam.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("registerBean")
@RequestScoped
public class RegisterBean extends BaseBean {

    private UserRegisterBindingModel userRegisterBindingModel;

    private UserService userService;
    private ModelMapper modelMapper;

    public RegisterBean() {
    }

    @Inject
    public RegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        this.userRegisterBindingModel = new UserRegisterBindingModel();
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }

    public void register() {
        if(!this.userRegisterBindingModel.getPassword().equals(this.userRegisterBindingModel.getConfirmPassword())){
            return;
        }
        this.userService
                .createUser(this.modelMapper.map(this.userRegisterBindingModel, UserServiceModel.class));

        this.redirect("login");
    }
}
