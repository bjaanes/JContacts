package com.gjermundbjaanes.controllers;

import com.gjermundbjaanes.JContactException;
import com.gjermundbjaanes.data.Address;
import com.gjermundbjaanes.services.ContactService;
import com.gjermundbjaanes.data.Contact;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import javax.inject.Inject;

public class AddContactController {
    @Inject private ContactService contactService;

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField streetAddressField;
    @FXML private TextField postalCodeField;
    @FXML private TextField cityField;
    @FXML private TextField countryField;

    @FXML protected void addContact() {
        Contact contact = new Contact();
        contact.setFirstname(firstNameField.getText());
        contact.setLastname(lastNameField.getText());

        Address address = setUpAddress();

        contact.setAddress(address);
        try {
            contactService.addContact(contact);
        } catch (JContactException e) {
            // TODO: HANDLE THIS
            e.printStackTrace();
        }

        resetFields();
    }

    private Address setUpAddress() {
        Address address = new Address();
        address.setStreet(streetAddressField.getText());
        address.setPostalCode(postalCodeField.getText());
        address.setCity(cityField.getText());
        address.setCountry(countryField.getText());
        return address;
    }

    private void resetFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        streetAddressField.setText("");
        postalCodeField.setText("");
        cityField.setText("");
        countryField.setText("");
    }
}
