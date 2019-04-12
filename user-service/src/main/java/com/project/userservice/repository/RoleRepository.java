package com.project.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.userservice.model.User;

@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<User, Integer> {
}