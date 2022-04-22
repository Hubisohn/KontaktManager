package com.example.observlist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private TableView<Person> tableView;
    @FXML
    private Button addUser;
    @FXML
    private Button removeUser;
    static HelloApplication helloApplication;
    @FXML
    private static ObservableList<Person> list = null;

    Stage editStage;
    EditMenu editMenu = null;

    int index;
    Person person;

    @FXML
    protected void onAddUserClick() throws IOException {
    	list.add(new Person("Name", "Nachname", 0));
        tableView.setItems(list);
        index = list.size() - 1;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("edit-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        editMenu = fxmlLoader.getController();
        editMenu.setMainController(this);
        editStage = new Stage();
        editStage.setScene(scene);
        editStage.setTitle("Edit User");
        editStage.show();

    }

    @FXML
    protected void onRemoveUserClick() {
        list.removeAll(tableView.getSelectionModel().getSelectedItems());
        tableView.setItems(list);
    }

    @FXML
    protected static void setMainController(HelloApplication helloApplication) {
        HelloController.helloApplication = helloApplication;
        System.out.println(helloApplication.arrayList);
        list = FXCollections.observableArrayList(helloApplication.getArrayList());
    }

    protected static ObservableList<Person> getList() {
        return list;
    }
    protected int getIndex() {
        return index;
    }

    protected void closeEditStage() {
        editStage.close();
    }

    @FXML
    protected void onSaveFileClick(){
        File file = new File("Personenliste.txt");
        try {
            if(file.createNewFile() == false) {
                file.delete();
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            int counter = 0;
            while (counter != list.size()) {
                fileWriter.write(list.get(counter).getVorname() + "-" + list.get(counter).getNachname() + "-" + list.get(counter).getAlter() + "\n" +
                        "");
                counter++;
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onLoadFileClick(){
        FileChooser fileChooser = new FileChooser();
        Stage fileStage = new Stage();
        File selectedFile = fileChooser.showOpenDialog(fileStage);
        File file = new File(selectedFile.getAbsolutePath());

        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        list.clear();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            list.add(new Person(line.split("-")[0], line.split("-")[1], Integer.parseInt(line.split("-")[2])));
            tableView.setItems(list);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        tableView.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            if(event.getClickCount() == 2) {
                index = tableView.getSelectionModel().getSelectedIndex();

                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("edit-menu.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    editMenu = fxmlLoader.getController();
                    editMenu.setMainController(this);
                    editStage = new Stage();
                    editStage.setScene(scene);
                    editStage.setTitle("Edit User");
                    editStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        tableView.addEventHandler(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.DELETE) {
                list.removeAll(tableView.getSelectionModel().getSelectedItems());
            }
        });

        tableView.addEventHandler(javafx.scene.input.KeyEvent.KEY_PRESSED, event -> {
            if(event.getCode() == KeyCode.INSERT) {
                try {
                    onAddUserClick();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}