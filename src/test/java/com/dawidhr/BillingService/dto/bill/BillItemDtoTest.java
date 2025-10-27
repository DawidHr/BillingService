package com.dawidhr.BillingService.dto.bill;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillItemDtoTest {

    @Test
    void isValid() {
        assertFalse(BillItemDto.isValid(null));
        assertFalse(BillItemDto.isValid(new BillItemDto()));
        assertFalse(BillItemDto.isValid(new BillItemDto(null, 5.23d)));
        assertFalse(BillItemDto.isValid(new BillItemDto("", 5.23d)));
        assertFalse(BillItemDto.isValid(new BillItemDto("   ", 5.23d)));
        assertTrue(BillItemDto.isValid(new BillItemDto("test", 5.23d)));
    }
}