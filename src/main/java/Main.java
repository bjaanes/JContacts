import javafx.application.Application;
import javafx.stage.Stage;

import org.jboss.weld.environment.se.Weld;

public class Main extends Application {

    private Weld weld;

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

    @Override
    public void init() {
        weld = new Weld();
    }

    @Override
    public void start(Stage stage) throws Exception {
        weld.initialize()
                .instance()
                .select(FxMain.class)
                .get()
                .start(stage, getParameters());
    }

    @Override
    public void stop() {
        weld.shutdown();
    }
}