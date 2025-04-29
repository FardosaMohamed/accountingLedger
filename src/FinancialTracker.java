import java.util.Arrays;
import java.util.Scanner;

public class FinancialTracker {
    public static void main(String[] args) {
        TransactionManager manager = new TransactionManager();
        manager.loadTransactionsFromFile(); // Load existing transaction

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Home Screen Menu ---");
            System.out.println("D. Add Deposit ");
            System.out.println("P. Make Payment");
            System.out.println("L. Ledger");
            System.out.println("X. Exist");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().toUpperCase();

//            switch (choice) {
//                case "D":
//                    manager.addDeposit();
//                    break;
//                case "P":
//                    manager.makePayment(); // Now this also saves to file
//                    break;
//                case "L":
//                    manager.showLedgerMenu()
//                    break;
//                case "X"
//                     System.out.println("Exiting. Goodbye!");
//                     return;
//                default:
//                    System.out.println("Invalid choice. Please try again.");}


            }
        }
    }
