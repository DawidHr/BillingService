package com.dawidhr.BillingService.model.service;

import com.dawidhr.BillingService.model.account.Account;
import com.dawidhr.BillingService.model.dao.AccountDao;
import com.dawidhr.BillingService.model.dto.account.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountDao accountDao;


    public void crate(AccountDto accountDto) {
        if(!AccountDto.isValid(accountDto))
            return;

        Account account = accountDao.findByEmail(accountDto.getEmail());
        if (account != null)
            return;


        //crate
        // return ok
    }
}
