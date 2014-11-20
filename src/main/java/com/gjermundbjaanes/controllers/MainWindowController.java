package com.gjermundbjaanes.controllers;

import com.gjermundbjaanes.data.Contact;
import com.gjermundbjaanes.services.ContactService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable, Observer {
    @FXML private ListView<Contact> contactListView;
    @FXML private Pane contentPane;
    @Inject private ContactService contactService;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactService.addObserver(this);
        refreshContacts();
    }

    @FXML
    protected void addContactClickHandler() throws IOException {
        showAddContactPane();
    }

    private void showAddContactPane() throws IOException {
        SubPaneLoader subPaneLoader = javax.enterprise.inject.spi.CDI.current().select(SubPaneLoader.class).get();
        contentPane.getChildren().clear();
        contentPane.getChildren().add(subPaneLoader.createAddContact());
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

    private static class ContactCellFactoryCallback implements Callback<ListView<Contact>, ListCell<Contact>> {
        @Override
        public ListCell<Contact> call(ListView<Contact> contactListView) {
            return new ContactListCell();
        }

    }
}