package com.gjermundbjaanes.controllers;

import com.gjermundbjaanes.data.Contact;
import com.gjermundbjaanes.services.ContactService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable, Observer {
    @FXML private ListView<Contact> contactListView;
    @FXML private AnchorPane contentPane;
    @Inject private ContactService contactService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshContacts();
        contactService.addObserver(this);
        contactListView.getSelectionModel().selectedItemProperty().addListener(new ContactSelectChangeListener());
    }

    @FXML
    protected void addContactClickHandler() throws IOException {
        showAddContactPane();
    }

    private void showShowContactPane(Contact contact) throws IOException {
        SubPaneLoader subPaneLoader = javax.enterprise.inject.spi.CDI.current().select(SubPaneLoader.class).get();
        Node showContactPane = subPaneLoader.createShowContact(contact);

        contentPane.getChildren().clear();
        contentPane.getChildren().add(showContactPane);

        fillOutContentPane(showContactPane);
    }

    private void showAddContactPane() throws IOException {
        SubPaneLoader subPaneLoader = javax.enterprise.inject.spi.CDI.current().select(SubPaneLoader.class).get();
        Node addContactPane = subPaneLoader.createAddContact();

        contentPane.getChildren().clear();
        contentPane.getChildren().add(addContactPane);

        fillOutContentPane(addContactPane);
    }

    private void fillOutContentPane(Node node) {
        AnchorPane.setTopAnchor(node, 5.0);
        AnchorPane.setBottomAnchor(node, 5.0);
        AnchorPane.setLeftAnchor(node, 5.0);
        AnchorPane.setRightAnchor(node, 5.0);
    }

    private void refreshContacts() {
        ObservableList<Contact> observableList = FXCollections.observableList(contactService.getContacts());
        contactListView.setItems(observableList);
        contactListView.setCellFactory(new ContactCellFactoryCallback());
    }

    @Override
    public void update(Observable o, Object arg) {
        refreshContacts();
    }

    private class ContactSelectChangeListener implements ChangeListener<Contact> {
        @Override
        public void changed(ObservableValue<? extends Contact> observableValue, Contact previousContact, Contact selectedContact) {
            try {
                showShowContactPane(selectedContact);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}