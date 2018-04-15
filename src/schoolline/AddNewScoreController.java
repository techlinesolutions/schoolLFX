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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Eche Michael
 */
public class AddNewScoreController implements Initializable {

    @FXML
    private Button btnClose;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnClear;
    @FXML
    private TextField subject_1_col_1_box;
    @FXML
    private ChoiceBox choicebox_subject_3;
    @FXML
    private ChoiceBox choicebox_subject_1;
    @FXML
    private ChoiceBox choicebox_subject_4;
    @FXML
    private ChoiceBox choicebox_subject_2;
    @FXML
    private ChoiceBox choicebox_gender;
    @FXML
    private ChoiceBox choicebox_grade;
    @FXML
    private ChoiceBox choicebox_class;
    @FXML
    private ChoiceBox choicebox_name;
    @FXML
    private TextField subject_1_col_2_box;
    @FXML
    private TextField subject_1_col_3_box;
    @FXML
    private TextField subject_1_col_4_box;
    @FXML
    private TextField subject_1_col_7_box;
    @FXML
    private TextField subject_1_col_6_box;
    @FXML
    private TextField subject_1_col_5_box;
    @FXML
    private ChoiceBox choicebox_subject_5;
    @FXML
    private ChoiceBox choicebox_subject_8;
    @FXML
    private ChoiceBox choicebox_subject_6;
    @FXML
    private ChoiceBox choicebox_subject_7;
    @FXML
    private TextField subject_1_avg_box;
    @FXML
    private TextField subject_1_sum_box;
    @FXML
    private TextField subject_2_col_3_box;
    @FXML
    private TextField subject_2_avg_box;
    @FXML
    private TextField subject_2_col_2_box;
    @FXML
    private TextField subject_2_col_1_box;
    @FXML
    private TextField subject_2_sum_box;
    @FXML
    private TextField subject_2_col_7_box;
    @FXML
    private TextField subject_2_col_6_box;
    @FXML
    private TextField subject_2_col_5_box;
    @FXML
    private TextField subject_2_col_4_box;
    @FXML
    private TextField subject_3_col_5_box;
    @FXML
    private TextField subject_3_col_3_box;
    @FXML
    private TextField subject_3_col_1_box;
    @FXML
    private TextField subject_3_col_7_box;
    @FXML
    private TextField subject_3_col_6_box;
    @FXML
    private TextField subject_3_avg_box;
    @FXML
    private TextField subject_3_col_2_box;
    @FXML
    private TextField subject_3_col_4_box;
    @FXML
    private TextField subject_3_sum_box;
    @FXML
    private TextField subject_4_col_7_box;
    @FXML
    private TextField subject_4_col_2_box;
    @FXML
    private TextField subject_4_col_6_box;
    @FXML
    private TextField subject_4_col_1_box;
    @FXML
    private TextField subject_4_sum_box;
    @FXML
    private TextField subject_4_col_3_box;
    @FXML
    private TextField subject_4_col_4_box;
    @FXML
    private TextField subject_5_col_3_box;
    @FXML
    private TextField subject_5_avg_box;
    @FXML
    private TextField subject_4_col_5_box;
    @FXML
    private TextField subject_5_col_5_box;
    @FXML
    private TextField subject_6_avg_box;
    @FXML
    private TextField subject_5_col_6_box;
    @FXML
    private TextField subject_5_col_2_box;
    @FXML
    private TextField subject_6_col_2_box;
    @FXML
    private TextField subject_6_col_4_box;
    @FXML
    private TextField subject_6_col_7_box;
    @FXML
    private TextField subject_6_col_6_box;
    @FXML
    private TextField subject_6_col_5_box;
    @FXML
    private TextField subject_7_col_6_box;
    @FXML
    private TextField subject_7_col_1_box;
    @FXML
    private TextField subject_7_col_3_box;
    @FXML
    private TextField subject_6_col_1_box;
    @FXML
    private TextField subject_8_sum_box;
    @FXML
    private TextField subject_8_col_5_box;
    @FXML
    private TextField subject_8_col_1_box;
    @FXML
    private TextField subject_5_col_1_box;
    @FXML
    private TextField subject_5_col_7_box;
    @FXML
    private TextField subject_5_sum_box;
    @FXML
    private TextField subject_6_sum_box;
    @FXML
    private TextField subject_5_col_4_box;
    @FXML
    private TextField subject_7_col_7_box;
    @FXML
    private TextField subject_7_col_2_box;
    @FXML
    private TextField subject_7_avg_box;
    @FXML
    private TextField subject_8_col_2_box;
    @FXML
    private TextField subject_8_col_6_box;
    @FXML
    private TextField subject_7_sum_box;
    @FXML
    private TextField subject_8_col_7_box;
    @FXML
    private TextField subject_8_col_4_box;
    @FXML
    private TextField subject_6_col_3_box;
    @FXML
    private TextField subject_8_avg_box;
    @FXML
    private TextField subject_4_avg_box;
    @FXML
    private TextField subject_7_col_4_box;
    @FXML
    private TextField subject_7_col_5_box;
    private ObservableList<Object> allGenders;
    private ObservableList<Object> allGrades;
    private ObservableList<Object> allClassrooms;
    private ObservableList<Object> AllSubjects;
    private ObservableList<Object> AllStudents;
    private String selectedClass;
    private String selectedGrade;
    private String selectedGender;
    private Object oldSelected;
    private Object box;

    private String newIdValew;
    private String newStrValew;
    private String selected_Student_Id;
    private ArrayList<String> arrayIndexStore;
    private int arrayIndexCounter;
    @FXML
    private TextField subject_8_col_3_box;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        refreshItemListInCombo();
        selectFirstOne();
        checkTextFields();

        // name choicebox listener
        choicebox_name.getSelectionModel().selectedItemProperty().addListener((v,
                oldIdValew, newStrValew) -> System.out.println("newValew string is: " + newStrValew));
        choicebox_name.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            public void changed(ObservableValue<? extends Number> v, Number oldValew, Number newIdValew) {
                System.out.println("newValew id is: " + newIdValew);

                if (newIdValew != null || (!"-1".equals(newIdValew))) {
                    Number strId = newIdValew;
                    if ((int) strId == -1) {

                    } else {
                        selected_Student_Id = getSelectedStdudent_id(strId);
                        System.out.println("selected_Student_Id is: " + selected_Student_Id);
                        startSelection(selected_Student_Id);
                    }
                }
            }
        }
        );

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
    private boolean onAddButtonClicked(ActionEvent event) {
        //insert into db if valid
        Date date = new Date();
        String theDate = date.toString();
        //String full_name = full_name_box.getText();
        String student_name = choicebox_name.getValue().toString();
        String subject_1 = choicebox_subject_1.getValue().toString();
        String subject_2 = choicebox_subject_2.getValue().toString();
        String subject_3 = choicebox_subject_3.getValue().toString();
        String subject_4 = choicebox_subject_4.getValue().toString();
        String subject_5 = choicebox_subject_5.getValue().toString();
        String subject_6 = choicebox_subject_6.getValue().toString();
        String subject_7 = choicebox_subject_7.getValue().toString();
        String subject_8 = choicebox_subject_8.getValue().toString();
        String subject_1_col_1 = subject_1_col_1_box.getText();
        String subject_2_col_1 = subject_2_col_1_box.getText();
        String subject_3_col_1 = subject_3_col_1_box.getText();
        String subject_4_col_1 = subject_4_col_1_box.getText();
        String subject_5_col_1 = subject_5_col_1_box.getText();
        String subject_6_col_1 = subject_6_col_1_box.getText();
        String subject_7_col_1 = subject_7_col_1_box.getText();
        String subject_8_col_1 = subject_8_col_1_box.getText();

        if ((student_name.trim().length() == 0) || (student_name == "") || (student_name.trim().isEmpty()) || (student_name == "--SELECT ONE--")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Select a Student.");

            alert.showAndWait();
            System.clearProperty(student_name);
            return false;
        }

        if ((!"--SELECT ONE--".equals(subject_1)) && (!"0".equals(subject_1_col_1))) {
            insert_subject_1();
        } else if ((!"--SELECT ONE--".equals(subject_2)) && (!"0".equals(subject_2_col_1))) {
            insert_subject_2();
        } else if ((!"--SELECT ONE--".equals(subject_3)) && (!"0".equals(subject_3_col_1))) {
            insert_subject_3();
        } else if ((!"--SELECT ONE--".equals(subject_4)) && (!"0".equals(subject_4_col_1))) {
            insert_subject_4();
        } else if ((!"--SELECT ONE--".equals(subject_5)) && (!"0".equals(subject_5_col_1))) {
            insert_subject_5();
        } else if ((!"--SELECT ONE--".equals(subject_6)) && (!"0".equals(subject_6_col_1))) {
            insert_subject_6();
        } else if ((!"--SELECT ONE--".equals(subject_7)) && (!"0".equals(subject_7_col_1))) {
            insert_subject_7();
        } else if ((!"--SELECT ONE--".equals(subject_8)) && (!"0".equals(subject_8_col_1))) {
            insert_subject_8();
        } else {
        }

        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
        alert3.setTitle("Information Dialog");
        alert3.setHeaderText(null);
        alert3.setContentText("Record(s) Added Succesfully.");

        alert3.showAndWait();

        System.out.println("All Succesfully Inserted");
        setColsToZero();
        clearAllChoiceSelections();

        return true;
    }

    @FXML
    private void onClearButtonClicked(ActionEvent event
    ) {
        subject_1_col_1_box.setText("0");
        subject_1_col_2_box.setText("0");
        subject_1_col_3_box.setText("0");
        subject_1_col_4_box.setText("0");
        subject_1_col_5_box.setText("0");
        subject_1_col_6_box.setText("0");
        subject_1_col_7_box.setText("0");

        subject_2_col_1_box.setText("0");
        subject_2_col_2_box.setText("0");
        subject_2_col_3_box.setText("0");
        subject_2_col_4_box.setText("0");
        subject_2_col_5_box.setText("0");
        subject_2_col_6_box.setText("0");
        subject_2_col_7_box.setText("0");

        subject_3_col_1_box.setText("0");
        subject_3_col_2_box.setText("0");
        subject_3_col_3_box.setText("0");
        subject_3_col_4_box.setText("0");
        subject_3_col_5_box.setText("0");
        subject_3_col_6_box.setText("0");
        subject_3_col_7_box.setText("0");

        subject_4_col_1_box.setText("0");
        subject_4_col_2_box.setText("0");
        subject_4_col_3_box.setText("0");
        subject_4_col_4_box.setText("0");
        subject_4_col_5_box.setText("0");
        subject_4_col_6_box.setText("0");
        subject_4_col_7_box.setText("0");

        subject_5_col_1_box.setText("0");
        subject_5_col_2_box.setText("0");
        subject_5_col_3_box.setText("0");
        subject_5_col_4_box.setText("0");
        subject_5_col_5_box.setText("0");
        subject_5_col_6_box.setText("0");
        subject_5_col_7_box.setText("0");

        subject_6_col_1_box.setText("0");
        subject_6_col_2_box.setText("0");
        subject_6_col_3_box.setText("0");
        subject_6_col_4_box.setText("0");
        subject_6_col_5_box.setText("0");
        subject_6_col_6_box.setText("0");
        subject_6_col_7_box.setText("0");

        subject_7_col_1_box.setText("0");
        subject_7_col_2_box.setText("0");
        subject_7_col_3_box.setText("0");
        subject_7_col_4_box.setText("0");
        subject_7_col_5_box.setText("0");
        subject_7_col_6_box.setText("0");
        subject_7_col_7_box.setText("0");

        subject_8_col_1_box.setText("0");
        subject_8_col_2_box.setText("0");
        subject_8_col_3_box.setText("0");
        subject_8_col_4_box.setText("0");
        subject_8_col_5_box.setText("0");
        subject_8_col_6_box.setText("0");
        subject_8_col_7_box.setText("0");

        clearAllChoiceSelections();
    }

    private void refreshItemListInCombo() {
        //Set items equal to an empty ArrayList
        allGenders = FXCollections.observableArrayList();
        allGrades = FXCollections.observableArrayList();
        allClassrooms = FXCollections.observableArrayList();
        AllSubjects = FXCollections.observableArrayList();
        AllStudents = FXCollections.observableArrayList();

        //Select out of the DB, fill accordingly
        getGenders(allGenders);
        getGrades(allGrades);
        getClassroom(allClassrooms);
        getAllSubjects(AllSubjects);
        getAllStudents(AllStudents);

        //Set the listview to what we just populated with DB contents
        choicebox_gender.setItems(allGenders);
        choicebox_grade.setItems(allGrades);
        choicebox_class.setItems(allClassrooms);
        choicebox_name.setItems(AllStudents);
        choicebox_subject_1.setItems(AllSubjects);
        choicebox_subject_2.setItems(AllSubjects);
        choicebox_subject_3.setItems(AllSubjects);
        choicebox_subject_4.setItems(AllSubjects);
        choicebox_subject_5.setItems(AllSubjects);
        choicebox_subject_6.setItems(AllSubjects);
        choicebox_subject_7.setItems(AllSubjects);
        choicebox_subject_8.setItems(AllSubjects);

    }

    private void selectFirstOne() {
        choicebox_gender.getSelectionModel().selectFirst();
        choicebox_grade.getSelectionModel().selectFirst();
        choicebox_class.getSelectionModel().selectFirst();
        choicebox_name.getSelectionModel().selectFirst();

        choicebox_subject_1.getSelectionModel().selectFirst();
        choicebox_subject_2.getSelectionModel().selectFirst();
        choicebox_subject_3.getSelectionModel().selectFirst();
        choicebox_subject_4.getSelectionModel().selectFirst();
        choicebox_subject_5.getSelectionModel().selectFirst();
        choicebox_subject_6.getSelectionModel().selectFirst();
        choicebox_subject_7.getSelectionModel().selectFirst();
        choicebox_subject_8.getSelectionModel().selectFirst();
    }

    private void getGenders(ObservableList<Object> allGenders) {
        allGenders.add("--NONE SELECTED--");
        allGenders.add("MALE");
        allGenders.add("FEMALE");
    }

    private void getGrades(ObservableList<Object> allGrades) {
        Connection c = null;
        Statement stmt = null;

        //orderby = sort_menubutton.getText();
        // ascdesc = ascdesc_menubutton.getText();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully aaaa");

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

    private void getClassroom(ObservableList<Object> allClassrooms) {
        Connection c = null;
        Statement stmt = null;

        //orderby = sort_menubutton.getText();
        // ascdesc = ascdesc_menubutton.getText();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully bbb");

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

    private void getAllSubjects(ObservableList<Object> AllSubjects) {
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
            AllSubjects.add("--NONE SELECTED--");
            int arrayIndexCounter = 0;
            ArrayList<String> arrayIndexStore = new ArrayList<String>();
            System.out.println("before while");

            while (rs.next()) {
                String subject_name = rs.getString("name");

                AllSubjects.add(subject_name);
            }
            rs.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    private void getAllStudents(ObservableList<Object> AllStudents) {
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
            AllStudents.add("--SELECT ONE--");
            arrayIndexCounter = 0;
            arrayIndexStore = new ArrayList<String>();
            System.out.println("before while");

            while (rs.next()) {
                String Student_firstname = rs.getString("first_name");
                String Student_lastname = rs.getString("last_name");
                String Student_full_name = Student_firstname + " " + Student_lastname;

                String Student_id = rs.getString("Student_id");
                System.out.println("Student_full_name = " + Student_full_name);
                System.out.println("the Student_id = " + Student_id);

                arrayIndexStore.add(arrayIndexCounter, Student_id);
                System.out.println("arraylist store is ;;;;;" + arrayIndexStore.get(arrayIndexCounter));
                arrayIndexCounter++;
                //IMPORTANT STATEMENT HERE:
                AllStudents.add(Student_full_name);
            }
            rs.close();
            stmt.close();
            c.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    private String getSelectedStdudent_id(Number idVal) {

        System.out.println("inside getSelectedStudentId BLOCK");
        Connection c = null;
        Statement stmt = null;
        String selectedStudentId = "";
        // int choiceIndex = choicebox_name.getSelectionModel().getSelectedIndex();

        System.out.println("Students choiceIndex is: " + idVal);

        //orderby = sort_menubutton.getText();
        // ascdesc = ascdesc_menubutton.getText();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully aa");

            //if (orderby.equals("Time")) { orderby = "TIMING";}
            //else if (orderby.equals("Title")) { orderby = "TITLE";}
            //else { orderby = "LOCATION";}
            String orderby = "student_id";
            int arraySize = arrayIndexStore.size();
            int intIdVal = (int) idVal;
            if (arraySize == intIdVal) {
                intIdVal = intIdVal + 2;
                arrayIndexStore.add(intIdVal + "");

            }
            String newString = arrayIndexStore.get((int) idVal);
            int newInt = Integer.parseInt(newString);
            if (newInt != 0) {
                newInt = newInt - 1;
            } else {
                newInt = 1;
            }
            System.out.println("Students newInt is: " + newInt);

            System.out.println("Query is: SELECT * FROM Student_tbl WHERE student_id = " + newInt + " ORDER BY " + orderby);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Student_tbl WHERE student_id = " + newInt + " ORDER BY " + orderby);

            while (rs.next()) {

                selectedStudentId = rs.getString("student_id");
                System.out.println("selectedStudentId = " + selectedStudentId);
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

    private void startSelection(String selected_Student_Id) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully ddd");

            //if (orderby.equals("Time")) { orderby = "TIMING";}
            //else if (orderby.equals("Title")) { orderby = "TITLE";}
            //else { orderby = "LOCATION";}
            String orderby = "student_id";

            System.out.println("Query is: SELECT * FROM Student_tbl WHERE student_id = " + selected_Student_Id + " ORDER BY " + orderby);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Student_tbl WHERE student_id = " + selected_Student_Id + " ORDER BY " + orderby);
//
//            System.out.println("Query is: SELECT * FROM Student_tbl WHERE student_id = " + arrayIndexStore.get(Integer.parseInt(selected_Student_Id)) + " ORDER BY " + orderby);
//            stmt = c.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM Student_tbl WHERE student_id = " + arrayIndexStore.get(Integer.parseInt(selected_Student_Id)) + " ORDER BY " + orderby);

            while (rs.next()) {

                selectedClass = rs.getString("classroom");
                selectedGrade = rs.getString("grade");
                selectedGender = rs.getString("gender");

                System.out.println("selectedClass = " + selectedClass);
                System.out.println("selectedGrade = " + selectedGrade);
                System.out.println("selectedGender = " + selectedGender);

                choicebox_gender.setValue(selectedGender);
                choicebox_class.setValue(selectedClass);
                choicebox_grade.setValue(selectedGrade);

            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    private void checkTextFields() {
        //subj 1 col 1
        subject_1_col_1_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_1_col_1_box.getText());
                col2 = Integer.parseInt(subject_1_col_2_box.getText());
                col3 = Integer.parseInt(subject_1_col_3_box.getText());
                col4 = Integer.parseInt(subject_1_col_4_box.getText());
                col5 = Integer.parseInt(subject_1_col_5_box.getText());
                col6 = Integer.parseInt(subject_1_col_6_box.getText());
                col7 = Integer.parseInt(subject_1_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_1_sum_box.setText(colSum + "");
                subject_1_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 2
        subject_1_col_2_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_1_col_1_box.getText());
                col2 = Integer.parseInt(subject_1_col_2_box.getText());
                col3 = Integer.parseInt(subject_1_col_3_box.getText());
                col4 = Integer.parseInt(subject_1_col_4_box.getText());
                col5 = Integer.parseInt(subject_1_col_5_box.getText());
                col6 = Integer.parseInt(subject_1_col_6_box.getText());
                col7 = Integer.parseInt(subject_1_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_1_sum_box.setText(colSum + "");
                subject_1_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
        //----------------------------end   
        //subj 1 col 3
        subject_1_col_3_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_1_col_1_box.getText());
                col2 = Integer.parseInt(subject_1_col_2_box.getText());
                col3 = Integer.parseInt(subject_1_col_3_box.getText());
                col4 = Integer.parseInt(subject_1_col_4_box.getText());
                col5 = Integer.parseInt(subject_1_col_5_box.getText());
                col6 = Integer.parseInt(subject_1_col_6_box.getText());
                col7 = Integer.parseInt(subject_1_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_1_sum_box.setText(colSum + "");
                subject_1_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 4
        subject_1_col_4_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_1_col_1_box.getText());
                col2 = Integer.parseInt(subject_1_col_2_box.getText());
                col3 = Integer.parseInt(subject_1_col_3_box.getText());
                col4 = Integer.parseInt(subject_1_col_4_box.getText());
                col5 = Integer.parseInt(subject_1_col_5_box.getText());
                col6 = Integer.parseInt(subject_1_col_6_box.getText());
                col7 = Integer.parseInt(subject_1_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_1_sum_box.setText(colSum + "");
                subject_1_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 5
        subject_1_col_5_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_1_col_1_box.getText());
                col2 = Integer.parseInt(subject_1_col_2_box.getText());
                col3 = Integer.parseInt(subject_1_col_3_box.getText());
                col4 = Integer.parseInt(subject_1_col_4_box.getText());
                col5 = Integer.parseInt(subject_1_col_5_box.getText());
                col6 = Integer.parseInt(subject_1_col_6_box.getText());
                col7 = Integer.parseInt(subject_1_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_1_sum_box.setText(colSum + "");
                subject_1_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 6
        subject_1_col_6_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_1_col_1_box.getText());
                col2 = Integer.parseInt(subject_1_col_2_box.getText());
                col3 = Integer.parseInt(subject_1_col_3_box.getText());
                col4 = Integer.parseInt(subject_1_col_4_box.getText());
                col5 = Integer.parseInt(subject_1_col_5_box.getText());
                col6 = Integer.parseInt(subject_1_col_6_box.getText());
                col7 = Integer.parseInt(subject_1_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_1_sum_box.setText(colSum + "");
                subject_1_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
        //----------------------------end   
        //subj 1 col 7
        subject_1_col_7_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_1_col_1_box.getText());
                col2 = Integer.parseInt(subject_1_col_2_box.getText());
                col3 = Integer.parseInt(subject_1_col_3_box.getText());
                col4 = Integer.parseInt(subject_1_col_4_box.getText());
                col5 = Integer.parseInt(subject_1_col_5_box.getText());
                col6 = Integer.parseInt(subject_1_col_6_box.getText());
                col7 = Integer.parseInt(subject_1_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_1_sum_box.setText(colSum + "");
                subject_1_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
        //----------------------------end   
        //subj 1 col 1
        subject_2_col_1_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_2_col_1_box.getText());
                col2 = Integer.parseInt(subject_2_col_2_box.getText());
                col3 = Integer.parseInt(subject_2_col_3_box.getText());
                col4 = Integer.parseInt(subject_2_col_4_box.getText());
                col5 = Integer.parseInt(subject_2_col_5_box.getText());
                col6 = Integer.parseInt(subject_2_col_6_box.getText());
                col7 = Integer.parseInt(subject_2_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_2_sum_box.setText(colSum + "");
                subject_2_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 2
        subject_2_col_2_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_2_col_1_box.getText());
                col2 = Integer.parseInt(subject_2_col_2_box.getText());
                col3 = Integer.parseInt(subject_2_col_3_box.getText());
                col4 = Integer.parseInt(subject_2_col_4_box.getText());
                col5 = Integer.parseInt(subject_2_col_5_box.getText());
                col6 = Integer.parseInt(subject_2_col_6_box.getText());
                col7 = Integer.parseInt(subject_2_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_2_sum_box.setText(colSum + "");
                subject_2_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
        //----------------------------end   
        //subj 1 col 3
        subject_2_col_3_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_2_col_1_box.getText());
                col2 = Integer.parseInt(subject_2_col_2_box.getText());
                col3 = Integer.parseInt(subject_2_col_3_box.getText());
                col4 = Integer.parseInt(subject_2_col_4_box.getText());
                col5 = Integer.parseInt(subject_2_col_5_box.getText());
                col6 = Integer.parseInt(subject_2_col_6_box.getText());
                col7 = Integer.parseInt(subject_2_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_2_sum_box.setText(colSum + "");
                subject_2_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 4
        subject_2_col_4_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_2_col_1_box.getText());
                col2 = Integer.parseInt(subject_2_col_2_box.getText());
                col3 = Integer.parseInt(subject_2_col_3_box.getText());
                col4 = Integer.parseInt(subject_2_col_4_box.getText());
                col5 = Integer.parseInt(subject_2_col_5_box.getText());
                col6 = Integer.parseInt(subject_2_col_6_box.getText());
                col7 = Integer.parseInt(subject_2_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_2_sum_box.setText(colSum + "");
                subject_2_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 5
        subject_2_col_5_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_2_col_1_box.getText());
                col2 = Integer.parseInt(subject_2_col_2_box.getText());
                col3 = Integer.parseInt(subject_2_col_3_box.getText());
                col4 = Integer.parseInt(subject_2_col_4_box.getText());
                col5 = Integer.parseInt(subject_2_col_5_box.getText());
                col6 = Integer.parseInt(subject_2_col_6_box.getText());
                col7 = Integer.parseInt(subject_2_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_2_sum_box.setText(colSum + "");
                subject_2_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 6
        subject_2_col_6_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_2_col_1_box.getText());
                col2 = Integer.parseInt(subject_2_col_2_box.getText());
                col3 = Integer.parseInt(subject_2_col_3_box.getText());
                col4 = Integer.parseInt(subject_2_col_4_box.getText());
                col5 = Integer.parseInt(subject_2_col_5_box.getText());
                col6 = Integer.parseInt(subject_2_col_6_box.getText());
                col7 = Integer.parseInt(subject_2_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_2_sum_box.setText(colSum + "");
                subject_2_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
        //----------------------------end   
        //subj 1 col 7
        subject_2_col_7_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_2_col_1_box.getText());
                col2 = Integer.parseInt(subject_2_col_2_box.getText());
                col3 = Integer.parseInt(subject_2_col_3_box.getText());
                col4 = Integer.parseInt(subject_2_col_4_box.getText());
                col5 = Integer.parseInt(subject_2_col_5_box.getText());
                col6 = Integer.parseInt(subject_2_col_6_box.getText());
                col7 = Integer.parseInt(subject_2_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_2_sum_box.setText(colSum + "");
                subject_2_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
        //----------------------------end   
        //subj 1 col 1
        subject_3_col_1_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_3_col_1_box.getText());
                col2 = Integer.parseInt(subject_3_col_2_box.getText());
                col3 = Integer.parseInt(subject_3_col_3_box.getText());
                col4 = Integer.parseInt(subject_3_col_4_box.getText());
                col5 = Integer.parseInt(subject_3_col_5_box.getText());
                col6 = Integer.parseInt(subject_3_col_6_box.getText());
                col7 = Integer.parseInt(subject_3_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_3_sum_box.setText(colSum + "");
                subject_3_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 2
        subject_3_col_2_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_3_col_1_box.getText());
                col2 = Integer.parseInt(subject_3_col_2_box.getText());
                col3 = Integer.parseInt(subject_3_col_3_box.getText());
                col4 = Integer.parseInt(subject_3_col_4_box.getText());
                col5 = Integer.parseInt(subject_3_col_5_box.getText());
                col6 = Integer.parseInt(subject_3_col_6_box.getText());
                col7 = Integer.parseInt(subject_3_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_3_sum_box.setText(colSum + "");
                subject_3_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
        //----------------------------end   
        //subj 1 col 3
        subject_3_col_3_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_3_col_1_box.getText());
                col2 = Integer.parseInt(subject_3_col_2_box.getText());
                col3 = Integer.parseInt(subject_3_col_3_box.getText());
                col4 = Integer.parseInt(subject_3_col_4_box.getText());
                col5 = Integer.parseInt(subject_3_col_5_box.getText());
                col6 = Integer.parseInt(subject_3_col_6_box.getText());
                col7 = Integer.parseInt(subject_3_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_3_sum_box.setText(colSum + "");
                subject_3_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 4
        subject_3_col_4_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_3_col_1_box.getText());
                col2 = Integer.parseInt(subject_3_col_2_box.getText());
                col3 = Integer.parseInt(subject_3_col_3_box.getText());
                col4 = Integer.parseInt(subject_3_col_4_box.getText());
                col5 = Integer.parseInt(subject_3_col_5_box.getText());
                col6 = Integer.parseInt(subject_3_col_6_box.getText());
                col7 = Integer.parseInt(subject_3_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_3_sum_box.setText(colSum + "");
                subject_3_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 5
        subject_3_col_5_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_3_col_1_box.getText());
                col2 = Integer.parseInt(subject_3_col_2_box.getText());
                col3 = Integer.parseInt(subject_3_col_3_box.getText());
                col4 = Integer.parseInt(subject_3_col_4_box.getText());
                col5 = Integer.parseInt(subject_3_col_5_box.getText());
                col6 = Integer.parseInt(subject_3_col_6_box.getText());
                col7 = Integer.parseInt(subject_3_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_3_sum_box.setText(colSum + "");
                subject_3_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 6
        subject_3_col_6_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_3_col_1_box.getText());
                col2 = Integer.parseInt(subject_3_col_2_box.getText());
                col3 = Integer.parseInt(subject_3_col_3_box.getText());
                col4 = Integer.parseInt(subject_3_col_4_box.getText());
                col5 = Integer.parseInt(subject_3_col_5_box.getText());
                col6 = Integer.parseInt(subject_3_col_6_box.getText());
                col7 = Integer.parseInt(subject_3_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_3_sum_box.setText(colSum + "");
                subject_3_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
        //----------------------------end   
        //subj 1 col 7
        subject_3_col_7_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_3_col_1_box.getText());
                col2 = Integer.parseInt(subject_3_col_2_box.getText());
                col3 = Integer.parseInt(subject_3_col_3_box.getText());
                col4 = Integer.parseInt(subject_3_col_4_box.getText());
                col5 = Integer.parseInt(subject_3_col_5_box.getText());
                col6 = Integer.parseInt(subject_3_col_6_box.getText());
                col7 = Integer.parseInt(subject_3_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_3_sum_box.setText(colSum + "");
                subject_3_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
        //----------------------------end   
        //subj 1 col 1
        subject_4_col_1_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_4_col_1_box.getText());
                col2 = Integer.parseInt(subject_4_col_2_box.getText());
                col3 = Integer.parseInt(subject_4_col_3_box.getText());
                col4 = Integer.parseInt(subject_4_col_4_box.getText());
                col5 = Integer.parseInt(subject_4_col_5_box.getText());
                col6 = Integer.parseInt(subject_4_col_6_box.getText());
                col7 = Integer.parseInt(subject_4_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_4_sum_box.setText(colSum + "");
                subject_4_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 2
        subject_4_col_2_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_4_col_1_box.getText());
                col2 = Integer.parseInt(subject_4_col_2_box.getText());
                col3 = Integer.parseInt(subject_4_col_3_box.getText());
                col4 = Integer.parseInt(subject_4_col_4_box.getText());
                col5 = Integer.parseInt(subject_4_col_5_box.getText());
                col6 = Integer.parseInt(subject_4_col_6_box.getText());
                col7 = Integer.parseInt(subject_4_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_4_sum_box.setText(colSum + "");
                subject_4_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
        //----------------------------end   
        //subj 1 col 3
        subject_4_col_3_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_4_col_1_box.getText());
                col2 = Integer.parseInt(subject_4_col_2_box.getText());
                col3 = Integer.parseInt(subject_4_col_3_box.getText());
                col4 = Integer.parseInt(subject_4_col_4_box.getText());
                col5 = Integer.parseInt(subject_4_col_5_box.getText());
                col6 = Integer.parseInt(subject_4_col_6_box.getText());
                col7 = Integer.parseInt(subject_4_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_4_sum_box.setText(colSum + "");
                subject_4_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 4
        subject_4_col_4_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_4_col_1_box.getText());
                col2 = Integer.parseInt(subject_4_col_2_box.getText());
                col3 = Integer.parseInt(subject_4_col_3_box.getText());
                col4 = Integer.parseInt(subject_4_col_4_box.getText());
                col5 = Integer.parseInt(subject_4_col_5_box.getText());
                col6 = Integer.parseInt(subject_4_col_6_box.getText());
                col7 = Integer.parseInt(subject_4_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_4_sum_box.setText(colSum + "");
                subject_4_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 5
        subject_4_col_5_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_4_col_1_box.getText());
                col2 = Integer.parseInt(subject_4_col_2_box.getText());
                col3 = Integer.parseInt(subject_4_col_3_box.getText());
                col4 = Integer.parseInt(subject_4_col_4_box.getText());
                col5 = Integer.parseInt(subject_4_col_5_box.getText());
                col6 = Integer.parseInt(subject_4_col_6_box.getText());
                col7 = Integer.parseInt(subject_4_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_4_sum_box.setText(colSum + "");
                subject_4_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 6
        subject_4_col_6_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_4_col_1_box.getText());
                col2 = Integer.parseInt(subject_4_col_2_box.getText());
                col3 = Integer.parseInt(subject_4_col_3_box.getText());
                col4 = Integer.parseInt(subject_4_col_4_box.getText());
                col5 = Integer.parseInt(subject_4_col_5_box.getText());
                col6 = Integer.parseInt(subject_4_col_6_box.getText());
                col7 = Integer.parseInt(subject_4_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_4_sum_box.setText(colSum + "");
                subject_4_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
        //----------------------------end   
        //subj 1 col 7
        subject_4_col_7_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_4_col_1_box.getText());
                col2 = Integer.parseInt(subject_4_col_2_box.getText());
                col3 = Integer.parseInt(subject_4_col_3_box.getText());
                col4 = Integer.parseInt(subject_4_col_4_box.getText());
                col5 = Integer.parseInt(subject_4_col_5_box.getText());
                col6 = Integer.parseInt(subject_4_col_6_box.getText());
                col7 = Integer.parseInt(subject_4_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_4_sum_box.setText(colSum + "");
                subject_4_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 1
        subject_5_col_1_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_5_col_1_box.getText());
                col2 = Integer.parseInt(subject_5_col_2_box.getText());
                col3 = Integer.parseInt(subject_5_col_3_box.getText());
                col4 = Integer.parseInt(subject_5_col_4_box.getText());
                col5 = Integer.parseInt(subject_5_col_5_box.getText());
                col6 = Integer.parseInt(subject_5_col_6_box.getText());
                col7 = Integer.parseInt(subject_5_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_5_sum_box.setText(colSum + "");
                subject_5_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 2
        subject_5_col_2_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_5_col_1_box.getText());
                col2 = Integer.parseInt(subject_5_col_2_box.getText());
                col3 = Integer.parseInt(subject_5_col_3_box.getText());
                col4 = Integer.parseInt(subject_5_col_4_box.getText());
                col5 = Integer.parseInt(subject_5_col_5_box.getText());
                col6 = Integer.parseInt(subject_5_col_6_box.getText());
                col7 = Integer.parseInt(subject_5_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_5_sum_box.setText(colSum + "");
                subject_5_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
        //----------------------------end   
        //subj 1 col 3
        subject_5_col_3_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_5_col_1_box.getText());
                col2 = Integer.parseInt(subject_5_col_2_box.getText());
                col3 = Integer.parseInt(subject_5_col_3_box.getText());
                col4 = Integer.parseInt(subject_5_col_4_box.getText());
                col5 = Integer.parseInt(subject_5_col_5_box.getText());
                col6 = Integer.parseInt(subject_5_col_6_box.getText());
                col7 = Integer.parseInt(subject_5_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_5_sum_box.setText(colSum + "");
                subject_5_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 4
        subject_5_col_4_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_5_col_1_box.getText());
                col2 = Integer.parseInt(subject_5_col_2_box.getText());
                col3 = Integer.parseInt(subject_5_col_3_box.getText());
                col4 = Integer.parseInt(subject_5_col_4_box.getText());
                col5 = Integer.parseInt(subject_5_col_5_box.getText());
                col6 = Integer.parseInt(subject_5_col_6_box.getText());
                col7 = Integer.parseInt(subject_5_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_5_sum_box.setText(colSum + "");
                subject_5_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 5
        subject_5_col_5_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_5_col_1_box.getText());
                col2 = Integer.parseInt(subject_5_col_2_box.getText());
                col3 = Integer.parseInt(subject_5_col_3_box.getText());
                col4 = Integer.parseInt(subject_5_col_4_box.getText());
                col5 = Integer.parseInt(subject_5_col_5_box.getText());
                col6 = Integer.parseInt(subject_5_col_6_box.getText());
                col7 = Integer.parseInt(subject_5_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_5_sum_box.setText(colSum + "");
                subject_5_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 6
        subject_5_col_6_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_5_col_1_box.getText());
                col2 = Integer.parseInt(subject_5_col_2_box.getText());
                col3 = Integer.parseInt(subject_5_col_3_box.getText());
                col4 = Integer.parseInt(subject_5_col_4_box.getText());
                col5 = Integer.parseInt(subject_5_col_5_box.getText());
                col6 = Integer.parseInt(subject_5_col_6_box.getText());
                col7 = Integer.parseInt(subject_5_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_5_sum_box.setText(colSum + "");
                subject_5_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
        //----------------------------end   
        //subj 1 col 7
        subject_5_col_7_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_5_col_1_box.getText());
                col2 = Integer.parseInt(subject_5_col_2_box.getText());
                col3 = Integer.parseInt(subject_5_col_3_box.getText());
                col4 = Integer.parseInt(subject_5_col_4_box.getText());
                col5 = Integer.parseInt(subject_5_col_5_box.getText());
                col6 = Integer.parseInt(subject_5_col_6_box.getText());
                col7 = Integer.parseInt(subject_5_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_5_sum_box.setText(colSum + "");
                subject_5_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
        //----------------------------end   
        //subj 1 col 1
        subject_6_col_1_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_6_col_1_box.getText());
                col2 = Integer.parseInt(subject_6_col_2_box.getText());
                col3 = Integer.parseInt(subject_6_col_3_box.getText());
                col4 = Integer.parseInt(subject_6_col_4_box.getText());
                col5 = Integer.parseInt(subject_6_col_5_box.getText());
                col6 = Integer.parseInt(subject_6_col_6_box.getText());
                col7 = Integer.parseInt(subject_6_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_6_sum_box.setText(colSum + "");
                subject_6_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 2
        subject_6_col_2_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_6_col_1_box.getText());
                col2 = Integer.parseInt(subject_6_col_2_box.getText());
                col3 = Integer.parseInt(subject_6_col_3_box.getText());
                col4 = Integer.parseInt(subject_6_col_4_box.getText());
                col5 = Integer.parseInt(subject_6_col_5_box.getText());
                col6 = Integer.parseInt(subject_6_col_6_box.getText());
                col7 = Integer.parseInt(subject_6_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_6_sum_box.setText(colSum + "");
                subject_6_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
        //----------------------------end   
        //subj 1 col 3
        subject_6_col_3_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_6_col_1_box.getText());
                col2 = Integer.parseInt(subject_6_col_2_box.getText());
                col3 = Integer.parseInt(subject_6_col_3_box.getText());
                col4 = Integer.parseInt(subject_6_col_4_box.getText());
                col5 = Integer.parseInt(subject_6_col_5_box.getText());
                col6 = Integer.parseInt(subject_6_col_6_box.getText());
                col7 = Integer.parseInt(subject_6_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_6_sum_box.setText(colSum + "");
                subject_6_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 4
        subject_6_col_4_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_6_col_1_box.getText());
                col2 = Integer.parseInt(subject_6_col_2_box.getText());
                col3 = Integer.parseInt(subject_6_col_3_box.getText());
                col4 = Integer.parseInt(subject_6_col_4_box.getText());
                col5 = Integer.parseInt(subject_6_col_5_box.getText());
                col6 = Integer.parseInt(subject_6_col_6_box.getText());
                col7 = Integer.parseInt(subject_6_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_6_sum_box.setText(colSum + "");
                subject_6_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 5
        subject_6_col_5_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_6_col_1_box.getText());
                col2 = Integer.parseInt(subject_6_col_2_box.getText());
                col3 = Integer.parseInt(subject_6_col_3_box.getText());
                col4 = Integer.parseInt(subject_6_col_4_box.getText());
                col5 = Integer.parseInt(subject_6_col_5_box.getText());
                col6 = Integer.parseInt(subject_6_col_6_box.getText());
                col7 = Integer.parseInt(subject_6_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_6_sum_box.setText(colSum + "");
                subject_6_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 6
        subject_6_col_6_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_6_col_1_box.getText());
                col2 = Integer.parseInt(subject_6_col_2_box.getText());
                col3 = Integer.parseInt(subject_6_col_3_box.getText());
                col4 = Integer.parseInt(subject_6_col_4_box.getText());
                col5 = Integer.parseInt(subject_6_col_5_box.getText());
                col6 = Integer.parseInt(subject_6_col_6_box.getText());
                col7 = Integer.parseInt(subject_6_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_6_sum_box.setText(colSum + "");
                subject_6_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
        //----------------------------end   
        //subj 1 col 7
        subject_6_col_7_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_6_col_1_box.getText());
                col2 = Integer.parseInt(subject_6_col_2_box.getText());
                col3 = Integer.parseInt(subject_6_col_3_box.getText());
                col4 = Integer.parseInt(subject_6_col_4_box.getText());
                col5 = Integer.parseInt(subject_6_col_5_box.getText());
                col6 = Integer.parseInt(subject_6_col_6_box.getText());
                col7 = Integer.parseInt(subject_6_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_6_sum_box.setText(colSum + "");
                subject_6_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
        //----------------------------end   
        //subj 1 col 1
        subject_7_col_1_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_7_col_1_box.getText());
                col2 = Integer.parseInt(subject_7_col_2_box.getText());
                col3 = Integer.parseInt(subject_7_col_3_box.getText());
                col4 = Integer.parseInt(subject_7_col_4_box.getText());
                col5 = Integer.parseInt(subject_7_col_5_box.getText());
                col6 = Integer.parseInt(subject_7_col_6_box.getText());
                col7 = Integer.parseInt(subject_7_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_7_sum_box.setText(colSum + "");
                subject_7_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 2
        subject_7_col_2_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_7_col_1_box.getText());
                col2 = Integer.parseInt(subject_7_col_2_box.getText());
                col3 = Integer.parseInt(subject_7_col_3_box.getText());
                col4 = Integer.parseInt(subject_7_col_4_box.getText());
                col5 = Integer.parseInt(subject_7_col_5_box.getText());
                col6 = Integer.parseInt(subject_7_col_6_box.getText());
                col7 = Integer.parseInt(subject_7_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_7_sum_box.setText(colSum + "");
                subject_7_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
        //----------------------------end   
        //subj 1 col 3
        subject_7_col_3_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_7_col_1_box.getText());
                col2 = Integer.parseInt(subject_7_col_2_box.getText());
                col3 = Integer.parseInt(subject_7_col_3_box.getText());
                col4 = Integer.parseInt(subject_7_col_4_box.getText());
                col5 = Integer.parseInt(subject_7_col_5_box.getText());
                col6 = Integer.parseInt(subject_7_col_6_box.getText());
                col7 = Integer.parseInt(subject_7_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_7_sum_box.setText(colSum + "");
                subject_7_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 4
        subject_7_col_4_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_7_col_1_box.getText());
                col2 = Integer.parseInt(subject_7_col_2_box.getText());
                col3 = Integer.parseInt(subject_7_col_3_box.getText());
                col4 = Integer.parseInt(subject_7_col_4_box.getText());
                col5 = Integer.parseInt(subject_7_col_5_box.getText());
                col6 = Integer.parseInt(subject_7_col_6_box.getText());
                col7 = Integer.parseInt(subject_7_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_7_sum_box.setText(colSum + "");
                subject_7_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 5
        subject_7_col_5_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_7_col_1_box.getText());
                col2 = Integer.parseInt(subject_7_col_2_box.getText());
                col3 = Integer.parseInt(subject_7_col_3_box.getText());
                col4 = Integer.parseInt(subject_7_col_4_box.getText());
                col5 = Integer.parseInt(subject_7_col_5_box.getText());
                col6 = Integer.parseInt(subject_7_col_6_box.getText());
                col7 = Integer.parseInt(subject_7_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_7_sum_box.setText(colSum + "");
                subject_7_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 6
        subject_7_col_6_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_7_col_1_box.getText());
                col2 = Integer.parseInt(subject_7_col_2_box.getText());
                col3 = Integer.parseInt(subject_7_col_3_box.getText());
                col4 = Integer.parseInt(subject_7_col_4_box.getText());
                col5 = Integer.parseInt(subject_7_col_5_box.getText());
                col6 = Integer.parseInt(subject_7_col_6_box.getText());
                col7 = Integer.parseInt(subject_7_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_7_sum_box.setText(colSum + "");
                subject_7_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
        //----------------------------end   
        //subj 1 col 7
        subject_7_col_7_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_7_col_1_box.getText());
                col2 = Integer.parseInt(subject_7_col_2_box.getText());
                col3 = Integer.parseInt(subject_7_col_3_box.getText());
                col4 = Integer.parseInt(subject_7_col_4_box.getText());
                col5 = Integer.parseInt(subject_7_col_5_box.getText());
                col6 = Integer.parseInt(subject_7_col_6_box.getText());
                col7 = Integer.parseInt(subject_7_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_7_sum_box.setText(colSum + "");
                subject_7_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
        //----------------------------end   
        //subj 1 col 1
        subject_8_col_1_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_8_col_1_box.getText());
                col2 = Integer.parseInt(subject_8_col_2_box.getText());
                col3 = Integer.parseInt(subject_8_col_3_box.getText());
                col4 = Integer.parseInt(subject_8_col_4_box.getText());
                col5 = Integer.parseInt(subject_8_col_5_box.getText());
                col6 = Integer.parseInt(subject_8_col_6_box.getText());
                col7 = Integer.parseInt(subject_8_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_8_sum_box.setText(colSum + "");
                subject_8_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 2
        subject_8_col_2_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_8_col_1_box.getText());
                col2 = Integer.parseInt(subject_8_col_2_box.getText());
                col3 = Integer.parseInt(subject_8_col_3_box.getText());
                col4 = Integer.parseInt(subject_8_col_4_box.getText());
                col5 = Integer.parseInt(subject_8_col_5_box.getText());
                col6 = Integer.parseInt(subject_8_col_6_box.getText());
                col7 = Integer.parseInt(subject_8_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_8_sum_box.setText(colSum + "");
                subject_8_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
        //----------------------------end   
        //subj 1 col 3
        subject_8_col_3_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_8_col_1_box.getText());
                col2 = Integer.parseInt(subject_8_col_2_box.getText());
                col3 = Integer.parseInt(subject_8_col_3_box.getText());
                col4 = Integer.parseInt(subject_8_col_4_box.getText());
                col5 = Integer.parseInt(subject_8_col_5_box.getText());
                col6 = Integer.parseInt(subject_8_col_6_box.getText());
                col7 = Integer.parseInt(subject_8_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_8_sum_box.setText(colSum + "");
                subject_8_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 4
        subject_8_col_4_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_8_col_1_box.getText());
                col2 = Integer.parseInt(subject_8_col_2_box.getText());
                col3 = Integer.parseInt(subject_8_col_3_box.getText());
                col4 = Integer.parseInt(subject_8_col_4_box.getText());
                col5 = Integer.parseInt(subject_8_col_5_box.getText());
                col6 = Integer.parseInt(subject_8_col_6_box.getText());
                col7 = Integer.parseInt(subject_8_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_8_sum_box.setText(colSum + "");
                subject_8_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 5
        subject_8_col_5_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_8_col_1_box.getText());
                col2 = Integer.parseInt(subject_8_col_2_box.getText());
                col3 = Integer.parseInt(subject_8_col_3_box.getText());
                col4 = Integer.parseInt(subject_8_col_4_box.getText());
                col5 = Integer.parseInt(subject_8_col_5_box.getText());
                col6 = Integer.parseInt(subject_8_col_6_box.getText());
                col7 = Integer.parseInt(subject_8_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_8_sum_box.setText(colSum + "");
                subject_8_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
		//----------------------------end   

        //subj 1 col 6
        subject_8_col_6_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_8_col_1_box.getText());
                col2 = Integer.parseInt(subject_8_col_2_box.getText());
                col3 = Integer.parseInt(subject_8_col_3_box.getText());
                col4 = Integer.parseInt(subject_8_col_4_box.getText());
                col5 = Integer.parseInt(subject_8_col_5_box.getText());
                col6 = Integer.parseInt(subject_8_col_6_box.getText());
                col7 = Integer.parseInt(subject_8_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_8_sum_box.setText(colSum + "");
                subject_8_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
        //----------------------------end   
        //subj 1 col 7
        subject_8_col_7_box.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                System.out.println(" Text Changed to  " + newValue + ")\n");
                int col1, col2, col3, col4, col5, col6, col7, colAvg, colSum;
                col1 = Integer.parseInt(subject_8_col_1_box.getText());
                col2 = Integer.parseInt(subject_8_col_2_box.getText());
                col3 = Integer.parseInt(subject_8_col_3_box.getText());
                col4 = Integer.parseInt(subject_8_col_4_box.getText());
                col5 = Integer.parseInt(subject_8_col_5_box.getText());
                col6 = Integer.parseInt(subject_8_col_6_box.getText());
                col7 = Integer.parseInt(subject_8_col_7_box.getText());

                int someArray[] = {col1, col2, col3, col4, col5, col6, col7};
                colSum = 0;
                colAvg = 0;
                for (int i : someArray) {
                    colSum += i;
                }
                colAvg = colSum / 7;
                subject_8_sum_box.setText(colSum + "");
                subject_8_avg_box.setText(colAvg + "");
                System.out.println("Calculated ");
                System.out.println("colSum is " + colSum);
                System.out.println("colAvg is  " + colAvg);
            }
        });
        //----------------------------end   

    }

    private void insert_subject_1() {
        Date date = new Date();
        String theDate = date.toString();
        String txnMonth = theDate.substring(4, 7);
        String txnYear = theDate.substring(24, 28);
        String txnDay = theDate.substring(0, 10) + " " + txnYear;
        //format for report queries
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        cal.add(Calendar.DATE, 0);
        String strDateInFormat = dateFormat.format(cal.getTime());
        System.out.println("strDateInFormat is: " + strDateInFormat);

        String query = "INSERT INTO Scores_tbl(student_name,student_id,gender,classroom,gradelevel,subject,"
                + "col1,col2,col3,col4,col5,col6,col7,colavg,colsum,formated_date,scores_date"
                + " ) VALUES (" + "'" + choicebox_name.getValue() + "','"
                + selected_Student_Id + "','" + choicebox_gender.getValue() + "','" + choicebox_class.getValue() + "','"
                + choicebox_grade.getValue() + "','" + choicebox_subject_1.getValue() + "','" + subject_1_col_1_box.getText() + "','"
                + subject_1_col_2_box.getText() + "','" + subject_1_col_3_box.getText() + "','" + subject_1_col_4_box.getText() + "','"
                + subject_1_col_5_box.getText() + "','" + subject_1_col_6_box.getText() + "','" + subject_1_col_7_box.getText() + "','"
                + subject_1_avg_box.getText() + "','" + subject_1_sum_box.getText() + "','" + strDateInFormat + "','" + theDate + "');";

        System.out.println("Inserting\n" + query);
        insertStatement(query);

    }

    private void insert_subject_2() {
        Date date = new Date();
        String theDate = date.toString();
        String txnMonth = theDate.substring(4, 7);
        String txnYear = theDate.substring(24, 28);
        String txnDay = theDate.substring(0, 10) + " " + txnYear;
        //format for report queries
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        cal.add(Calendar.DATE, 0);
        String strDateInFormat = dateFormat.format(cal.getTime());
        System.out.println("strDateInFormat is: " + strDateInFormat);

        String query = "INSERT INTO Scores_tbl(student_name,student_id,gender,classroom,gradelevel,subject,"
                + "col1,col2,col3,col4,col5,col6,col7,colavg,colsum,formated_date,scores_date"
                + " ) VALUES (" + "'" + choicebox_name.getValue() + "','"
                + selected_Student_Id + "','" + choicebox_gender.getValue() + "','" + choicebox_class.getValue() + "','"
                + choicebox_grade.getValue() + "','" + choicebox_subject_2.getValue() + "','" + subject_2_col_1_box.getText() + "','"
                + subject_2_col_2_box.getText() + "','" + subject_2_col_3_box.getText() + "','" + subject_2_col_4_box.getText() + "','"
                + subject_2_col_5_box.getText() + "','" + subject_2_col_6_box.getText() + "','" + subject_2_col_7_box.getText() + "','"
                + subject_2_avg_box.getText() + "','" + subject_2_sum_box.getText() + "','" + strDateInFormat + "','" + theDate + "');";

        System.out.println("Inserting\n" + query);
        insertStatement(query);

    }

    private void insert_subject_3() {
        Date date = new Date();
        String theDate = date.toString();
        String txnMonth = theDate.substring(4, 7);
        String txnYear = theDate.substring(24, 28);
        String txnDay = theDate.substring(0, 10) + " " + txnYear;
        //format for report queries
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        cal.add(Calendar.DATE, 0);
        String strDateInFormat = dateFormat.format(cal.getTime());
        System.out.println("strDateInFormat is: " + strDateInFormat);

        String query = "INSERT INTO Scores_tbl(student_name,student_id,gender,classroom,gradelevel,subject,"
                + "col1,col2,col3,col4,col5,col6,col7,colavg,colsum,formated_date,scores_date"
                + " ) VALUES (" + "'" + choicebox_name.getValue() + "','"
                + selected_Student_Id + "','" + choicebox_gender.getValue() + "','" + choicebox_class.getValue() + "','"
                + choicebox_grade.getValue() + "','" + choicebox_subject_3.getValue() + "','" + subject_3_col_1_box.getText() + "','"
                + subject_3_col_2_box.getText() + "','" + subject_3_col_3_box.getText() + "','" + subject_3_col_4_box.getText() + "','"
                + subject_3_col_5_box.getText() + "','" + subject_3_col_6_box.getText() + "','" + subject_3_col_7_box.getText() + "','"
                + subject_3_avg_box.getText() + "','" + subject_3_sum_box.getText() + "','" + strDateInFormat + "','" + theDate + "');";

        System.out.println("Inserting\n" + query);
        insertStatement(query);

    }

    private void insert_subject_4() {
        Date date = new Date();
        String theDate = date.toString();
        String txnMonth = theDate.substring(4, 7);
        String txnYear = theDate.substring(24, 28);
        String txnDay = theDate.substring(0, 10) + " " + txnYear;
        //format for report queries
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        cal.add(Calendar.DATE, 0);
        String strDateInFormat = dateFormat.format(cal.getTime());
        System.out.println("strDateInFormat is: " + strDateInFormat);

        String query = "INSERT INTO Scores_tbl(student_name,student_id,gender,classroom,gradelevel,subject,"
                + "col1,col2,col3,col4,col5,col6,col7,colavg,colsum,formated_date,scores_date"
                + " ) VALUES (" + "'" + choicebox_name.getValue() + "','"
                + selected_Student_Id + "','" + choicebox_gender.getValue() + "','" + choicebox_class.getValue() + "','"
                + choicebox_grade.getValue() + "','" + choicebox_subject_4.getValue() + "','" + subject_4_col_1_box.getText() + "','"
                + subject_4_col_2_box.getText() + "','" + subject_4_col_3_box.getText() + "','" + subject_4_col_4_box.getText() + "','"
                + subject_4_col_5_box.getText() + "','" + subject_4_col_6_box.getText() + "','" + subject_4_col_7_box.getText() + "','"
                + subject_4_avg_box.getText() + "','" + subject_4_sum_box.getText() + "','" + strDateInFormat + "','" + theDate + "');";

        System.out.println("Inserting\n" + query);
        insertStatement(query);

    }

    private void insert_subject_5() {
        Date date = new Date();
        String theDate = date.toString();
        String txnMonth = theDate.substring(4, 7);
        String txnYear = theDate.substring(24, 28);
        String txnDay = theDate.substring(0, 10) + " " + txnYear;
        //format for report queries
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        cal.add(Calendar.DATE, 0);
        String strDateInFormat = dateFormat.format(cal.getTime());
        System.out.println("strDateInFormat is: " + strDateInFormat);

        String query = "INSERT INTO Scores_tbl(student_name,student_id,gender,classroom,gradelevel,subject,"
                + "col1,col2,col3,col4,col5,col6,col7,colavg,colsum,formated_date,scores_date"
                + " ) VALUES (" + "'" + choicebox_name.getValue() + "','"
                + selected_Student_Id + "','" + choicebox_gender.getValue() + "','" + choicebox_class.getValue() + "','"
                + choicebox_grade.getValue() + "','" + choicebox_subject_5.getValue() + "','" + subject_5_col_1_box.getText() + "','"
                + subject_5_col_2_box.getText() + "','" + subject_5_col_3_box.getText() + "','" + subject_5_col_4_box.getText() + "','"
                + subject_5_col_5_box.getText() + "','" + subject_5_col_6_box.getText() + "','" + subject_5_col_7_box.getText() + "','"
                + subject_5_avg_box.getText() + "','" + subject_5_sum_box.getText() + "','" + strDateInFormat + "','" + theDate + "');";

        System.out.println("Inserting\n" + query);
        insertStatement(query);

    }

    private void insert_subject_6() {
        Date date = new Date();
        String theDate = date.toString();
        String txnMonth = theDate.substring(4, 7);
        String txnYear = theDate.substring(24, 28);
        String txnDay = theDate.substring(0, 10) + " " + txnYear;
        //format for report queries
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        cal.add(Calendar.DATE, 0);
        String strDateInFormat = dateFormat.format(cal.getTime());
        System.out.println("strDateInFormat is: " + strDateInFormat);

        String query = "INSERT INTO Scores_tbl(student_name,student_id,gender,classroom,gradelevel,subject,"
                + "col1,col2,col3,col4,col5,col6,col7,colavg,colsum,formated_date,scores_date"
                + " ) VALUES (" + "'" + choicebox_name.getValue() + "','"
                + selected_Student_Id + "','" + choicebox_gender.getValue() + "','" + choicebox_class.getValue() + "','"
                + choicebox_grade.getValue() + "','" + choicebox_subject_6.getValue() + "','" + subject_6_col_1_box.getText() + "','"
                + subject_6_col_2_box.getText() + "','" + subject_6_col_3_box.getText() + "','" + subject_6_col_4_box.getText() + "','"
                + subject_6_col_5_box.getText() + "','" + subject_6_col_6_box.getText() + "','" + subject_6_col_7_box.getText() + "','"
                + subject_6_avg_box.getText() + "','" + subject_6_sum_box.getText() + "','" + strDateInFormat + "','" + theDate + "');";

        System.out.println("Inserting\n" + query);
        insertStatement(query);

    }

    private void insert_subject_7() {
        Date date = new Date();
        String theDate = date.toString();
        String txnMonth = theDate.substring(4, 7);
        String txnYear = theDate.substring(24, 28);
        String txnDay = theDate.substring(0, 10) + " " + txnYear;
        //format for report queries
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        cal.add(Calendar.DATE, 0);
        String strDateInFormat = dateFormat.format(cal.getTime());
        System.out.println("strDateInFormat is: " + strDateInFormat);

        String query = "INSERT INTO Scores_tbl(student_name,student_id,gender,classroom,gradelevel,subject,"
                + "col1,col2,col3,col4,col5,col6,col7,colavg,colsum,formated_date,scores_date"
                + " ) VALUES (" + "'" + choicebox_name.getValue() + "','"
                + selected_Student_Id + "','" + choicebox_gender.getValue() + "','" + choicebox_class.getValue() + "','"
                + choicebox_grade.getValue() + "','" + choicebox_subject_7.getValue() + "','" + subject_7_col_1_box.getText() + "','"
                + subject_7_col_2_box.getText() + "','" + subject_7_col_3_box.getText() + "','" + subject_7_col_4_box.getText() + "','"
                + subject_7_col_5_box.getText() + "','" + subject_7_col_6_box.getText() + "','" + subject_7_col_7_box.getText() + "','"
                + subject_7_avg_box.getText() + "','" + subject_7_sum_box.getText() + "','" + strDateInFormat + "','" + theDate + "');";

        System.out.println("Inserting\n" + query);
        insertStatement(query);

    }

    private void insert_subject_8() {
        Date date = new Date();
        String theDate = date.toString();
        String txnMonth = theDate.substring(4, 7);
        String txnYear = theDate.substring(24, 28);
        String txnDay = theDate.substring(0, 10) + " " + txnYear;
        //format for report queries
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        cal.add(Calendar.DATE, 0);
        String strDateInFormat = dateFormat.format(cal.getTime());
        System.out.println("strDateInFormat is: " + strDateInFormat);

        String query = "INSERT INTO Scores_tbl(student_name,student_id,gender,classroom,gradelevel,subject,"
                + "col1,col2,col3,col4,col5,col6,col7,colavg,colsum,formated_date,scores_date"
                + " ) VALUES (" + "'" + choicebox_name.getValue() + "','"
                + selected_Student_Id + "','" + choicebox_gender.getValue() + "','" + choicebox_class.getValue() + "','"
                + choicebox_grade.getValue() + "','" + choicebox_subject_8.getValue() + "','" + subject_8_col_1_box.getText() + "','"
                + subject_8_col_2_box.getText() + "','" + subject_8_col_3_box.getText() + "','" + subject_8_col_4_box.getText() + "','"
                + subject_8_col_5_box.getText() + "','" + subject_8_col_6_box.getText() + "','" + subject_8_col_7_box.getText() + "','"
                + subject_8_avg_box.getText() + "','" + subject_8_sum_box.getText() + "','" + strDateInFormat + "','" + theDate + "');";

        System.out.println("Inserting\n" + query);
        insertStatement(query);

    }

    private void insertStatement(String insert_query) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully eee");
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

    private void clearAllChoiceSelections() {
        choicebox_name.getSelectionModel().clearSelection();
        choicebox_class.getSelectionModel().clearSelection();
        choicebox_gender.getSelectionModel().clearSelection();
        choicebox_grade.getSelectionModel().clearSelection();
        choicebox_subject_1.getSelectionModel().clearSelection();
        choicebox_subject_2.getSelectionModel().clearSelection();
        choicebox_subject_3.getSelectionModel().clearSelection();
        choicebox_subject_4.getSelectionModel().clearSelection();
        choicebox_subject_5.getSelectionModel().clearSelection();
        choicebox_subject_6.getSelectionModel().clearSelection();
        choicebox_subject_7.getSelectionModel().clearSelection();
        choicebox_subject_8.getSelectionModel().clearSelection();

        choicebox_name.setValue("--NONE SELECTED--");
        choicebox_class.setValue("--NONE SELECTED--");
        choicebox_gender.setValue("--NONE SELECTED--");
        choicebox_grade.setValue("--NONE SELECTED--");
        choicebox_subject_1.setValue("--NONE SELECTED--");
        choicebox_subject_2.setValue("--NONE SELECTED--");
        choicebox_subject_3.setValue("--NONE SELECTED--");
        choicebox_subject_4.setValue("--NONE SELECTED--");
        choicebox_subject_5.setValue("--NONE SELECTED--");
        choicebox_subject_6.setValue("--NONE SELECTED--");
        choicebox_subject_7.setValue("--NONE SELECTED--");
        choicebox_subject_8.setValue("--NONE SELECTED--");
    }

    private void setColsToZero() {
        subject_1_col_1_box.setText("0");
        subject_1_col_2_box.setText("0");
        subject_1_col_3_box.setText("0");
        subject_1_col_4_box.setText("0");
        subject_1_col_5_box.setText("0");
        subject_1_col_6_box.setText("0");
        subject_1_col_7_box.setText("0");

        subject_2_col_1_box.setText("0");
        subject_2_col_2_box.setText("0");
        subject_2_col_3_box.setText("0");
        subject_2_col_4_box.setText("0");
        subject_2_col_5_box.setText("0");
        subject_2_col_6_box.setText("0");
        subject_2_col_7_box.setText("0");

        subject_3_col_1_box.setText("0");
        subject_3_col_2_box.setText("0");
        subject_3_col_3_box.setText("0");
        subject_3_col_4_box.setText("0");
        subject_3_col_5_box.setText("0");
        subject_3_col_6_box.setText("0");
        subject_3_col_7_box.setText("0");

        subject_4_col_1_box.setText("0");
        subject_4_col_2_box.setText("0");
        subject_4_col_3_box.setText("0");
        subject_4_col_4_box.setText("0");
        subject_4_col_5_box.setText("0");
        subject_4_col_6_box.setText("0");
        subject_4_col_7_box.setText("0");

        subject_5_col_1_box.setText("0");
        subject_5_col_2_box.setText("0");
        subject_5_col_3_box.setText("0");
        subject_5_col_4_box.setText("0");
        subject_5_col_5_box.setText("0");
        subject_5_col_6_box.setText("0");
        subject_5_col_7_box.setText("0");

        subject_6_col_1_box.setText("0");
        subject_6_col_2_box.setText("0");
        subject_6_col_3_box.setText("0");
        subject_6_col_4_box.setText("0");
        subject_6_col_5_box.setText("0");
        subject_6_col_6_box.setText("0");
        subject_6_col_7_box.setText("0");

        subject_7_col_1_box.setText("0");
        subject_7_col_2_box.setText("0");
        subject_7_col_3_box.setText("0");
        subject_7_col_4_box.setText("0");
        subject_7_col_5_box.setText("0");
        subject_7_col_6_box.setText("0");
        subject_7_col_7_box.setText("0");

        subject_8_col_1_box.setText("0");
        subject_8_col_2_box.setText("0");
        subject_8_col_3_box.setText("0");
        subject_8_col_4_box.setText("0");
        subject_8_col_5_box.setText("0");
        subject_8_col_6_box.setText("0");
        subject_8_col_7_box.setText("0");
    }

}
