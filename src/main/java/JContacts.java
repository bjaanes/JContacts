import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class JContacts extends Application {

    public static void main(String[] args) {
        Application.launch(JContacts.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/jcontacts.fxml"));

        stage.setTitle("JContacts by the Lura's");
        stage.setScene(new Scene(root, 300, 275));
        stage.show();
    }
}