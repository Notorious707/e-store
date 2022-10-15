package com.estore.accountservice.repository;

import com.estore.accountservice.model.Account;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    boolean existsByEmail(String email);
    Optional<Account> findByEmail(String email);
}
