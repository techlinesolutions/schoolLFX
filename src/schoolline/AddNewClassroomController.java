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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Eche Michael
 */
public class AddNewClassroomController implements Initializable {
    @FXML
    private Button addButton;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnClose;
    @FXML
    private TextArea item_desc_box;
    @FXML
    private TextField item_name_box;
    @FXML
    private ChoiceBox<?> choicebox_grade;
    private ObservableList allGrades;

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
        Date date = new Date();
        String theDate = date.toString();
        String classroom_name = item_name_box.getText();
        String classroom_description = item_desc_box.getText();
        String selectedGrade=  choicebox_grade.getValue().toString();

        if ((classroom_name.trim().length() == 0) || (classroom_name == "") || (classroom_name.trim().isEmpty()) || (classroom_name == "--SELECT ONE--")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter A Class Room");

            alert.showAndWait();
            System.clearProperty(classroom_name);
            return false;
        }
         classroom_name = classroom_name.toUpperCase();
        classroom_description = classroom_description.toUpperCase();

        //format for report queries
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        cal.add(Calendar.DATE, 0);
        System.out.println("classroom_name is : " + classroom_name);
        System.out.println("amount_description is : " + classroom_description);
        String query = "INSERT INTO Classrooms_tbl (name,description,gradelevel,formated_date,classroom_date ) VALUES ("
                + "'" + classroom_name + "'," + "'" + classroom_description + "'," + "'"
                + selectedGrade + "','" + dateFormat + "','" + theDate + "');";

        System.out.println("Inserting\n" + query);
        insertStatement(query);
        
         Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
        alert3.setTitle("Information Dialog");
        alert3.setHeaderText(null);
        alert3.setContentText("Record Added Succesfully.");

        alert3.showAndWait();
        
        System.out.println("Succesfully Inserted");
        item_name_box.clear();
        item_desc_box.clear();
        choicebox_grade.getSelectionModel().clearSelection();
        return true;
    }

    @FXML
    private void onClearButtonClicked(ActionEvent event) {
         item_name_box.clear();
        item_desc_box.clear();
         choicebox_grade.getSelectionModel().clearSelection();
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

    private void refreshItemListInCombo() {
         //Set items equal to an empty ArrayList
        allGrades = FXCollections.observableArrayList();

        //Select out of the DB, fill accordingly
        getGrades(allGrades);

        //Set the listview to what we just populated with DB contents
        choicebox_grade.setItems(allGrades);
    }

    private void selectFirstOne() {
         choicebox_grade.getSelectionModel().selectFirst();
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
    
}
