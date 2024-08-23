package com.mark;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccountSafe {

    private volatile double balance;
    private AtomicInteger numberOfTransactions;
    Lock lock = new ReentrantLock();

    public BankAccountSafe(double balance, AtomicInteger numberOfTransactions) {
        this.balance = balance;
        this.numberOfTransactions = numberOfTransactions;
    }

    public void deposit(double amount) {
        try {
            lock.lock();
            balance += amount;
            System.out.println("The current balance: " + balance);
            System.out.println("The current num of transactions: " + numberOfTransactions.incrementAndGet());
            Thread.sleep(1000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void withdraw(double amount) {
        try {
            lock.lock();
            balance -= amount;
            System.out.println("The current balance: " + balance);
            System.out.println("The current num of transactions: " + numberOfTransactions.incrementAndGet());
            Thread.sleep(2000);
        } catch(InterruptedException e){
            e.printStackTrace();
        }  finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(20);
        BankAccountSafe bankAccount = new BankAccountSafe(100, new AtomicInteger(0));
        try {
            service.submit(() -> bankAccount.deposit(10));
            service.submit(() -> bankAccount.deposit(20));
            service.submit(() -> bankAccount.deposit(30));
            service.submit(() -> bankAccount.withdraw(10));
            service.submit(() -> bankAccount.withdraw(20));
            service.submit(() -> bankAccount.withdraw(30));
        } finally {
            service.shutdown();
        }
    }
}
