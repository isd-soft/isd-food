package com.example.isdbackend.repository;

import com.example.isdbackend.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNameContaining(String name);
}
