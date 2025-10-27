package com.dawidhr.BillingService.dao;

import com.dawidhr.BillingService.repository.BillRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BillDao {
    @Autowired
    BillRepository billRepository;
    @Autowired
    EntityManager entityManager;
}
