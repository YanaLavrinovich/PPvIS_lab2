package View;


import Model.Student;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.List;


public class Form {

    private TableView<Student> studentTable;
    private VBox paneBox;
    private int currentPage = 0;
    private int recordsOnPage = 10;
    private List<Student> list;
    private Label allRecordsLabel;
    private Label allPageLabel;


    public Form() {
        studentTable = new TableView<>();

        List<TableColumn> listOfColumns = new ArrayList<>();

        TableColumn socialWorkColumn = new TableColumn("Social work");

        TableColumn<Student,String> fullNameColumn = new TableColumn<>("Full student's name");
        fullNameColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().toString()));
        fullNameColumn.setMinWidth(200);

        TableColumn<Student,String> studentGroup = new TableColumn<>("Group");
        studentGroup.setCellValueFactory(new PropertyValueFactory<>("group"));
        studentGroup.setMinWidth(50);

        int numberOfColomn = 10;
        for(int i = 0; i < numberOfColomn; i++) {
            TableColumn<Student, String> semestrColumn = new TableColumn<>(String.valueOf(i+1) + " semestr");
            semestrColumn.setMinWidth(90);
            final int number = i;
            semestrColumn.setCellValueFactory(param -> new SimpleStringProperty(
                    param.getValue().getSocialWorkBase().get(number).toString()));

            listOfColumns.add(i,semestrColumn);
            socialWorkColumn.getColumns().add(semestrColumn);
        }

        studentTable.setPrefSize(1180,500);

        studentTable.getColumns().addAll(fullNameColumn, studentGroup,socialWorkColumn);

        paneBox = new VBox(20);

        HBox pageBox = new HBox(20);

        Label currentPageLabel = new Label("Current page: "+ (currentPage+1));

        allPageLabel = new Label("Number of pages: " + 0);
        allRecordsLabel = new Label();
        Label recordOnPages = new Label("Number of record on page: " + recordsOnPage);

        Button firstPage = new Button("First page");
        firstPage.setOnAction(e-> {
            currentPage = 0;
            setCurrentPage(currentPage);
            currentPageLabel.setText("Current page: "+ (currentPage+1));
        });

        Button previousPage = new Button("Previous page");
        previousPage.setOnAction(e -> {
            if(currentPage>0){
                currentPage--;
                setCurrentPage(currentPage);
                currentPageLabel.setText("Current page: "+ (currentPage+1));
            }
        });

        Button nextPage = new Button("Next page");
        nextPage.setOnAction(e -> {
            if(currentPage<(list.size()-1)/recordsOnPage){
                currentPage++;
                setCurrentPage(currentPage);
                currentPageLabel.setText("Current page: "+ (currentPage+1));
            }
        });

        Button lastPage = new Button("Last page");
        lastPage.setOnAction(e -> {
            currentPage=(list.size()-1)/recordsOnPage;
            setCurrentPage(currentPage);
            currentPageLabel.setText("Current page: "+ (currentPage+1));
        });


        TextField recordOnPagesField = new TextField();
        recordOnPagesField.setPromptText("Number of record on page: ");
        Button enterRecord = new Button("OK");
        enterRecord.setOnAction(e -> {
            int records = Integer.parseInt(recordOnPagesField.getText());
            recordsOnPage=records;
            recordOnPages.setText("Number of record on page: " + recordsOnPage);
            currentPage=0;
            setCurrentPage(currentPage);
            currentPageLabel.setText("Current page: "+(currentPage+1));
            recordOnPagesField.clear();
            allPageLabel.setText("Number of pages: " + (int)Math.ceil((double)list.size()/recordsOnPage));

        });
        pageBox.getChildren().addAll(firstPage, previousPage, nextPage, lastPage, currentPageLabel, allPageLabel,
                allRecordsLabel, recordOnPages, recordOnPagesField, enterRecord);

        paneBox.getChildren().addAll(studentTable, pageBox);
        paneBox.setSpacing(3);

    }

    public VBox getPaneBox() {
        return paneBox;
    }

    public TableView<Student> getStudentTable() {
        return studentTable;
    }

    public void setPaneBox(VBox paneBox) {
        this.paneBox = paneBox;
    }

    public void setStudentTable(TableView<Student> studentTable) {
        this.studentTable = studentTable;
    }

    public void clear() {
        ObservableList<Student> list = FXCollections.observableArrayList();
        studentTable.setItems(list);
    }

    public void setList(List<Student> studentList)
    {
        this.list = studentList;
        allRecordsLabel.setText("Number of records: " + studentList.size());
        allPageLabel.setText("Number of pages: " + (int)Math.ceil((double)list.size()/recordsOnPage));
    }

    public void setCurrentPage(int currentPage) {
        List<Student> page = new ArrayList<>();
        int numberOfRecords = recordsOnPage;
        if((list.size()-currentPage*recordsOnPage)<recordsOnPage) {
            numberOfRecords = this.list.size()-currentPage*recordsOnPage;
        }

        for (int stud=currentPage*recordsOnPage;stud<currentPage*recordsOnPage+numberOfRecords;stud++){
            page.add(this.list.get(stud));
        }
        clear();
        studentTable.setItems(FXCollections.observableArrayList(page));
    }
}
