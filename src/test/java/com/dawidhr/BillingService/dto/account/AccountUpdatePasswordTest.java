package com.dawidhr.BillingService.dto.account;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountUpdatePasswordTest {

    @Test
    void isValid() {
        assertFalse(AccountUpdatePassword.isValid(null));
        assertFalse(AccountUpdatePassword.isValid(new AccountUpdatePassword()));
        assertFalse(AccountUpdatePassword.isValid(new AccountUpdatePassword(null, "test", "test")));
        assertFalse(AccountUpdatePassword.isValid(new AccountUpdatePassword("", "test", "test")));
        assertFalse(AccountUpdatePassword.isValid(new AccountUpdatePassword("   ", "test", "test")));
        assertFalse(AccountUpdatePassword.isValid(new AccountUpdatePassword("test", null, "test")));
        assertFalse(AccountUpdatePassword.isValid(new AccountUpdatePassword("test", "", "test")));
        assertFalse(AccountUpdatePassword.isValid(new AccountUpdatePassword("test", "    ", "test")));
        assertFalse(AccountUpdatePassword.isValid(new AccountUpdatePassword("test", "test", null)));
        assertFalse(AccountUpdatePassword.isValid(new AccountUpdatePassword("test", "test", "")));
        assertFalse(AccountUpdatePassword.isValid(new AccountUpdatePassword("test", "test", "   ")));
        assertTrue(AccountUpdatePassword.isValid(new AccountUpdatePassword("test", "test", "test")));
    }
}