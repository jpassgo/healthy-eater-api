package com.pascoe.healthyeaterapi.service;

import com.pascoe.healthyeaterapi.model.EmailAddress;
import com.pascoe.healthyeaterapi.model.UserAccount;
import org.apache.commons.validator.routines.EmailValidator;

public class AccountValidator {

    public static boolean isAccountValid(UserAccount userAccount) {
        return isEmailAddressValid(userAccount.getEmailAddress());
    }

    public static boolean isEmailAddressValid(String emailAddress) {
        return EmailValidator.getInstance().isValid(emailAddress);
    }
}
