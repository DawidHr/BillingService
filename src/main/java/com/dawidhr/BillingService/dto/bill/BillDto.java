package com.dawidhr.BillingService.dto.bill;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class BillDto {
    private String name;
    private String description;
    private LocalDateTime buyDate;
    private double totalPrice;
    private List<BillItemDto> items;

    public static boolean isValid(BillDto billDto) {
        if (billDto == null)
            return false;

        if (StringUtils.isBlank(billDto.getName()))
            return false;

        if (billDto.getBuyDate() == null)
            return false;

        if (billDto.getItems() == null)
            return false;

        for (BillItemDto item : billDto.getItems())
            if (!BillItemDto.isValid(item))
                return false;

        return true;
    }
}
