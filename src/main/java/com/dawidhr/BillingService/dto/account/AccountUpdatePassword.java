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
public class AccountUpdatePassword {
    private String email;
    private String oldPassword;
    private String newPassword;

    public static boolean isValid(AccountUpdatePassword accountUpdatePassword) {
        if (accountUpdatePassword == null)
            return false;

        if (StringUtils.isBlank(accountUpdatePassword.getEmail()))
            return false;

        if (StringUtils.isBlank(accountUpdatePassword.getOldPassword()))
            return false;

        return StringUtils.isNotBlank(accountUpdatePassword.getNewPassword());
    }
}
