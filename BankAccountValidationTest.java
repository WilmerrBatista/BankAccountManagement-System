/** Run with: java -Djava.awt.headless=true -cp build BankAccountValidationTest */
public class BankAccountValidationTest {
    private static int checks;

    public static void main(String[] args) {
        double[] invalid = {0, -10, Double.NaN, Double.POSITIVE_INFINITY,
                Double.NEGATIVE_INFINITY};
        for (double amount : invalid) {
            SavingsAccount account = new SavingsAccount(100, 0.05);
            rejected(() -> account.deposit(amount));
            unchanged(account, 100, 0, 0);
            rejected(() -> account.withdraw(amount));
            unchanged(account, 100, 0, 0);
        }

        SavingsAccount account = new SavingsAccount(100, 0.05);
        rejected(() -> account.withdraw(101));
        unchanged(account, 100, 0, 0);
        account.deposit(25.50);
        account.withdraw(50);
        unchanged(account, 75.50, 1, 1);

        SavingsAccount inactive = new SavingsAccount(20, 0.05);
        rejected(() -> inactive.withdraw(1));
        unchanged(inactive, 20, 0, 0);
        inactive.deposit(10);
        inactive.withdraw(1);
        unchanged(inactive, 29, 1, 1);

        SavingsAccount large = new SavingsAccount(Double.MAX_VALUE, 0);
        rejected(() -> large.deposit(Double.MAX_VALUE));
        unchanged(large, Double.MAX_VALUE, 0, 0);

        for (String input : new String[] {"", " ", "abc", "-10", "0", "NaN", "Infinity", "1e309"}) {
            rejected(() -> BankAccountGUI.parseAmount(input));
        }
        if (BankAccountGUI.parseAmount(" 25.50 ") != 25.50) {
            throw new AssertionError("Valid input with whitespace was not parsed.");
        }
        checks++;
        System.out.println("Passed " + checks + " validation checks.");
    }

    private static void rejected(Runnable operation) {
        try {
            operation.run();
        } catch (IllegalArgumentException | IllegalStateException expected) {
            checks++;
            return;
        }
        throw new AssertionError("Invalid transaction was accepted.");
    }

    private static void unchanged(SavingsAccount account, double balance, int deposits, int withdrawals) {
        if (account.getBalance() != balance || account.getNumOfDeposits() != deposits
                || account.getNumOfWithdrawals() != withdrawals) {
            throw new AssertionError("Unexpected balance or transaction counters.");
        }
        checks++;
    }
}
