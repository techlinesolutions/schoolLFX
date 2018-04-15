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
import schoolline.entities.gradeLevel;
public class AllGradesController implements Initializable {
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
    private ObservableList<Object> transactionData;
    private Connection con;
    private Statement stat;
    private String orderby;
    private String transact_id;
    private String name;
    private String desctription;
    private String expdate;
    private String id;
    private final String REPORT_NAME = "TODAY_GRADES_REPORT";
    private String path;
    @FXML
    private TextField name_box;
    @FXML
    private TextField description_box;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("before refresh");
        refreshList();       
        System.out.println("after refresh");
    }    

    @FXML
    private void onClickDownloadReport(ActionEvent event) {
         System.err.println("inside onClickDownloadReport");
        TableView<gradeLevel> table = new TableView<gradeLevel>();

        ObservableList<gradeLevel> teamMembers = getReportDownloadMembers();
        table.setItems(teamMembers);
//column names and values
        TableColumn<gradeLevel, String> excelIdCol = new TableColumn<gradeLevel, String>("ID");
        excelIdCol.setCellValueFactory(new PropertyValueFactory<gradeLevel, String>("id"));

        TableColumn<gradeLevel, String> excelnameCol = new TableColumn<gradeLevel, String>("NAME");
        excelnameCol.setCellValueFactory(new PropertyValueFactory<gradeLevel, String>("name"));

        TableColumn<gradeLevel, String> exceldescriptionCol = new TableColumn<gradeLevel, String>("DESCRIPTION");
        exceldescriptionCol.setCellValueFactory(new PropertyValueFactory<gradeLevel, String>("description"));

        TableColumn<gradeLevel, String> exceldateCol = new TableColumn<gradeLevel, String>("DATE");
        exceldateCol.setCellValueFactory(new PropertyValueFactory<gradeLevel, String>("grades_date"));

 
   

        System.err.println("after property factory");

        ObservableList<TableColumn<gradeLevel, ?>> columns = table.getColumns();
        columns.add(excelIdCol);
        columns.add(excelnameCol);
        columns.add(exceldescriptionCol);
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
                new PropertyValueFactory<gradeLevel, String>("id")
        );
        nameCol.setCellValueFactory(
                new PropertyValueFactory<gradeLevel, String>("name")
        );
        descriptionCol.setCellValueFactory(
                new PropertyValueFactory<gradeLevel, String>("description")
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
            String todRepQuery = "SELECT * FROM Grades_tbl "
                    + " ORDER BY " + orderby;
            System.out.println("todRepQuery is :" + todRepQuery);
            ResultSet rs = con.createStatement().executeQuery(todRepQuery);
            while (rs.next()) {
                gradeLevel myExpgradeLevel = new gradeLevel();

                transact_id = rs.getString("id");
                name = rs.getString("name");
                desctription = rs.getString("description");
                expdate = rs.getString("grades_date");

                System.out.println("ezpenseid = " + transact_id);
                System.out.println("name = " + name);
                System.out.println("desctription = " + desctription);
                System.out.println("expdate = " + expdate);


                myExpgradeLevel.setId(rs.getString("id"));
                myExpgradeLevel.setName(rs.getString("name"));
                myExpgradeLevel.setDescription(rs.getString("description"));
                myExpgradeLevel.setGrade_date(rs.getString("grades_date"));

                transactionData.add(myExpgradeLevel);
            }
            tableExpenses.setItems(transactionData);

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    

    }

    private ObservableList<gradeLevel> getReportDownloadMembers() {
        
        ObservableList<gradeLevel> allExpgradeLevels = FXCollections.observableArrayList();
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
            String todRepQuery = "SELECT * FROM Grades_tbl "
                    + " ORDER BY " + orderby;
            System.out.println("todRepQuery is :" + todRepQuery);
            ResultSet rs = con.createStatement().executeQuery(todRepQuery);
            while (rs.next()) {
                gradeLevel myExpgradeLevel = new gradeLevel();

                id = rs.getString("id");
                name = rs.getString("name");
                desctription = rs.getString("description");
                expdate = rs.getString("grades_date");
               

                System.out.println("ezpenseid = " + id);
                System.out.println("name = " + name);
                System.out.println("desctription = " + desctription);
                System.out.println("expdate = " + expdate);
        

                myExpgradeLevel.setId(rs.getString("id"));
                myExpgradeLevel.setName(rs.getString("name"));
                myExpgradeLevel.setDescription(rs.getString("description"));
                myExpgradeLevel.setGrade_date(rs.getString("grades_date"));

                allExpgradeLevels.add(myExpgradeLevel);
            }

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return allExpgradeLevels;  }

 @FXML
    private boolean onClickUpdate(ActionEvent event) throws IOException {
        gradeLevel myClassroom = new gradeLevel();
        Date date = new Date();
        int theId = Integer.parseInt(id);
        String theDate = date.toString();
        //format for report queries
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        cal.add(Calendar.DATE, 0);
        String gradeLeve_name = name_box.getText();
        String gradeLeve_description = description_box.getText().toUpperCase();

        System.out.println("gradeLeve_name is: " + gradeLeve_name);

        if ((gradeLeve_name.trim().length() == 0) || (gradeLeve_name == "") || (gradeLeve_name.trim().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please Highlight A Grade Record");

            alert.showAndWait();
            //System.clearProperty(gradeLeve_name);
            return false;
        }
        //myClassroom.setId(rs.getString("id"));

        myClassroom.setName(gradeLeve_name);
        myClassroom.setDescription(gradeLeve_description);
        myClassroom.setGrade_date(theDate);

        ObservableList highlightedClassRecord, allClassRecords;
        allClassRecords = tableExpenses.getItems();
        highlightedClassRecord = tableExpenses.getSelectionModel().getSelectedItems();

        System.out.println("gradeLeve_name is : " + gradeLeve_name);
        System.out.println("amount_description is : " + gradeLeve_description);
        System.out.println("the ID IS: " + theId);
        String query = "UPDATE Grades_tbl set name ='"
                + gradeLeve_name + "',"
                + "description ='"
                + gradeLeve_description + "'"
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

        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("AllGrades.fxml"));
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
        String gradeLeve_name = name_box.getText();
        String gradeLeve_description = description_box.getText().toUpperCase();
        if ((gradeLeve_name.trim().length() == 0) || (gradeLeve_name == "") || (gradeLeve_name.trim().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please Highlight A Class Room Record");

            alert.showAndWait();
            System.clearProperty(gradeLeve_name);
        } 
            //myClassroom.setId(rs.getString("id"));

            ObservableList highlightedClassRecord, allClassRecords;
            allClassRecords = tableExpenses.getItems();
            highlightedClassRecord = tableExpenses.getSelectionModel().getSelectedItems();
                System.out.println("gradeLeve_name is : " + gradeLeve_name);
        System.out.println("amount_description is : " + gradeLeve_description);
        System.out.println("the ID IS: " + theId);
        String query = "DELETE FROM Grades_tbl "             
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

        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("AllGrades.fxml"));
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
    
        gradeLevel myClassroom = new gradeLevel();
        Date date = new Date();
        String theDate = date.toString();
        String gradeLeve_name;
        String gradeLeve_description;
        String selectedGrade;
        myClassroom = (gradeLevel) tableExpenses.getSelectionModel().getSelectedItem();
        id = myClassroom.getId();
        gradeLeve_name = myClassroom.getName();
        gradeLeve_description = myClassroom.getDescription();
        name_box.setText(gradeLeve_name);
        description_box.setText(gradeLeve_description);
    }

    private void updateStatement(String update_Query) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            System.out.println("Our query was: " + update_Query);
            stmt.executeUpdate(update_Query);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
}
    
}