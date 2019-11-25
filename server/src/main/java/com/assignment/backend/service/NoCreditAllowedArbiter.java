package com.assignment.backend.service;

import com.assignment.backend.model.Account;

/**
 * TransferArbiter implementation that forbids negative account balance
 */
public class NoCreditAllowedArbiter implements TransferArbiter {
    @Override
    public boolean isTransferAllowed(Account accountFrom, Account accountTo, long amount) {
        return accountFrom.getMinorUnitBalance() - amount >= 0;
    }
}
