package atm;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

public class LoginController {
    @FXML private TextField accountNumberField;
    @FXML private PasswordField pinField;
    @FXML private Label errorLabel;

    private BankDatabase bankDatabase = new BankDatabase();

    @FXML
    private void handleLogin() {
        try {
            int accountNumber = Integer.parseInt(accountNumberField.getText().trim());
            int pin = Integer.parseInt(pinField.getText().trim());

            if (bankDatabase.authenticateUser(accountNumber, pin)) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/atm/menu.fxml"));
                Scene scene = new Scene(loader.load(), 400, 600);
                MenuController controller = loader.getController();
                controller.setSession(accountNumber, bankDatabase);
                Main.primaryStage.setScene(scene);
            } else {
                errorLabel.setText("Invalid account number or PIN.");
            }
        } catch (NumberFormatException e) {
            errorLabel.setText("Please enter valid numbers.");
        } catch (Exception e) {
            errorLabel.setText("Error: " + e.getMessage());
        }
    }
}
