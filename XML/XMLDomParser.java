package XML;

import Model.SocialWork;
import Model.Student;
import Model.StudentBase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class XMLDomParser {
    private File file;
    private Document document;
    private StudentBase studentBase;
    Element socialWorkBase;

    public void write(File file, StudentBase studentBase) {
        this.file = file;
        this.studentBase = studentBase;
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element list = document.createElement("list");

            for (int numOfStudent = 0; numOfStudent < studentBase.getStudentsBase().size(); numOfStudent++) {
                Element student = document.createElement("student");


                Element surname = document.createElement("surname");
                surname.setTextContent(studentBase.getStudentsBase().get(numOfStudent).getSurname());
                student.appendChild(surname);

                Element name = document.createElement("name");
                name.setTextContent(studentBase.getStudentsBase().get(numOfStudent).getName());
                student.appendChild(name);

                Element fatherName = document.createElement("fatherName");
                fatherName.setTextContent(studentBase.getStudentsBase().get(numOfStudent).getFatherName());
                student.appendChild(fatherName);


                Element group = document.createElement("group");
                group.setTextContent(studentBase.getStudentsBase().get(numOfStudent).getGroup());
                student.appendChild(group);


                socialWorkBase = document.createElement("socialWorkBase");
                addSemToFile(studentBase.getStudentsBase().get(numOfStudent).getSocialWorkBase());
                student.appendChild(socialWorkBase);

                list.appendChild(student);
            }

            document.appendChild(list);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(source, streamResult);
        } catch (ParserConfigurationException ex) {
            String exText = ex.getMessage();
            studentBase.getController().alertMessage(exText);
        } catch (TransformerConfigurationException ex) {
            String exText = ex.getMessage();
            studentBase.getController().alertMessage(exText);
        } catch (TransformerException ex) {
            String exText = ex.getMessage();
            studentBase.getController().alertMessage(exText);
        }
    }

    public void addSemToFile(List<SocialWork> list) {
        for (SocialWork sw : list) {
            if (sw.getSem() != 0) {
                Element socialWork = document.createElement("socialWork");

                Element typeWork = document.createElement("typeWork");
                typeWork.setTextContent(sw.getTypeWork());
                socialWork.appendChild(typeWork);

                Element numberWork = document.createElement("numberWork");
                numberWork.setTextContent(sw.getNumberWork() + "");
                socialWork.appendChild(numberWork);

                Element sem = document.createElement("sem");
                sem.setTextContent(sw.getSem() + "");
                socialWork.appendChild(sem);


                socialWorkBase.appendChild(socialWork);
            }
        }
    }
}
