package controllers;

import services.ContactService;
import data.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import javax.inject.Inject;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable, Observer {
    @FXML private ListView<Contact> contactListView;
    @Inject private ContactService contactService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactService.addObserver(this);
        refreshContacts();
    }

    @FXML
    protected void refreshContacts() {
        ObservableList<Contact> observableList = FXCollections.observableList(contactService.getContacts());
        contactListView.setItems(observableList);
        contactListView.setCellFactory(new ContactCellFactoryCallback());
    }

    @Override
    public void update(Observable o, Object arg) {
        refreshContacts();
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