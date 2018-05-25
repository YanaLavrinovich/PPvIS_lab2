package Model;


import java.util.ArrayList;
import java.util.List;


public class Student {

    private String surname;
    private String name;
    private String fatherName;
    private String group;

    private List<SocialWork> socialWorkBase;


    public Student() {
        surname = "";
        name = "";
        fatherName = "";
        group = "";

        socialWorkBase = new ArrayList<>();
        for (int numberOfSemestr = 0; numberOfSemestr < 10; numberOfSemestr++) {
            int num = numberOfSemestr;
            num++;
            socialWorkBase.add(numberOfSemestr, new SocialWork("", 0, num));
        }
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

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    @Override
    public String toString() {
        return surname + " " +
                name + " " +
                fatherName;
    }


    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setSocialWorkBase(List<SocialWork> socialWorkBase) {
        this.socialWorkBase = socialWorkBase;
    }

    public List<SocialWork> getSocialWorkBase() {
        return socialWorkBase;
    }

    public void addWorkInSemestr(SocialWork socialWork) {
        socialWorkBase.add(socialWork.getSem() - 1, socialWork);
    }

    public void setFullName(String surname, String name, String fatherName) {
        this.surname = surname;
        this.name = name;
        this.fatherName = fatherName;
    }

    public boolean equalsWork(String work) {
        for (SocialWork socialWork : socialWorkBase) {
            if (socialWork.getTypeWork().equals(work)) {
                return true;
            }
        }
        return false;
    }


    public boolean equalsNumberOfWork(String work, int minNumber, int maxNumber) {
        for (SocialWork socialWork : socialWorkBase) {
            if (socialWork.getTypeWork().equals(work) &&
                    socialWork.getNumberWork() >= minNumber &&
                    socialWork.getNumberWork() <= maxNumber) {
                return true;
            }
        }
        return false;
    }

    public void clean() {
        surname = "";
        name = "";
        fatherName = "";
        group = "";

        socialWorkBase.clear();
    }
}
