package com.dawidhr.BillingService.service;


import com.dawidhr.BillingService.dao.AccountDao;
import com.dawidhr.BillingService.dto.account.AccountDto;
import com.dawidhr.BillingService.dto.account.AccountUpdatePassword;
import com.dawidhr.BillingService.exception.DataAlreadyExistException;
import com.dawidhr.BillingService.exception.DataNotFoundException;
import com.dawidhr.BillingService.exception.DataNotValidException;
import com.dawidhr.BillingService.model.account.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    AccountService accountService;

    @Mock
    AccountDao accountDao;

    @Mock
    AuthService authService;

    @Test
    public void createNotValidTest() {
       assertThrows(DataNotValidException.class, () -> accountService.crate(new AccountDto()));
        Mockito.verify(accountDao, Mockito.never()).findByEmail(Mockito.anyString());
        Mockito.verify(accountDao, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void createAccountAlreadyExistsTest() {
        Mockito.when(accountDao.findByEmail(Mockito.anyString())).thenReturn(new Account());
        assertThrows(DataAlreadyExistException.class, () -> accountService.crate(new AccountDto("test", "Test123")));
        Mockito.verify(accountDao, Mockito.times(1)).findByEmail(Mockito.anyString());
        Mockito.verify(accountDao, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void createTest() {
        Mockito.when(accountDao.findByEmail(Mockito.anyString())).thenReturn(null);
        accountService.crate(new AccountDto("test", "Test123"));
        Mockito.verify(accountDao, Mockito.times(1)).findByEmail(Mockito.anyString());
        Mockito.verify(accountDao, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void updatePasswordRequestNotValidTest() {
        assertThrows(DataNotValidException.class, () -> accountService.updatePassword(new AccountUpdatePassword()));
    }

    @Test
    public void updatePasswordAccountNotFoundTest() {
        Mockito.when(accountDao.findByEmail(Mockito.anyString())).thenReturn(null);
        assertThrows(DataNotFoundException.class, () -> accountService.updatePassword(new AccountUpdatePassword("test@test.pl", "test", "test2")));
        Mockito.verify(accountDao, Mockito.times(1)).findByEmail(Mockito.anyString());
        Mockito.verify(accountDao, Mockito.never()).update(Mockito.any());
    }

    @Test
    public void updatePasswordOldPassNotValidTest() {
        Account account = new Account();
        account.setPassword("test4");
        Mockito.when(accountDao.findByEmail(Mockito.anyString())).thenReturn(account);
        assertThrows(DataNotValidException.class, () -> accountService.updatePassword(new AccountUpdatePassword("test@test.pl", "test", "test2")));
        Mockito.verify(accountDao, Mockito.times(1)).findByEmail(Mockito.anyString());
        Mockito.verify(accountDao, Mockito.never()).update(Mockito.any());
    }

    @Test
    public void updatePasswordTest() {
        Account account = new Account();
        account.setPassword("test");
        Mockito.when(accountDao.findByEmail(Mockito.anyString())).thenReturn(account);
        accountService.updatePassword(new AccountUpdatePassword("test@test.pl", "test", "test2"));
        Mockito.verify(accountDao, Mockito.times(1)).findByEmail(Mockito.anyString());
        Mockito.verify(accountDao, Mockito.times(1)).update(Mockito.any());
    }

    @Test
    public void loginRequestNotValidTest() {
        assertThrows(DataNotValidException.class, () -> accountService.login(new AccountDto()));
        Mockito.verify(accountDao, Mockito.never()).findByEmail(Mockito.anyString());
        Mockito.verify(authService, Mockito.never()).create(Mockito.anyString());
    }

    @Test
    public void loginAccountNotFoundTest() {
        Mockito.when(accountDao.findByEmail(Mockito.anyString())).thenReturn(null);
        assertThrows(DataNotFoundException.class, () -> accountService.login(new AccountDto("test@test.pl", "test")));
        Mockito.verify(accountDao, Mockito.times(1)).findByEmail(Mockito.anyString());
        Mockito.verify(authService, Mockito.never()).create(Mockito.anyString());
    }

    @Test
    public void loginBadPasswordTest() {
        Account account = new Account();
        account.setPassword("test2");
        Mockito.when(accountDao.findByEmail(Mockito.anyString())).thenReturn(account);
        assertThrows(DataNotValidException.class, () -> accountService.login(new AccountDto("test@test.pl", "test")));
        Mockito.verify(accountDao, Mockito.times(1)).findByEmail(Mockito.anyString());
        Mockito.verify(authService, Mockito.never()).create(Mockito.anyString());
    }

    @Test
    public void loginTest() {
        Account account = new Account();
        account.setPassword("test");
        Mockito.when(accountDao.findByEmail(Mockito.anyString())).thenReturn(account);
        accountService.login(new AccountDto("test@test.pl", "test"));
        Mockito.verify(accountDao, Mockito.times(1)).findByEmail(Mockito.anyString());
        Mockito.verify(authService, Mockito.times(1)).create(Mockito.anyString());
    }
}