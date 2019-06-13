package org.softuin.exam.service;

import org.softuin.exam.domain.models.service.JobApplicationServiceModel;

import java.util.List;

public interface JobApplicationService {

    JobApplicationServiceModel createJobApplication(JobApplicationServiceModel jobApplication);

    JobApplicationServiceModel getJobApplicationById(String id);

    List<JobApplicationServiceModel> getAllJobApplications();

    void delete(String id);
}
