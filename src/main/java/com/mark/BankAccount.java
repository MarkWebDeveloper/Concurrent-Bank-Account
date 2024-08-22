package com.mark;

public class BankAccount {

    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public double deposit(double amount) {
        balance += amount;
        return this.balance;
    }

    public double withdraw(double amount) {
        balance -= amount;
        return this.balance;
    }

    // Main method to create multiple threads and simulate customer activities
}