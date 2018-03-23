package Controller;

import Model.Person;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonWrapper {

    private StringProperty name;
    private IntegerProperty group;
    private Person person;

    public PersonWrapper(Person p) {
        this.person = p;
        this.name = new SimpleStringProperty(p.getName());
        this.group = new SimpleIntegerProperty(p.getGroup());
    }

    public String getName() {
        return name.get();
    }

    public Integer getGroup() {
        return group.get();
    }

    public Person getPerson() {
        return person;
    }
}
