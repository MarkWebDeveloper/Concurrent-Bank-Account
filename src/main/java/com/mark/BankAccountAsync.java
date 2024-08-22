package com.mark;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankAccountAsync {

    private double balance;

    public BankAccountAsync(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        this.balance += amount;
        System.out.println(this.balance);
    }

    public void withdraw(double amount) {
        this.balance -= amount;
        System.out.println(this.balance);
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        try {
            BankAccountAsync bankAccount = new BankAccountAsync(0);
            for (int i = 0; i < 10; i++) {
                service.submit(() -> bankAccount.deposit(10));
            }
        } finally {
            service.shutdown();
        }
    }
}
