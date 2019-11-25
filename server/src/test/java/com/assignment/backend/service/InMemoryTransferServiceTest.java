package com.assignment.backend.service;

import com.assignment.backend.model.Account;
import org.junit.Assert;
import org.junit.Test;

import static com.assignment.backend.util.AccountUtil.createAccountWithBalanceAndNumber;

public class InMemoryTransferServiceTest {

    @Test
    public void transferNotAllowedTest() {
        InMemoryTransferService inMemoryTransferService =
                new InMemoryTransferService((accountFrom, accountTo, amount) -> false);

        boolean isTransferred = inMemoryTransferService.transfer(
                createAccountWithBalanceAndNumber(10, 1L),
                createAccountWithBalanceAndNumber(10, 2L), 1);

        Assert.assertFalse(isTransferred);
    }

    @Test
    public void transferAllowedTest() {
        InMemoryTransferService inMemoryTransferService =
                new InMemoryTransferService((accountFrom, accountTo, amount) -> true);

        boolean isTransferred = inMemoryTransferService.transfer(
                createAccountWithBalanceAndNumber(10, 1L),
                createAccountWithBalanceAndNumber(10, 2L), 1);

        Assert.assertTrue(isTransferred);
    }

    @Test
    public void transferToTheSameAccountTest() {
        InMemoryTransferService inMemoryTransferService =
                new InMemoryTransferService((accountFrom, accountTo, amount) -> true);

        Account account = createAccountWithBalanceAndNumber(10, 1L);
        boolean isTransferred = inMemoryTransferService.transfer(
                account,
                account, 1);

        Assert.assertTrue(isTransferred);
    }

    @Test
    public void transferConsistentTest() {
        InMemoryTransferService inMemoryTransferService =
                new InMemoryTransferService((accountFrom, accountTo, amount) -> true);

        Account accountFrom = createAccountWithBalanceAndNumber(10, 1L);
        Account accountTo = createAccountWithBalanceAndNumber(10, 1L);
        boolean isTransferred = inMemoryTransferService.transfer(
                accountFrom,
                accountTo, 1);

        Assert.assertTrue(isTransferred);
        Assert.assertEquals(11, accountTo.getBalance());
        Assert.assertEquals(9, accountFrom.getBalance());
    }
}
