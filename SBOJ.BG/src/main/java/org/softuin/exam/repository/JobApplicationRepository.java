package org.softuin.exam.repository;

import org.softuin.exam.domain.entities.JobApplication;

public interface JobApplicationRepository extends GenericRepository<JobApplication,String> {
    void delete(String id);
}
