package com.dawidhr.BillingService.dto.account;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountDtoTest {

    @Test
    void isValid() {
        assertFalse(AccountDto.isValid(null));
        assertFalse(AccountDto.isValid(new AccountDto()));
        assertFalse(AccountDto.isValid(new AccountDto(null, "test")));
        assertFalse(AccountDto.isValid(new AccountDto("", "test")));
        assertFalse(AccountDto.isValid(new AccountDto("      ", "test")));
        assertFalse(AccountDto.isValid(new AccountDto("test",null)));
        assertFalse(AccountDto.isValid(new AccountDto("test","")));
        assertFalse(AccountDto.isValid(new AccountDto("test","    ")));
        assertTrue(AccountDto.isValid(new AccountDto("test","test")));
    }
}