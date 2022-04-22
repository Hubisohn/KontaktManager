package com.example.observlist;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {

    ArrayList<Person> arrayList = new ArrayList<>();
    Stage sendStage;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        HelloController.setMainController(this);
        sendStage = stage;
        stage.setTitle("Lista de Personas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public Stage getStage() {
        return sendStage;
    }

    public ArrayList<Person> getArrayList() {
        return arrayList;
    }

}