package org.softuin.exam.service;

import org.modelmapper.ModelMapper;
import org.softuin.exam.domain.entities.JobApplication;
import org.softuin.exam.domain.models.service.JobApplicationServiceModel;
import org.softuin.exam.repository.JobApplicationRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class JobApplicationServiceImpl implements JobApplicationService{

    private final JobApplicationRepository jobApplicationRepository;
    private final ModelMapper modelMapper;

    @Inject
    public JobApplicationServiceImpl(JobApplicationRepository jobApplicationRepository, ModelMapper modelMapper) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public JobApplicationServiceModel createJobApplication(JobApplicationServiceModel jobApplication) {
        return this.modelMapper.map(
                this.jobApplicationRepository
                        .save(this.modelMapper.map(jobApplication, JobApplication.class))
                , JobApplicationServiceModel.class);
    }

    @Override
    public JobApplicationServiceModel getJobApplicationById(String id) {
        return this.modelMapper.map(this.jobApplicationRepository.findById(id), JobApplicationServiceModel.class);
    }

    @Override
    public List<JobApplicationServiceModel> getAllJobApplications() {
        return this.jobApplicationRepository.findAll()
                .stream()
                .map((ja) -> this.modelMapper.map(ja,JobApplicationServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        this.jobApplicationRepository.delete(id);
    }
}
