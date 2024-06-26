import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;


/**
 * A GUI application to interact with a bank account.
 */
public class BankAccountGUI extends JFrame implements ActionListener {
    // Label to display the balance
    private JLabel txtBalance;

    // Text field for deposit input
    private JTextField txtDeposit;

    // Text field for withdrawal input
    private JTextField txtWithdraw;

    // Button to initiate deposit action
    private JButton btnDeposit;

    // Button to initiate withdrawal action
    private JButton btnWithdraw;

    // Button to initiate monthly process action
    private JButton btnMonthlyProcess;

    // Text area to display transaction output
    private JTextArea outputArea;

    // Instance of SavingsAccount class to manage bank account operations
    private SavingsAccount savingsAccount;

    // Decimal formatter to format balance amounts
    private DecimalFormat decimalFormat;


    /**
     * Constructs a new BankAccountGUI object.
     */
    public BankAccountGUI() {
        // Create SavingsAccount object
        savingsAccount = new SavingsAccount(100, 0.05);
        // Create DecimalFormat object to format balance
        decimalFormat = new DecimalFormat("#.###");
        initUi();
    }



    /**
     * Initializes the user interface.
     */
    private void initUi()
    {
        setTitle("Bank Account GUI");
        setSize(750, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize components
        txtBalance = new JLabel();
        txtBalance.setHorizontalAlignment(SwingConstants.CENTER);
        txtBalance.setText("Your Balance: $" + decimalFormat.format(savingsAccount.getBalance()));

        txtDeposit = new JTextField(10);
        txtWithdraw = new JTextField(10);
        btnDeposit = new JButton("Deposit");
        btnWithdraw = new JButton("Withdraw");
        btnMonthlyProcess = new JButton("Monthly Process");
        outputArea = new JTextArea(10, 30);




        // Create panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(new JLabel("Deposit:"));
        buttonPanel.add(txtDeposit);
        buttonPanel.add(btnDeposit);
        buttonPanel.add(new JLabel("Withdraw:"));
        buttonPanel.add(txtWithdraw);
        buttonPanel.add(btnWithdraw);
        buttonPanel.add(btnMonthlyProcess);

        // Add components to content pane
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(txtBalance, BorderLayout.NORTH);
        container.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        container.add(buttonPanel, BorderLayout.SOUTH);


        // Add action listeners to buttons
        btnDeposit.addActionListener(this);

        btnWithdraw.addActionListener(this);

        btnMonthlyProcess.addActionListener(this);
    }



    public static void main(String[] args) {

                BankAccountGUI gui = new BankAccountGUI();
                gui.setVisible(true);

    }

    /**
     * Performs actions when buttons are clicked.
     *   * Overrides the actionPerformed method of the ActionListener interface to handle button clicks.
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if the source of the event is the Deposit button
        if (e.getSource() == btnDeposit) {
            // Parse the amount entered in the deposit text field
            double amount = Double.parseDouble(txtDeposit.getText());
            // Check if the amount is less than or equal to 0
            if (amount <= 0) {
                // Display an error message if the amount is not positive
                JOptionPane.showMessageDialog(this, "Amount must be greater than 0");
                return;
            }
            // Deposit the amount into the savings account
            savingsAccount.deposit(amount);
            // Update the balance label with the new balance
            txtBalance.setText("Your Balance: $" + decimalFormat.format(savingsAccount.getBalance()));
            // Append a transaction log to the output area
            outputArea.append("Deposited $" + amount +"\n");
            // Clear the deposit text field
            txtDeposit.setText("");
        }
        // Check if the source of the event is the Withdraw button
        if (e.getSource() == btnWithdraw) {
            // Parse the amount entered in the withdraw text field
            double amount = Double.parseDouble(txtWithdraw.getText());
            // Check if the amount is greater than the balance
            if (amount > savingsAccount.getBalance()) {
                // Display an error message if the amount exceeds the balance
                JOptionPane.showMessageDialog(this, "Insufficient Balance");
                return;
            }
            // Withdraw the amount from the savings account
            savingsAccount.withdraw(amount);
            // Update the balance label with the new balance
            txtBalance.setText("Your Balance: $" + decimalFormat.format(savingsAccount.getBalance()));
            // Append a transaction log to the output area
            outputArea.append("Withdrawn $" + amount + "\n");
            // Clear the withdraw text field
            txtWithdraw.setText("");
        }
        // Check if the source of the event is the Monthly Process button
        if (e.getSource() == btnMonthlyProcess) {
            // Perform the monthly process on the savings account
            savingsAccount.monthlyProcess();
            // Update the balance label with the new balance
            txtBalance.setText("Your Balance: $" + decimalFormat.format(savingsAccount.getBalance()));
            outputArea.append("Monthly process performed. New balance: $" + decimalFormat.format(savingsAccount.getBalance()) + "\n");

        }
    }
}
