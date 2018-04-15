/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolline;

import schoolline.entities.Teacher;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
public class AllTeachersController implements Initializable {

    @FXML
    private Button btnClose;
    String orderby;

    int intBal;

    private ObservableList<Teacher> TeacherData;

    @FXML
    private TableView tableTeacher;

    @FXML
    private TableColumn idCol;

    @FXML
    private TableColumn genderCol;
    @FXML
    private TableColumn addressCol;

    @FXML
    private TableColumn phoneCol;
    @FXML
    private TableColumn emailCol;

    @FXML
    private TableColumn typeCol;
    @FXML
    private TableColumn dateCol;

    //START | SQLITE
    private static Connection con;
    private static Statement stat;
    private PreparedStatement prep;
    //END | SQLITE
    private String id;
    //private String full_name;
    private String first_name;
    private String last_name;
    private String address;
    private String gender;
    private String phone;
    private String email;
    private String type;
    private String Teacher_date;
    @FXML
    private Button btnDownloadReport;

    private final String REPORT_NAME = "ALL_TEACHERS_REPORT";
    private String path;
    @FXML
    private TableColumn subject1Col;
    @FXML
    private TableColumn subject2Col;
    @FXML
    private TableColumn subject3Col;
    @FXML
    private TableColumn subject4Col;
    @FXML
    private TableColumn nameCol;
    private String subject1;
    private String subject2;
    private String subject3;
    private String subject4;
    @FXML
    private TextField name_box;
    @FXML
    private ChoiceBox choiceboxGender;

    @FXML
    private ChoiceBox choiceboxSubject3;

    @FXML
    private ChoiceBox choiceboxSubject4;
    @FXML
    private Button btnUpdate1;
    @FXML
    private TextField type_box;
    @FXML
    private Button btnDelete1;
    @FXML
    private ChoiceBox choiceboxSubject1;
    @FXML
    private TextField phone_box;
    @FXML
    private TextField address_box;
    @FXML
    private ChoiceBox choiceboxSubject2;
    @FXML
    private TextField email_box;
    private ObservableList allGenders;
    private ObservableList Subjects;

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
        TeacherData = FXCollections.observableArrayList();

        idCol.setCellValueFactory(
                new PropertyValueFactory<Teacher, String>("Teacher_id")
        );

        nameCol.setCellValueFactory(
                new PropertyValueFactory<Teacher, String>("name")
        );
        genderCol.setCellValueFactory(
                new PropertyValueFactory<Teacher, String>("gender")
        );
        addressCol.setCellValueFactory(
                new PropertyValueFactory<Teacher, String>("address")
        );
        phoneCol.setCellValueFactory(
                new PropertyValueFactory<Teacher, String>("phone")
        );
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Teacher, String>("email")
        );

        typeCol.setCellValueFactory(
                new PropertyValueFactory<Teacher, String>("type")
        );

        subject1Col.setCellValueFactory(
                new PropertyValueFactory<Teacher, String>("subject1")
        );
        subject2Col.setCellValueFactory(
                new PropertyValueFactory<Teacher, String>("subject2")
        );
        subject3Col.setCellValueFactory(
                new PropertyValueFactory<Teacher, String>("subject3")
        );
        subject4Col.setCellValueFactory(
                new PropertyValueFactory<Teacher, String>("subject4")
        );
        dateCol.setCellValueFactory(
                new PropertyValueFactory<Teacher, String>("teacher_date")
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
            orderby = "Teacher_id";
            String todRepQuery = "SELECT * FROM Teachers_tbl"
                    + " ORDER BY " + orderby;
            System.out.println("todRepQuery is :" + todRepQuery);
            ResultSet rs = con.createStatement().executeQuery(todRepQuery);
            while (rs.next()) {
                Teacher theTeacher = new Teacher();

                id = rs.getString("Teacher_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String full_name = first_name + " " + last_name;

                address = rs.getString("address");
                gender = rs.getString("gender");
                phone = rs.getString("phone");
                email = rs.getString("email");
                type = rs.getString("type");
                Teacher_date = rs.getString("teacher_date");

                subject1 = rs.getString("subject1");
                subject2 = rs.getString("subject2");
                subject3 = rs.getString("subject3");
                subject4 = rs.getString("subject4");

                System.out.println("id = " + id);

                System.out.println("address = " + address);
                System.out.println("gender = " + gender);
                System.out.println("phone = " + phone);
                System.out.println("email = " + email);
                System.out.println("type = " + type);
                System.out.println("TeachersDate = " + Teacher_date);

                theTeacher.setTeacher_id(id);
                System.out.println("after seting id is :" + theTeacher.getTeacher_id());

                theTeacher.setName(full_name);

                theTeacher.setSubject1(subject1);
                theTeacher.setSubject2(subject2);
                theTeacher.setSubject3(subject3);
                theTeacher.setSubject4(subject4);

                theTeacher.setGender(gender);
                System.out.println("after seting gender is :" + theTeacher.getGender());

                theTeacher.setAddress(address);
                System.out.println("after seting Teachers_address1 is :" + theTeacher.getAddress());

                theTeacher.setPhone(phone);
                System.out.println("after seting Teachers_phone_1 is :" + theTeacher.getPhone());

                theTeacher.setEmail(email);
                System.out.println("after seting Teachers_email is :" + theTeacher.getEmail());

                theTeacher.setType(type);
                System.out.println("after seting type is :" + theTeacher.getType());

                theTeacher.setTeacher_date(Teacher_date);
                System.out.println("after seting TeachersDate is :" + theTeacher.getTeacher_date());

                TeacherData.add(theTeacher);
                System.out.println("after Teachers  data");
            }
            tableTeacher.setItems(TeacherData);

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    @FXML
    private void onClickDownloadReport(ActionEvent event) throws FileNotFoundException, IOException {
        System.err.println("inside onClickDownloadReport");
        TableView<Teacher> table = new TableView<Teacher>();

        ObservableList<Teacher> teamMembers = getReportDownloadMembers();
        table.setItems(teamMembers);
//column names and values
        TableColumn<Teacher, String> excelIdCol = new TableColumn<Teacher, String>("ID");
        excelIdCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("Teacher_id"));

        TableColumn<Teacher, String> excelfirst_nameCol = new TableColumn<Teacher, String>("FIRST NAME");
        excelfirst_nameCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("first_name"));

        TableColumn<Teacher, String> excellast_nameCol = new TableColumn<Teacher, String>("LAST NAME");
        excellast_nameCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("last_name"));

        TableColumn<Teacher, String> excelgenderCol = new TableColumn<Teacher, String>("GENDER");
        excelgenderCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("gender"));

        TableColumn<Teacher, String> exceladdressCol = new TableColumn<Teacher, String>("ADDRESS");
        exceladdressCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("address"));

        TableColumn<Teacher, String> excelphoneCol = new TableColumn<Teacher, String>("PHONE");
        excelphoneCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("phone"));

        TableColumn<Teacher, String> excelemailCol = new TableColumn<Teacher, String>("EMAIL");
        excelemailCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("email"));

        TableColumn<Teacher, String> exceltypeCol = new TableColumn<Teacher, String>("TYPE");
        exceltypeCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("type"));

        TableColumn<Teacher, String> exceldateCol = new TableColumn<Teacher, String>("DATE");
        exceldateCol.setCellValueFactory(new PropertyValueFactory<Teacher, String>("teacher_date"));

        System.err.println("after property factory");

        ObservableList<TableColumn<Teacher, ?>> columns = table.getColumns();
        columns.add(excelIdCol);
        columns.add(excelfirst_nameCol);
        columns.add(excellast_nameCol);
        columns.add(excelgenderCol);
        columns.add(exceladdressCol);
        columns.add(excelphoneCol);
        columns.add(excelemailCol);
        columns.add(exceltypeCol);
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

    private ObservableList<Teacher> getReportDownloadMembers() {

        ObservableList<Teacher> theTeacher = FXCollections.observableArrayList();
        try {
            java.util.Date maindate = new java.util.Date();
            String theDate = maindate.toString();
            String theYear = theDate.substring(24, 28);
            String theDay = theDate.substring(0, 10) + " " + theYear;

            // SQLiteConfig config = new SQLiteConfig();
            con = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            stat = con.createStatement();
            System.out.println("Opened database successfully");
            orderby = "Teacher_id";
            String todRepQuery = "SELECT * FROM Teachers_tbl"
                    + " ORDER BY " + orderby;
            System.out.println("todRepQuery is :" + todRepQuery);
            ResultSet rs = con.createStatement().executeQuery(todRepQuery);
            while (rs.next()) {
                Teacher myTeacher = new Teacher();

                id = rs.getString("Teacher_id");
                first_name = rs.getString("first_name");
                last_name = rs.getString("last_name");
                gender = rs.getString("gender");
                address = rs.getString("address");
                phone = rs.getString("phone");
                email = rs.getString("email");
                type = rs.getString("type");
                Teacher_date = rs.getString("teacher_date");

                System.out.println("id = " + id);
                System.out.println("firstName = " + first_name);
                System.out.println("lastName = " + last_name);
                System.out.println("address = " + address);
                System.out.println("phone = " + phone);
                System.out.println("email = " + email);
                System.out.println("type = " + type);
                System.out.println("TeachersDate = " + Teacher_date);

                myTeacher.setTeacher_id(id);
                System.out.println("after seting id is :" + myTeacher.getTeacher_id());

                myTeacher.setFirst_name(first_name);
                System.out.println("after seting firstName is :" + myTeacher.getFirst_name());

                myTeacher.setLast_name(last_name);
                System.out.println("after seting lastName is :" + myTeacher.getLast_name());

                myTeacher.setGender(gender);
                System.out.println("after seting gender is :" + myTeacher.getGender());

                myTeacher.setAddress(address);
                System.out.println("after seting Teachers_address1 is :" + myTeacher.getAddress());

                myTeacher.setPhone(phone);
                System.out.println("after seting Teachers_phone_1 is :" + myTeacher.getPhone());

                myTeacher.setEmail(email);
                System.out.println("after seting Teachers_email is :" + myTeacher.getEmail());

                myTeacher.setType(type);
                System.out.println("after seting type is :" + myTeacher.getType());

                myTeacher.setDotor_date(Teacher_date);
                System.out.println("after seting TeachersDate is :" + myTeacher.getDotor_date());

                theTeacher.add(myTeacher);
            }

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return theTeacher;
    }

    private void onClickExit(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private boolean onClickUpdate(ActionEvent event) throws IOException {
        Teacher myTeacher = new Teacher();
        Date date = new Date();
        int theId = Integer.parseInt(id);
        String theDate = date.toString();
        //format for report queries
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        cal.add(Calendar.DATE, 0);
        String Teacher_name = name_box.getText();
        String Teacher_address = address_box.getText().toUpperCase();
        String Teacher_phone = phone_box.getText().toUpperCase();
        String Teacher_email = email_box.getText().toUpperCase();
        String Teacher_type = type_box.getText().toUpperCase();
        String selected_Gender = choiceboxGender.getValue().toString().toUpperCase();
        String selected_Subject1 = choiceboxSubject1.getValue().toString().toUpperCase();
        String selected_Subject2 = choiceboxSubject2.getValue().toString().toUpperCase();
        String selected_Subject3 = choiceboxSubject3.getValue().toString().toUpperCase();
        String selected_Subject4 = choiceboxSubject4.getValue().toString().toUpperCase();

        System.out.println("Teacher_name is: " + Teacher_name);

        if ((Teacher_name.trim().length() == 0) || (Teacher_name == "") || (Teacher_name.trim().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please Highlight A Teacher Record");

            alert.showAndWait();
            //System.clearProperty(Teacher_name);
            return false;
        }
        //myTeacher.setId(rs.getString("id"));

        myTeacher.setName(Teacher_name);
        myTeacher.setTeacher_date(theDate);
        myTeacher.setAddress(Teacher_address);
        myTeacher.setPhone(Teacher_phone);
        myTeacher.setEmail(Teacher_email);
        myTeacher.setType(Teacher_type);
        myTeacher.setGender(selected_Gender);
        myTeacher.setSubject1(selected_Subject1);
        myTeacher.setSubject2(selected_Subject2);
        myTeacher.setSubject3(selected_Subject3);
        myTeacher.setSubject4(selected_Subject4);

        ObservableList highlightedTeacherRecord, allTeacherRecords;

        highlightedTeacherRecord = tableTeacher.getSelectionModel().getSelectedItems();

        System.out.println("Teacher_name is : " + Teacher_name);
        System.out.println("the ID IS: " + theId);
        String query = "UPDATE Teachers_tbl set gender ='"
                + selected_Gender + "',"
                + "address ='"
                + Teacher_address + "',"
                + "phone ='"
                + Teacher_phone + "',"
                + "email ='"
                + Teacher_email + "',"
                + "type ='"
                + Teacher_type + "',"
                + "Subject1 ='"
                + selected_Subject1 + "',"
                + "Subject2 ='"
                + selected_Subject2 + "',"
                + "Subject3 ='"
                + selected_Subject3 + "',"
                + "Subject4 ='"
                + selected_Subject4 + "',"
                + "Teacher_date ='"
                + theDate + "'"
                + " WHERE teacher_id = "
                + theId;

        System.out.println("updating\n" + query);
        updateStatement(query);

        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
        alert3.setTitle("Information Dialog");
        alert3.setHeaderText(null);
        alert3.setContentText("Record updated Succesfully.");

        alert3.showAndWait();

        System.out.println("Succesfully Updated");

        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("AllTeachers.fxml"));
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
        String Teacher_name = name_box.getText();

        if ((Teacher_name.trim().length() == 0) || (Teacher_name == "") || (Teacher_name.trim().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please Highlight A Class Room Record");

            alert.showAndWait();
            System.clearProperty(Teacher_name);
        }
        //myTeacher.setId(rs.getString("id"));

        ObservableList highlightedTeacherRecord, allTeacherRecords;
        allTeacherRecords = tableTeacher.getItems();
        highlightedTeacherRecord = tableTeacher.getSelectionModel().getSelectedItems();
        System.out.println("Teacher_name is : " + Teacher_name);

        String query = "DELETE FROM Teachers_tbl "
                + " WHERE teacher_id = "
                + theId;

        System.out.println("updating\n" + query);
        updateStatement(query);

        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
        alert3.setTitle("Information Dialog");
        alert3.setHeaderText(null);
        alert3.setContentText("Record Deleted Succesfully.");

        alert3.showAndWait();

        System.out.println("Succesfully Updated");

        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("AllTeachers.fxml"));
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
        type_box.clear();

        choiceboxSubject1.getSelectionModel().clearSelection();
        choiceboxSubject2.getSelectionModel().clearSelection();
        choiceboxSubject3.getSelectionModel().clearSelection();
        choiceboxSubject4.getSelectionModel().clearSelection();

        choiceboxGender.getSelectionModel().clearSelection();
        selectFirstOne();
        Teacher myTeacher = new Teacher();
        Date date = new Date();
        String theDate = date.toString();
        String Teacher_name;
        String selected_Gender;
        String Teacher_phone;
        String Teacher_address;
        String Teacher_email;
        String Teacher_type;
        String selected_Subject1;
        String selected_Subject2;
        String selected_Subject3;
        String selected_Subject4;

        myTeacher = (Teacher) tableTeacher.getSelectionModel().getSelectedItem();
        id = myTeacher.getTeacher_id();
        Teacher_name = myTeacher.getName();
        selected_Gender = myTeacher.getGender();
        Teacher_phone = myTeacher.getPhone();
        Teacher_address = myTeacher.getAddress();
        Teacher_email = myTeacher.getEmail();
        Teacher_type = myTeacher.getType();
        selected_Subject1 = myTeacher.getSubject1();
        selected_Subject2 = myTeacher.getSubject2();
        selected_Subject3 = myTeacher.getSubject3();
        selected_Subject4 = myTeacher.getSubject4();

        name_box.setText(Teacher_name);
        address_box.setText(Teacher_address);
        phone_box.setText(Teacher_phone);
        email_box.setText(Teacher_email);
        type_box.setText(Teacher_type);
        choiceboxSubject1.setValue(selected_Subject1);
        choiceboxSubject2.setValue(selected_Subject2);
        choiceboxSubject3.setValue(selected_Subject3);
        choiceboxSubject4.setValue(selected_Subject4);
        choiceboxGender.setValue(selected_Gender);
        if (("".equals(selected_Subject1))  || (selected_Subject1==null)){
            choiceboxSubject1.getSelectionModel().selectFirst();
        }
        if (("".equals(selected_Subject2)) || (selected_Subject2==null)) {
            choiceboxSubject2.getSelectionModel().selectFirst();
        }
        if (("".equals(selected_Subject3)) || (selected_Subject3==null)) {
            choiceboxSubject3.getSelectionModel().selectFirst();
        }
        if (("".equals(selected_Subject4)) || (selected_Subject4==null) ) {
            choiceboxSubject4.getSelectionModel().selectFirst();
        }
        
        if (("".equals(selected_Gender)) || (selected_Gender==null)) {
            choiceboxGender.getSelectionModel().selectFirst();
        }
    }

    private void refreshItemListInCombo() {
        //Set items equal to an empty ArrayList
        allGenders = FXCollections.observableArrayList();
        Subjects = FXCollections.observableArrayList();

        //Select out of the DB, fill accordingly
        getGenders(allGenders);
        getAllSubjects(Subjects);

        //Set the listview to what we just populated with DB contents
        choiceboxGender.setItems(allGenders);
        choiceboxSubject1.setItems(Subjects);
        choiceboxSubject2.setItems(Subjects);
        choiceboxSubject3.setItems(Subjects);
        choiceboxSubject4.setItems(Subjects);
    }

    private void selectFirstOne() {
        choiceboxGender.getSelectionModel().selectFirst();
        choiceboxSubject1.getSelectionModel().selectFirst();
        choiceboxSubject2.getSelectionModel().selectFirst();
        choiceboxSubject3.getSelectionModel().selectFirst();
        choiceboxSubject4.getSelectionModel().selectFirst();
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

    private void getGenders(ObservableList allGenders) {
        allGenders.add("--NONE SELECTED--");
        allGenders.add("MALE");
        allGenders.add("FEMALE");
    }
}
