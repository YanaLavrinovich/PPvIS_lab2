package XML;

import Model.SocialWork;
import Model.Student;
import Model.StudentBase;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

import java.util.ArrayList;
import java.util.List;

public class XMLSaxParser extends DefaultHandler {
    private Student student;
    private StudentBase studentBase;
    private List<Student> studentList;
    private List<SocialWork> socialWorkList;
    private String currentElement;
    private SocialWork socialWork;
    private StringBuilder content;

    public XMLSaxParser() {
        studentList = new ArrayList<>();
        currentElement = "";
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parsing...");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("...End parsing");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attr) {
        currentElement = qName;
        if (currentElement.equals("student")) {
            student = new Student();
            studentList.add(student);
            System.out.println("New student add!");
        }

        if (currentElement.equals("socialWorkBase")) {
            socialWorkList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                socialWorkList.add(new SocialWork());
            }
            student.setSocialWorkBase(socialWorkList);
        }

        if (currentElement.equals("socialWork")) {
            socialWork = new SocialWork();
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) {
        content = new StringBuilder(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (currentElement.equals("surname")) {
            student.setSurname(content.toString());
            return;
        }
        if (currentElement.equals("name")) {
            student.setName(content.toString());
            return;
        }
        if (currentElement.equals("fatherName")) {
            student.setFatherName(content.toString());
            return;
        }
        if (currentElement.equals("group")) {
            student.setGroup(content.toString());
            return;
        }
        if (currentElement.equals("typeWork")) {
            socialWork.setTypeWork(content.toString());
            return;
        }
        if (currentElement.equals("numberWork")) {
            socialWork.setNumberWork(Integer.parseInt(content.toString()));
            return;
        }
        if (currentElement.equals("sem")) {
            if (content.toString().contains("\t") || content.toString().contains("\n")) {
                return;
            }
            socialWork.setSem(Integer.parseInt(content.toString()));
            student.addWorkInSemestr(socialWork);
            return;
        }
        if (qName.equals("socialWork")) {
            socialWork = new SocialWork();
        }

        if (qName.equals("student")) {
            student.setSocialWorkBase(socialWorkList);
            System.out.println("Student add!");
        }
        if (qName.equals("socialWorkBase")) {
            socialWorkList.add(socialWork);
        }
        if (qName.equals("list")) {
            studentBase.setStudentsBase(studentList);
        }
    }

    public void setBase(StudentBase studentBase) {
        this.studentBase = studentBase;
    }

}
