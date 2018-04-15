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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Eche Michael
 */
public class AddNewAppointmentController implements Initializable {

    @FXML
    private Button btnClose;

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnClear;

    @FXML
    private TextField amount_box;
    @FXML
    private TextArea notes_box;

    @FXML
    private ChoiceBox<?> choicebox_Student;

    private ChoiceBox<?> choicebox_Teachers;

    @FXML
    private TextField date_time_box;
    //String Student_id = "";

    String orderby;
    String ascdesc;
    ObservableList Students;
    ObservableList ClassRooms;
    ObservableList Grades;

    @FXML
    private TextField invoiceNo_box;

    @FXML
    private ChoiceBox<?> choicebox_classrooms;
    @FXML
    private ChoiceBox<?> choicebox_grades;

//    String[] arrayIndexStore ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadDateTime();
        refreshItemListInCombo();
        selectFirstOne();
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

    private void loadDateTime() {
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        cal.add(Calendar.DATE, 0);
        String strDateInFormat = dateFormat.format(cal.getTime());
        System.out.println("strDateInFormat is: " + strDateInFormat);
        date_time_box.setText(strDateInFormat);

    }

    //code for combo starts
    private void refreshItemListInCombo() {
        //Set items equal to an empty ArrayList
        Students = FXCollections.observableArrayList();
        ClassRooms = FXCollections.observableArrayList();
        Grades = FXCollections.observableArrayList();

        //Select out of the DB, fill accordingly
        getStudents(Students);
        getAllClassrooms(ClassRooms);
        getAllGrades(Grades);

        //Set the listview to what we just populated with DB contents
        choicebox_Student.setItems(Students);
        choicebox_classrooms.setItems(ClassRooms);
        choicebox_grades.setItems(Grades);
    }

    private void getStudents(ObservableList Students) {
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
            String orderby = "Student_id";
            System.out.println("Query is: SELECT * FROM Student_tbl" + " ORDER BY " + orderby);
            stmt = c.createStatement();
            System.out.println("after stmt");

            ResultSet rs = stmt.executeQuery("SELECT * FROM Student_tbl" + " ORDER BY " + orderby);
            Students.add("--SELECT ONE--");
            int arrayIndexCounter = 0;
            ArrayList<String> arrayIndexStore = new ArrayList<String>();
            System.out.println("before while");

            while (rs.next()) {
                String Student_firstname = rs.getString("first_name");
                String Student_lastname = rs.getString("last_name");
                String Student_full_name = Student_firstname + " " + Student_lastname;

                String Student_id = rs.getString("Student_id");
                System.out.println("Student_full_name = " + Student_full_name);
                System.out.println("the Student_id = " + Student_id);

                arrayIndexStore.add(arrayIndexCounter, Student_id);
                arrayIndexCounter++;
                //IMPORTANT STATEMENT HERE:
                Students.add(Student_full_name);
            }
            rs.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    @FXML
    private boolean onAddButtonClicked(ActionEvent event) {
        //insert into db if valid
        Date date = new Date();
        String theDate = date.toString();
        //String full_name = full_name_box.getText();

        String full_name = choicebox_Student.getValue().toString();
        //String selectedTeacher_id = getSelectedTeacherId();
        String amount = amount_box.getText();
        String notes = notes_box.getText();
        String selectedStudent_id = getSelectedStudentId();
        String selected_Student = choicebox_Student.getValue().toString();
        String invoiceNo = invoiceNo_box.getText();
        String classroom = choicebox_classrooms.getValue().toString();
        String grades = choicebox_grades.getValue().toString();

        if ((!amount.matches("[0-9]*")) || (amount.trim().length() == 0) || (amount == "") || (amount.trim().isEmpty())) {

            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information Dialog");
            alert1.setHeaderText(null);
            alert1.setContentText("AMOUNT has to be numeric.");

            alert1.showAndWait();
            //System.clearProperty(str_cost);
            amount_box.setText("0");
            return false;

        }

        if ((full_name.trim().length() == 0) || (full_name == "") || (full_name.trim().isEmpty()) || (full_name == "--SELECT ONE--")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Select a Student.");

            alert.showAndWait();
            System.clearProperty(full_name);
            return false;
        }

        if ((selected_Student.trim().length() == 0) || (selected_Student == "") || (selected_Student.trim().isEmpty()) || (selected_Student == "--SELECT ONE--")) {
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Information Dialog");
            alert2.setHeaderText(null);
            alert2.setContentText("select a Teacher.");

            alert2.showAndWait();
            System.clearProperty(selected_Student);
            return false;
        }

        full_name = full_name.toUpperCase();
        notes = notes.toUpperCase();
        selected_Student = selected_Student.toUpperCase();

        String txnMonth = theDate.substring(4, 7);
        String txnYear = theDate.substring(24, 28);
        String txnDay = theDate.substring(0, 10) + " " + txnYear;
        //format for report queries
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        cal.add(Calendar.DATE, 0);
        String strDateInFormat = dateFormat.format(cal.getTime());
        System.out.println("strDateInFormat is: " + strDateInFormat);

        String query = "INSERT INTO Appointment_tbl (Student_id,full_name,amount,notes,txnDay,txnMonth,txnYear,appointment_date"
                + ",invoiceNo,gradelevel,classroom,date_time ) VALUES ("
                + "'" + selectedStudent_id + "'," + "'" + full_name + "'," + "'" + amount + "'," + "'"
                + notes + "','" + txnDay + "'," + "'" + txnMonth + "'," + "'" + txnYear + "'," + "'" + strDateInFormat + "'," + "'"
                + invoiceNo + "'," + "'" + classroom + "'," + "'" + grades + "'," + "'"
                + theDate + "');";

        System.out.println("Inserting\n" + query);
        insertStatement(query);

        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
        alert3.setTitle("Information Dialog");
        alert3.setHeaderText(null);
        alert3.setContentText("Record Added Succesfully.");

        alert3.showAndWait();

        System.out.println("Succesfully Inserted");

        choicebox_grades.getSelectionModel().clearSelection();
        choicebox_classrooms.getSelectionModel().clearSelection();

        amount_box.clear();
        notes_box.clear();
        choicebox_Student.getSelectionModel().clearSelection();
        invoiceNo_box.clear();

        loadDateTime();
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
    private void onClearButtonClicked(ActionEvent event) throws IOException {

        choicebox_grades.getSelectionModel().clearSelection();
        choicebox_classrooms.getSelectionModel().clearSelection();

        amount_box.clear();
        notes_box.clear();
        choicebox_Student.getSelectionModel().clearSelection();
        invoiceNo_box.clear();
    }

    private void selectFirstOne() {

        choicebox_Student.getSelectionModel().selectFirst();
        choicebox_classrooms.getSelectionModel().selectFirst();
        choicebox_grades.getSelectionModel().selectFirst();

    }

    private String getSelectedStudentId() {
        System.out.println("inside getSelectedStudentId");
        Connection c = null;
        Statement stmt = null;
        String selectedStudentId = "";
        int choiceIndex = choicebox_Student.getSelectionModel().getSelectedIndex();

        System.out.println("Student choiceIndex is: " + choiceIndex);

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
            String orderby = "Student_id";
            System.out.println("Query is: SELECT * FROM Student_tbl WHERE Student_id = " + choiceIndex + " ORDER BY " + orderby);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Student_tbl WHERE Student_id = " + choiceIndex + " ORDER BY " + orderby);

            int arrayIndexCounter = 0;
            while (rs.next()) {

                String Student_id = rs.getString("Student_id");
                System.out.println("Student_id = " + Student_id);
                selectedStudentId = Student_id;

            }
            rs.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return selectedStudentId;

    }

    private void getAllClassrooms(ObservableList ClassRooms) {
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
            System.out.println("Query is: SELECT * FROM Classrooms_tbl" + " ORDER BY " + orderby);
            stmt = c.createStatement();
            System.out.println("after stmt");

            ResultSet rs = stmt.executeQuery("SELECT * FROM Classrooms_tbl" + " ORDER BY " + orderby);
            ClassRooms.add("--SELECT ONE--");
            int arrayIndexCounter = 0;
            ArrayList<String> arrayIndexStore = new ArrayList<String>();
            System.out.println("before while");

            while (rs.next()) {
                String class_name = rs.getString("name");
                
                ClassRooms.add(class_name);
            }
            rs.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    private String getSelectedTeacherId() {
        System.out.println("inside getSelectedTeacherId");
        Connection c = null;
        Statement stmt = null;
        String selectedTeacherId = "";
        int choiceIndex = choicebox_Teachers.getSelectionModel().getSelectedIndex();

        System.out.println("Teacher choiceIndex is: " + choiceIndex);

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
            String orderby = "Teacher_id";
            System.out.println("Query is: SELECT * FROM Teachers_tbl WHERE Teacher_id = " + choiceIndex + " ORDER BY " + orderby);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Teachers_tbl WHERE Teacher_id = " + choiceIndex + " ORDER BY " + orderby);

            int arrayIndexCounter = 0;
            while (rs.next()) {

                String Teacher_id = rs.getString("Teacher_id");
                System.out.println("Teacher_id = " + Teacher_id);
                selectedTeacherId = Teacher_id;

            }
            rs.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        return selectedTeacherId;
    }

    private void getAllGrades(ObservableList grades) {
        //StudentTypes.add("--SELECT ONE--");
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
            System.out.println("Query is: SELECT * FROM Grades_tbl" + " ORDER BY " + orderby);
            stmt = c.createStatement();
            System.out.println("after stmt");

            ResultSet rs = stmt.executeQuery("SELECT * FROM Grades_tbl" + " ORDER BY " + orderby);
            Grades.add("--SELECT ONE--");
            int arrayIndexCounter = 0;
            ArrayList<String> arrayIndexStore = new ArrayList<String>();
            System.out.println("before while");

            while (rs.next()) {
                String gradeName = rs.getString("name");
                String gradeId = rs.getString("id");
                System.out.println("the gradeName = " + gradeName);

                arrayIndexStore.add(arrayIndexCounter, gradeId);
                arrayIndexCounter++;
                //IMPORTANT STATEMENT HERE:
                Grades.add(gradeName);
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
