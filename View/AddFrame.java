package View;

import Controller.Controller;
import Model.SocialWork;
import Model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AddFrame {
    private MainFrame mainFrame;
    private ObservableList<Integer> semestrs = FXCollections.observableArrayList();
    private Controller controller;
    private List<SocialWork> works = new ArrayList<>();

    public AddFrame(MainFrame mainFrame, Controller controller) {
        this.mainFrame = mainFrame;
        this.controller = controller;


        int numberSemestr = 10;

        for (int i = 0; i < numberSemestr; i++) {
            works.add(new SocialWork());
            int sem = i;
            semestrs.add(++sem);
        }

        GridPane pane=new GridPane();
        pane.setPadding(new Insets(15));
        pane.setVgap(15);
        pane.setHgap(15);

        pane.add(new Label("Student"),0,0);

        pane.add(new Label("Surname"),0,1);
        pane.add(new Label("Name"),0,2);
        pane.add(new Label("Fathername"),0,3);
        pane.add(new Label("Group"), 0,4);

        TextField surnameField = new TextField();
        surnameField.setPromptText("Surname");
        pane.add(surnameField,1,1);

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        pane.add(nameField,1,2);

        TextField fatherNameField = new TextField();
        fatherNameField.setPromptText("Fathername");
        pane.add(fatherNameField,1,3);

        TextField groupField = new TextField();
        groupField.setPromptText("Group");
        pane.add(groupField,1,4);

        Button addStudentButton=new Button(" Add student");
        addStudentButton.setOnAction(e -> {
            Student student = new Student();
            student.setFullName(surnameField.getText(),nameField.getText(),fatherNameField.getText());

            student.setGroup(groupField.getText());

            student.setSocialWorkBase(works);
            //создать объект модели на основе введенных данных -> create Student
            // проверка даннных
            // контроллер добавить объект (студента)
            controller.addStudent(student);
            // главное окно - обновить данные

            //контроллер(addStudent)
            //mainFrame.addStudent(student);
           // mainFrame.update();

            surnameField.clear();
            nameField.clear();
            fatherNameField.clear();
            groupField.clear();

            works = new ArrayList<>();
            for (int i = 0; i < numberSemestr; i++) {
                works.add(new SocialWork());
            }

            mainFrame.update();
        });


        pane.add(addStudentButton,2,0);

        pane.add(new Label("Social work"),0,5);
        pane.add(new Label("Type"),0,6);
        pane.add(new Label("Number"),0,7);

        TextField typeWorkField = new TextField();
        typeWorkField.setPromptText("Type");
        pane.add(typeWorkField,1,6);

        TextField numberWorkField = new TextField();
        numberWorkField.setPromptText("Number");
        pane.add(numberWorkField,1,7);

        pane.add(new Label("Semestr"),0,8);
        ChoiceBox<Integer> semestrsBox = new ChoiceBox<>(semestrs);
        pane.add(semestrsBox,1,8);
        semestrsBox.getSelectionModel().selectFirst();

        Button addSocialWorkButton=new Button(" Add work");
        addSocialWorkButton.setOnAction((ActionEvent e) -> {

            try {
                if (works.get((semestrsBox.getValue()) - 1).getTypeWork().equals("-")) {
                    works.add((semestrsBox.getValue()) - 1, new SocialWork(typeWorkField.getText(),
                            Integer.parseInt(numberWorkField.getText()),semestrsBox.getValue()));
                }
                else {
                    alertMessage("This semester has already filled!" +
                            "Please, Choose other semestr.");
                }
            }
            catch (Exception ex) {
                    alertMessage("Please, Enter number.");
            }
            typeWorkField.clear();
            numberWorkField.clear();
        });
        pane.add(addSocialWorkButton,2,5);

        Scene scene = new Scene(pane);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Add new student");

        stage.show();
    }

    public void alertMessage(String text) {
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setContentText(text);
        alert.showAndWait();
    }

}