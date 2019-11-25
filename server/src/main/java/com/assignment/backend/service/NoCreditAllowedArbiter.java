package com.assignment.backend.service;

import com.assignment.backend.model.Account;

public class NoCreditAllowedArbiter implements TransferArbiter{
    @Override
    public boolean isTransferAllowed(Account accountFrom, Account accountTo, long amount) {
        return accountFrom.getBalance() - amount >= 0;
    }
}
