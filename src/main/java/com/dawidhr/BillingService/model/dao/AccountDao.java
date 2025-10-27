package com.dawidhr.BillingService.model.dao;

import com.dawidhr.BillingService.model.account.Account;
import com.dawidhr.BillingService.model.repository.AccountRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountDao {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    EntityManager entityManager;

    public Account findByEmail(String email) {
        TypedQuery<Account> query = entityManager.createQuery("SELECT u FROM Account WHERE u.email = :email", Account.class);
        query.setParameter("email", email);
        return query.getResultList().isEmpty() ? null : query.getSingleResult();
    }

    public void save(Account account) {
        accountRepository.save(account);
    }
}
