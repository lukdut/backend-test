package com.assignment.backend.service;

import com.assignment.backend.model.Account;

public interface TransferArbiter {
    /**
     * Makes a decision, if transfer allowed
     *
     * @param accountFrom account to withdraw money
     * @param accountTo   account to deposit money
     * @param amount      value to transfer
     * @return true if transfer allowed, false otherwise
     */
    boolean isTransferAllowed(Account accountFrom, Account accountTo, long amount);
}
