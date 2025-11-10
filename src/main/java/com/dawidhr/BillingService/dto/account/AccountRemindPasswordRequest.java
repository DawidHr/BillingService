package com.dawidhr.BillingService.dto.account;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AccountRemindPasswordRequest {
    private String email;

    public static boolean isValid(AccountRemindPasswordRequest accountDto) {
        if (accountDto == null)
            return false;

        return StringUtils.isNotBlank(accountDto.getEmail());
    }

}
