package com.assignment.backend.model;

public class Account {
    private long minorUnitBalance;
    private Long number;

    public long getMinorUnitBalance() {
        return minorUnitBalance;
    }

    public void setMinorUnitBalance(long minorUnitBalance) {
        this.minorUnitBalance = minorUnitBalance;
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
                "balance=" + minorUnitBalance +
                ", number=" + number +
                '}';
    }
}
