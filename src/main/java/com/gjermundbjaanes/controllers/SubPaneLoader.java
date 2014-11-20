package com.gjermundbjaanes.controllers;

import com.gjermundbjaanes.cdi.AppQualified;
import com.gjermundbjaanes.fx.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;

public class SubPaneLoader {
    @Inject @AppQualified
    FXMLLoader fxmlLoader;

    public Node createAddContact() throws IOException {
        InputStream fxml = Main.class.getResourceAsStream("addContact.fxml");
        return (Node)fxmlLoader.load(fxml);
    }
}
