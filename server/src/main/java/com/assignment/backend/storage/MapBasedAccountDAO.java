package com.assignment.backend.storage;

import com.assignment.backend.model.Account;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.LongSupplier;

public class MapBasedAccountDAO implements InHeapAccountDAO {

    private final Map<Long, Account> accountStorage = new ConcurrentHashMap<>();
    private final LongSupplier accountIdGenerationStrategy;


    public MapBasedAccountDAO(LongSupplier accountIdGenerationStrategy) {
        this.accountIdGenerationStrategy = accountIdGenerationStrategy;
    }

    @Override
    public Optional<Account> getByNumber(Long accountNumber) {
        return Optional.ofNullable(accountStorage.get(accountNumber));
    }

    @Override
    public Account createNewAccount() {
        long number = accountIdGenerationStrategy.getAsLong();

        Account account = new Account();
        account.setNumber(number);
        account.setMinorUnitBalance(0);

        accountStorage.put(account.getNumber(), account);
        return account;
    }
}
