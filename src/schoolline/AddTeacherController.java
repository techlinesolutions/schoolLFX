/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolline;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Eche Michael
 */
public class AddTeacherController implements Initializable {

    @FXML
    private Button btnClose;
    @FXML
    private TextField item_first_name_box;

    @FXML
    private TextField item_phone_box;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnClear;
    @FXML
    private TextField item_email_box;
    @FXML
    private TextField item_last_name_box;
    @FXML
    private TextArea item_home_address_box;
    @FXML
    private RadioButton radio_female;
    @FXML
    private ToggleGroup radioGroupToggle;
    @FXML
    private RadioButton radio_male;
    @FXML
    private TextField type_box;
    @FXML
    private ChoiceBox<?> choicebox_subject1;
    @FXML
    private ChoiceBox<?> choicebox_subject2;
    @FXML
    private ChoiceBox<?> choicebox_subject3;
    @FXML
    private ChoiceBox<?> choicebox_subject4;

    
    private ObservableList Subjects;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshItemListInCombo();
        selectFirstOne();
    }

    @FXML
    private boolean onAddButtonClicked(ActionEvent event) {
        //insert into db if valid
         RadioButton selectedRadioButton = (RadioButton) radioGroupToggle.getSelectedToggle();
        String selectedGender = selectedRadioButton.getText();
        Date date = new Date();
        String theDate = date.toString();
        String Teacher_firstname = item_first_name_box.getText();
        String Teacher_lastname = item_last_name_box.getText();
        String Teacher_address1 = item_home_address_box.getText();
        String Teacher_phone_1 = item_phone_box.getText();
        String Teacher_email = item_email_box.getText();

        String Teacher_type = type_box.getText().toUpperCase();
        String Teacher_subject1 = choicebox_subject1.getValue().toString();
        String Teacher_subject2 = choicebox_subject2.getValue().toString();
        String Teacher_subject3 = choicebox_subject3.getValue().toString();
        String Teacher_subject4 = choicebox_subject4.getValue().toString();
        if ((Teacher_firstname.trim().length() == 0) || (Teacher_firstname == "") || (Teacher_firstname.trim().isEmpty()) || (Teacher_firstname == "--SELECT ONE--")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Enter a Teacher.");

            alert.showAndWait();
            System.clearProperty(Teacher_firstname);
            return false;
        }
        if ((!Teacher_phone_1.matches("[0-9]*")) || (Teacher_phone_1.trim().length() == 0) || (Teacher_phone_1 == "") || (Teacher_phone_1.trim().isEmpty())) {

            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("PHONE NUMBER has to be numeric.");

            alert1.showAndWait();
            //System.clearProperty(str_cost);
            item_phone_box.setText("0");
            return false;

        }

        Teacher_firstname = Teacher_firstname.toUpperCase();
        Teacher_lastname = Teacher_lastname.toUpperCase();
        Teacher_address1 = Teacher_address1.toUpperCase();
        Teacher_phone_1 = Teacher_phone_1.toUpperCase();
        Teacher_email = Teacher_email.toUpperCase();

        String query = "INSERT INTO Teachers_tbl (first_name,last_name,address,phone,email,type,gender,subject1,"
                + "subject2,subject3,subject4,teacher_date ) VALUES ("
                + "'" + Teacher_firstname + "'," + "'" + Teacher_lastname + "'," + "'" + Teacher_address1
                + "'," + "'" + Teacher_phone_1 + "'," + "'"
                + Teacher_email + "','" + Teacher_type + "','" + selectedGender + "','" 
                + Teacher_subject1 + "','" + Teacher_subject2 + "','" + Teacher_subject3 + "','" + Teacher_subject4 
                + "','" + theDate + "');";

        System.out.println("Inserting\n" + query);
        insertStatement(query);

        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
        alert3.setTitle("Information Dialog");
        alert3.setHeaderText(null);
        alert3.setContentText("Record Added Succesfully.");

        alert3.showAndWait();

        System.out.println("Succesfully Inserted");
        item_first_name_box.clear();
        item_last_name_box.clear();
        item_home_address_box.clear();
        item_phone_box.clear();
        item_email_box.clear();

        type_box.clear();
        choicebox_subject1.getSelectionModel().clearSelection();
        choicebox_subject2.getSelectionModel().clearSelection();
        choicebox_subject3.getSelectionModel().clearSelection();
        choicebox_subject4.getSelectionModel().clearSelection();

        return true;

    }

    private void insertStatement(String insert_query) {

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            System.out.println("Our query was: " + insert_query);
            stmt.executeUpdate(insert_query);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    @FXML
    private void onClickClose(ActionEvent event) throws IOException {

        Parent clpage_parent = FXMLLoader.load(getClass().getResource("SchoolLine.fxml"));
        Scene page_scene = new Scene(clpage_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(page_scene);
        app_stage.show();
    }

    @FXML
    private void onClearButtonClicked() {
        item_first_name_box.clear();
        item_last_name_box.clear();
        item_home_address_box.clear();
        item_phone_box.clear();
        item_email_box.clear();

        type_box.clear();
        choicebox_subject1.getSelectionModel().clearSelection();
        choicebox_subject2.getSelectionModel().clearSelection();
        choicebox_subject3.getSelectionModel().clearSelection();
        choicebox_subject4.getSelectionModel().clearSelection();

        selectFirstOne();
    }

    private void refreshItemListInCombo() {
        //Set items equal to an empty ArrayList
        Subjects = FXCollections.observableArrayList();

        //Select out of the DB, fill accordingly
        getAllSubjects(Subjects);

        //Set the listview to what we just populated with DB contents
        choicebox_subject1.setItems(Subjects);
        choicebox_subject2.setItems(Subjects);
        choicebox_subject3.setItems(Subjects);
        choicebox_subject4.setItems(Subjects);

    }

    private void selectFirstOne() {
        choicebox_subject1.getSelectionModel().selectFirst();
        choicebox_subject2.getSelectionModel().selectFirst();
        choicebox_subject3.getSelectionModel().selectFirst();
        choicebox_subject4.getSelectionModel().selectFirst();

    }

    private void getAllSubjects(ObservableList Subjects) {
        Connection c = null;
        Statement stmt = null;

        //orderby = sort_menubutton.getText();
        // ascdesc = ascdesc_menubutton.getText();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            c.setAutoCommit(false);
            System.out.println("Opened database getStudents successfully");

            //if (orderby.equals("Time")) { orderby = "TIMING";}
            //else if (orderby.equals("Title")) { orderby = "TITLE";}
            //else { orderby = "LOCATION";}
            String orderby = "id";
            System.out.println("Query is: SELECT * FROM Subjects_tbl" + " ORDER BY " + orderby);
            stmt = c.createStatement();
            System.out.println("after stmt");

            ResultSet rs = stmt.executeQuery("SELECT * FROM Subjects_tbl" + " ORDER BY " + orderby);
            Subjects.add("--NONE SELECTED--");
            int arrayIndexCounter = 0;
            ArrayList<String> arrayIndexStore = new ArrayList<String>();
            System.out.println("before while");

            while (rs.next()) {
                String subject_name = rs.getString("name");

                Subjects.add(subject_name);
            }
            rs.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

}
