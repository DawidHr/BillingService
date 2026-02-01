package com.dawidhr.BillingService.controller;

import com.dawidhr.BillingService.dto.bill.BillDto;
import com.dawidhr.BillingService.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/bill", consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class BillController {

    @Autowired
    BillService billService;

    @PostMapping
    public void create(@RequestBody BillDto billDto) {
        billService.create(billDto);
    }
}
