package com.assignment.backend.service;

import com.assignment.backend.model.Account;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class RequestHandlerTest {
    @Test
    public void positiveTest() {
        RequestHandler requestHandler =
                new RequestHandler(value -> {
                    Account account = new Account();
                    account.setNumber(value);
                    return Optional.of(account);
                }, (accountFrom, accountTo, amount) -> true);

        Assert.assertTrue(requestHandler.transfer(1L, 2L, 3L));
    }

    @Test
    public void fromAccountNotFoundTest() {
        RequestHandler requestHandler =
                new RequestHandler(value -> {
                    if (value == 1L) return Optional.empty();
                    Account account = new Account();
                    account.setNumber(value);
                    return Optional.of(account);
                }, (accountFrom, accountTo, amount) -> true);

        Assert.assertFalse(requestHandler.transfer(1L, 2L, 1L));
    }

    @Test
    public void toAccountNotFoundTest() {
        RequestHandler requestHandler =
                new RequestHandler(value -> {
                    if (value == 2L) return Optional.empty();
                    Account account = new Account();
                    account.setNumber(value);
                    return Optional.of(account);
                }, (accountFrom, accountTo, amount) -> true);

        Assert.assertFalse(requestHandler.transfer(1L, 2L, 1L));
    }

    @Test
    public void zeroAmountTest() {
        RequestHandler requestHandler =
                new RequestHandler(value -> {
                    Account account = new Account();
                    account.setNumber(value);
                    return Optional.of(account);
                }, (accountFrom, accountTo, amount) -> true);

        Assert.assertFalse(requestHandler.transfer(1L, 2L, 0L));
    }

    @Test
    public void sameAccountTest() {
        RequestHandler requestHandler =
                new RequestHandler(value -> {
                    Account account = new Account();
                    account.setNumber(value);
                    return Optional.of(account);
                }, (accountFrom, accountTo, amount) -> true);

        Assert.assertFalse(requestHandler.transfer(1L, 1L, 1L));
    }
}
