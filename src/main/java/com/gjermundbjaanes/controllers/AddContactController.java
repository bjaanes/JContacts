package com.gjermundbjaanes.controllers;

import com.gjermundbjaanes.services.ContactService;
import com.gjermundbjaanes.data.Contact;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.inject.Inject;

public class AddContactController {
    @Inject private ContactService contactService;

    @FXML private TextField firstNameField;
    @FXML private TextField lastName;

    @FXML protected void addContact() {
        Contact contact = new Contact(firstNameField.getText(), lastName.getText());
        contactService.addContact(contact);
    }
}
