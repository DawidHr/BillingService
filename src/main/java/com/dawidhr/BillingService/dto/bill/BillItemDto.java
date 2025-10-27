package com.dawidhr.BillingService.dto.bill;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BillItemDto {
    private String name;
    private double price;

    public static boolean isValid(BillItemDto item) {
        if (item == null)
            return false;

        return StringUtils.isNotBlank(item.getName());
    }
}
