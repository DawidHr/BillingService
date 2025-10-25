package com.dawidhr.BillingService.model.dao;

import com.dawidhr.BillingService.model.repository.AccountRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountDao {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    EntityManager entityManager;
}
