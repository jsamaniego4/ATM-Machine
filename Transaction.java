package atm;

public abstract class Transaction {
    private int accountNumber;
    protected BankDatabase bankDatabase;

    public Transaction(int accountNumber, BankDatabase bankDatabase) {
        this.accountNumber = accountNumber;
        this.bankDatabase = bankDatabase;
    }

    public int getAccountNumber() { return accountNumber; }
    public abstract void execute();
}
