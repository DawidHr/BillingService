package com.dawidhr.BillingService.controller;

import com.dawidhr.BillingService.dto.account.AccountDto;
import com.dawidhr.BillingService.dto.account.AccountUpdatePassword;
import com.dawidhr.BillingService.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class Account {

    @Autowired
    AccountService accountService;

    @PostMapping
    @RequestMapping("/create")
    public void create(@RequestBody AccountDto accountDto) {
        accountService.crate(accountDto);
    }

    @PatchMapping
    public void updatePassword(@RequestBody AccountUpdatePassword accountUpdatePassword) {
        accountService.updatePassword(accountUpdatePassword);
    }

    @PostMapping
    @RequestMapping("/login")
    public String login(@RequestBody AccountDto accountDto) {
        return accountService.login(accountDto);
    }
}
