package atm;

import java.util.*;

public class BankDatabase {
    private Map<Integer, Integer> pins = new HashMap<>();
    private Map<Integer, Double> balances = new HashMap<>();
    private Map<Integer, List<String>> history = new HashMap<>();

    public BankDatabase() {
        // Seed two accounts
        pins.put(12345, 1111);
        balances.put(12345, 676767.00);
        history.put(12345, new ArrayList<>());

        pins.put(67890, 2222);
        balances.put(67890, 676767.00);
        history.put(67890, new ArrayList<>());
    }

    public boolean authenticateUser(int accountNumber, int pin) {
        return pins.containsKey(accountNumber) && pins.get(accountNumber) == pin;
    }

    public double getBalance(int accountNumber) {
        return balances.getOrDefault(accountNumber, 0.0);
    }

    public void deposit(int accountNumber, double amount) {
        balances.put(accountNumber, getBalance(accountNumber) + amount);
        history.get(accountNumber).add(String.format("Deposit: +$%.2f", amount));
    }

    public void withdraw(int accountNumber, double amount) {
        balances.put(accountNumber, getBalance(accountNumber) - amount);
        history.get(accountNumber).add(String.format("Withdrawal: -$%.2f", amount));
    }

    public List<String> getTransactionHistory(int accountNumber) {
        return history.getOrDefault(accountNumber, new ArrayList<>());
    }
}
