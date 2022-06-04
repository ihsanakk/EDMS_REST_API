package com.tempter.edms_rest_api.dao;

import com.tempter.edms_rest_api.entity.User;
import com.tempter.edms_rest_api.entity.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    List<User> findByDepartment(String department);

    List<User> findByRole(ERole role);
}
