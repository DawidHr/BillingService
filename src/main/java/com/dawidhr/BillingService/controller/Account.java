package com.dawidhr.BillingService.controller;

import com.dawidhr.BillingService.dto.account.AccountDto;
import com.dawidhr.BillingService.dto.account.AccountRemindPasswordRequest;
import com.dawidhr.BillingService.dto.account.AccountUpdatePassword;
import com.dawidhr.BillingService.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/account", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class Account {

    @Autowired
    AccountService accountService;

    @PostMapping
    @RequestMapping("/create")
    public void create(@RequestBody AccountDto accountDto) {
        accountService.crate(accountDto);
    }

    @PatchMapping
    @RequestMapping("/password-update")
    public void updatePassword(@RequestHeader("Authorization") String authHeader, @RequestBody AccountUpdatePassword accountUpdatePassword) {
        accountService.updatePassword(authHeader, accountUpdatePassword);
    }

    @PostMapping
    @RequestMapping("/login")
    public String login(@RequestBody AccountDto accountDto) {
        return accountService.login(accountDto);
    }


    @PostMapping
    @RequestMapping("/password-remind")
    public String remindPassword(@RequestBody AccountRemindPasswordRequest remindPasswordRequest) {
        accountService.remindPassword(remindPasswordRequest);
        return "Mail was send";
    }
}
