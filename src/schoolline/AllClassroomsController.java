/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolline;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import schoolline.entities.classroom;

/**
 * FXML Controller class
 *
 * @author Eche Michael
 */
public class AllClassroomsController implements Initializable {

    @FXML
    private Button btnDownloadReport;
    @FXML
    private TableView tableExpenses;
    @FXML
    private TableColumn idCol;
    @FXML
    private TableColumn nameCol;
    @FXML
    private TableColumn descriptionCol;

    @FXML
    private Button btnClose;
    private ObservableList transactionData;
    private Connection con;
    private Statement stat;
    private String orderby;
    private String transact_id;
    private String name;
    private String desctription;
    private String expdate;
    private String id;
    private final String REPORT_NAME = "ALL_CLASSROOMS_REPORT";
    private String path;
    @FXML
    private TableColumn gradeleveleCol;
    @FXML
    private TextField name_box;
    @FXML
    private TextField description_box;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private ChoiceBox choiceboxGrade;
    private ObservableList allGrades;
    private String gradelevel;
    @FXML
    private Button btnAddNew;

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
    private void onClickDownloadReport(ActionEvent event) {
        System.err.println("inside onClickDownloadReport");
        TableView<classroom> table = new TableView<classroom>();

        ObservableList<classroom> teamMembers = getReportDownloadMembers();
        table.setItems(teamMembers);
//column names and values
        TableColumn<classroom, String> excelIdCol = new TableColumn<classroom, String>("ID");
        excelIdCol.setCellValueFactory(new PropertyValueFactory<classroom, String>("id"));

        TableColumn<classroom, String> excelnameCol = new TableColumn<classroom, String>("NAME");
        excelnameCol.setCellValueFactory(new PropertyValueFactory<classroom, String>("name"));

        TableColumn<classroom, String> exceldescriptionCol = new TableColumn<classroom, String>("DESCRIPTION");
        exceldescriptionCol.setCellValueFactory(new PropertyValueFactory<classroom, String>("description"));

        TableColumn<classroom, String> excelgradeCol = new TableColumn<classroom, String>("DESCRIPTION");
        exceldescriptionCol.setCellValueFactory(new PropertyValueFactory<classroom, String>("gradelevel"));
        
        TableColumn<classroom, String> exceldateCol = new TableColumn<classroom, String>("DATE");
        exceldateCol.setCellValueFactory(new PropertyValueFactory<classroom, String>("classroom_date"));

        System.err.println("after property factory");

        ObservableList<TableColumn<classroom, ?>> columns = table.getColumns();
        columns.add(excelIdCol);
        columns.add(excelnameCol);
        columns.add(exceldescriptionCol);
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
                new PropertyValueFactory<classroom, String>("id")
        );
        nameCol.setCellValueFactory(
                new PropertyValueFactory<classroom, String>("name")
        );
        descriptionCol.setCellValueFactory(
                new PropertyValueFactory<classroom, String>("description")
        );
        gradeleveleCol.setCellValueFactory(
                new PropertyValueFactory<classroom, String>("gradelevel")
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
            orderby = "id";
            String todRepQuery = "SELECT * FROM Classrooms_tbl "
                    + " ORDER BY " + orderby;
            System.out.println("todRepQuery is :" + todRepQuery);
            ResultSet rs = con.createStatement().executeQuery(todRepQuery);
            while (rs.next()) {
                classroom myExpclassroom = new classroom();

                transact_id = rs.getString("id");
                name = rs.getString("name");
                desctription = rs.getString("description");
                gradelevel = rs.getString("gradelevel");
                expdate = rs.getString("classroom_date");

                System.out.println("ezpenseid = " + transact_id);
                System.out.println("name = " + name);
                System.out.println("desctription = " + desctription);
                System.out.println("expdate = " + expdate);

                myExpclassroom.setId(rs.getString("id"));
                myExpclassroom.setName(rs.getString("name"));
                myExpclassroom.setDescription(rs.getString("description"));
                myExpclassroom.setClassroom_date(rs.getString("classroom_date"));
                 myExpclassroom.setGradelevel(rs.getString("gradelevel"));

                transactionData.add(myExpclassroom);
            }
            tableExpenses.setItems(transactionData);

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    private ObservableList<classroom> getReportDownloadMembers() {

        ObservableList<classroom> allExpclassrooms = FXCollections.observableArrayList();
        try {
            java.util.Date maindate = new java.util.Date();
            String theDate = maindate.toString();
            String theYear = theDate.substring(24, 28);
            String theDay = theDate.substring(0, 10) + " " + theYear;

            // SQLiteConfig config = new SQLiteConfig();
            con = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            stat = con.createStatement();
            System.out.println("Opened database successfully");
            orderby = "id";
            String todRepQuery = "SELECT * FROM Classrooms_tbl "
                    + " ORDER BY " + orderby;
            System.out.println("todRepQuery is :" + todRepQuery);
            ResultSet rs = con.createStatement().executeQuery(todRepQuery);
            while (rs.next()) {
                classroom myExpclassroom = new classroom();

                id = rs.getString("id");
                name = rs.getString("name");
                desctription = rs.getString("description");
                expdate = rs.getString("classroom_date");

                System.out.println("ezpenseid = " + id);
                System.out.println("name = " + name);
                System.out.println("desctription = " + desctription);
                System.out.println("expdate = " + expdate);

                myExpclassroom.setId(rs.getString("id"));
                myExpclassroom.setName(rs.getString("name"));
                myExpclassroom.setDescription(rs.getString("description"));
                myExpclassroom.setClassroom_date(rs.getString("classroom_date"));

                allExpclassrooms.add(myExpclassroom);
            }

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return allExpclassrooms;
    }

    private void refreshItemListInCombo() {
        //Set items equal to an empty ArrayList
        allGrades = FXCollections.observableArrayList();

        //Select out of the DB, fill accordingly
        getGrades(allGrades);

        //Set the listview to what we just populated with DB contents
        choiceboxGrade.setItems(allGrades);
    }

    private void selectFirstOne() {
        choiceboxGrade.getSelectionModel().selectFirst();
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

    @FXML
    private boolean onClickUpdate(ActionEvent event) throws IOException {
        classroom myClassroom = new classroom();
        Date date = new Date();
        int theId = Integer.parseInt(id);
        String theDate = date.toString();
        //format for report queries
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        cal.add(Calendar.DATE, 0);
        String classroom_name = name_box.getText();
        String classroom_description = description_box.getText().toUpperCase();
        String selectedGrade = choiceboxGrade.getValue().toString().toUpperCase();

        System.out.println("classroom_name is: " + classroom_name);

        if ((classroom_name.trim().length() == 0) || (classroom_name == "") || (classroom_name.trim().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please Highlight A Class Room Record");

            alert.showAndWait();
            //System.clearProperty(classroom_name);
            return false;
        }
        //myClassroom.setId(rs.getString("id"));

        myClassroom.setName(classroom_name);
        myClassroom.setDescription(classroom_description);
        myClassroom.setClassroom_date(theDate);
        myClassroom.setGradelevel(selectedGrade);

        ObservableList highlightedClassRecord, allClassRecords;
        allClassRecords = tableExpenses.getItems();
        highlightedClassRecord = tableExpenses.getSelectionModel().getSelectedItems();

        System.out.println("classroom_name is : " + classroom_name);
        System.out.println("amount_description is : " + classroom_description);
        System.out.println("the ID IS: " + theId);
        System.out.println("the selectedGrade IS: " + selectedGrade);
        String query = "UPDATE Classrooms_tbl set name ='"
                + classroom_name + "',"
                + "description ='"
                + classroom_description + "',"
                + "gradelevel ='"
                + selectedGrade + "',"
                + "formated_date ='"
                + dateFormat + "',"
                + "classroom_date ='"
                + theDate + "'"
                + " WHERE id = "
                + theId;

        System.out.println("updating\n" + query);
        updateStatement(query);

        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
        alert3.setTitle("Information Dialog");
        alert3.setHeaderText(null);
        alert3.setContentText("Record updated Succesfully.");

        alert3.showAndWait();

        System.out.println("Succesfully Updated");

        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("AllClassrooms.fxml"));
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
        String classroom_name = name_box.getText();
        String classroom_description = description_box.getText().toUpperCase();
        String selectedGrade = choiceboxGrade.getValue().toString().toUpperCase();
        if ((classroom_name.trim().length() == 0) || (classroom_name == "") || (classroom_name.trim().isEmpty()) || (selectedGrade == "--NONE SELECTED--")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please Highlight A Class Room Record");

            alert.showAndWait();
            System.clearProperty(classroom_name);
        } 
            //myClassroom.setId(rs.getString("id"));

            ObservableList highlightedClassRecord, allClassRecords;
            allClassRecords = tableExpenses.getItems();
            highlightedClassRecord = tableExpenses.getSelectionModel().getSelectedItems();
                System.out.println("classroom_name is : " + classroom_name);
        System.out.println("amount_description is : " + classroom_description);
        System.out.println("the ID IS: " + theId);
        System.out.println("the selectedGrade IS: " + selectedGrade);
        String query = "DELETE FROM Classrooms_tbl "             
                + " WHERE id = "
                + theId;

        System.out.println("updating\n" + query);
        updateStatement(query);

        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
        alert3.setTitle("Information Dialog");
        alert3.setHeaderText(null);
        alert3.setContentText("Record Deleted Succesfully.");

        alert3.showAndWait();

        System.out.println("Succesfully Updated");

        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("AllClassrooms.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();
        
    }
        
    

    @FXML
    private void onClickTable(MouseEvent event) {
        name_box.clear();
        description_box.clear();
        choiceboxGrade.getSelectionModel().clearSelection();
        selectFirstOne();
        classroom myClassroom = new classroom();
        Date date = new Date();
        String theDate = date.toString();
        String classroom_name;
        String classroom_description;
        String selectedGrade;
        myClassroom = (classroom) tableExpenses.getSelectionModel().getSelectedItem();
        id = myClassroom.getId();
        classroom_name = myClassroom.getName();
        classroom_description = myClassroom.getDescription();
        selectedGrade = myClassroom.getGradelevel();
        name_box.setText(classroom_name);
        description_box.setText(classroom_description);
        choiceboxGrade.setValue(selectedGrade);
    }

    private void updateStatement(String insert_query) {
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

 
}
