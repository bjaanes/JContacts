package com.gjermundbjaanes.controllers;

import com.gjermundbjaanes.data.Contact;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ContactCellFactoryCallback implements Callback<ListView<Contact>, ListCell<Contact>> {
    @Override
    public ListCell<Contact> call(ListView<Contact> contactListView) {
        return new ContactListCell();
    }

}