package org.softuin.exam.repository;

import org.softuin.exam.domain.entities.User;

public interface UserRepository extends GenericRepository<User,String> {

    User findByUsername(String username);


}
