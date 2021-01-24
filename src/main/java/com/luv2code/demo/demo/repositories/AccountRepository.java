package com.luv2code.demo.demo.repositories;

import com.luv2code.demo.demo.modals.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Boolean existsAccountByUsername(String username);
    Boolean existsAccountByEmail(String email);
    Optional<Account> findAccountByUsername(String username);
}
