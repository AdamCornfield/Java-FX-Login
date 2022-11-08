package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.*;
import java.util.Scanner;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("MainPage"), 640, 480);
        scene.getStylesheets().add("App.css");
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static String ceaserEncrypt(String inputText, int shift) {
        String encryptedMessage = "";

        for (int i = 0; i < inputText.length(); i++) {
            char textVal = inputText.charAt(i);
            char shiftedVal;

            if ((textVal + shift) >= 126) {
                textVal = (char) (((textVal + shift) - 126) + 33);

                shiftedVal = (char)(textVal);
            } else {
                shiftedVal = (char)(textVal + shift);
            }

            encryptedMessage = encryptedMessage + shiftedVal;
        }

        
        return encryptedMessage;
    }

    public static String ceaserDecrypt(String inputText, int shift) {
        String Message = "";

        for (int i = 0; i < inputText.length(); i++) {
            char textVal = inputText.charAt(i);
            char shiftedVal;

            if ((textVal - shift) <= 32) {
                textVal = (char)(126 - (32 - (textVal - shift)));

                shiftedVal = (char)(textVal);
            } else {
                shiftedVal = (char)(textVal - shift);
            }

            Message = Message + shiftedVal;
        }
        
        return Message;
    }

    public static void appendToFile(String path, String data) throws IOException {
        File file = new File(path);
        Scanner myReader = new Scanner(file);
        String fileData = "";
            
        while (myReader.hasNextLine()) {
            fileData = fileData + myReader.nextLine() + "\n";
        }
        myReader.close();

        FileWriter fw = new FileWriter(path);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.append(fileData + data);
        bw.newLine();
        bw.close();
    }

    public static void main(String[] args) {

        launch();
    }

}