package View;

import Controller.Controller;
import Model.SocialWork;
import Model.Student;
import Model.StudentBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SearchFrame {
    private MainFrame mainFrame;
    private Student searchStudent = new Student();
    private Controller controller;
    private TabPane tp;
    private List<Button> buttons = new ArrayList<>();
    private TextField surnameFieldGroup;
    private TextField groupField;
    private Form groupForm;
    private TextField surnameFieldType;
    private TextField typeField;
    private Form typeForm;
    private TextField surnameFieldNumber;
    private TextField numberTypeField;
    private TextField numberMinField;
    private TextField numberMaxField;
    private Form numberForm;
    private VBox rootGroup;
    private VBox rootType;
    private VBox rootNumber;

    public SearchFrame(MainFrame mainFrame, Controller controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;
    }

    public void start () {
        Group root = new Group();
        setTabPane("Search");
        root.getChildren().addAll(getTabPane());
        setForm();
        setActionOnButtons();
        Scene scene = new Scene(root, 1200, 680);
        Stage stage = new Stage();
        stage.setTitle("Search students");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void setActionOnButtons () {
        buttons.get(0).setOnAction(e -> {
            searchStudent.setSurname(surnameFieldGroup.getText());
            searchStudent.setGroup(groupField.getText());
            List<Student> resultOfSearch = controller.searchStudents(searchStudent, "Group");
            groupForm.setList(resultOfSearch);
            groupForm.getStudentTable().setItems(FXCollections.observableArrayList(resultOfSearch));
            searchStudent.clean();
            alertMessage(resultOfSearch, " searched");
        });

        buttons.get(1).setOnAction(e -> {
            searchStudent.setSurname(surnameFieldType.getText());
            searchStudent.addWorkInSemestr(new SocialWork(typeField.getText(), 0, 1));
            List<Student> resultOfSearch = controller.searchStudents(searchStudent, "Type");
            typeForm.setList(resultOfSearch);
            typeForm.getStudentTable().setItems(FXCollections.observableArrayList(resultOfSearch));
            searchStudent.clean();
            alertMessage(resultOfSearch, " searched");
        });

        buttons.get(2).setOnAction(e -> {
            searchStudent.setSurname(surnameFieldNumber.getText());
            searchStudent.addWorkInSemestr(new SocialWork(numberTypeField.getText(), Integer.valueOf(numberMinField.getText()), 1));
            searchStudent.addWorkInSemestr(new SocialWork(numberTypeField.getText(), Integer.valueOf(numberMaxField.getText()), 2));
            List<Student> resultOfSearch = controller.searchStudents(searchStudent, "Number");
            numberForm.setList(resultOfSearch);
            numberForm.getStudentTable().setItems(FXCollections.observableArrayList(resultOfSearch));
            searchStudent.clean();
            alertMessage(resultOfSearch, " searched");
        });
    }

    public void alertMessage(List<Student> list, String str) {
            if (list.size() != 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(list.size() + str + " students");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("No such students");
            alert.showAndWait();
        }
    }

    public void setTabPane(String search) {
        tp = new TabPane();
        tp.setLayoutX(3);
        tp.setLayoutY(7);

        //search Surname and group
        Tab tabGroup = new Tab(search +" Surname and Group");
        rootGroup = new VBox();
        tabGroup.setContent(rootGroup);
        GridPane groupBox = new GridPane();
        groupBox.setPadding(new Insets(15));
        groupBox.setVgap(15);
        groupBox.setHgap(15);

        Label surnameLabelGroup = new Label("Surname  ");
        surnameFieldGroup = new TextField();
        surnameFieldGroup.setPromptText("Surname");
        groupBox.add(surnameLabelGroup, 0, 0);
        groupBox.add(surnameFieldGroup, 1, 0);

        Label groupLabel = new Label("Group  ");
        groupField = new TextField();
        groupField.setPromptText("Group");
        groupBox.add(groupLabel, 0, 1);
        groupBox.add(groupField, 1, 1);


        Button groupButton = new Button(search);
        groupBox.add(groupButton, 2, 0);

        rootGroup.getChildren().addAll(groupBox);


        //search surname and type
        Tab tabType = new Tab(search + " Surname and Type of Social work");
        rootType = new VBox();
        GridPane typeBox = new GridPane();
        tabType.setContent(rootType);
        typeBox.setPadding(new Insets(15));
        typeBox.setVgap(15);
        typeBox.setHgap(15);

        Label surnameLabelType = new Label("Surname  ");
        surnameFieldType = new TextField();
        surnameFieldType.setPromptText("Surname");
        typeBox.add(surnameLabelType, 0, 0);
        typeBox.add(surnameFieldType, 1, 0);

        Label typeLabel = new Label("Type of social work  ");
        typeField = new TextField();
        typeField.setPromptText("Type of social work");
        typeBox.add(typeLabel, 0, 1);
        typeBox.add(typeField, 1, 1);



        Button typeButton = new Button(search);

        typeBox.add(typeButton, 2, 0);

        rootType.getChildren().addAll(typeBox);


        //search surname and number
        Tab tabNumber = new Tab(search + " Surname and Number of Social work");
        rootNumber = new VBox();
        GridPane numberBox = new GridPane();
        tabNumber.setContent(rootNumber);
        numberBox.setPadding(new Insets(15));
        numberBox.setVgap(15);
        numberBox.setHgap(15);

        Label surnameLabelNumber = new Label("Surname  ");
        surnameFieldNumber = new TextField();
        surnameFieldNumber.setPromptText("Surname");
        numberBox.add(surnameLabelNumber, 0, 0);
        numberBox.add(surnameFieldNumber, 1, 0);

        Label numberTypeLabel = new Label("Type of social work  ");
        numberTypeField = new TextField();
        numberTypeField.setPromptText("Type of social work");
        numberBox.add(numberTypeLabel, 0, 1);
        numberBox.add(numberTypeField, 1, 1);

        Label numberLabel = new Label("Number of social work  ");
        numberMinField = new TextField();
        numberMinField.setPromptText("Min number of social work");
        numberBox.add(numberLabel, 0, 2);
        numberBox.add(numberMinField, 1, 2);

        numberMaxField = new TextField();
        numberMaxField.setPromptText("Max number of social work");
        numberBox.add(numberMaxField, 2, 2);



        Button numberButton = new Button(search);

        numberBox.add(numberButton, 2, 0);

        rootNumber.getChildren().addAll(numberBox);

        buttons.add(groupButton);
        buttons.add(typeButton);
        buttons.add(numberButton);
        tp.getTabs().addAll(tabGroup, tabType, tabNumber);
    }

    public void setForm () {
        groupForm = new Form();
        rootGroup.getChildren().add(groupForm.getPaneBox());

        typeForm = new Form();
        rootType.getChildren().add(typeForm.getPaneBox());

        numberForm = new Form();
        rootNumber.getChildren().add(numberForm.getPaneBox());
    }

    public TabPane getTabPane() {
        return tp;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public Student getSearchStudent() {
        return searchStudent;
    }

    public Controller getController() {
        return controller;
    }

    public Form getGroupForm() {
        return groupForm;
    }

    public TextField getSurnameFieldGroup() {
        return surnameFieldGroup;
    }

    public Form getNumberForm() {
        return numberForm;
    }

    public Form getTypeForm() {
        return typeForm;
    }

    public TextField getGroupField() {
        return groupField;
    }

    public TextField getNumberMaxField() {
        return numberMaxField;
    }

    public TextField getNumberMinField() {
        return numberMinField;
    }

    public TextField getNumberTypeField() {
        return numberTypeField;
    }

    public TextField getSurnameFieldNumber() {
        return surnameFieldNumber;
    }

    public TextField getSurnameFieldType() {
        return surnameFieldType;
    }

    public TextField getTypeField() {
        return typeField;
    }
}
