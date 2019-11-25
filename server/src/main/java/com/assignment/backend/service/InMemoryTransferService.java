package com.assignment.backend.service;

import com.assignment.backend.model.Account;

public class InMemoryTransferService implements TransferService {

    private final TransferArbiter transferArbiter;

    public InMemoryTransferService(TransferArbiter transferArbiter) {
        this.transferArbiter = transferArbiter;
    }

    @Override
    public boolean transfer(Account accountFrom, Account accountTo, long amount) {
        Account first = accountFrom.getNumber() > accountTo.getNumber() ? accountFrom : accountTo;
        Account second = first == accountFrom ? accountTo : accountFrom;

        synchronized (first) {
            synchronized (second) {
                if (transferArbiter.isTransferAllowed(accountFrom, accountTo, amount)) {
                    accountFrom.setBalance(accountFrom.getBalance() - amount);
                    accountTo.setBalance(accountTo.getBalance() + amount);
                    return true;
                }
                return false;
            }
        }
    }
}
