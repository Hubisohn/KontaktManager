package com.example.observlist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class EditMenu implements Initializable {

    private static HelloController helloController;
    private Person person;
    @FXML
    private BorderPane borderPane;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField nameField;

    private static ObservableList<Person> list = null;
    int index;

    @FXML
    protected void setMainController(HelloController helloController) {
        EditMenu.helloController = helloController;
        list = HelloController.getList();
        index = helloController.getIndex();
        person = list.get(index);
        nameField.setText(person.getVorname());
        lastnameField.setText(person.getNachname());
        ageField.setText(String.valueOf(person.getAlter()));
    }

    @FXML
    protected void cancleEvent(){
        helloController.closeEditStage();
    }

    @FXML
    protected void onSaveClick(){
        if(nameField.getText().isEmpty() || lastnameField.getText().isEmpty() || ageField.getText().isEmpty()){
            System.out.println("Enter a Text!");
        }else{
            person.setVorname(nameField.getText());
            person.setNachname(lastnameField.getText());
            person.setAlter(Integer.parseInt(ageField.getText()));
            list.set(index, person);
            helloController.closeEditStage();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        borderPane.addEventHandler(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.ENTER) {
                onSaveClick();
            }
        });
        borderPane.addEventHandler(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.ESCAPE) {
                cancleEvent();
            }
        });
    }
}
