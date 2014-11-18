import com.google.common.eventbus.EventBus;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Main extends Application {

    public static EventBus eventBus = new EventBus(); // TODO: REMOVE AS SOON AS DEPENDENCY INJECTION IS STARTING!

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/main.fxml"));

        stage.setTitle("JContacts");
        stage.setScene(new Scene(root, 750, 500));
        stage.show();
    }
}