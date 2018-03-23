package Model;

public class Student {

    private String surname;
    private String name;
    private String fatherName;
    private int group;

    public Student(String surname, String name, String fatherName, int group) {
        this.surname = surname;
        this.name = name;
        this.fatherName = fatherName;
        this.group = group;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public int getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "{SurName: " + surname +
                ", name:" + name +
                ", fathername:" + fatherName +
                ", group: " + group +
                '}';
    }
}
