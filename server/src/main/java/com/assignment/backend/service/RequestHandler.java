package com.assignment.backend.service;

import com.assignment.backend.model.Account;

import java.util.Optional;
import java.util.function.LongFunction;

public class RequestHandler {
    private final LongFunction<Optional<Account>> accountProvider;
    private final TransferService transferService;

    public RequestHandler(LongFunction<Optional<Account>> accountProvider,
                          TransferService transferService) {
        this.accountProvider = accountProvider;
        this.transferService = transferService;
    }

    public boolean transfer(Long accountIdFrom, Long accountIdTo, long amount) {
        if (amount == 0 || accountIdFrom.equals(accountIdTo)) return false;

        Optional<Account> accountFromOpt = accountProvider.apply(accountIdFrom);
        if (accountFromOpt.isEmpty()) {
            return false;
        }
        Optional<Account> accountToOpt = accountProvider.apply(accountIdTo);
        if (accountToOpt.isEmpty()) {
            return false;
        }

        return transferService.transfer(accountFromOpt.get(), accountToOpt.get(), amount);
    }
}
