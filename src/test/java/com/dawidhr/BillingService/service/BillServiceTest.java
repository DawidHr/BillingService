package com.dawidhr.BillingService.service;

import com.dawidhr.BillingService.dao.BillDao;
import com.dawidhr.BillingService.dto.bill.BillDto;
import com.dawidhr.BillingService.dto.bill.BillItemDto;
import com.dawidhr.BillingService.exception.DataNotValidException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BillServiceTest {

    @InjectMocks
    BillService billService;

    @Mock
    BillDao billDao;

    @Test
    public void createBadRequestTest() {
        assertThrows(DataNotValidException.class, () -> billService.create(new BillDto()));
        verify(billDao, never()).save(any());
    }

    @Test
    public void createTest() {
        BillDto billDto = new BillDto();
        billDto.setName("Test");
        billDto.setBuyDate(LocalDateTime.now());
        billDto.setTotalPrice(33.25);
        billDto.setItems(List.of(new BillItemDto("test item", 33.12)));
        billService.create(billDto);
        verify(billDao, times(1)).save(any());
    }
}