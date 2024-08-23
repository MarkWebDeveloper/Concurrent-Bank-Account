
# Bank Account Safe: A Concurrency Exercise in Java

This Java program demonstrates how to implement a thread-safe bank account using a ReentrantLock and volatile keywords. It showcases proper synchronization techniques to prevent race conditions and ensure data integrity in a multi-threaded environment.


## Explanation

- The BankAccountSafe class represents a bank account with a balance and a numberOfTransactions counter.
- The deposit() and withdraw() methods are synchronized using a ReentrantLock to ensure exclusive access to the shared variables.
- The volatile keyword on the balance field guarantees that changes made by one thread are immediately visible to others.
- The AtomicInteger for numberOfTransactions provides atomic increment operations, eliminating the need for explicit synchronization.
- The main method creates an ExecutorService and submits multiple tasks to deposit and withdraw funds concurrently.
## Expected Output

The program will output the current balance and transaction count after each deposit or withdrawal. The output should be consistent and free from race condition-related errors.
## Features

- Thread Safety: Employs a ReentrantLock to protect critical sections and avoid race conditions.
- Atomic Operations: Uses an AtomicInteger for atomic updates to the transaction count.
- Volatile Keyword: Ensures visibility of shared data among threads.
- Multi-Threaded Operations: Simulates concurrent deposits and withdrawals using an ExecutorService.


## Prerequisites

- Java Development Kit (JDK) installed
- Maven installed
## Run Locally

Clone the project

```bash
git clone https://github.com/MarkWebDeveloper/Concurrent-Bank-Account
```

Build the project

```bash
cd your-project-directory
mvn clean install
```

Run the app

```bash
  mvn exec:java -Dexec.mainClass="your.package.BankAccountSafe"
```

