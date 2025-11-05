package com.dawidhr.BillingService.service;

import com.dawidhr.BillingService.dao.BillDao;
import com.dawidhr.BillingService.dto.bill.BillDto;
import com.dawidhr.BillingService.exception.DataNotValidException;
import com.dawidhr.BillingService.model.bill.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService {

    @Autowired
    BillDao billDao;

    public void create(BillDto billDto) {
        if (!BillDto.isValid(billDto))
            throw new DataNotValidException("Crate bill request error. Data = "+billDto);

        Bill bill = Bill.create(billDto);
        billDao.save(bill);
    }
}
