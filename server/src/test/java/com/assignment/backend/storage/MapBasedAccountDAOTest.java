package com.assignment.backend.storage;

import com.assignment.backend.model.Account;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class MapBasedAccountDAOTest {
    @Test
    public void saveAccountWithIdTest() {
        MapBasedAccountDAO mapBasedAccountDAO = new MapBasedAccountDAO(() -> 1L);


        Assert.assertTrue(mapBasedAccountDAO.getByNumber(1L).isEmpty());

        Account account = mapBasedAccountDAO.createNewAccount();

        Optional<Account> retrievedAccount = mapBasedAccountDAO.getByNumber(1L);
        Assert.assertTrue(retrievedAccount.isPresent());
        Assert.assertEquals(account, retrievedAccount.get());
    }
}
