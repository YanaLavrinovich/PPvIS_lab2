package Controller;

import Model.Student;
import Model.StudentBase;
import View.MainFrame;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private MainFrame mainFrame;
    private StudentBase studentBase;

    public Controller() {
        studentBase = new StudentBase(this);
        mainFrame = new MainFrame(this);

    }

    public void addStudent(Student student) {
        studentBase.add(student);
    }

    public void setStudentBase(StudentBase studentBase) {
        this.studentBase = studentBase;
    }

    public List<Student> getStudentBase() {
        return studentBase.getStudentsBase();
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setFile(File file){
        studentBase.setFile(file);
    }

    public void removeStudent(List<Student> stud) {
        getStudentBase().removeAll(stud);
    }
    public File getFile(){
        return studentBase.getFile();
    }

    public void toFile(File file){
        studentBase.toFile(file);
    }

    public void fromFile(){
        studentBase.fromFile();
        mainFrame.update();
    }

    public List<Student> searchStudents (Student searchStudent, String paramSearch) {
        List<Student> searchStud = new ArrayList<>();
        List<Student> allStudents = getStudentBase();

        if (paramSearch.equals("Group")) {
            for (Student stud : allStudents) {
                if (stud.getSurname().equals(searchStudent.getSurname())
                        && stud.getGroup().equals(searchStudent.getGroup())) {
                    searchStud.add(stud);
                }
            }
        }
        else if (paramSearch.equals("Type")) {
            for (Student stud : allStudents) {
                if (stud.getSurname().equals(searchStudent.getSurname()) &&
                        stud.equalsWork(searchStudent.getSocialWorkBase().get(0).getTypeWork())) {
                    searchStud.add(stud);
                }
            }
        }

        else if (paramSearch.equals("Number")) {
            for (Student stud : allStudents) {
                if (stud.getSurname().equals(searchStudent.getSurname()) &&
                        stud.equalsNumberOfWork(searchStudent.getSocialWorkBase().get(0).getTypeWork(),
                                searchStudent.getSocialWorkBase().get(0).getNumberWork(),
                                searchStudent.getSocialWorkBase().get(1).getNumberWork())) {
                    searchStud.add(stud);
                }
            }
        }

        return searchStud;
    }

    public void alertMessage(String text){
        mainFrame.alertMessage(text);
    }
}
