package atm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class WithdrawController {
    @FXML private Label balanceLabel;
    @FXML private TextField amountField;
    @FXML private Label errorLabel;

    private int accountNumber;
    private BankDatabase bankDatabase;

    public void setSession(int accountNumber, BankDatabase bankDatabase) {
        this.accountNumber = accountNumber;
        this.bankDatabase = bankDatabase;
        refreshBalance();
    }

    private void refreshBalance() {
        double balance = bankDatabase.getBalance(accountNumber);
        balanceLabel.setText(String.format("Balance: $%.2f", balance));
    }

    @FXML
    private void handleWithdraw() {
        try {
            double amount = Double.parseDouble(amountField.getText().trim());
            if (amount <= 0) {
                errorLabel.setText("Enter an amount greater than 0.");
                return;
            }
            if (amount > bankDatabase.getBalance(accountNumber)) {
                errorLabel.setText("Insufficient funds.");
                return;
            }
            bankDatabase.withdraw(accountNumber, amount);
            refreshBalance();
            errorLabel.setStyle("-fx-text-fill: #4ecca3;");
            errorLabel.setText(String.format("Withdrew $%.2f successfully.", amount));
            amountField.clear();
        } catch (NumberFormatException e) {
            errorLabel.setText("Please enter a valid amount.");
        }
    }

    @FXML
    private void handleBack() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/atm/menu.fxml"));
        Scene scene = new Scene(loader.load(), 400, 600);
        MenuController controller = loader.getController();
        controller.setSession(accountNumber, bankDatabase);
        Main.primaryStage.setScene(scene);
    }
}
