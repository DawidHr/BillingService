package com.dawidhr.BillingService.service;

import com.dawidhr.BillingService.dto.account.AccountUpdatePassword;
import com.dawidhr.BillingService.model.account.Account;
import com.dawidhr.BillingService.dao.AccountDao;
import com.dawidhr.BillingService.dto.account.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountDao accountDao;

    @Autowired
    AuthService authService;

    public void crate(AccountDto accountDto) {
        if (!AccountDto.isValid(accountDto))
            return;

        Account account = accountDao.findByEmail(accountDto.getEmail());
        if (account != null)
            return;

        account = Account.create(accountDto);
        accountDao.save(account);
    }

    public void updatePassword(AccountUpdatePassword accountUpdatePassword) {
        if (!AccountUpdatePassword.isValid(accountUpdatePassword))
            return;

        Account account = accountDao.findByEmail(accountUpdatePassword.getEmail());
        if (account == null)
            return;

        if (!account.getPassword().equals(accountUpdatePassword.getOldPassword()))
            return;

        account.setPassword(accountUpdatePassword.getNewPassword());

        accountDao.update(account);
    }

    public String login(AccountDto accountDto) {
        if(!AccountDto.isValid(accountDto))
            return null;

        Account accountFromDb = accountDao.findByEmail(accountDto.getEmail());
        if (accountFromDb == null)
            return null;

        if (accountDto.getEmail().equals(accountFromDb.getEmail()))
            return authService.create(accountDto.getEmail());

        return null;
    }
}
