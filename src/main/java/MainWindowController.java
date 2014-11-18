import com.google.common.eventbus.Subscribe;
import data.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    @FXML
    private ListView<Contact> contactListView;
    private ArrayList<Contact> contactList = new ArrayList<Contact>();

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("JContactsPersistenceUnit");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshContacts();
        Main.eventBus.register(this);
    }

    @Subscribe
    public void handleChangeEvent(ContactListChangedEvent event) {
        refreshContacts();
    }

    @FXML
    protected void openAddContactDialog() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/addContact.fxml"));
        Parent root = (Parent)fxmlLoader.load();

        Stage stage = new Stage();
        stage.setTitle("Add New Contact");
        stage.setScene(new Scene(root, 450, 450));
        stage.show();
    }

    @FXML
    protected void refreshContacts() {
        updateContactList();
        ObservableList<Contact> observableList = FXCollections.observableList(contactList);
        contactListView.setItems(observableList);
        contactListView.setCellFactory(new ContactCellFactoryCallback());
    }

    private void updateContactList() {
        contactList = new ArrayList<Contact>();

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery("SELECT t FROM Contact t");
        List resultList = query.getResultList();
        for (int i = 0; i < resultList.size(); ++i) {
            Contact resultContact = (Contact)resultList.get(i);
            contactList.add(resultContact);
        }
    }

    private static class ContactCellFactoryCallback implements Callback<ListView<Contact>, ListCell<Contact>> {
        @Override
        public ListCell<Contact> call(ListView<Contact> contactListView) {
            return new ContactListCell();
        }

        private static class ContactListCell extends ListCell<Contact> {
            @Override
            protected void updateItem(Contact contact, boolean bln) {
                super.updateItem(contact, bln);
                if (contact != null) {
                    setText(contact.getFirstname() + " " + contact.getLastname());
                }
            }
        }
    }
}