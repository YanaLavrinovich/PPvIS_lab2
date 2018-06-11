package View;

import Controller.Controller;
import Model.Student;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;

public class DeleteFrame {
    private MainFrame mainFrame;
    private Controller controller;
    private TabPane tp;
    private Button groupButton;
    private Button typeButton;
    private Button numberButton;
    private Widget widget;


    public DeleteFrame(MainFrame mainFrame, Controller controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
    }

    public void start() {
        widget = new Widget();
        Group root = new Group();
        setTabPane();
        root.getChildren().addAll(tp);
        setActionOnButtons();
        Scene scene = new Scene(root, 670, 200);
        Stage stage = new Stage();
        stage.setTitle("Delete students");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void setTabPane() {
        tp = new TabPane();
        tp.setLayoutX(3);
        tp.setLayoutY(7);

        //search Surname and group
        Tab tabGroup = new Tab("Delete Surname and Group");
        tabGroup.setContent(widget.getRootGroup());
        groupButton = new Button("Delete");
        widget.getRootGroup().getChildren().addAll(groupButton);

        //search surname and type
        Tab tabType = new Tab("Delete Surname and Type of Social work");
        tabType.setContent(widget.getRootType());

        typeButton = new Button("Delete");
        widget.getRootType().getChildren().addAll(typeButton);

        //search surname and number
        Tab tabNumber = new Tab("Delete Surname and Number of Social work");
        tabNumber.setContent(widget.getRootNumber());

        numberButton = new Button("Delete");
        widget.getRootNumber().getChildren().addAll(numberButton);

        tp.getTabs().addAll(tabGroup, tabType, tabNumber);
    }

    public void setActionOnButtons() {
        groupButton.setOnAction(e -> {
            List<Student> resultOfSearch = controller.searchStudents(widget.getStudentGroup(), "Group");
            controller.removeStudent(resultOfSearch);
            mainFrame.update();
            alertMessage(resultOfSearch);
        });

        typeButton.setOnAction(e -> {
            List<Student> resultOfSearch = controller.searchStudents(widget.getStudentType(), "Type");
            controller.removeStudent(resultOfSearch);
            mainFrame.update();
            alertMessage(resultOfSearch);
        });

        numberButton.setOnAction(e -> {
            List<Student> resultOfSearch = controller.searchStudents(widget.getStudentNumber(), "Number");
            controller.getStudentBase().removeAll(resultOfSearch);
            mainFrame.update();
            alertMessage(resultOfSearch);
        });
    }

    public void alertMessage(List<Student> list) {
        if (list.size() != 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(list.size() + " deleted students");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No such students");
            alert.showAndWait();
        }
    }

}


