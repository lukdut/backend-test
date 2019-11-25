package com.assignment.backend.service;

import com.assignment.backend.model.Account;

public interface TransferArbiter {
    boolean isTransferAllowed(Account accountFrom, Account accountTo, long amount);
}
