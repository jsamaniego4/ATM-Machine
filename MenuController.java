package atm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class MenuController {
    @FXML private Label balanceLabel;

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
    private void handleBalance() {
        refreshBalance();
    }

    @FXML
    private void handleDeposit() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/atm/deposit.fxml"));
        Scene scene = new Scene(loader.load(), 400, 600);
        DepositController controller = loader.getController();
        controller.setSession(accountNumber, bankDatabase);
        Main.primaryStage.setScene(scene);
    }

    @FXML
    private void handleWithdraw() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/atm/withdraw.fxml"));
        Scene scene = new Scene(loader.load(), 400, 600);
        WithdrawController controller = loader.getController();
        controller.setSession(accountNumber, bankDatabase);
        Main.primaryStage.setScene(scene);
    }

    @FXML
    private void handleHistory() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/atm/history.fxml"));
        Scene scene = new Scene(loader.load(), 400, 600);
        HistoryController controller = loader.getController();
        controller.setSession(accountNumber, bankDatabase);
        Main.primaryStage.setScene(scene);
    }

    @FXML
    private void handleLogout() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/atm/login.fxml"));
        Scene scene = new Scene(loader.load(), 400, 600);
        Main.primaryStage.setScene(scene);
    }
}
