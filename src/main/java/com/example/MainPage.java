/* Program By Adam Cornfield */

package com.example;

import javafx.event.*;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.*;
import java.util.Scanner;

public class MainPage {
    @FXML private TextField userName;
    @FXML private TextField passwordField;
    @FXML private Button loginButton;
    @FXML private Button registerButton;

    
    private File file = new File("src\\main\\resources\\test.txt");

    public void initialize() {        
        loginButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override public void handle(ActionEvent event) {
                String user = userName.getText();
                String passw = passwordField.getText();

                try {
                    Scanner myReader = new Scanner(file);
                    
                    while (myReader.hasNextLine()) {
                        String data = myReader.nextLine();
                        String storedName = data.split("\\~")[0];
                        String storedPassw = App.ceaserDecrypt(data.split("\\~")[1], 5);
                        String perms = data.split("\\~")[2];

                        if (storedName.equals(user) && storedPassw.equals(passw)) {
                            if (perms.equals("ADMIN")) {
                                try {
                                    switchToAdmin();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else if (perms.equals("USER")) {
                                try {
                                    switchToUser();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                System.out.println("Error perms not found");
                            }
                        } else {
                        }
                    }
                    myReader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error has occured");
                    e.printStackTrace();
                }
            }
        });  

        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                try {
                    switchToRegister();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void switchToAdmin() throws IOException {
        App.setRoot("adminPage");
    }

    private void switchToUser() throws IOException {
        App.setRoot("userPage");
    }

    private void switchToRegister() throws IOException {
        App.setRoot("registerPage");
    }

    @FXML
    private void switchToTest(ActionEvent event) throws IOException {
        System.out.println("Test");
        System.out.println(event);
    }
}
