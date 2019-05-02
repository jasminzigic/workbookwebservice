package com.jobs.workbook.repositories;

import com.jobs.workbook.entites.user.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface UserRepository extends JpaRepository<User, Integer> {

    @EntityGraph(attributePaths = {"jobList", "settings"})
    User findOneByEmail(String email);

    @EntityGraph(attributePaths = {"jobList", "settings"})
    User findOneById(long id);

    @EntityGraph(attributePaths = {"jobList", "settings"})
    ArrayList<User> findAll();
}
