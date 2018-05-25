package View;

import Controller.Controller;
import Model.SocialWork;
import Model.Student;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class DeleteFrame extends SearchFrame {

    public DeleteFrame (MainFrame mainFrame, Controller controller){
        super(mainFrame, controller);
    }

    @Override
    public void start () {
        Group root = new Group();
        setTabPane("Delete");
        root.getChildren().addAll(getTabPane());
        setActionOnButtons();
        Scene scene = new Scene(root, 670, 200);
        Stage stage = new Stage();
        stage.setTitle("Delete students");
        stage.setScene(scene);
        stage.showAndWait();
    }

    @Override
    public void setActionOnButtons() {
        getButtons().get(0).setOnAction(e -> {
            getSearchStudent().setSurname(getSurnameFieldGroup().getText());
            getSearchStudent().setGroup(getGroupField().getText());
            List<Student> resultOfSearch = getController().searchStudents(getSearchStudent(), "Group");
            getController().getStudentBase().removeAll(resultOfSearch);
            getMainFrame().update();
            getSearchStudent().clean();
            alertMessage(resultOfSearch, " deleted");
        });

        getButtons().get(1).setOnAction(e -> {
            getSearchStudent().setSurname(getSurnameFieldType().getText());
            getSearchStudent().addWorkInSemestr(new SocialWork(getTypeField().getText(), 0, 1));
            List<Student> resultOfSearch = getController().searchStudents(getSearchStudent(), "Type");
            getController().getStudentBase().removeAll(resultOfSearch);
            getMainFrame().update();
            getSearchStudent().clean();
            alertMessage(resultOfSearch, " deleted");
        });

        getButtons().get(2).setOnAction(e -> {
            getSearchStudent().setSurname(getSurnameFieldNumber().getText());
            getSearchStudent().addWorkInSemestr(new SocialWork(getNumberTypeField().getText(), Integer.valueOf(getNumberMinField().getText()), 1));
            getSearchStudent().addWorkInSemestr(new SocialWork(getNumberTypeField().getText(), Integer.valueOf(getNumberMaxField().getText()), 2));
            List<Student> resultOfSearch = getController().searchStudents(getSearchStudent(), "Number");
            getController().getStudentBase().removeAll(resultOfSearch);
            getMainFrame().update();
            getSearchStudent().clean();
            alertMessage(resultOfSearch, " deleted");
        });
    }
}


