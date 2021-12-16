package com.example.account.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
		Optional<Account> findBySocialSecurityNumber(String socialSecurityNumber);
}
