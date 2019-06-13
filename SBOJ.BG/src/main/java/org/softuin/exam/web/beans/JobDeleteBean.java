package org.softuin.exam.web.beans;

import org.softuin.exam.domain.models.service.JobApplicationServiceModel;
import org.softuin.exam.service.JobApplicationService;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Named("jobDeleteBean")
@RequestScoped
public class JobDeleteBean extends BaseBean {

    private JobApplicationService jobApplicationService;

    public JobDeleteBean() {
    }

    @Inject
    public JobDeleteBean(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    public JobApplicationServiceModel getJobApplication(String id) {
        JobApplicationServiceModel result = this.jobApplicationService.getJobApplicationById(id);
        return result;
    }

    public void delete() {
        String id = ((HttpServletRequest) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequest()).getParameter("id");

        this.jobApplicationService.delete(id);
        this.redirect("home");
    }
}
