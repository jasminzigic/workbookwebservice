package com.jobs.workbook.repositories;

import com.jobs.workbook.entites.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    @EntityGraph(attributePaths = "jobList")
    User findOneByEmail(String email);

    @EntityGraph(attributePaths = "jobList")
    User findOneById(long email);
}
