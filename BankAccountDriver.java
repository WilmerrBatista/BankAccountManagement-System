import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class BankAccountDriver {
    public static void main(String[] args) {
        // Create a SavingsAccount object
        SavingsAccount savingsAccount1 = new SavingsAccount(100, 0.05);
        System.out.println("Initial Balance in Savings Account: $" + savingsAccount1.getBalance());

        // Deposit and withdraw operations for savings account
        savingsAccount1.deposit(50);
        System.out.println("Deposited:50");
        System.out.println("Balance after deposit: $" + savingsAccount1.getBalance());

        System.out.println("Withdrawn:30");
        savingsAccount1.withdraw(30);
        System.out.println("Balance after withdrawal: $" + savingsAccount1.getBalance());

        // Monthly process for savings account
        savingsAccount1.monthlyProcess();
        System.out.println("Balance after monthly process: $" + savingsAccount1.getBalance());

        // UML Class Diagrams
        // BankAccount class diagram
        String bankAccountUML = "BankAccount <-- SavingsAccount : extends\n" +
                "-----------------------------\n" +
                "- balance: double\n" +
                "- numOfDeposits: int\n" +
                "- numOfWithdrawals: int\n" +
                "- annualInterestRate: double\n" +
                "- monthlyServiceCharge: double\n" +
                "-----------------------------\n" +
                "+ BankAccount(balance: double, annualInterestRate: double)\n" +
                "+ deposit(amount: double)\n" +
                "+ withdraw(amount: double)\n" +
                "+ calcInterest()\n" +
                "+ monthlyProcess()\n" +
                "+ getBalance(): double\n" +
                "+ getNumOfDeposits(): int\n" +
                "+ getNumOfWithdrawals(): int\n" +
                "+ getAnnualInterestRate(): double\n" +
                "+ getMonthlyServiceCharge(): double\n" +
                "+ setMonthlyServiceCharge(monthlyServiceCharge: double)\n";

        // SavingsAccount class diagram
        String savingsAccountUML = "SavingsAccount --> BankAccount : extends\n" +
                "--------------------------------------\n" +
                "- active: boolean\n" +
                "--------------------------------------\n" +
                "+ SavingsAccount(balance: double, annualInterestRate: double)\n" +
                "+ isActive(): boolean\n" +
                "+ setActive(active: boolean)\n" +
                "+ withdraw(amount: double)\n" +
                "+ deposit(amount: double)\n" +
                "+ monthlyProcess()\n";

        // Display UML diagrams
        System.out.println("\nBankAccount Class UML Diagram:\n" + bankAccountUML);
        System.out.println("\nSavingsAccount Class UML Diagram:\n" + savingsAccountUML);

        // Write UML diagrams to a file
        try {PrintWriter writer = new PrintWriter(new FileWriter("output.txt"));

            writer.println("Initial Balance in Savings Account: $" + savingsAccount1.getBalance());

            // Deposit and withdraw operations for savings account
            savingsAccount1.deposit(50);
            writer.println("Deposited:50");
            writer.println("Balance after deposit: $" + savingsAccount1.getBalance());

            writer.println("Withdrawn:30");
            savingsAccount1.withdraw(30);
            writer.println("Balance after withdrawal: $" + savingsAccount1.getBalance());

            // Monthly process for savings account
            savingsAccount1.monthlyProcess();
            writer.println("Balance after monthly process: $" + savingsAccount1.getBalance());

            writer.println("BankAccount Class UML Diagram:\n" + bankAccountUML);
            writer.println("\nSavingsAccount Class UML Diagram:\n" + savingsAccountUML);
            writer.flush();
            System.out.println("\nUML diagrams written to file 'uml_diagrams.txt'");
        } catch (IOException e) {
            System.err.println("Error writing UML diagrams to file: " + e.getMessage());
        }
    }

}
