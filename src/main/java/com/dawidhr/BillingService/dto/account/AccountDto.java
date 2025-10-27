package com.dawidhr.BillingService.dto.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AccountDto {
    private String email;
    private String password;

    public static boolean isValid(AccountDto accountDto) {
        if (accountDto == null)
            return false;

        if(StringUtils.isBlank(accountDto.getEmail()))
            return false;

        return StringUtils.isNotBlank(accountDto.getPassword());
    }

}
