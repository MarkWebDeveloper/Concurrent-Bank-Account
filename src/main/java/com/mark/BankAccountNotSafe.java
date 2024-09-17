package com.mark;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BankAccountNotSafe {

    private long balance;
    private int numberOfTransactions;

    public BankAccountNotSafe(long balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("The current balance is " + balance + " and the current number of transactions is:" + ++numberOfTransactions);
    }

    public static void main(String[] args) {
        BankAccountNotSafe bankAccount = new BankAccountNotSafe(100);
        ExecutorService service = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {
            service.submit(() -> {
                try {
                    Thread.sleep(10);
                    bankAccount.deposit(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    service.shutdown();
                }
            });

        }
    }
}
