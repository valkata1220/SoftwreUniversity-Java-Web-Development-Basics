package org.softuin.exam.config;

import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ApplicationBeanConfiguration {

    @Produces
    private EntityManager entityManager(){
        return Persistence
                .createEntityManagerFactory("SbojPU")
                .createEntityManager();
    }

    @Produces
    private ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
