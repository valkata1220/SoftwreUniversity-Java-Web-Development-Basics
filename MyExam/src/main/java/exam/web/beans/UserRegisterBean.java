package exam.web.beans;

import exam.domain.models.binding.UserRegisterBindingModel;
import exam.domain.models.service.UserServiceModel;
import exam.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named("userRegisterBean")
@RequestScoped
public class UserRegisterBean {

    private UserRegisterBindingModel model;

    private UserService userService;
    private ModelMapper modelMapper;

    public UserRegisterBean() {
    }

    @Inject
    public UserRegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.initMethod();
    }

    private void initMethod() {
        this.model = new UserRegisterBindingModel();
    }

    public UserRegisterBindingModel getModel() {
        return model;
    }

    public void setModel(UserRegisterBindingModel model) {
        this.model = model;
    }

    public void register() throws IOException {
        if(!this.model.getPassword().equals(this.model.getConfirmPassword())){
            throw new IllegalArgumentException("Password do not match!");
        }

        if(!this.userService.registerUser(this.modelMapper.map(this.model, UserServiceModel.class))){
            throw new IllegalArgumentException("Something went wrong!");
        }

        FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .redirect("/login");
    }

}
