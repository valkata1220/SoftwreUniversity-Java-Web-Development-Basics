package metube.domain.models.view;

import java.util.List;

public class UserProfileViewModel {

    private String username;
    private String email;
    private List<UsersTubeViewModel> tubes;

    public UserProfileViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UsersTubeViewModel> getTubes() {
        return tubes;
    }

    public void setTubes(List<UsersTubeViewModel> tubes) {
        this.tubes = tubes;
    }
}
