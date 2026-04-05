import java.util.Scanner;

class BankAccount {

    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Amount Deposited Successfully.");
    }

    public boolean withdraw(double amount) {

        if (amount > balance) {
            System.out.println("Insufficient Balance.");
            return false;
        } 
        else {
            balance -= amount;
            System.out.println("Withdrawal Successful.");
            return true;
        }
    }
}

class ATM {

    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void showMenu() {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();

                    if (withdrawAmount <= 0) {
                        System.out.println("Invalid Amount.");
                    } 
                    else {
                        account.withdraw(withdrawAmount);
                    }
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();

                    if (depositAmount <= 0) {
                        System.out.println("Invalid Amount.");
                    } 
                    else {
                        account.deposit(depositAmount);
                    }
                    break;

                case 3:
                    System.out.println("Current Balance: " + account.getBalance());
                    break;

                case 4:
                    System.out.println("Thank you for using ATM.");
                    break;

                default:
                    System.out.println("Invalid Choice.");
            }

        } while (choice != 4);

        sc.close();
    }
}

public class ATMInterface {

    public static void main(String[] args) {

        BankAccount account = new BankAccount(10000);
        ATM atm = new ATM(account);

        atm.showMenu();
    }
}