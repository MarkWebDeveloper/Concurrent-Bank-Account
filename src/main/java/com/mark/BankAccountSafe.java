package com.mark;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankAccountSafe {
    private double balance;
    private int numberOfTransactions;

    public BankAccountSafe(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("The current balance: " + balance);
        System.out.println("The current num of transactions: " + ++numberOfTransactions);
    }

    public void withdraw(double amount) {
        balance -= amount;
        System.out.println("The current balance: " + balance);
        System.out.println("The current num of transactions: " + ++numberOfTransactions);
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        try {
            BankAccountSafe bankAccount = new BankAccountSafe(100);
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
