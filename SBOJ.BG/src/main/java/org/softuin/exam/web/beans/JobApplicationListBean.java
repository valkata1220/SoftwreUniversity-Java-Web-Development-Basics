package org.softuin.exam.web.beans;

import org.modelmapper.ModelMapper;
import org.softuin.exam.domain.models.binding.JobApplicationBindingModel;
import org.softuin.exam.domain.models.service.JobApplicationServiceModel;
import org.softuin.exam.service.JobApplicationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("jobApplicationListBean")
@RequestScoped
public class JobApplicationListBean extends BaseBean{

    private List<JobApplicationServiceModel> jobApplications;

    private JobApplicationService jobApplicationService;

    public JobApplicationListBean() {
    }

    @Inject
    public JobApplicationListBean(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @PostConstruct
    private void init(){
        this.setJobApplications(this.jobApplicationService.getAllJobApplications());
        this.getJobApplications().forEach(x -> x.setSector(x.getSector().toLowerCase()));
    }

    public List<JobApplicationServiceModel> getJobApplications() {
        return jobApplications;
    }

    public void setJobApplications(List<JobApplicationServiceModel> jobApplications) {
        this.jobApplications = jobApplications;
    }
}
