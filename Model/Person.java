package Model;

public class Person {

    private String name;
    private int group;

    public Person(String name, int group) {
        this.name = name;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public int getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "{Name: " + name +
                ", group: " + group +
                '}';
    }
}
