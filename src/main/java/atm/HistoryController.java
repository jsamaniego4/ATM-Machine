package atm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import java.util.List;

public class HistoryController {
    @FXML private ListView<String> historyList;

    private int accountNumber;
    private BankDatabase bankDatabase;

    public void setSession(int accountNumber, BankDatabase bankDatabase) {
        this.accountNumber = accountNumber;
        this.bankDatabase = bankDatabase;
        loadHistory();
    }

    private void loadHistory() {
        List<String> history = bankDatabase.getTransactionHistory(accountNumber);
        historyList.getItems().clear();
        if (history.isEmpty()) {
            historyList.getItems().add("No transactions found.");
        } else {
            historyList.getItems().addAll(history);
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
