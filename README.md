# Bank Account Management System

A Java Swing learning project demonstrating inheritance, account operations, and a desktop interface. The GUI starts with one savings account with a $100 balance and a 5% annual interest rate. Data is held in memory and resets when the app closes.

## Transaction validation

Deposits and withdrawals must be positive, finite numbers. The account model rejects overdrafts and deposits that overflow the supported balance. Inactive savings accounts reject withdrawals. Failed transactions leave the balance and transaction counters unchanged, and the GUI displays an error instead of recording a successful transaction.

## Build and test

Requires a JDK (tested with Java 17). From this directory:

```sh
mkdir build
javac -d build BankAccount.java SavingsAccount.java BankAccountGUI.java BankAccountDriver.java BankAccountValidationTest.java
java -Djava.awt.headless=true -cp build BankAccountValidationTest
java -cp build BankAccountGUI
```

The standalone regression suite checks negative amounts, zero, NaN, infinity, malformed input, overdrafts, inactive accounts, overflow, and normal transactions. No external libraries are required.

## Scope and next steps

This is an educational simulation, not real banking software. It uses `double` rather than decimal currency arithmetic, has no authentication or persistence, and does not create multiple accounts. Future improvements include `BigDecimal` amounts, saved transaction history, and clearer account-status rules around the $25 threshold. Monthly fees and interest behavior remain part of the original exercise.

## Original usage guide

This project implements a simple bank account management system with a graphical user interface (GUI) using Java Swing.


Instructions:
1. Compilation:
   - Ensure you have Java Development Kit (JDK) installed on your system.
   - Open a command prompt or terminal.
   - Navigate to the directory containing the source code files (BankAccountGUI.java, BankAccount.java, SavingsAccount.java).
   - Compile the source code using the following command:
     javac BankAccountGUI.java BankAccount.java SavingsAccount.java

2. Execution:
   - After compiling the source code, execute the BankAccountGUI class to launch the graphical user interface:
     java BankAccountGUI

3. Using the GUI:
   - Once the GUI is launched, you can interact with it to perform various banking operations.
   - Enter the deposit amount in the "Deposit" text field and click the "Deposit" button to deposit money into your account.
   - Enter the withdrawal amount in the "Withdraw" text field and click the "Withdraw" button to withdraw money from your account.
   - Click the "Monthly Process" button to perform monthly processing on your account, including applying service charges for excess withdrawals.
