import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class TransactionManager {
    ArrayList<Transaction> transactions = new ArrayList<>();

    public TransactionManager() {
    }

    public void loadTransactionsFromFile() {
        String fileName = "transactions.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            // Skip the header line
            br.readLine();

            while (true) {
                try {
                    if ((line = br.readLine()) == null) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                // Skip empty lines
                if (line.trim().isEmpty()) continue;

                // Split line by "|"
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String date = parts[0];
                    String time = parts[1];
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);

                    // Create a Transaction object
                    Transaction transaction = new Transaction(date, time, description, vendor, amount);

                    // Add to list
                    transactions.add(transaction);
                }
            }
            System.out.println("Transactions loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


    public void saveTransactionToFile(Transaction transactions) {
        String fileName = "transactions.csv";

        try (FileWriter writer = new FileWriter(fileName, true)) { // true = append mode
            String line = transactions.getDate() + "|" +
                    transactions.getTime() + "|" +
                    transactions.getDescription() + "|" +
                    transactions.getVendor() + "|" +
                    transactions.getAmount() + "\n";

            writer.write(line);
        } catch (IOException e) {
            System.out.println("Error writing transaction to file: " + e.getMessage());
        }
    }

    public void addDeposit() {
        Scanner scanner = new Scanner(System.in);

        // Collect user input for deposit
        System.out.println("Enter the date (yyyy-mm-dd): ");
        String date = scanner.nextLine();
        System.out.println("Enter the time (hh:mm:ss): ");
        String time = scanner.nextLine();
        System.out.println("Enter the description: ");
        String description = scanner.nextLine();
        System.out.println("Enter the vendor (optional): ");
        String vendor = scanner.nextLine();
        System.out.println("Enter the deposit amount: ");
        double amount = scanner.nextDouble();

        // Ensure amount is positive for deposit
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }

        // Create and save the deposit transaction
        Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(newTransaction);
        saveTransactionToFile(newTransaction); // Save to CSV
        System.out.println("Deposit added successfully!");
    }

    public void makePayment() {
        Scanner scanner = new Scanner(System.in);

        // Collect user input for payment
        System.out.println("Enter the date (yyyy-mm-dd): ");
        String date = scanner.nextLine();
        System.out.println("Enter the time (hh:mm:ss): ");
        String time = scanner.nextLine();
        System.out.println("Enter the description: ");
        String description = scanner.nextLine();
        System.out.println("Enter the vendor (optional): ");
        String vendor = scanner.nextLine();
        System.out.println("Enter the payment amount: ");
        double amount = scanner.nextDouble();

        // Ensure amount is negative for payment
        if (amount >= 0) {
            System.out.println("Payment amount must be negative.");
            return;
        }

        // Create and save the payment transaction
        Transaction newTransaction = new Transaction(date, time, description, vendor, amount);
        transactions.add(newTransaction);
        saveTransactionToFile(newTransaction); // Save to CSV
        System.out.println("Payment added successfully!");
    }
    public void showLedgerMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Ledger Screen ---");
            System.out.println("A) All Transactions");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "A":
                    viewAllTransactions();
                    break;
                case "D":
                    viewDeposits();
                    break;
                case "P":
                    viewPayments();
                    break;
                case "R":
//                    showReportsMenu(); // We’ll add this later
//                    break;
                case "H":
                    return; // Go back to Home screen
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    public void viewAllTransactions() {
        System.out.println("\n--- All Transactions ---");
        for (int i = transactions.size() - 1; i >= 0; i--) {
            System.out.println(transactions.get(i)); // Newest first
        }
    }

    public void viewDeposits() {
        System.out.println("\n--- Deposits Only ---");
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            if (t.getAmount() > 0) {
                System.out.println(t);
            }
        }
    }

    public void viewPayments() {
        System.out.println("\n--- Payments Only ---");
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            if (t.getAmount() < 0) {
                System.out.println(t);
            }
        }
    }
}
