package Controller;

import Model.Student;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentWrapper {

    private StringProperty surname;
    private StringProperty name;
    private StringProperty fatherName;
    private IntegerProperty group;
    private Student student;

    public StudentWrapper(Student s) {
        this.student = s;
        this.surname = new SimpleStringProperty(s.getSurname());
        this.name = new SimpleStringProperty(s.getName());
        this.fatherName = new SimpleStringProperty(s.getFatherName());
        this.group = new SimpleIntegerProperty(s.getGroup());
    }

    public String getSurname() {
        return surname.get();
    }

    public String getName() {
        return name.get();
    }

    public String getFatherName() {
        return fatherName.get();
    }

    public Integer getGroup() {
        return group.get();
    }

    public Student getStudent() {
        return student;
    }
}
