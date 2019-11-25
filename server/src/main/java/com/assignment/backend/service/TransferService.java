package com.assignment.backend.service;

import com.assignment.backend.model.Account;

public interface TransferService {
    boolean transfer(Account accountFrom, Account accountTo, long amount);
}
