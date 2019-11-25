package com.assignment.backend.service;

import com.assignment.backend.model.Account;

public class InMemoryTransferService implements TransferService {

    private final TransferArbiter transferArbiter;

    public InMemoryTransferService(TransferArbiter transferArbiter) {
        this.transferArbiter = transferArbiter;
    }

    /**
     * Performs money transfer given the fact that accounts store in heap memory
     *
     * @param accountFrom account to withdraw money
     * @param accountTo   account to deposit money
     * @param amount      value to transfer
     * @return true in case of successful transfer, false otherwise
     */
    @Override
    public boolean transfer(Account accountFrom, Account accountTo, long amount) {
        Account first = accountFrom.getNumber() > accountTo.getNumber() ? accountFrom : accountTo;
        Account second = first == accountFrom ? accountTo : accountFrom;

        synchronized (first) {
            synchronized (second) {
                if (transferArbiter.isTransferAllowed(accountFrom, accountTo, amount)) {
                    accountFrom.setMinorUnitBalance(accountFrom.getMinorUnitBalance() - amount);
                    accountTo.setMinorUnitBalance(accountTo.getMinorUnitBalance() + amount);
                    return true;
                }
                return false;
            }
        }
    }
}
