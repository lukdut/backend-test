package com.assignment.backend;

import com.assignment.backend.model.Account;
import com.assignment.backend.service.InMemoryTransferService;
import com.assignment.backend.service.RequestHandler;
import com.assignment.backend.storage.IncrementalIdGenerator;
import com.assignment.backend.storage.MapBasedAccountDAO;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class FunctionalTest {
    private static final int ACCOUNTS_AMOUNT = 10;
    private static final long START_BALANCE = 1000L;
    private static final int TRANSFERS_AMOUNT = 1_000_000;

    @Test
    public void consistenceTest() throws InterruptedException {


        MapBasedAccountDAO mapBasedAccountDAO = new MapBasedAccountDAO(new IncrementalIdGenerator());
        RequestHandler requestHandler = new RequestHandler(mapBasedAccountDAO::getByNumber,
                new InMemoryTransferService((accountFrom, accountTo, amount) -> true));


        //prepare Accounts
        for (int i = 0; i < ACCOUNTS_AMOUNT; i++) {
            Account account = mapBasedAccountDAO.createNewAccount();
            account.setBalance(START_BALANCE);
        }

        //perform transfers
        ExecutorService executorService =
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < TRANSFERS_AMOUNT; i++) {
            executorService.submit(() -> {
                ThreadLocalRandom randomiser = ThreadLocalRandom.current();
                long accountNumberFrom = randomiser.nextLong(0, ACCOUNTS_AMOUNT);
                long accountNumberTo = randomiser.nextLong(0, ACCOUNTS_AMOUNT);
                while (accountNumberTo == accountNumberFrom) {
                    accountNumberTo = randomiser.nextLong(0, ACCOUNTS_AMOUNT);
                }
                long amount = randomiser.nextLong(0, START_BALANCE / 10);

                requestHandler.transfer(accountNumberFrom, accountNumberTo, amount);
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.MINUTES);

        //check consistence
        long totalStartBalance = ACCOUNTS_AMOUNT * START_BALANCE;
        long totalEndBalance = 0;
        for (int i = 0; i < ACCOUNTS_AMOUNT; i++) {
            Account account = mapBasedAccountDAO.getByNumber((long) i).get();
            System.out.println(account);
            totalEndBalance += account.getBalance();
        }

        Assert.assertEquals(totalStartBalance, totalEndBalance);
    }

}
