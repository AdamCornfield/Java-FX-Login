package com.example;

import javafx.event.*;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.*;
import java.util.Scanner;

public class registerPage {
    @FXML private Button cancelButton;
    @FXML private Button createButton;
    @FXML private TextField userName;
    @FXML private TextField passwordField;
    @FXML private TextField permsField;

    public void initialize() throws IOException {
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                String user = userName.getText();
                String passw = App.ceaserEncrypt(passwordField.getText(), 5);
                String perms = permsField.getText();

                try {
                    App.appendToFile("src\\main\\resources\\test.txt", user + "~" + passw + "~" + perms);
                    switchToMain();
                } catch (IOException e) {
                    System.out.println("An error has occured");
                    e.printStackTrace();
                }
            }
        });

        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                try {
                    switchToMain();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void switchToMain() throws IOException {
        App.setRoot("mainPage");
    }
}
