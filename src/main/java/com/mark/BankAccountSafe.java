package com.mark;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class BankAccountSafe {

    private double balance;
    private AtomicInteger numberOfTransactions;

    public BankAccountSafe(double balance, AtomicInteger numberOfTransactions) {
        this.balance = balance;
        this.numberOfTransactions = numberOfTransactions;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("The current balance: " + balance);
        System.out.println("The current num of transactions: " + numberOfTransactions.incrementAndGet());
    }

    public void withdraw(double amount) {
        balance -= amount;
        System.out.println("The current balance: " + balance);
        System.out.println("The current num of transactions: " + numberOfTransactions.incrementAndGet());
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        try {
            BankAccountSafe bankAccount = new BankAccountSafe(100, new AtomicInteger(0));
            for (int i = 0; i < 5; i++) {
                service.submit(() -> bankAccount.deposit(10));
            }
            for (int i = 0; i < 5; i++) {
                service.submit(() -> bankAccount.withdraw(10));
            }
        } finally {
            service.shutdown();
        }
    }
}
