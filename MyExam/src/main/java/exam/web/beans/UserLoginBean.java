package exam.web.beans;


import exam.domain.models.binding.UserLoginBindingModel;
import exam.domain.models.service.UserServiceModel;
import exam.service.UserService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class UserLoginBean {

    private UserLoginBindingModel model;

    private UserService userService;
    private ModelMapper modelMapper;

    public UserLoginBean() {
    }

    @Inject
    public UserLoginBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.initMethod();
    }

    private void initMethod() {
        this.model = new UserLoginBindingModel();
    }

    public UserLoginBindingModel getModel() {
        return model;
    }

    public void setModel(UserLoginBindingModel model) {
        this.model = model;
    }

    public void login() throws IOException {
        UserServiceModel userServiceModel = this.userService
                .loginUser(this.modelMapper.map(this.model, UserServiceModel.class));

        if (userServiceModel == null) {
            throw new IllegalArgumentException("Something went wrong!");
        }

        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
                .getSession(true);

        session.setAttribute("username", userServiceModel.getUsername());
        session.setAttribute("user_id", userServiceModel.getId());

        FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
    }
}
