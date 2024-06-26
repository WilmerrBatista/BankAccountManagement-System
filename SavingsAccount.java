/**
 * Represents a savings account, which is a type of bank account.
 * Inherits from the BankAccount class.
 */
public class SavingsAccount extends BankAccount {
    // Indicates whether the savings account is active or inactive
    private boolean active;


    /**
     * Constructs a new SavingsAccount object with the given initial balance and annual interest rate.
     * Sets the account status based on whether the initial balance is greater than $25.
     * @param balance The initial balance of the savings account.
     * @param annualInterestRate The annual interest rate of the savings account.
     */
    public SavingsAccount(double balance, double annualInterestRate) {
        // Call the constructor of the superclass (BankAccount)
        super(balance, annualInterestRate);
        // Set the account status based on whether the initial balance is greater than $25
        setActive(balance > 25);
    }


    /**
     * Gets the status of the savings account (active or inactive).
     * @return true if the account is active, false otherwise.
     */
    public boolean isActive() {
        return active;
    }



    /**
     * Sets the status of the savings account (active or inactive).
     * @param active true to set the account as active, false to set it as inactive.
     */
    public void setActive(boolean active) {
        this.active = active;
    }


    /**
     * Overrides the withdraw method from the BankAccount class.
     * Checks if the account is active before allowing withdrawal.
     * If the balance falls below $25 after withdrawal, the account becomes inactive.
     * @param amount The amount to be withdrawn.
     */
    @Override
    public void withdraw(double amount) {
        // Check if the account is active
        if (!isActive()) {
            System.out.println("Cannot withdraw from inactive account.");
            return;
        }
        // Call the withdraw method from the superclass
        super.withdraw(amount);
        // Check if the balance falls below $25 after withdrawal
        if (getBalance() < 25) {
            // Set the account as inactive
            setActive(false);
        }
    }


    /**
     * Method to deposit money
     * @param amount
     */
    @Override
    public void deposit(double amount) {
        super.deposit(amount); // call super class deposit method
        if (!isActive() && getBalance() > 25) {
            setActive(true);
        }
    }

    // Method to process monthly operations
    /**
     * Overrides the monthlyProcess method from the BankAccount class.
     * Performs monthly processing for the savings account, including applying service charges for excess withdrawals.
     * If the balance falls below $25 after deducting service charges, the account becomes inactive.
     */
    @Override
    public void monthlyProcess() {
        // Get the number of withdrawals made in the month
        int numOfWithdrawals = getNumOfWithdrawals();
        // Check if the number of withdrawals is more than 4
        if (numOfWithdrawals > 4) {
            // Calculate the service charge for each withdrawal above 4
            double serviceCharge = (numOfWithdrawals - 4) * 1.0; // $1 service charge for each withdrawal above 4
            // Add the service charge to the monthly service charge
            setMonthlyServiceCharge(getMonthlyServiceCharge() + serviceCharge);
            // Check if the balance falls below $25 after deducting service charges
            if (getBalance() - getMonthlyServiceCharge() < 25) {
                // Set the account as inactive
                setActive(false);
            }
        }
        // Call the monthlyProcess method from the superclass
        super.monthlyProcess();
    }

}
