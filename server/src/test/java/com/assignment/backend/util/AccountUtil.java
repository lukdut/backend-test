package com.assignment.backend.util;

import com.assignment.backend.model.Account;

public class AccountUtil {
    public static Account createAccountWithBalance(long startBalance) {
        Account account = new Account();
        account.setMinorUnitBalance(startBalance);
        return account;
    }

    public static Account createAccountWithBalanceAndNumber(long startBalance, Long number) {
        Account account = new Account();
        account.setMinorUnitBalance(startBalance);
        account.setNumber(number);
        return account;
    }
}
