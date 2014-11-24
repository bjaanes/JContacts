package com.gjermundbjaanes.controllers;

import com.gjermundbjaanes.data.Address;
import com.gjermundbjaanes.data.Contact;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ShowContactController {
    Contact contact;

    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label streetAddressLabel;
    @FXML private Label postalCodeLabel;
    @FXML private Label cityLabel;
    @FXML private Label countryLabel;

    public void setContact(Contact contact) {
        this.contact = contact;
        fillContactInfo();
    }

    private void fillContactInfo() {
        firstNameLabel.setText(contact.getFirstname());
        lastNameLabel.setText(contact.getLastname());
        fillAddress();
    }

    private void fillAddress() {
        Address address = contact.getAddress();
        streetAddressLabel.setText(address.getStreet());
        postalCodeLabel.setText(address.getPostalCode());
        cityLabel.setText(address.getCity());
        countryLabel.setText(address.getCountry());
    }
}
