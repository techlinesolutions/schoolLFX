/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolline;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import schoolline.entities.score;

/**
 * FXML Controller class
 *
 * @author Eche Michael
 */
public class AllScoresController implements Initializable {

    @FXML
    private Button btnDownloadReport;
    @FXML
    private TableView tableExpenses;
    @FXML
    private TableColumn idCol;
    @FXML
    private TableColumn nameCol;

    @FXML
    private Button btnClose;
    private ObservableList<Object> transactionData;
    private Connection con;
    private Statement stat;
    private String orderby;
    private String transact_id;
    private String name;
    private String expdate;
    private String score_id;
    private final String REPORT_NAME = "ALL_SCORES_REPORT";
    private String path;
    @FXML
    private TableColumn col1Col;
    @FXML
    private TableColumn col2Col;
    @FXML
    private TableColumn col3Col;
    @FXML
    private TableColumn col4Col;
    @FXML
    private TableColumn col5Col;
    @FXML
    private TableColumn col6Col;
    @FXML
    private TableColumn col7Col;
    @FXML
    private TableColumn sumCol;
    @FXML
    private TableColumn avrgCol;
    @FXML
    private TableColumn classCol;
    @FXML
    private TableColumn gradeCol;
    @FXML
    private AnchorPane updatePane;
    @FXML
    private TextField name_box;
    @FXML
    private ChoiceBox choiceboxClassroom;
    @FXML
    private Button btnUpdate1;
    @FXML
    private Button btnDelete1;
    @FXML
    private ChoiceBox choiceboxGrade;
    @FXML
    private ChoiceBox choiceboxGender;
    @FXML
    private TextField subject_1_sum_box;
    @FXML
    private TextField subject_1_avg_box;
    @FXML
    private TextField subject_1_col_5_box;
    @FXML
    private TextField subject_1_col_6_box;
    @FXML
    private TextField subject_1_col_7_box;
    @FXML
    private TextField subject_1_col_4_box;
    @FXML
    private TextField subject_1_col_3_box;
    @FXML
    private TextField subject_1_col_2_box;
    @FXML
    private TextField subject_1_col_1_box;
    private ObservableList allGenders;
    private ObservableList allGrades;
    private ObservableList allClassrooms;
    private ObservableList AllSubjects;
    private ObservableList AllStudents;
    @FXML
    private ChoiceBox choiceboxSubject;

    @FXML
    private ChoiceBox choiceboxStudent;
    private int arrayIndexCounter;
    private ArrayList<String> arrayIndexStore;
    private String id;
    @FXML
    private TableColumn subjectCol;
    @FXML
    private Button btnDownloadStudentReport;
    private String newStrValew;
    private String newStrstore;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("before refresh");
        refreshList();
        refreshItemListInCombo();
        selectFirstOne();
        checkTextFields();
        studNameChoiceBoxListener();

        System.out.println("after refresh");
    }

    @FXML
    private void onClickDownloadReport(ActionEvent event) {
        System.err.println("inside onClickDownloadReport");
        TableView<score> table = new TableView<score>();

        ObservableList<score> teamMembers = getReportDownloadMembers();
        table.setItems(teamMembers);
//column names and values
        TableColumn<score, String> excelIdCol = new TableColumn<score, String>("ID");
        excelIdCol.setCellValueFactory(new PropertyValueFactory<score, String>("score_id"));

        TableColumn<score, String> excelnameCol = new TableColumn<score, String>("NAME");
        excelnameCol.setCellValueFactory(new PropertyValueFactory<score, String>("student_name"));

        TableColumn<score, String> excelsubjectCol = new TableColumn<score, String>("SUBJECT");
        excelsubjectCol.setCellValueFactory(new PropertyValueFactory<score, String>("subject"));

        TableColumn<score, String> excelcol1Col = new TableColumn<score, String>("COL 1");
        excelcol1Col.setCellValueFactory(new PropertyValueFactory<score, String>("col1"));

        TableColumn<score, String> excelcol2CoL = new TableColumn<score, String>("COL 2");
        excelcol2CoL.setCellValueFactory(new PropertyValueFactory<score, String>("col2"));

        TableColumn<score, String> excelcol3Col = new TableColumn<score, String>("COL3");
        excelcol3Col.setCellValueFactory(new PropertyValueFactory<score, String>("col3"));

        TableColumn<score, String> excelcol4Col = new TableColumn<score, String>("COL 4");
        excelcol4Col.setCellValueFactory(new PropertyValueFactory<score, String>("col4"));

        TableColumn<score, String> excelcol5Col = new TableColumn<score, String>("COL 5");
        excelcol5Col.setCellValueFactory(new PropertyValueFactory<score, String>("col5"));

        TableColumn<score, String> excelcol6Col = new TableColumn<score, String>("COL 6");
        excelcol6Col.setCellValueFactory(new PropertyValueFactory<score, String>("col6"));

        TableColumn<score, String> excelcol7Col = new TableColumn<score, String>("COL 7");
        excelcol7Col.setCellValueFactory(new PropertyValueFactory<score, String>("col7"));

        TableColumn<score, String> excelcolsumCol = new TableColumn<score, String>("SUM");
        excelcolsumCol.setCellValueFactory(new PropertyValueFactory<score, String>("colsum"));

        TableColumn<score, String> excelcolavgCol = new TableColumn<score, String>("AVERAGE");
        excelcolavgCol.setCellValueFactory(new PropertyValueFactory<score, String>("colavg"));

        TableColumn<score, String> excelclassCol = new TableColumn<score, String>("CLS");
        excelclassCol.setCellValueFactory(new PropertyValueFactory<score, String>("classroom"));

        TableColumn<score, String> excelgradeCol = new TableColumn<score, String>("GRADE");
        excelgradeCol.setCellValueFactory(new PropertyValueFactory<score, String>("gradelevel"));

        TableColumn<score, String> exceldateCol = new TableColumn<score, String>("DATE");
        exceldateCol.setCellValueFactory(new PropertyValueFactory<score, String>("scores_date"));

        System.err.println("after property factory");

        ObservableList<TableColumn<score, ?>> columns = table.getColumns();
        columns.add(excelIdCol);
        columns.add(excelnameCol);
        columns.add(excelsubjectCol);
        columns.add(excelcol1Col);
        columns.add(excelcol2CoL);
        columns.add(excelcol3Col);
        columns.add(excelcol4Col);
        columns.add(excelcol5Col);
        columns.add(excelcol6Col);
        columns.add(excelcol7Col);
        columns.add(excelclassCol);
        columns.add(excelgradeCol);
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
        transactionData = FXCollections.observableArrayList();

        idCol.setCellValueFactory(
                new PropertyValueFactory<score, String>("score_id")
        );
        nameCol.setCellValueFactory(
                new PropertyValueFactory<score, String>("student_name")
        );
        subjectCol.setCellValueFactory(
                new PropertyValueFactory<score, String>("subject")
        );

        col1Col.setCellValueFactory(
                new PropertyValueFactory<score, String>("col1")
        );
        col2Col.setCellValueFactory(
                new PropertyValueFactory<score, String>("col2")
        );
        col3Col.setCellValueFactory(
                new PropertyValueFactory<score, String>("col3")
        );
        col4Col.setCellValueFactory(
                new PropertyValueFactory<score, String>("col4")
        );
        col5Col.setCellValueFactory(
                new PropertyValueFactory<score, String>("col5")
        );
        col6Col.setCellValueFactory(
                new PropertyValueFactory<score, String>("col6")
        );
        col7Col.setCellValueFactory(
                new PropertyValueFactory<score, String>("col7")
        );
        sumCol.setCellValueFactory(
                new PropertyValueFactory<score, String>("colsum")
        );
        classCol.setCellValueFactory(
                new PropertyValueFactory<score, String>("classroom")
        );
        gradeCol.setCellValueFactory(
                new PropertyValueFactory<score, String>("gradelevel")
        );
        avrgCol.setCellValueFactory(
                new PropertyValueFactory<score, String>("colavg")
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
            orderby = "score_id";
            String todRepQuery = "SELECT * FROM Scores_tbl "
                    + " ORDER BY " + orderby;
            System.out.println("todRepQuery is :" + todRepQuery);
            ResultSet rs = con.createStatement().executeQuery(todRepQuery);
            while (rs.next()) {
                score myExpscore = new score();

                transact_id = rs.getString("score_id");
                name = rs.getString("student_name");
                expdate = rs.getString("scores_date");

                System.out.println("ezpenseid = " + transact_id);
                System.out.println("name = " + name);
                System.out.println("expdate = " + expdate);

                myExpscore.setScore_id(rs.getString("score_id"));
                myExpscore.setStudent_name(rs.getString("student_name"));

                myExpscore.setCol1(rs.getString("col1"));
                myExpscore.setCol2(rs.getString("col2"));
                myExpscore.setCol3(rs.getString("col3"));
                myExpscore.setCol4(rs.getString("col4"));
                myExpscore.setCol5(rs.getString("col5"));
                myExpscore.setCol6(rs.getString("col6"));
                myExpscore.setCol7(rs.getString("col7"));

                myExpscore.setColavg(rs.getString("colavg"));
                myExpscore.setColsum(rs.getString("colsum"));
                myExpscore.setGradelevel(rs.getString("gradelevel"));
                myExpscore.setClassroom(rs.getString("classroom"));
                myExpscore.setScores_date(rs.getString("scores_date"));
                myExpscore.setGender(rs.getString("gender"));
                myExpscore.setSubject(rs.getString("subject"));

                transactionData.add(myExpscore);
            }
            tableExpenses.setItems(transactionData);

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    private ObservableList<score> getReportDownloadMembers() {
        ObservableList<score> allExpscores = FXCollections.observableArrayList();
        try {
            java.util.Date maindate = new java.util.Date();
            String theDate = maindate.toString();
            String theYear = theDate.substring(24, 28);
            String theDay = theDate.substring(0, 10) + " " + theYear;

            // SQLiteConfig config = new SQLiteConfig();
            con = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            stat = con.createStatement();
            System.out.println("Opened database successfully");
            orderby = "score_id";
            String todRepQuery = "SELECT * FROM Scores_tbl "
                    + " ORDER BY " + orderby;
            System.out.println("todRepQuery is :" + todRepQuery);
            ResultSet rs = con.createStatement().executeQuery(todRepQuery);
            while (rs.next()) {
                score myExpscore = new score();

                myExpscore.setScore_id(rs.getString("score_id"));
                myExpscore.setStudent_name(rs.getString("student_name"));
                myExpscore.setSubject(rs.getString("subject"));
                myExpscore.setCol1(rs.getString("col1"));
                myExpscore.setCol2(rs.getString("col2"));
                myExpscore.setCol3(rs.getString("col3"));
                myExpscore.setCol4(rs.getString("col4"));
                myExpscore.setCol5(rs.getString("col5"));
                myExpscore.setCol6(rs.getString("col6"));
                myExpscore.setCol7(rs.getString("col7"));
                myExpscore.setColsum(rs.getString("colsum"));
                myExpscore.setColavg(rs.getString("colavg"));
                myExpscore.setClassroom(rs.getString("classroom"));
                myExpscore.setGradelevel(rs.getString("gradelevel"));
                

                myExpscore.setScores_date(rs.getString("scores_date"));
                myExpscore.setGender(rs.getString("gender"));

                allExpscores.add(myExpscore);
            }

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return allExpscores;
    }

    @FXML
    private boolean onClickUpdate(ActionEvent event) throws IOException {
        score myscore = new score();
        Date date = new Date();
        String theDate = date.toString();
        //format for report queries
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        cal.add(Calendar.DATE, 0);
        String score_name = name_box.getText();
        String selected_Gender = choiceboxGender.getValue().toString().toUpperCase();
        String Score_grade = choiceboxGrade.getValue().toString().toUpperCase();
        String Score_Classroom = choiceboxClassroom.getValue().toString().toUpperCase();
        String Score_Subject = choiceboxSubject.getValue().toString().toUpperCase();
        String col1 = subject_1_col_1_box.getText().toUpperCase();
        String col2 = subject_1_col_2_box.getText().toUpperCase();
        String col3 = subject_1_col_3_box.getText().toUpperCase();
        String col4 = subject_1_col_4_box.getText().toUpperCase();
        String col5 = subject_1_col_5_box.getText().toUpperCase();
        String col6 = subject_1_col_6_box.getText().toUpperCase();
        String col7 = subject_1_col_7_box.getText().toUpperCase();
        String colsum = subject_1_sum_box.getText().toUpperCase();
        String colavg = subject_1_avg_box.getText().toUpperCase();

        if ((score_name.trim().length() == 0) || (score_name == "") || (score_name.trim().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please Highlight A score Record");

            alert.showAndWait();
            //System.clearProperty(Students_name);
            return false;
        }
        //myStudents.setId(rs.getString("id"));

        myscore.setStudent_name(score_name);
        myscore.setScores_date(theDate);
        myscore.setGender(selected_Gender);
        myscore.setGradelevel(Score_grade);
        myscore.setClassroom(Score_Classroom);
        myscore.setCol1(col1);
        myscore.setCol2(col2);
        myscore.setCol3(col3);
        myscore.setCol4(col4);
        myscore.setCol5(col5);
        myscore.setCol6(col6);
        myscore.setCol7(col7);
        myscore.setColsum(colsum);
        myscore.setColavg(colavg);
        myscore.setSubject(Score_Subject);

        ObservableList highlightedStudentsRecord, allStudentsRecords;

        highlightedStudentsRecord = tableExpenses.getSelectionModel().getSelectedItems();

        System.out.println("Students_name is : " + score_name);
        score selectedscore = new score();
        selectedscore = (score) tableExpenses.getSelectionModel().getSelectedItem();
        id = selectedscore.getStudent_id();
        int theId = Integer.parseInt(id);

        System.out.println("the ID IS: " + theId);
        String query = "UPDATE Scores_tbl set gender ='"
                + selected_Gender + "',"
                + "classroom ='"
                + Score_Classroom + "',"
                + "gradelevel ='"
                + Score_grade + "',"
                + "subject ='"
                + Score_Subject + "',"
                + "col1 ='"
                + col1 + "',"
                + "col2 ='"
                + col2 + "',"
                + "col3 ='"
                + col3 + "',"
                + "col4 ='"
                + col4 + "',"
                + "col5 ='"
                + col5 + "',"
                + "col6 ='"
                + col6 + "',"
                + "col7 ='"
                + col7 + "',"
                + "colavg ='"
                + colavg + "',"
                + "colsum ='"
                + colsum + "',"
                + "student_date ='"
                + theDate + "'"
                + " WHERE score_id = "
                + theId;

        System.out.println("updating\n" + query);
        updateStatement(query);

        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
        alert3.setTitle("Information Dialog");
        alert3.setHeaderText(null);
        alert3.setContentText("Record updated Succesfully.");

        alert3.showAndWait();

        System.out.println("Succesfully Updated");

        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("AllScores.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();

        return true;

    }

    @FXML
    private void onClickDelete(ActionEvent event) throws IOException {
       
        //int theId = Integer.parseInt(id);
        String Students_name = name_box.getText();

        if ((Students_name.trim().length() == 0) || (Students_name == "") || (Students_name.trim().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please Highlight A Student Record");

            alert.showAndWait();
            System.clearProperty(Students_name);
        }
        //myscore.setId(rs.getString("id"));

        ObservableList highlightedscoreRecord, allscoreRecords;
        allscoreRecords = tableExpenses.getItems();
        highlightedscoreRecord = tableExpenses.getSelectionModel().getSelectedItems();
        System.out.println("Students_name is : " + Students_name);
        score selectedscore = new score();
        selectedscore = (score) tableExpenses.getSelectionModel().getSelectedItem();
        id = selectedscore.getScore_id();
         System.out.println("id is "+id);
        String query = "DELETE FROM Scores_tbl "
                + " WHERE score_id = "
                + id;

        System.out.println("updating\n" + query);
        updateStatement(query);

        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
        alert3.setTitle("Information Dialog");
        alert3.setHeaderText(null);
        alert3.setContentText("Record Deleted Succesfully.");

        alert3.showAndWait();

        System.out.println("Succesfully Updated");

        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("AllScores.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();

    }

    @FXML
    private void onClickTable(MouseEvent event) {
        name_box.clear();
        choiceboxGender.getSelectionModel().clearSelection();

        choiceboxGrade.getSelectionModel().clearSelection();
        choiceboxClassroom.getSelectionModel().clearSelection();
        choiceboxSubject.getSelectionModel().clearSelection();

        //selectFirstOne();

        score myscore = new score();
        Date date = new Date();
        String theDate = date.toString();
        String score_name;
        String selected_Gender;
        String score_grade;
        String score_Classroom;
        String Score_Subject;
        String col1;
        String col2;
        String col3;
        String col4;
        String col5;
        String col6;
        String col7;
        String colsum;
        String colavg;

        myscore = (score) tableExpenses.getSelectionModel().getSelectedItem();
        id = myscore.getStudent_id();
        score_name = myscore.getStudent_name();
        selected_Gender = myscore.getGender();
        score_Classroom = myscore.getClassroom();
        score_grade = myscore.getGradelevel();
        Score_Subject = myscore.getSubject();
        col1 = myscore.getCol1();
        col2 = myscore.getCol2();
        col3 = myscore.getCol3();
        col4 = myscore.getCol4();
        col5 = myscore.getCol5();
        col6 = myscore.getCol6();
        col7 = myscore.getCol7();
        colsum = myscore.getColsum();
        colavg = myscore.getColavg();

        name_box.setText(score_name);
        choiceboxGender.setValue(selected_Gender);
        choiceboxGrade.setValue(score_grade);
        choiceboxClassroom.setValue(score_Classroom);
        choiceboxSubject.setValue(Score_Subject);
        subject_1_col_1_box.setText(col1);
        subject_1_col_2_box.setText(col2);
        subject_1_col_3_box.setText(col3);
        subject_1_col_4_box.setText(col4);
        subject_1_col_5_box.setText(col5);
        subject_1_col_6_box.setText(col6);
        subject_1_col_7_box.setText(col7);
        subject_1_sum_box.setText(colsum);
        subject_1_avg_box.setText(colavg);

        if (("".equals(selected_Gender)) || (selected_Gender == null)) {
            choiceboxGender.getSelectionModel().selectFirst();
        }

        if (("".equals(score_grade)) || (score_grade == null)) {
            choiceboxGrade.getSelectionModel().selectFirst();
        }

        if (("".equals(score_Classroom)) || (score_Classroom == null)) {
            choiceboxClassroom.getSelectionModel().selectFirst();
        }

        if (("".equals(Score_Subject)) || (Score_Subject == null)) {
            choiceboxSubject.getSelectionModel().selectFirst();
        }

        if (("".equals(score_name)) || (score_name == null)) {
            choiceboxStudent.getSelectionModel().selectFirst();
        }
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
        choiceboxGender.setItems(allGenders);
        choiceboxGrade.setItems(allGrades);
        choiceboxClassroom.setItems(allClassrooms);
        choiceboxStudent.setItems(AllStudents);
        choiceboxSubject.setItems(AllSubjects);
    }

    private void getGenders(ObservableList allGenders) {
        allGenders.add("--NONE SELECTED--");
        allGenders.add("MALE");
        allGenders.add("FEMALE");
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

    private void selectFirstOne() {
        choiceboxGender.getSelectionModel().selectFirst();
        choiceboxGrade.getSelectionModel().selectFirst();
        choiceboxClassroom.getSelectionModel().selectFirst();
        choiceboxStudent.getSelectionModel().selectFirst();

        choiceboxSubject.getSelectionModel().selectFirst();
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
    }

    private void studNameChoiceBoxListener() {
        // name choicebox listener
        choiceboxStudent.getSelectionModel().selectedItemProperty().addListener((v,
                oldIdValew, newStrValew) -> startSelection((String) newStrValew));
         newStrstore = newStrValew;
    }
   

            private int getSelectedStdudent_id(Number idVal) {

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

                return newInt;
            }

            private void startSelection(String selected_Student_Id) {

                btnDownloadReport.setVisible(false);
                btnDownloadReport.setDisable(true);
                btnDownloadStudentReport.setVisible(true);
                btnDownloadStudentReport.setDisable(false);
                transactionData = FXCollections.observableArrayList();

                idCol.setCellValueFactory(
                        new PropertyValueFactory<score, String>("score_id")
                );
                nameCol.setCellValueFactory(
                        new PropertyValueFactory<score, String>("student_name")
                );
                subjectCol.setCellValueFactory(
                        new PropertyValueFactory<score, String>("subject")
                );

                col1Col.setCellValueFactory(
                        new PropertyValueFactory<score, String>("col1")
                );
                col2Col.setCellValueFactory(
                        new PropertyValueFactory<score, String>("col2")
                );
                col3Col.setCellValueFactory(
                        new PropertyValueFactory<score, String>("col3")
                );
                col4Col.setCellValueFactory(
                        new PropertyValueFactory<score, String>("col4")
                );
                col5Col.setCellValueFactory(
                        new PropertyValueFactory<score, String>("col5")
                );
                col6Col.setCellValueFactory(
                        new PropertyValueFactory<score, String>("col6")
                );
                col7Col.setCellValueFactory(
                        new PropertyValueFactory<score, String>("col7")
                );
                sumCol.setCellValueFactory(
                        new PropertyValueFactory<score, String>("colsum")
                );
                classCol.setCellValueFactory(
                        new PropertyValueFactory<score, String>("classroom")
                );
                gradeCol.setCellValueFactory(
                        new PropertyValueFactory<score, String>("gradelevel")
                );
                avrgCol.setCellValueFactory(
                        new PropertyValueFactory<score, String>("colavg")
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
                    orderby = "score_id";
                    String todRepQuery = "SELECT * FROM Scores_tbl WHERE student_name ='"
                            + selected_Student_Id
                            + "' ORDER BY " + orderby;
                    System.out.println("todRepQuery is :" + todRepQuery);
                    ResultSet rs = con.createStatement().executeQuery(todRepQuery);
                    while (rs.next()) {
                        score myExpscore = new score();

                        transact_id = rs.getString("score_id");
                        name = rs.getString("student_name");
                        expdate = rs.getString("scores_date");

                        System.out.println("ezpenseid = " + transact_id);
                        System.out.println("name = " + name);
                        System.out.println("expdate = " + expdate);

                        myExpscore.setScore_id(rs.getString("score_id"));
                        myExpscore.setStudent_name(rs.getString("student_name"));

                        myExpscore.setCol1(rs.getString("col1"));
                        myExpscore.setCol2(rs.getString("col2"));
                        myExpscore.setCol3(rs.getString("col3"));
                        myExpscore.setCol4(rs.getString("col4"));
                        myExpscore.setCol5(rs.getString("col5"));
                        myExpscore.setCol6(rs.getString("col6"));
                        myExpscore.setCol7(rs.getString("col7"));

                        myExpscore.setColavg(rs.getString("colavg"));
                        myExpscore.setColsum(rs.getString("colsum"));
                        myExpscore.setGradelevel(rs.getString("gradelevel"));
                        myExpscore.setClassroom(rs.getString("classroom"));
                        myExpscore.setScores_date(rs.getString("scores_date"));
                        myExpscore.setGender(rs.getString("gender"));
                        myExpscore.setSubject(rs.getString("subject"));

                        transactionData.add(myExpscore);
                    }
                    tableExpenses.setItems(transactionData);

                } catch (SQLException e) {
                    System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    System.exit(0);
                }
            }
       
  

    @FXML
    private void onClickDownloadStudentReport(ActionEvent event) {

        String stdName = choiceboxStudent.getValue().toString();
        System.err.println("inside student onClickDownloadReport");
        System.err.println(" student newStrstore is "+newStrstore);
        System.err.println(" student stdName is "+stdName);
        TableView<score> table = new TableView<score>();

        ObservableList<score> teamMembers = getStudentReportDownloadMembers(stdName);
        table.setItems(teamMembers);
//column names and values
        TableColumn<score, String> excelIdCol = new TableColumn<score, String>("ID");
        excelIdCol.setCellValueFactory(new PropertyValueFactory<score, String>("score_id"));

        TableColumn<score, String> excelnameCol = new TableColumn<score, String>("NAME");
        excelnameCol.setCellValueFactory(new PropertyValueFactory<score, String>("student_name"));

        TableColumn<score, String> excelsubjectCol = new TableColumn<score, String>("SUBJECT");
        excelsubjectCol.setCellValueFactory(new PropertyValueFactory<score, String>("subject"));

        TableColumn<score, String> excelcol1Col = new TableColumn<score, String>("COL 1");
        excelcol1Col.setCellValueFactory(new PropertyValueFactory<score, String>("col1"));

        TableColumn<score, String> excelcol2CoL = new TableColumn<score, String>("COL 2");
        excelcol2CoL.setCellValueFactory(new PropertyValueFactory<score, String>("col2"));

        TableColumn<score, String> excelcol3Col = new TableColumn<score, String>("COL3");
        excelcol3Col.setCellValueFactory(new PropertyValueFactory<score, String>("col3"));

        TableColumn<score, String> excelcol4Col = new TableColumn<score, String>("COL 4");
        excelcol4Col.setCellValueFactory(new PropertyValueFactory<score, String>("col4"));

        TableColumn<score, String> excelcol5Col = new TableColumn<score, String>("COL 5");
        excelcol5Col.setCellValueFactory(new PropertyValueFactory<score, String>("col5"));

        TableColumn<score, String> excelcol6Col = new TableColumn<score, String>("COL 6");
        excelcol6Col.setCellValueFactory(new PropertyValueFactory<score, String>("col6"));

        TableColumn<score, String> excelcol7Col = new TableColumn<score, String>("COL 7");
        excelcol7Col.setCellValueFactory(new PropertyValueFactory<score, String>("col7"));

        TableColumn<score, String> excelcolsumCol = new TableColumn<score, String>("SUM");
        excelcolsumCol.setCellValueFactory(new PropertyValueFactory<score, String>("colsum"));

        TableColumn<score, String> excelcolavgCol = new TableColumn<score, String>("AVERAGE");
        excelcolavgCol.setCellValueFactory(new PropertyValueFactory<score, String>("colavg"));

        TableColumn<score, String> excelclassCol = new TableColumn<score, String>("CLS");
        excelclassCol.setCellValueFactory(new PropertyValueFactory<score, String>("classroom"));

        TableColumn<score, String> excelgradeCol = new TableColumn<score, String>("GRADE");
        excelgradeCol.setCellValueFactory(new PropertyValueFactory<score, String>("gradelevel"));

        TableColumn<score, String> exceldateCol = new TableColumn<score, String>("DATE");
        exceldateCol.setCellValueFactory(new PropertyValueFactory<score, String>("scores_date"));

        System.err.println("after property factory");

        ObservableList<TableColumn<score, ?>> columns = table.getColumns();
        columns.add(excelIdCol);
        columns.add(excelnameCol);
        columns.add(excelsubjectCol);
        columns.add(excelcol1Col);
        columns.add(excelcol2CoL);
        columns.add(excelcol3Col);
        columns.add(excelcol4Col);
        columns.add(excelcol5Col);
        columns.add(excelcol6Col);
        columns.add(excelcol7Col);
        columns.add(excelclassCol);
        columns.add(excelgradeCol);
        columns.add(exceldateCol);

        System.err.println("after adding columns");

        Workbook workbook = new HSSFWorkbook();
        Sheet spreadsheet = workbook.createSheet(stdName + "_" + REPORT_NAME);
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
            FileOutputStream fileOut = new FileOutputStream(path + stdName + "_" + REPORT_NAME + strDateInFormat + ".xls");
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
        alert.setContentText("Report Downloaded Succesfully to " +  path + ".\n with Filename: " + stdName + "_" +REPORT_NAME + strDateInFormat + ".xls");

        alert.showAndWait();
    }

    private ObservableList<score> getStudentReportDownloadMembers(String stdName) {
        ObservableList<score> allExpscores = FXCollections.observableArrayList();
        try {
            java.util.Date maindate = new java.util.Date();
            String theDate = maindate.toString();
            String theYear = theDate.substring(24, 28);
            String theDay = theDate.substring(0, 10) + " " + theYear;

            // SQLiteConfig config = new SQLiteConfig();
            con = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            stat = con.createStatement();
            System.out.println("Opened database successfully");
            orderby = "score_id";
            String todRepQuery = "SELECT * FROM Scores_tbl WHERE student_name ='"
                    + stdName
                    + "'  ORDER BY " + orderby;
            System.out.println("todRepQuery is :" + todRepQuery);
            ResultSet rs = con.createStatement().executeQuery(todRepQuery);
            while (rs.next()) {
                score myExpscore = new score();



           myExpscore.setScore_id(rs.getString("score_id"));
                myExpscore.setStudent_name(rs.getString("student_name"));
                myExpscore.setSubject(rs.getString("subject"));
                myExpscore.setCol1(rs.getString("col1"));
                myExpscore.setCol2(rs.getString("col2"));
                myExpscore.setCol3(rs.getString("col3"));
                myExpscore.setCol4(rs.getString("col4"));
                myExpscore.setCol5(rs.getString("col5"));
                myExpscore.setCol6(rs.getString("col6"));
                myExpscore.setCol7(rs.getString("col7"));
                myExpscore.setColsum(rs.getString("colsum"));
                myExpscore.setColavg(rs.getString("colavg"));
                      myExpscore.setClassroom(rs.getString("classroom"));
                myExpscore.setGradelevel(rs.getString("gradelevel"));

                myExpscore.setScores_date(rs.getString("scores_date"));
                myExpscore.setGender(rs.getString("gender"));

                allExpscores.add(myExpscore);
            }

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return allExpscores;
    }

}
