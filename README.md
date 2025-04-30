# Accounting Ledger Project  
  
## Overview  
Welcome to the Accounting Ledger App. This is a command line interface (CLI) project that is written in Java.  
  
## Screens  
This project has three screens:  
1) Home Screen  
  ![image](https://github.com/user-attachments/assets/4f894e0a-f6ef-42b0-b03c-92e011179eba)
2) Ledger Screen  
  ![image](https://github.com/user-attachments/assets/ab83a765-e157-4973-bdcb-b0d8b46ce9fb)
3) Report Screen
  ![image](https://github.com/user-attachments/assets/3e289907-60e7-4f04-a024-3fbbeab931d8)

  
## Features  
* In the Home screen, Users can add deposits and make payments  
* In the Ledger screen, users can see all transactions, their deposits, their payments  
* In the Reports screen, they can see what transactions they made including transactions by month, by year, by previous year and by vendor name  
  
## One interesting piece of code from my project  
One key feature is how the app filters transactions by date. Here's the logic used to show Year-to-Date transactions:

    public void showYearToDate() {
    
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    for (Transaction transaction : transactions) {
        LocalDate transDate = LocalDate.parse(transaction.getDate(), formatter);

        if (transDate.getYear() == today.getYear() &&
            (transDate.isBefore(today) || transDate.isEqual(today))) {
            System.out.println(transaction);
        }
      }
    }

This method:
- Gets the current date
- Loops through all transactions
- Parses each transaction’s date (converts them into a real date/formats them)
- Checks if the year matches and the transaction is not in the future 
- Prints all valid transactions for the current year up to today
