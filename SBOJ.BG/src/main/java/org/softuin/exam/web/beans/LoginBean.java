package org.softuin.exam.web.beans;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import org.softuin.exam.domain.models.binding.UserBindingModel;
import org.softuin.exam.domain.models.service.UserServiceModel;
import org.softuin.exam.service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named("loginBean")
@RequestScoped
public class LoginBean extends BaseBean{

    private UserBindingModel userBindingModel;

    private UserService userService;

    public LoginBean() {
    }

    @Inject
    public LoginBean(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init(){
        this.userBindingModel = new UserBindingModel();
    }

    public UserBindingModel getUserBindingModel() {
        return userBindingModel;
    }

    public void setUserBindingModel(UserBindingModel userBindingModel) {
        this.userBindingModel = userBindingModel;
    }

    public void login(){
        UserServiceModel user = this.userService.getUserByUsername(this.userBindingModel.getUsername());

        if(user == null || !user.getPassword().equals(DigestUtils.sha256Hex(this.userBindingModel.getPassword()))){
            return;
        }

        var sessionMap = FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap();

        sessionMap.put("user-id",user.getId());
        sessionMap.put("username",user.getUsername());

        this.redirect("home");
    }
}
