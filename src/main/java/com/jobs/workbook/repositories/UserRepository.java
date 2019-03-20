package com.jobs.workbook.repositories;

import com.jobs.workbook.entites.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findOneByEmail(String email);
    User findOneById(long email);
}
