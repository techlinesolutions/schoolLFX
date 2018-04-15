/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolline;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import schoolline.entities.gradeLevel;
import schoolline.entities.score;

/**
 * FXML Controller class
 *
 * @author Eche Michael
 */
public class AddNewStudentController implements Initializable {

    @FXML
    private Button btnClose;

    @FXML
    private TextField item_first_name_box;
    @FXML
    private TextField item_last_name_box;
    @FXML
    private DatePicker item_dob_box;
    @FXML
    private TextArea item_home_address_box;

    @FXML
    private TextField item_phone_box;
    @FXML
    private TextField item_email_box;

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnClear;
    @FXML
    private TextField mother_first_name_box;
    @FXML
    private TextField mother_last_name_box;
    @FXML
    private TextField father_first_name_box;
    @FXML
    private TextField father_last_name_box;
    @FXML
    private RadioButton radio_male;
    @FXML
    private RadioButton radio_female;
    @FXML
    private ChoiceBox choicebox_classroom;
    @FXML
    private ChoiceBox choicebox_grade;
    @FXML
    private TextArea item_remarks_box;
    @FXML
    private ToggleGroup radioGroupToggle;
    @FXML
    private TextField item_prev_school_box;
    @FXML
    private TextField item_hostel_box;
    @FXML
    private ImageView imgTPlaceHolder;
    @FXML
    private Button btnChose;
    @FXML
    private TextField imagePath_box;
    private FileChooser fileChooser;
    @FXML
    private AnchorPane addNewStudentPane;

    private FileInputStream fis;
    private File file;
    private ObservableList allGrades;
    private ObservableList allClassrooms;
    private String item_dob;

    @FXML
    private void onChosePicture(ActionEvent event) throws IOException {
//        Pane root = FXMLLoader.load(getClass().getResource("AddNewStudent.fxml"));
//        Scene scene = new Scene(root, 745.0, 618.0, null);

        fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        file = fileChooser.showOpenDialog(app_stage);

        //Single File Selection
        if (file != null) {
            imagePath_box.setText(file.getAbsolutePath());
            Image image = new Image(file.toURI().toString(), 150, 152, true, true); //path, PrefWidth, PrefHeight, PreserveRatio, Smooth
            imgTPlaceHolder.setFitWidth(150);
            imgTPlaceHolder.setFitHeight(152);
            imgTPlaceHolder.setPreserveRatio(true);
            System.out.println("file is: " + file);
            imgTPlaceHolder.setImage(image);

        }

    }

    @FXML
    private boolean onAddButtonClicked(ActionEvent event) {
        //insert into db if valid
        RadioButton selectedRadioButton = (RadioButton) radioGroupToggle.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();
        System.out.println("toogleGroupValue is: " + toogleGroupValue);
        Date date = new Date();
        String theDate = date.toString();
        String item_first_name = item_first_name_box.getText();
        String item_last_name = item_last_name_box.getText();

        if (item_dob_box.getValue() == null) {

        } else {
            item_dob = item_dob_box.getValue().toString();
        }
        String item_home_address = item_home_address_box.getText();
        String item_phone = item_phone_box.getText();
        String item_email = item_email_box.getText();
        String choiceboxClass = choicebox_classroom.getValue().toString();
        String choiceboxGrade = choicebox_grade.getValue().toString();

        String mother_first_name = mother_first_name_box.getText();
        String mother_last_name = mother_last_name_box.getText();
        String father_first_name = father_first_name_box.getText();
        String father_last_name = father_last_name_box.getText();
        String item_remarks = item_remarks_box.getText();
        String item_prev_school = item_prev_school_box.getText();
        String item_hostel = item_hostel_box.getText();

        if ((!item_phone.matches("[0-9]*")) || (item_phone.trim().length() == 0) || (item_phone == "") || (item_phone.trim().isEmpty())) {

            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("PHONE NUMBER has to be numeric.");

            alert1.showAndWait();
            //System.clearProperty(str_cost);
            item_phone_box.setText("0");
            return false;

        }

        if ((item_first_name.trim().length() == 0) || (item_first_name == "") || (item_first_name.trim().isEmpty()) || (item_first_name == "--SELECT ONE--")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("FIRST NAME is mandatory.");

            alert.showAndWait();
            //System.clearProperty(item_first_name);
            return false;
        }

        item_first_name = item_first_name.toUpperCase();
        item_last_name = item_last_name.toUpperCase();
        item_home_address = item_home_address.toUpperCase();
        item_phone = item_phone.toUpperCase();
        item_email = item_email.toUpperCase();
        choiceboxClass = choiceboxClass.toUpperCase();
        choiceboxGrade = choiceboxGrade.toUpperCase();

        mother_first_name = mother_first_name.toUpperCase();
        mother_last_name = mother_last_name.toUpperCase();
        father_first_name = father_first_name.toUpperCase();
        father_last_name = father_last_name.toUpperCase();
        item_remarks = item_remarks.toUpperCase();
        item_prev_school = item_prev_school.toUpperCase();
        item_hostel = item_hostel.toUpperCase();
if (imagePath_box.getText().isEmpty()){
    
}
else{
        try {
            fis = new FileInputStream(file);
            System.out.println("fis  is: " + fis);
            System.out.println("file Length is: " + file.length());
        } catch (FileNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
}

        String txnMonth = theDate.substring(4, 7);
        String txnYear = theDate.substring(24, 28);
        String txnDay = theDate.substring(0, 10) + " " + txnYear;
//         Calendar cal = Calendar.getInstance();
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        cal.add(Calendar.DATE, 0);
//        String strDateInFormat = dateFormat.format(cal.getTime());
//        System.out.println("strDateInFormat is: " + strDateInFormat);

        String query = "INSERT INTO Student_tbl (first_name,last_name,age,dob,address,gender,phone,email,"
                + "mother_first_name,mother_last_name,father_first_name,father_last_name,remarks,prev_school,"
                + "hostel,classroom,grade,image,student_date ) VALUES ("
                + "'" + item_first_name + "'," + "'" + item_last_name + "'," + "'" + toogleGroupValue + "'," + "'" + item_dob + "',"
                + "'" + item_home_address + "'," + "'" + toogleGroupValue + "'," + "'" + item_phone + "'," + "'" + item_email + "'," + "'"
                + mother_first_name + "','" + mother_last_name + "','" + father_first_name + "','" + father_last_name + "','"
                + item_remarks + "','" + item_prev_school + "','" + item_hostel + "','" + choiceboxClass + "','"
                + choiceboxGrade + "','" + fis + "','" + theDate + "');";

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
        item_dob_box.setValue(LocalDate.MIN);
        item_home_address_box.clear();
        item_phone_box.clear();
        item_email_box.clear();
        choicebox_classroom.getSelectionModel().clearSelection();
        choicebox_grade.getSelectionModel().clearSelection();

        mother_first_name_box.clear();
        mother_last_name_box.clear();
        father_first_name_box.clear();
        father_last_name_box.clear();
        item_remarks_box.clear();
        item_prev_school_box.clear();
        item_hostel_box.clear();

        selectFirstOne();

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
        item_dob_box.setValue(LocalDate.MIN);
        choicebox_classroom.getSelectionModel().clearSelection();
        choicebox_grade.getSelectionModel().clearSelection();
        item_home_address_box.clear();
        item_phone_box.clear();
        item_email_box.clear();

        mother_first_name_box.clear();
        mother_last_name_box.clear();
        father_first_name_box.clear();
        father_last_name_box.clear();
        item_remarks_box.clear();
        item_prev_school_box.clear();
        item_hostel_box.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshItemListInCombo();
        selectFirstOne();
    }

    private void selectFirstOne() {
        choicebox_grade.getSelectionModel().selectFirst();
        choicebox_classroom.getSelectionModel().selectFirst();
    }

    private void refreshItemListInCombo() {
        //Set items equal to an empty ArrayList
        allGrades = FXCollections.observableArrayList();
        allClassrooms = FXCollections.observableArrayList();

        //Select out of the DB, fill accordingly
        getGrades(allGrades);
        getClassroomss(allClassrooms);

        //Set the listview to what we just populated with DB contents
        choicebox_grade.setItems(allGrades);
        choicebox_classroom.setItems(allClassrooms);
    }

    private void getGrades(ObservableList allGrades) {
        Connection c = null;
        Statement stmt = null;

        //orderby = sort_menubutton.getText();
        // ascdesc = ascdesc_menubutton.getText();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            //if (orderby.equals("Time")) { orderby = "TIMING";}
            //else if (orderby.equals("Title")) { orderby = "TITLE";}
            //else { orderby = "LOCATION";}
            String orderby = "id";
            System.out.println("Query is: SELECT * FROM Grades_tbl" + " ORDER BY " + orderby);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Grades_tbl" + " ORDER BY " + orderby);
            allGrades.add("--NONE SELECTED--");
            while (rs.next()) {
                String grade_name = rs.getString("name");

                //IMPORTANT STATEMENT HERE:
                allGrades.add(grade_name);
            }
            rs.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    private void getClassroomss(ObservableList allClassrooms) {
        Connection c = null;
        Statement stmt = null;

        //orderby = sort_menubutton.getText();
        // ascdesc = ascdesc_menubutton.getText();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            //if (orderby.equals("Time")) { orderby = "TIMING";}
            //else if (orderby.equals("Title")) { orderby = "TITLE";}
            //else { orderby = "LOCATION";}
            String orderby = "id";
            System.out.println("Query is: SELECT * FROM Classrooms_tbl" + " ORDER BY " + orderby);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Classrooms_tbl" + " ORDER BY " + orderby);
            allClassrooms.add("--NONE SELECTED--");
            while (rs.next()) {
                String classrooms_name = rs.getString("name");

                //IMPORTANT STATEMENT HERE:
                allClassrooms.add(classrooms_name);
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
