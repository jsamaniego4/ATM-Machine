package atm;

public class Account {
    private int accountNumber;
    private int pin;
    private double balance;

    public Account(int accountNumber, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public boolean validatePin(int enteredPin) {
        return enteredPin == this.pin;
    }

    public int getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }
    public void credit(double amount) { balance += amount; }
    public void debit(double amount) { balance -= amount; }
}
