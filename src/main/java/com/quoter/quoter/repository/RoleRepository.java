package com.quoter.quoter.repository;

import com.quoter.quoter.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}