import cdi.AppQualified;
import javafx.fxml.FXMLLoader;
import javafx.util.Callback;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

public class FXMLLoaderProducer {
    @Inject
    Instance<Object> instance;

    @Produces @AppQualified
    public FXMLLoader createLoader() {
        FXMLLoader loader = new FXMLLoader();

        loader.setControllerFactory(new ControllerFactoryCallback());
        loader.setLocation(Main.class.getResource("fxml/"));

        return loader;
    }

    private class ControllerFactoryCallback implements Callback<Class<?>, Object> {
        @Override
        public Object call(Class<?> param) {
            return instance.select(param).get();
        }
    }
}
