package com.assignment.backend.model;

public class Account {
    private long balance;
    private Long number;

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", number=" + number +
                '}';
    }
}
