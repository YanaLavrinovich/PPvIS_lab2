package Controller;

import Model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;


public class Forms {

    private ObservableList<StudentWrapper> allStudents
            = FXCollections.observableArrayList();

    private TableView<StudentWrapper> studentTable;
    public Pane createStudentBox() {
        studentTable = new TableView<>();
        TableColumn studentName = new TableColumn("SNF");
        studentName.setCellValueFactory(new PropertyValueFactory<StudentWrapper, String>("surname, name, fatherName"));
        studentName.setMinWidth(200);
        TableColumn studentGroup = new TableColumn("Group");
        studentGroup.setCellValueFactory(new PropertyValueFactory<StudentWrapper, Integer>("group"));
        studentGroup.setMinWidth(200);

        studentTable.setItems(allStudents);
        studentTable.getColumns().addAll(studentName,studentGroup);
    }
}
