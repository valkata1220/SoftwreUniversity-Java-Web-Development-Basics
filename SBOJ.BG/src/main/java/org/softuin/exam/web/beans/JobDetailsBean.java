package org.softuin.exam.web.beans;

import org.softuin.exam.domain.models.service.JobApplicationServiceModel;
import org.softuin.exam.service.JobApplicationService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("jobDetailsBean")
@RequestScoped
public class JobDetailsBean extends BaseBean {

    private JobApplicationService jobApplicationService;

    public JobDetailsBean() {
    }

    @Inject
    public JobDetailsBean(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    public JobApplicationServiceModel getJobApplication(String id){
        return this.jobApplicationService.getJobApplicationById(id);
    }
}
