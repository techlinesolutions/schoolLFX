/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolline;

import java.io.File;
import schoolline.entities.Students;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * FXML Controller class
 *
 * @author Eche Michael
 */
public class AllStudentsController implements Initializable {

    @FXML
    private Button btnClose;
    String orderby;

    int intBal;

    private ObservableList<Students> StudentData;

    @FXML
    private TableView tableStudent;

    @FXML
    private TableColumn idCol;

    @FXML
    private TableColumn addressCol;
    @FXML
    private TableColumn genderCol;

    @FXML
    private TableColumn dobCol;
    @FXML
    private TableColumn phoneCol;
    @FXML
    private TableColumn emailCol;

    @FXML
    private TableColumn dateCol;

    //START | SQLITE
    private static Connection con;
    private static Statement stat;
    private PreparedStatement prep;
    //END | SQLITE
    private String id;
    private String name;
    private String gender;
    private String address;
    private String dob;
    private String phone;
    private String email;
    private String Students_date;

    @FXML
    private Button btnDownloadReport;
    private final String REPORT_NAME = "ALL_STUDENTS_REPORT";
    private String path;
    @FXML
    private TableColumn nameCol;
    @FXML
    private TableColumn motherCol;
    @FXML
    private TableColumn fatherColl;
    @FXML
    private TableColumn gradeCol;
    @FXML
    private TableColumn classCol;
    @FXML
    private TableColumn hostelCol;
    @FXML
    private TableColumn prevschCol;
    private String mother;
    private String father;
    private String grade;
    private String classes;
    private String hostel;
    private String prevsch;
    @FXML
    private TextField name_box;
    @FXML
    private Button btnUpdate1;

    @FXML
    private Button btnDelete1;
    @FXML
    private ChoiceBox choiceboxClassroom;
    @FXML
    private TextField dob_box;
    @FXML
    private TextField father_box;
    @FXML
    private TextField mother_box;
    @FXML
    private TextField address_box;
    @FXML
    private ChoiceBox choiceboxGrade;
    @FXML
    private TextField prevsch_box;
    @FXML
    private TextField hostel_box;
    @FXML
    private TextField phone_box;
    @FXML
    private TextField email_box;
    @FXML
    private ChoiceBox choiceboxGender;
    private ObservableList allGenders;
    private ObservableList allGrades;
    private ObservableList allClassrooms;
    @FXML
    private ImageView imgTPlaceHolder;
    private String image_path;
    @FXML
    private AnchorPane updatePane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("before refresh");
        refreshList();
        refreshItemListInCombo();
        selectFirstOne();
        System.out.println("after refresh");

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

    private void refreshList() {
        StudentData = FXCollections.observableArrayList();

        idCol.setCellValueFactory(
                new PropertyValueFactory<Students, String>("Student_id")
        );
        nameCol.setCellValueFactory(
                new PropertyValueFactory<Students, String>("name")
        );

        genderCol.setCellValueFactory(
                new PropertyValueFactory<Students, String>("gender")
        );

        addressCol.setCellValueFactory(
                new PropertyValueFactory<Students, String>("address")
        );
        dobCol.setCellValueFactory(
                new PropertyValueFactory<Students, String>("dob")
        );
        phoneCol.setCellValueFactory(
                new PropertyValueFactory<Students, String>("phone")
        );
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Students, String>("email")
        );

        motherCol.setCellValueFactory(
                new PropertyValueFactory<Students, String>("mother")
        );
        fatherColl.setCellValueFactory(
                new PropertyValueFactory<Students, String>("father")
        );
        gradeCol.setCellValueFactory(
                new PropertyValueFactory<Students, String>("grade")
        );
        classCol.setCellValueFactory(
                new PropertyValueFactory<Students, String>("classroom")
        );
        hostelCol.setCellValueFactory(
                new PropertyValueFactory<Students, String>("hostel")
        );
        prevschCol.setCellValueFactory(
                new PropertyValueFactory<Students, String>("prev_school")
        );
        dateCol.setCellValueFactory(
                new PropertyValueFactory<Students, String>("Student_date")
        );

        try {
            java.util.Date maindate = new java.util.Date();
            String theDate = maindate.toString();
            String theYear = theDate.substring(24, 28);
            String theDay = theDate.substring(0, 10) + " " + theYear;

            // SQLiteConfig config = new SQLiteConfig();
            con = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            stat = con.createStatement();
            System.out.println("Opened database successfully");
            orderby = "Student_id";
            String todRepQuery = "SELECT * FROM Student_tbl"
                    + " ORDER BY " + orderby;
            System.out.println("todRepQuery is :" + todRepQuery);
            ResultSet rs = con.createStatement().executeQuery(todRepQuery);
            while (rs.next()) {
                Students theStudent = new Students();

                id = rs.getString("Student_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String full_name = first_name + " " + last_name;

                String mother_first_name = rs.getString("mother_first_name");
                String mother_last_name = rs.getString("mother_last_name");
                String full_mother = mother_first_name + " " + mother_last_name;

                String father_first_name = rs.getString("father_first_name");
                String father_last_name = rs.getString("father_last_name");
                String full_father = father_first_name + " " + father_last_name;
                gender = rs.getString("gender");
                address = rs.getString("address");
                dob = rs.getString("dob");
                phone = rs.getString("phone");
                email = rs.getString("email");
                Students_date = rs.getString("Student_date");

                grade = rs.getString("grade");
                classes = rs.getString("classroom");
                hostel = rs.getString("hostel");
                prevsch = rs.getString("prev_school");

                System.out.println("id = " + id);
                System.out.println("firstName = " + first_name);
                System.out.println("lastName = " + last_name);
                System.out.println("gender = " + gender);
                System.out.println("address = " + address);
                System.out.println("dob = " + dob);
                System.out.println("phone = " + phone);
                System.out.println("email = " + email);

                System.out.println("StudentsDate = " + Students_date);

                theStudent.setStudent_id(id);
                System.out.println("after seting id is :" + theStudent.getStudent_id());

                theStudent.setName(full_name);

                theStudent.setGender(gender);
                System.out.println("after seting Students_address1 is :" + theStudent.getGender());

                theStudent.setAddress(address);
                System.out.println("after seting Students_address1 is :" + theStudent.getAddress());

                theStudent.setDob(dob);
                System.out.println("after seting Students_date_of_birth is :" + theStudent.getDob());

                theStudent.setPhone(phone);
                System.out.println("after seting Students_phone_1 is :" + theStudent.getPhone());

                theStudent.setEmail(email);
                System.out.println("after seting Students_email is :" + theStudent.getEmail());

                theStudent.setStudent_date(Students_date);
                System.out.println("after seting StudentsDate is :" + theStudent.getStudent_date());

                theStudent.setMother(full_mother);
                theStudent.setFather(full_father);
                theStudent.setGrade(grade);
                theStudent.setClassroom(classes);
                theStudent.setHostel(hostel);
                theStudent.setPrev_school(prevsch);

                StudentData.add(theStudent);
                System.out.println("after Students  data");
            }
            tableStudent.setItems(StudentData);

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    @FXML
    private void onClickDownloadReport(ActionEvent event) throws FileNotFoundException, IOException {
        System.err.println("inside onClickDownloadReport");
        TableView<Students> table = new TableView<Students>();

        ObservableList<Students> teamMembers = getReportDownloadMembers();
        table.setItems(teamMembers);
//column names and values
        TableColumn<Students, String> excelIdCol = new TableColumn<Students, String>("ID");
        excelIdCol.setCellValueFactory(new PropertyValueFactory<Students, String>("Student_id"));

        TableColumn<Students, String> excelnameCol = new TableColumn<Students, String>("FULL NAME");
        excelnameCol.setCellValueFactory(new PropertyValueFactory<Students, String>("name"));

        TableColumn<Students, String> excelgenderCol = new TableColumn<Students, String>("GENDER");
        excelgenderCol.setCellValueFactory(new PropertyValueFactory<Students, String>("gender"));

        TableColumn<Students, String> exceladdressCol = new TableColumn<Students, String>("ADDRESS");
        exceladdressCol.setCellValueFactory(new PropertyValueFactory<Students, String>("address"));

        TableColumn<Students, String> exceldobCol = new TableColumn<Students, String>("DATE OF BIRTH");
        exceldobCol.setCellValueFactory(new PropertyValueFactory<Students, String>("dob"));

        TableColumn<Students, String> excelphoneCol = new TableColumn<Students, String>("PHONE");
        excelphoneCol.setCellValueFactory(new PropertyValueFactory<Students, String>("phone"));

        TableColumn<Students, String> excelemailCol = new TableColumn<Students, String>("EMAIL");
        excelemailCol.setCellValueFactory(new PropertyValueFactory<Students, String>("email"));

        TableColumn<Students, String> excelmotherCol = new TableColumn<Students, String>("MOTHER");
        excelmotherCol.setCellValueFactory(new PropertyValueFactory<Students, String>("mother"));

        TableColumn<Students, String> excelfatherCol = new TableColumn<Students, String>("FATHER");
        excelfatherCol.setCellValueFactory(new PropertyValueFactory<Students, String>("father"));

        TableColumn<Students, String> excelgradeCol = new TableColumn<Students, String>("GRADE");
        excelgradeCol.setCellValueFactory(new PropertyValueFactory<Students, String>("grade"));

        TableColumn<Students, String> excelclassCol = new TableColumn<Students, String>("CLASS");
        excelclassCol.setCellValueFactory(new PropertyValueFactory<Students, String>("classroom"));

        TableColumn<Students, String> excelhostelCol = new TableColumn<Students, String>("HOSTEL");
        excelhostelCol.setCellValueFactory(new PropertyValueFactory<Students, String>("hostel"));

        TableColumn<Students, String> excelprevschCol = new TableColumn<Students, String>("PREV SCHOOL");
        excelprevschCol.setCellValueFactory(new PropertyValueFactory<Students, String>("prev_school"));

        TableColumn<Students, String> exceldateCol = new TableColumn<Students, String>("DATE");
        exceldateCol.setCellValueFactory(new PropertyValueFactory<Students, String>("Student_date"));

        System.err.println("after property factory");

        ObservableList<TableColumn<Students, ?>> columns = table.getColumns();
        columns.add(excelIdCol);
        columns.add(excelnameCol);
        columns.add(excelgenderCol);
        columns.add(exceladdressCol);
        columns.add(exceldobCol);
        columns.add(excelphoneCol);
        columns.add(excelemailCol);

        columns.add(excelmotherCol);
        columns.add(excelfatherCol);
        columns.add(excelgradeCol);
        columns.add(excelclassCol);
        columns.add(excelhostelCol);
        columns.add(excelprevschCol);
        columns.add(exceldateCol);

        System.err.println("after adding columns");

        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet(REPORT_NAME);
        System.err.println("after creating sheet in excel file");

        Row row = spreadsheet.createRow(0);

        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumns().get(j).getText());
        }

        for (int i = 0; i < table.getItems().size(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getColumns().get(j).getCellData(i) != null) {
                    row.createCell(j).setCellValue(table.getColumns().get(j).getCellData(i).toString());
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }

        try {
//            java.util.Date date = new java.util.Date();
//            String theDate = date.toString();
            Calendar cal = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            cal.add(Calendar.DATE, 0);
            String strDateInFormat = dateFormat.format(cal.getTime());
            System.out.println("strDateInFormat is: " + strDateInFormat);
            strDateInFormat = strDateInFormat.replace("-", "");
            strDateInFormat = strDateInFormat.replace(" ", "");
            strDateInFormat = strDateInFormat.replace(":", "");
            System.out.println("strDateInFormat is: " + strDateInFormat);

            path = "C:\\Downloads\\";
            path = path.replace("\\", "/");
            FileOutputStream fileOut = new FileOutputStream(path + REPORT_NAME + strDateInFormat + ".xls");
            workbook.write(fileOut);
            System.out.println("after creating excel file");
            fileOut.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        cal.add(Calendar.DATE, 0);
        String strDateInFormat = dateFormat.format(cal.getTime());
        System.out.println("strDateInFormat is: " + strDateInFormat);
        strDateInFormat = strDateInFormat.replace("-", "");
        strDateInFormat = strDateInFormat.replace(" ", "");
        strDateInFormat = strDateInFormat.replace(":", "");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Report Downloaded Succesfully to " + path + ".\n with Filename: " + REPORT_NAME + strDateInFormat + ".xls");

        alert.showAndWait();
    }

    private ObservableList<Students> getReportDownloadMembers() {

        ObservableList<Students> theStudents = FXCollections.observableArrayList();
        try {
            java.util.Date maindate = new java.util.Date();
            String theDate = maindate.toString();
            String theYear = theDate.substring(24, 28);
            String theDay = theDate.substring(0, 10) + " " + theYear;

            // SQLiteConfig config = new SQLiteConfig();
            con = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            stat = con.createStatement();
            System.out.println("Opened database successfully");
            orderby = "Student_id";
            String todRepQuery = "SELECT * FROM Student_tbl"
                    + " ORDER BY " + orderby;
            System.out.println("todRepQuery is :" + todRepQuery);
            ResultSet rs = con.createStatement().executeQuery(todRepQuery);
            while (rs.next()) {
                Students myStudent = new Students();

                id = rs.getString("student_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String full_name = first_name + " " + last_name;

                String mother_first_name = rs.getString("mother_first_name");
                String mother_last_name = rs.getString("mother_last_name");
                String full_mother = mother_first_name + " " + mother_last_name;

                String father_first_name = rs.getString("father_first_name");
                String father_last_name = rs.getString("father_last_name");
                String full_father = father_first_name + " " + father_last_name;

                name = full_name;
                gender = rs.getString("gender");
                address = rs.getString("address");
                dob = rs.getString("dob");
                phone = rs.getString("phone");
                email = rs.getString("email");
                Students_date = rs.getString("Student_date");

                grade = rs.getString("grade");
                classes = rs.getString("classroom");
                hostel = rs.getString("hostel");
                prevsch = rs.getString("prev_school");

                System.out.println("id = " + id);
                System.out.println("firstName = " + first_name);
                System.out.println("lastName = " + last_name);
                System.out.println("gender = " + gender);
                System.out.println("address = " + address);
                System.out.println("dob = " + dob);
                System.out.println("phone = " + phone);
                System.out.println("email = " + email);

                System.out.println("StudentsDate = " + Students_date);

                myStudent.setStudent_id(id);
                System.out.println("after seting id is :" + myStudent.getStudent_id());

                myStudent.setName(name);
                System.out.println("after seting firstName is :" + myStudent.getFirst_name());

                myStudent.setGender(gender);
                System.out.println("after seting gender is :" + myStudent.getGender());

                myStudent.setAddress(address);
                System.out.println("after seting Students_address1 is :" + myStudent.getAddress());

                myStudent.setDob(dob);
                System.out.println("after seting Students_date_of_birth is :" + myStudent.getDob());

                myStudent.setPhone(phone);
                System.out.println("after seting Students_phone_1 is :" + myStudent.getPhone());

                myStudent.setEmail(email);
                System.out.println("after seting Students_email is :" + myStudent.getEmail());

                myStudent.setMother(full_mother);
                myStudent.setFather(full_father);
                myStudent.setGrade(grade);
                myStudent.setClassroom(classes);
                myStudent.setHostel(hostel);
                myStudent.setPrev_school(prevsch);
                myStudent.setStudent_date(Students_date);
                System.out.println("after seting StudentsDate is :" + myStudent.getStudent_date());

                theStudents.add(myStudent);
            }

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return theStudents;
    }

    private void onClickExit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private boolean onClickUpdate(ActionEvent event) throws IOException {
        Students myStudents = new Students();
        Date date = new Date();
        int theId = Integer.parseInt(id);
        String theDate = date.toString();
        //format for report queries
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        cal.add(Calendar.DATE, 0);
        String Students_name = name_box.getText();
        String Students_address = address_box.getText().toUpperCase();
        String Students_phone = phone_box.getText().toUpperCase();
        String Students_email = email_box.getText().toUpperCase();
        String selected_Gender = choiceboxGender.getValue().toString().toUpperCase();

        String Student_grade = choiceboxGrade.getValue().toString().toUpperCase();
        String Student_Classroom = choiceboxClassroom.getValue().toString().toUpperCase();
        String Student_hostel = hostel_box.getText().toUpperCase();;
        String Student_mother = mother_box.getText().toUpperCase();;
        String Student_father = father_box.getText().toUpperCase();;
        String Student_dob = dob_box.getText().toUpperCase();;
        String Student_prevsch = prevsch_box.getText().toUpperCase();;

        System.out.println("Students_name is: " + Students_name);

        if ((Students_name.trim().length() == 0) || (Students_name == "") || (Students_name.trim().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please Highlight A Students Record");

            alert.showAndWait();
            //System.clearProperty(Students_name);
            return false;
        }
        //myStudents.setId(rs.getString("id"));

        myStudents.setName(Students_name);
        myStudents.setStudent_date(theDate);
        myStudents.setAddress(Students_address);
        myStudents.setPhone(Students_phone);
        myStudents.setEmail(Students_email);
        myStudents.setGender(selected_Gender);
        myStudents.setGrade(Student_grade);
        myStudents.setClassroom(Student_Classroom);
        myStudents.setHostel(Student_hostel);
        myStudents.setMother(Student_mother);
        myStudents.setFather(Student_father);
        myStudents.setDob(Student_dob);
        myStudents.setPrev_school(Student_prevsch);

        ObservableList highlightedStudentsRecord, allStudentsRecords;

        highlightedStudentsRecord = tableStudent.getSelectionModel().getSelectedItems();

        System.out.println("Students_name is : " + Students_name);
        System.out.println("the ID IS: " + theId);
        String query = "UPDATE Student_tbl set gender ='"
                + selected_Gender + "',"
                + "address ='"
                + Students_address + "',"
                + "phone ='"
                + Students_phone + "',"
                + "email ='"
                + Students_email + "',"
                + "grade ='"
                + Student_grade + "',"
                + "classroom ='"
                + Student_Classroom + "',"
                + "hostel ='"
                + Student_hostel + "',"
                + "dob ='"
                + Student_dob + "',"
                + "prev_school ='"
                + Student_prevsch + "',"
                + "student_date ='"
                + theDate + "'"
                + " WHERE student_id = "
                + theId;

        System.out.println("updating\n" + query);
        updateStatement(query);

        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
        alert3.setTitle("Information Dialog");
        alert3.setHeaderText(null);
        alert3.setContentText("Record updated Succesfully.");

        alert3.showAndWait();

        System.out.println("Succesfully Updated");

        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("AllStudents.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();

        return true;

    }

    @FXML
    private void onClickDelete(ActionEvent event) throws IOException {
        int theId = Integer.parseInt(id);
        String Students_name = name_box.getText();

        if ((Students_name.trim().length() == 0) || (Students_name == "") || (Students_name.trim().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please Highlight A Student Record");

            alert.showAndWait();
            System.clearProperty(Students_name);
        }
        //myStudents.setId(rs.getString("id"));

        ObservableList highlightedStudentsRecord, allStudentsRecords;
        allStudentsRecords = tableStudent.getItems();
        highlightedStudentsRecord = tableStudent.getSelectionModel().getSelectedItems();
        System.out.println("Students_name is : " + Students_name);

        String query = "DELETE FROM Student_tbl "
                + " WHERE student_id = "
                + theId;

        System.out.println("updating\n" + query);
        updateStatement(query);

        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
        alert3.setTitle("Information Dialog");
        alert3.setHeaderText(null);
        alert3.setContentText("Record Deleted Succesfully.");

        alert3.showAndWait();

        System.out.println("Succesfully Updated");

        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("AllStudents.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();

    }

    @FXML
    private void onClickTable(MouseEvent event) {
        name_box.clear();
        address_box.clear();
        phone_box.clear();
        email_box.clear();
        choiceboxGender.getSelectionModel().clearSelection();

        choiceboxGrade.getSelectionModel().clearSelection();
        choiceboxClassroom.getSelectionModel().clearSelection();
        hostel_box.clear();
        mother_box.clear();
        father_box.clear();
        dob_box.clear();
        prevsch_box.clear();
        selectFirstOne();

        Students myStudents = new Students();
        Date date = new Date();
        String theDate = date.toString();
        String Students_name;
        String selected_Gender;
        String Students_phone;
        String Students_address;
        String Students_email;

        String Student_grade;
        String Student_Classroom;
        String Student_hostel;
        String Student_mother;
        String Student_dob;
        String Student_prevsch;

        myStudents = (Students) tableStudent.getSelectionModel().getSelectedItem();
        id = myStudents.getStudent_id();
        Students_name = myStudents.getName();
        selected_Gender = myStudents.getGender();
        Students_phone = myStudents.getPhone();
        Students_address = myStudents.getAddress();
        Students_email = myStudents.getEmail();
        Student_grade = myStudents.getGrade();
        Student_Classroom = myStudents.getClassroom();
        Student_hostel = myStudents.getHostel();

        Student_dob = myStudents.getDob();
        Student_prevsch = myStudents.getPrev_school();

        Student_mother = myStudents.getMother();
        System.out.println("mother full name is : " + Student_mother);

        String Student_father = myStudents.getFather();
        System.out.println("father full name is : " + Student_father);

        //image retrieval
        Connection c = null;
        Statement stmt = null;

        //orderby = sort_menubutton.getText();
        // ascdesc = ascdesc_menubutton.getText();
        try {
            image_path = "C:\\Downloads\\";

            image_path = image_path.replace("\\", "/");
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            orderby = "student_id";
            System.out.println("Query is: SELECT * FROM Student_tbl WHERE student_id= "
                    + id + " ORDER BY " + orderby);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT image FROM Student_tbl WHERE student_id= "
                    + id + " ORDER BY " + orderby);
            InputStream is = rs.getBinaryStream("image");

            OutputStream os = new FileOutputStream(new File(image_path + "thePhoto.jpg"));
            byte[] content = new byte[1024];
            int size = 0;
            while ((size = is.read(content)) != -1) {
                os.write(content, 0, size);
                System.out.println("finished writing");

            }
            os.close();
            is.close();
            Image image = new Image("file:" + image_path + "thePhoto.jpg", 100, 150, true, true);
            imgTPlaceHolder = new ImageView(image);
            imgTPlaceHolder.setFitWidth(150);
            imgTPlaceHolder.setFitHeight(100);
            imgTPlaceHolder.setPreserveRatio(true);
            System.out.println("file IS is: " + is.toString());
            System.out.println("file OS is: " + os.toString());
            //imgTPlaceHolder.setImage(image);

            rs.close();
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        name_box.setText(Students_name);
        address_box.setText(Students_address);
        phone_box.setText(Students_phone);
        email_box.setText(Students_email);
        choiceboxGender.setValue(selected_Gender);

        choiceboxGrade.setValue(Student_grade);
        choiceboxClassroom.setValue(Student_Classroom);
        hostel_box.setText(Student_hostel);
        mother_box.setText(Student_mother);
        father_box.setText(Student_father);
        dob_box.setText(Student_dob);
        prevsch_box.setText(Student_prevsch);

        if (("".equals(selected_Gender)) || (selected_Gender == null)) {
            choiceboxGender.getSelectionModel().selectFirst();
        }

        if (("".equals(Student_grade)) || (Student_grade == null)) {
            choiceboxGrade.getSelectionModel().selectFirst();
        }

        if (("".equals(Student_Classroom)) || (Student_Classroom == null)) {
            choiceboxClassroom.getSelectionModel().selectFirst();
        }
    }

    private void refreshItemListInCombo() {
        //Set items equal to an empty ArrayList
        allGenders = FXCollections.observableArrayList();
        allGrades = FXCollections.observableArrayList();
        allClassrooms = FXCollections.observableArrayList();

        //Select out of the DB, fill accordingly
        getGenders(allGenders);
        getGrades(allGrades);
        getClassroom(allClassrooms);

        //Set the listview to what we just populated with DB contents
        choiceboxGender.setItems(allGenders);
        choiceboxGrade.setItems(allGrades);
        choiceboxClassroom.setItems(allClassrooms);
    }

    private void selectFirstOne() {
        choiceboxGender.getSelectionModel().selectFirst();
        choiceboxGrade.getSelectionModel().selectFirst();
        choiceboxClassroom.getSelectionModel().selectFirst();
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

    private void getClassroom(ObservableList allClassrooms) {
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

    private void getGenders(ObservableList allGenders) {
        allGenders.add("--NONE SELECTED--");
        allGenders.add("MALE");
        allGenders.add("FEMALE");
    }

    private void updateStatement(String update_query) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            System.out.println("Our query was: " + update_query);
            stmt.executeUpdate(update_query);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

}
