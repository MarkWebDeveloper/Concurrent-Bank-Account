package com.mark;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccountSafe {

    private AtomicLong balance;
    private AtomicInteger numberOfTransactions;
    ReentrantLock lock = new ReentrantLock();

    public BankAccountSafe(AtomicLong balance, AtomicInteger numberOfTransactions) {
        this.balance = balance;
        this.numberOfTransactions = numberOfTransactions;
    }

    public void deposit(long amount) {
        try {
            lock.lock();
            long newBalance = balance.addAndGet(amount);
            System.out.println("The current balance is " + newBalance + " and the current number of transactions is:" + numberOfTransactions.incrementAndGet());
        } finally {
            lock.unlock();
        } 
    }

    public static void main(String[] args) {
        BankAccountSafe bankAccount = new BankAccountSafe(new AtomicLong(), new AtomicInteger());
        ExecutorService service = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {
            service.submit(() -> {
                bankAccount.deposit(10);
            });
        }
        service.shutdown();
    }
}