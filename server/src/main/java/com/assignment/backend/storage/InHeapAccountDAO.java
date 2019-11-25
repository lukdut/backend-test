package com.assignment.backend.storage;

import com.assignment.backend.model.Account;

import java.util.Optional;

public interface InHeapAccountDAO {
    Optional<Account> getByNumber(Long accountNumber);
    Account createNewAccount();
}
