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
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Eche Michael
 */
public class AddNewGradeLevelController implements Initializable {
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void onClearButtonClicked(ActionEvent event) {
         item_name_box.clear();
        item_desc_box.clear();

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
    private boolean onAddButtonClicked(ActionEvent event) {
         //insert into db if valid
        Date date = new Date();
        String theDate = date.toString();
        String gradelevel_name = item_name_box.getText();
        String gradelevel_description = item_desc_box.getText();


        if ((gradelevel_name.trim().length() == 0) || (gradelevel_name == "") || (gradelevel_name.trim().isEmpty()) || (gradelevel_name == "--SELECT ONE--")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter A GRADE LEVEL");

            alert.showAndWait();
            System.clearProperty(gradelevel_name);
            return false;
        }
         gradelevel_name = gradelevel_name.toUpperCase();
        gradelevel_description = gradelevel_description.toUpperCase();

        //format for report queries
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        cal.add(Calendar.DATE, 0);
        System.out.println("gradelevel_name is : " + gradelevel_name);
        System.out.println("gradelevel_description is : " + gradelevel_description);
        String query = "INSERT INTO Grades_tbl (name,description,formated_date,grades_date ) VALUES ("
                + "'" + gradelevel_name + "'," + "'" + gradelevel_description + "'," + "'"
                 + dateFormat + "','" + theDate + "');";

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
        return true;
    }
    
}
