package com.gjermundbjaanes.controllers;

import com.gjermundbjaanes.data.Contact;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ShowContactController {
    Contact contact;

    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;

    public void setContact(Contact contact) {
        this.contact = contact;
        fillContactInfo();
    }

    private void fillContactInfo() {
        firstNameLabel.setText(contact.getFirstname());
        lastNameLabel.setText(contact.getLastname());
    }
}
