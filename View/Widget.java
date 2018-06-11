package View;

import Model.SocialWork;
import Model.Student;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class Widget {
    private TextField surnameFieldGroup;
    private TextField groupField;
    private TextField surnameFieldType;
    private TextField typeField;
    private Student searchStudent = new Student();
    private TextField surnameFieldNumber;
    private TextField numberTypeField;
    private TextField numberMinField;
    private TextField numberMaxField;
    private VBox rootGroup;
    private VBox rootType;
    private VBox rootNumber;

    public Widget() {
        rootGroup = new VBox();
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

        rootGroup.getChildren().addAll(groupBox);

/////////////////////////////////////////////
        rootType = new VBox();
        GridPane typeBox = new GridPane();

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

        rootType.getChildren().addAll(typeBox);

        ////////////////////////////////////////
        rootNumber = new VBox();
        GridPane numberBox = new GridPane();
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

        rootNumber.getChildren().addAll(numberBox);

    }

    public Student getStudentGroup() {
        searchStudent.clean();
        searchStudent.setSurname(surnameFieldGroup.getText());
        searchStudent.setGroup(groupField.getText());
        return searchStudent;
    }

    public Student getStudentType() {
        searchStudent.clean();
        searchStudent.setSurname(surnameFieldType.getText());
        searchStudent.addWorkInSemestr(new SocialWork(typeField.getText(), 0, 1));
        return searchStudent;
    }

    public Student getStudentNumber() {
        searchStudent.clean();
        searchStudent.setSurname(surnameFieldNumber.getText());
        searchStudent.addWorkInSemestr(new SocialWork(numberTypeField.getText(), Integer.valueOf
                (numberMinField.getText()), 1));
        searchStudent.addWorkInSemestr(new SocialWork(numberTypeField.getText(), Integer.valueOf
                (numberMaxField.getText()), 2));
        return searchStudent;
    }

    public VBox getRootGroup() {
        return rootGroup;
    }

    public VBox getRootNumber() {
        return rootNumber;
    }

    public VBox getRootType() {
        return rootType;
    }

    public TextField getSurnameFieldGroup() {
        return surnameFieldGroup;
    }

    public TextField getGroupField() {
        return groupField;
    }

    public TextField getSurnameFieldType() {
        return surnameFieldType;
    }

    public TextField getTypeField() {
        return typeField;
    }

    public TextField getSurnameFieldNumber() {
        return surnameFieldNumber;
    }

    public TextField getNumberTypeField() {
        return numberTypeField;
    }

    public TextField getNumberMinField() {
        return numberMinField;
    }

    public TextField getNumberMaxField() {
        return numberMaxField;
    }


}
