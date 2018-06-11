package View;

import Controller.Controller;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

import static javafx.geometry.Orientation.VERTICAL;

public class MainFrame {
    private Controller controller;
    private Form form;

    public MainFrame(Controller controller) {
        this.controller = controller;

        ToolBar tb = new ToolBar();
        tb.setOrientation(VERTICAL);
        Button load = new Button("Load");
        load.setOnAction( e -> {
            controller.getStudentBase().clear();
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open file");
            fileChooser.setInitialDirectory(new java.io.File("./"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML", "*.xml"));
            File file = fileChooser.showOpenDialog(stage);
            controller.setFile(file);
            controller.fromFile();
            update();
        });
        Button save = new Button("Save");
        save.setOnAction( e -> {
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new java.io.File("./"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML", "*.xml"));
            File file = fileChooser.showSaveDialog(stage);

            controller.toFile(file);
        });
        Button add = new Button("Add");
        add.setOnAction( e -> {
            AddFrame addFrame = new AddFrame(this, this.controller);
        });
        Button search = new Button("Search");
        search.setOnAction( e -> {
            SearchFrame searchFrame = new SearchFrame(this, this.controller);
            searchFrame.start();
        });
        Button delete = new Button("Delete");
        delete.setOnAction( e -> {
            DeleteFrame deleteFrame = new DeleteFrame(this, this.controller);
            deleteFrame.start();
        });
        tb.getItems().addAll(load, save, add, search, delete);

        MenuBar menuBar = new MenuBar();
        Menu menuF = new Menu("File");
        MenuItem menuItemP = new MenuItem("Load");
        menuItemP.setOnAction(e -> {
            controller.getStudentBase().clear();
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open file");
            fileChooser.setInitialDirectory(new java.io.File("./"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML", "*.xml"));
            File file = fileChooser.showOpenDialog(stage);
            controller.setFile(file);
            controller.fromFile();
            update();
        });
        MenuItem menuItemS = new MenuItem("Save");
        menuItemS.setOnAction(e -> {
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setInitialDirectory(new java.io.File("./"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("XML", "*.xml"));
            File file = fileChooser.showSaveDialog(stage);

            controller.toFile(file);
        });
        menuF.getItems().addAll(menuItemP, menuItemS);

        Menu menuT = new Menu("Table");
        MenuItem menuAdd = new MenuItem("Add");
        MenuItem menuSearch = new MenuItem("Search");
        MenuItem menuDelete = new MenuItem("Delete");
        menuT.getItems().addAll(menuAdd, menuSearch, menuDelete);

        menuBar.getMenus().addAll(menuF, menuT);


        menuAdd.setOnAction(e -> {
            System.out.println("Add is normal!");
            AddFrame addFrame = new AddFrame(this, this.controller);
        });

        menuSearch.setOnAction(e -> {
            System.out.println("Search is normal!");
            SearchFrame searchFrame = new SearchFrame(this, this.controller);
            searchFrame.start();
        });

        menuDelete.setOnAction(e -> {
            System.out.println("Delete is normal!");
            DeleteFrame deleteFrame = new DeleteFrame(this, this.controller);
            deleteFrame.start();
        });

        form = new Form();
        form.setList(controller.getStudentBase());

        VBox allBox = new VBox();
        allBox.setPadding(new Insets(50, 10, 30, 10));
        allBox.setSpacing(20);

        HBox collectiveMemberBox = new HBox();
        collectiveMemberBox.setSpacing(20);
        collectiveMemberBox.getChildren().addAll(tb, form.getPaneBox());
        allBox.getChildren().addAll(collectiveMemberBox);


        Scene scene = new Scene(new Group(), 1300, 600);
        ((Group) scene.getRoot()).getChildren().addAll(allBox, menuBar);

        Stage primaryStage = new Stage();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Social work");
        primaryStage.show();
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public void update() {
        form.clear();
        form.setList(controller.getStudentBase());
        form.getStudentTable().setItems(FXCollections.observableArrayList(controller.getStudentBase()));
    }


    public void alertMessage(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
