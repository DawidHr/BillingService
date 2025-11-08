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
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        verify(accountDao, never()).findByEmail(anyString());
        verify(accountDao, never()).save(any());
    }

    @Test
    public void createAccountAlreadyExistsTest() {
        when(accountDao.findByEmail(anyString())).thenReturn(new Account());
        assertThrows(DataAlreadyExistException.class, () -> accountService.crate(new AccountDto("test", "Test123")));
        verify(accountDao, times(1)).findByEmail(anyString());
        verify(accountDao, never()).save(any());
    }

    @Test
    public void createTest() {
        when(accountDao.findByEmail(anyString())).thenReturn(null);
        accountService.crate(new AccountDto("test", "Test123"));
        verify(accountDao, times(1)).findByEmail(anyString());
        verify(accountDao, times(1)).save(any());
    }

    @Test
    public void updatePasswordRequestNotValidTest() {
        Account account = new Account();
        account.setEmail("test@test.pl");
        when(authService.getSubject(anyString())).thenReturn("test@test.pl");
        when(accountDao.findByEmail(anyString())).thenReturn(account);
        assertThrows(DataNotValidException.class, () -> accountService.updatePassword("auth", new AccountUpdatePassword()));
    }

    @Test
    public void updatePasswordAccountNotFoundTest() {
        Account account = new Account();
        account.setEmail("test@test.pl");
        when(authService.getSubject(anyString())).thenReturn("test@test.pl");
        when(accountDao.findByEmail(anyString())).thenReturn(account).thenReturn(null);
        assertThrows(DataNotFoundException.class, () -> accountService.updatePassword("auth", new AccountUpdatePassword("test@test.pl", "test", "test2")));
        verify(accountDao, times(2)).findByEmail(anyString());
        verify(accountDao, never()).update(any());
    }

    @Test
    public void updatePasswordOldPassNotValidTest() {
        when(authService.getSubject(anyString())).thenReturn("test@test.pl");
        Account account = new Account();
        account.setEmail("test@test.pl");
        account.setPassword("test4");
        when(accountDao.findByEmail(anyString())).thenReturn(account);
        assertThrows(DataNotValidException.class, () -> accountService.updatePassword("auth", new AccountUpdatePassword("test@test.pl", "test", "test2")));
        verify(accountDao, times(2)).findByEmail(anyString());
        verify(accountDao, never()).update(any());
    }

    @Test
    public void updatePasswordTest() {
        when(authService.getSubject(anyString())).thenReturn("test@test.pl");
        Account account = new Account();
        account.setEmail("test@test.pl");
        account.setPassword("test");
        when(accountDao.findByEmail(anyString())).thenReturn(account);
        accountService.updatePassword("auth", new AccountUpdatePassword("test@test.pl", "test", "test2"));
        verify(accountDao, times(2)).findByEmail(anyString());
        verify(accountDao, times(1)).update(any());
    }

    @Test
    public void loginRequestNotValidTest() {
        assertThrows(DataNotValidException.class, () -> accountService.login(new AccountDto()));
        verify(accountDao, never()).findByEmail(anyString());
        verify(authService, never()).create(anyString());
    }

    @Test
    public void loginAccountNotFoundTest() {
        when(accountDao.findByEmail(anyString())).thenReturn(null);
        assertThrows(DataNotFoundException.class, () -> accountService.login(new AccountDto("test@test.pl", "test")));
        verify(accountDao, times(1)).findByEmail(anyString());
        verify(authService, never()).create(anyString());
    }

    @Test
    public void loginBadPasswordTest() {
        Account account = new Account();
        account.setPassword("test2");
        when(accountDao.findByEmail(anyString())).thenReturn(account);
        assertThrows(DataNotValidException.class, () -> accountService.login(new AccountDto("test@test.pl", "test")));
        verify(accountDao, times(1)).findByEmail(anyString());
        verify(authService, never()).create(anyString());
    }

    @Test
    public void loginTest() {
        Account account = new Account();
        account.setPassword("test");
        when(accountDao.findByEmail(anyString())).thenReturn(account);
        accountService.login(new AccountDto("test@test.pl", "test"));
        verify(accountDao, times(1)).findByEmail(anyString());
        verify(authService, times(1)).create(anyString());
    }
}