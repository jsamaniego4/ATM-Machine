package atm;

public class BalanceInquiry extends Transaction {
    public BalanceInquiry(int accountNumber, BankDatabase bankDatabase) {
        super(accountNumber, bankDatabase);
    }

    @Override
    public void execute() {
        double balance = bankDatabase.getBalance(getAccountNumber());
        System.out.println("Balance: $" + balance);
    }

    public double getBalance() {
        return bankDatabase.getBalance(getAccountNumber());
    }
}
