package com.dawidhr.BillingService.service;

import com.dawidhr.BillingService.dto.account.AccountUpdatePassword;
import com.dawidhr.BillingService.exception.DataAlreadyExistException;
import com.dawidhr.BillingService.exception.DataNotFoundException;
import com.dawidhr.BillingService.exception.DataNotValidException;
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
            throw new DataNotValidException("Crate account request error. Data = "+accountDto);

        Account account = accountDao.findByEmail(accountDto.getEmail());
        if (account != null)
            throw new DataAlreadyExistException("Account already exists.");

        account = Account.create(accountDto);
        accountDao.save(account);
    }

    public void updatePassword(AccountUpdatePassword accountUpdatePassword) {
        if (!AccountUpdatePassword.isValid(accountUpdatePassword))
            throw new DataNotValidException("Account update password error. Email = "+accountUpdatePassword.getEmail());

        Account account = accountDao.findByEmail(accountUpdatePassword.getEmail());
        if (account == null)
            throw new DataNotFoundException("Account not found");

        if (!account.getPassword().equals(accountUpdatePassword.getOldPassword()))
            throw new DataNotValidException("Account update password error. Not valid data for Email = "+accountUpdatePassword.getEmail());

        account.setPassword(accountUpdatePassword.getNewPassword());

        accountDao.update(account);
    }

    public String login(AccountDto accountDto) {
        if (!AccountDto.isValid(accountDto))
            throw new DataNotValidException("Account login error. Email = "+accountDto.getEmail());

        Account accountFromDb = accountDao.findByEmail(accountDto.getEmail());
        if (accountFromDb == null)
            throw new DataNotFoundException("Account not found");

        if (accountDto.getPassword().equals(accountFromDb.getPassword()))
            return authService.create(accountDto.getEmail());

        throw new DataNotValidException("Account login not valid for Email = "+accountDto.getEmail());
    }
}
