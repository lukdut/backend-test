package com.assignment.backend.service;

import org.junit.Assert;
import org.junit.Test;

import static com.assignment.backend.util.AccountUtil.createAccountWithBalance;

public class NoCreditAllowedArbiterTest {
    private static NoCreditAllowedArbiter arbiter = new NoCreditAllowedArbiter();

    @Test
    public void balanceIsGreaterTest() {
        Assert.assertTrue(arbiter.isTransferAllowed(createAccountWithBalance(11), null, 10));

    }

    @Test
    public void balanceIsEqualTest() {
        Assert.assertTrue(arbiter.isTransferAllowed(createAccountWithBalance(10), null, 10));

    }

    @Test
    public void balanceIsLowerTest() {
        Assert.assertFalse(arbiter.isTransferAllowed(createAccountWithBalance(9), null, 10));
    }
}
