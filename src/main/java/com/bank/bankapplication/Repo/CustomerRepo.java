package com.bank.bankapplication.Repo;

import com.bank.bankapplication.Entity.CustomerEntity;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity,Long> {

    Optional<CustomerEntity> findByMobileNo(String mobileNo);
}
