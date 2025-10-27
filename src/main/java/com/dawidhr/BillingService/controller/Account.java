package com.dawidhr.BillingService.controller;

import com.dawidhr.BillingService.dto.account.AccountDto;
import com.dawidhr.BillingService.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class Account {

    @Autowired
    AccountService accountService;

    @PostMapping
    public void create(@RequestBody AccountDto accountDto) {
        accountService.crate(accountDto);
    }
}
