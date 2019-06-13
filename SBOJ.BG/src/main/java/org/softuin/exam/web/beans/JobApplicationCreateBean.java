package org.softuin.exam.web.beans;

import org.modelmapper.ModelMapper;
import org.softuin.exam.domain.models.binding.JobApplicationBindingModel;
import org.softuin.exam.domain.models.service.JobApplicationServiceModel;
import org.softuin.exam.service.JobApplicationService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("jobApplicationCreateBean")
@RequestScoped
public class JobApplicationCreateBean extends BaseBean{

    private JobApplicationBindingModel jobApplicationModel;

    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public JobApplicationCreateBean() {
    }

    @Inject
    public JobApplicationCreateBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init(){
        this.jobApplicationModel = new JobApplicationBindingModel();
    }

    public JobApplicationBindingModel getJobApplicationModel() {
        return jobApplicationModel;
    }

    public void setJobApplicationModel(JobApplicationBindingModel jobApplicationModel) {
        this.jobApplicationModel = jobApplicationModel;
    }

    public void createJobApplication(){
        this.jobApplicationService
                .createJobApplication(
                        this.modelMapper.map(this.jobApplicationModel, JobApplicationServiceModel.class));

        this.redirect("home");
    }
}
