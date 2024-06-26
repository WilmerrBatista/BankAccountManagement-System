/**
 * This class represents a Bank account, The class is abstract class it means we have to
 * extend another class from this class in order to use it
 */
public abstract class BankAccount {
    // Balance of the bank account
    private double balance;

    // Number of deposits made in the month
    private int numOfDeposits;

    // Number of withdrawals made in the month
    private int numOfWithdrawals;

    // Annual interest rate of the bank account
    private double annualInterestRate;

    // Monthly service charge for the bank account
    private double monthlyServiceCharge;

    /**
     *  Constructor to initialize the bank account with the given balance and annual interest rate
     * @param balance
     * @param annualInterestRate
     */
      public BankAccount(double balance, double annualInterestRate) {
        this.balance = balance;
        this.annualInterestRate = annualInterestRate;
    }


    /**
     * Method to deposit money
     * @param amount
     */
    public void deposit(double amount) {
        balance += amount;
        numOfDeposits++;
    }

    /**
     * Method to withdraw money
     * @param amount
     */
    public void withdraw(double amount) {
        balance -= amount;
        numOfWithdrawals++;
    }

    /**
     * Method to calculate monthly interest
     */
    public void calcInterest() {
        double monthlyInterestRate = annualInterestRate / 12;
        double monthlyInterest = balance * monthlyInterestRate;
        balance += monthlyInterest;
    }

    /**
     * process monthly operations
     */
    public void monthlyProcess() {
        balance -= monthlyServiceCharge;
        calcInterest();
        numOfDeposits = 0;
        numOfWithdrawals = 0;
        monthlyServiceCharge = 0;
    }

    // Getters and setters for private variables
    public double getBalance() {
        return balance;
    }

    public int getNumOfDeposits() {
        return numOfDeposits;
    }

    public int getNumOfWithdrawals() {
        return numOfWithdrawals;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public double getMonthlyServiceCharge() {
        return monthlyServiceCharge;
    }

    public void setMonthlyServiceCharge(double monthlyServiceCharge) {
        this.monthlyServiceCharge = monthlyServiceCharge;
    }
}
