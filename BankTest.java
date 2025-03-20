class BankAccount {
    private String accountNumber;
    private double balance;

    public BankAccount(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: $" + amount);
            return true;
        } else {
            System.out.println("Withdrawal failed. Insufficient funds.");
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }
}

class SavingsAccount extends BankAccount {
    private double interestRate;

    public SavingsAccount(String accountNumber, double balance, double interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    public void applyInterest() {
        double interest = getBalance() * interestRate / 100;
        deposit(interest);
        System.out.println("Interest Applied: $" + interest);
    }
}

class CheckingAccount extends BankAccount {
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, double balance, double overdraftLimit) {
        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && getBalance() + overdraftLimit >= amount) {
            double newBalance = getBalance() - amount;
            if (newBalance < 0) {
                System.out.println("Withdrawn: $" + amount + " successfully!");
            } else {
                System.out.println("Withdrawn: $" + amount + " successfully!");
            }
            return true;
        } else {
            System.out.println("Withdrawal failed. Insufficient funds.");
            return false;
        }
    }
}

public class BankTest {
    public static void main(String[] args) {
        SavingsAccount savings = new SavingsAccount("S123", 1000.0, 5.0);
        CheckingAccount checking = new CheckingAccount("C456", 500.0, 0);

        System.out.println("Initial Savings Account Balance: $" + savings.getBalance());
        System.out.println("Initial Checking Account Balance: $" + checking.getBalance());


        savings.applyInterest();

        System.out.println("Attempting to withdraw $600...");
        checking.withdraw(600);

        System.out.println("Final Savings Account Balance: $" + savings.getBalance());
        System.out.println("Final Checking Account Balance: $" + checking.getBalance());
    }
}
