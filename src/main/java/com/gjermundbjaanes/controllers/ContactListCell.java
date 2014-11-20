package com.gjermundbjaanes.controllers;

import com.gjermundbjaanes.data.Contact;
import javafx.scene.control.ListCell;

public class ContactListCell extends ListCell<Contact> {
    @Override
    protected void updateItem(Contact contact, boolean bln) {
        super.updateItem(contact, bln);
        if (contact != null) {
            setText(contact.getFirstname() + " " + contact.getLastname());
        }
    }
}
