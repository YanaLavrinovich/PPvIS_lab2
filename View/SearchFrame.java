package View;

import Controller.Controller;
import Model.Student;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

public class SearchFrame {
    private MainFrame mainFrame;
    private Controller controller;
    private TabPane tp;
    private Form groupForm;
    private Form typeForm;
    private Widget widget;
    private Form numberForm;
    private Button groupButton;
    private Button typeButton;
    private Button numberButton;


    public SearchFrame(MainFrame mainFrame, Controller controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
    }

    public void start() {
        widget = new Widget();
        Group root = new Group();
        setTabPane();
        root.getChildren().addAll(getTabPane());
        setForm();
        setActionOnButtons();
        Scene scene = new Scene(root, 1200, 680);
        Stage stage = new Stage();
        stage.setTitle("Search students");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void setActionOnButtons() {
        groupButton.setOnAction(e -> {
            List<Student> resultOfSearch = controller.searchStudents(widget.getStudentGroup(), "Group");
            groupForm.setList(resultOfSearch);
            groupForm.getStudentTable().setItems(FXCollections.observableArrayList(resultOfSearch));
            alertMessage(resultOfSearch);
        });

        typeButton.setOnAction(e -> {
            List<Student> resultOfSearch = controller.searchStudents(widget.getStudentType(), "Type");
            typeForm.setList(resultOfSearch);
            typeForm.getStudentTable().setItems(FXCollections.observableArrayList(resultOfSearch));
            alertMessage(resultOfSearch);
        });

        numberButton.setOnAction(e -> {
            List<Student> resultOfSearch = controller.searchStudents(widget.getStudentNumber(), "Number");
            numberForm.setList(resultOfSearch);
            numberForm.getStudentTable().setItems(FXCollections.observableArrayList(resultOfSearch));
            alertMessage(resultOfSearch);
        });
    }

    public void alertMessage(List<Student> list) {
        if (list.size() != 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(list.size() + " searched students");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No such students");
            alert.showAndWait();
        }
    }

    public void setTabPane() {
        tp = new TabPane();
        tp.setLayoutX(3);
        tp.setLayoutY(7);

        //search Surname and group
        Tab tabGroup = new Tab("Search Surname and Group");
        tabGroup.setContent(widget.getRootGroup());
        groupButton = new Button("Search");
        widget.getRootGroup().getChildren().addAll(groupButton);

        //search surname and type
        Tab tabType = new Tab("Search Surname and Type of Social work");
        tabType.setContent(widget.getRootType());
        typeButton = new Button("Search");
        widget.getRootType().getChildren().addAll(typeButton);

        //search surname and number
        Tab tabNumber = new Tab("Search Surname and Number of Social work");
        tabNumber.setContent(widget.getRootNumber());
        numberButton = new Button("Search");
        widget.getRootNumber().getChildren().addAll(numberButton);

        tp.getTabs().addAll(tabGroup, tabType, tabNumber);
    }

    public void setForm() {
        groupForm = new Form();
        widget.getRootGroup().getChildren().add(groupForm.getPaneBox());

        typeForm = new Form();
        widget.getRootType().getChildren().add(typeForm.getPaneBox());

        numberForm = new Form();
        widget.getRootNumber().getChildren().add(numberForm.getPaneBox());
    }

    public TabPane getTabPane() {
        return tp;
    }


}
