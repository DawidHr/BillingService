package com.dawidhr.BillingService.dto.bill;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BillDtoTest {

    @Test
    void isValid() {
        assertFalse(BillDto.isValid(null));
        assertFalse(BillDto.isValid(new BillDto()));
        assertFalse(BillDto.isValid(new BillDto(null, "test", LocalDateTime.now(), 5.23d, prapareItems())));
        assertFalse(BillDto.isValid(new BillDto("", "test", LocalDateTime.now(), 5.23d, prapareItems())));
        assertFalse(BillDto.isValid(new BillDto("   ", "test", LocalDateTime.now(), 5.23d, prapareItems())));
        assertTrue(BillDto.isValid(new BillDto("test", "test", LocalDateTime.now(), 5.23d, prapareItems())));
        assertTrue(BillDto.isValid(new BillDto("test", null, LocalDateTime.now(), 5.23d, prapareItems())));
        assertTrue(BillDto.isValid(new BillDto("test", "", LocalDateTime.now(), 5.23d, prapareItems())));
        assertTrue(BillDto.isValid(new BillDto("test", "   ", LocalDateTime.now(), 5.23d, prapareItems())));
        assertFalse(BillDto.isValid(new BillDto("test", "test", null, 5.23d, prapareItems())));
        assertFalse(BillDto.isValid(new BillDto("test", "test", LocalDateTime.now(), 5.23d, null)));
    }

    private List<BillItemDto> prapareItems() {
        List<BillItemDto> items = new ArrayList<>();
        items.add(new BillItemDto("test", 3.23d));
        return items;
    }
}