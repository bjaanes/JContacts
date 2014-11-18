import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;

public class FxMain {
    @Inject @AppQualified
    private FXMLLoader fxmlLoader;

    public void start(Stage stage, Application.Parameters parameters) throws IOException
    {
        InputStream fxml = MainWindowController.class.getResourceAsStream("fxml/main.fxml");
        Parent root = (Parent) fxmlLoader.load(fxml);

        stage.setTitle("JContacts");
        stage.setScene(new Scene(root, 750, 500));
        stage.show();
    }
}
