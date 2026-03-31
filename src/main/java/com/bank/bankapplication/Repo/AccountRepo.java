package com.bank.bankapplication.Repo;

import com.bank.bankapplication.Entity.AccountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<AccountsEntity,Long> {

    Optional<AccountsEntity> findByCustomerID(Long customerId);


    @Transactional
    @Modifying
    void deleteByCustomerID(Long customerId);
}
