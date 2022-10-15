package com.estore.accountservice.repository;

import com.estore.accountservice.model.roles.ERole;
import com.estore.accountservice.model.roles.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
