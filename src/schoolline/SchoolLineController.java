/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolline;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Eche Michael
 */
public class SchoolLineController implements Initializable {

    @FXML
    private AnchorPane navigationPane;
    @FXML
    private Button btnThisWeek;
    @FXML
    private Button btnYesterday;
    @FXML
    private Button btnLastWeek;
    @FXML
    private Button btnToday1;
    @FXML
    private Button btnExpenditure;
    @FXML
    private Button btnYesterday1;
    @FXML
    private Button btnThisWeek1;
    @FXML
    private Button btnLastWeek1;
    @FXML
    private Button btnAllPatients;
    @FXML
    private Button btnAllDoctors;
    @FXML
    private Button btnThisMonth;
    @FXML
    private Button btnThisMonth1;
    @FXML
    private Button btnLastMonth1;
    @FXML
    private Button btnLastMonth;
    @FXML
    private Button btnAddNewExpItem;
    @FXML
    private Button btnToday;
    @FXML
    private AnchorPane mainContentPane;
    @FXML
    private Label copyRight;
    @FXML
    private Button btnIAddPayment;
    @FXML
    private Button btnAddNewIStudent;
    @FXML
    private Button btnAddGrade;
    @FXML
    private Button btnAddTeacher;
    @FXML
    private Button btnAddSubject;
    @FXML
    private Button btnAddGrade1;
    @FXML
    private Button btnAddSubject1;
    private String path;
    @FXML
    private Button btnAllSubjects;
    @FXML
    private Button btnAllClassrooms;
    @FXML
    private Button btnAllGrades;
    @FXML
    private Button btnAllScores;
    @FXML
    private MenuBar myMenuBar;
    @FXML
    private MenuItem menuExit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setCopyright();
    }

    @FXML
    private void onClickExit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void onClickedThisWeekReport(ActionEvent event) throws IOException {
        System.out.println("You clicked ThisWeekReport!");
        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("ThisWeekReport.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();
    }

    @FXML
    private void onClickedYesterdayReport(ActionEvent event) throws IOException {
        System.out.println("You clicked YesterdayReport!");
        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("YesterdayReport.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();
    }

    @FXML
    private void onClickedLastWeekReport(ActionEvent event) throws IOException {
        System.out.println("You clicked TodayReport!");
        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("LastWeek.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();
    }

    @FXML
    private void onClickTodayReportExp(ActionEvent event) throws IOException {
        System.out.println("You clicked TodayReport!");
        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("ExpTodayReport.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();
    }

    @FXML
    private void onClickedAddPayment(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddNewAppointment.fxml"));
        mainContentPane.getChildren().setAll(pane);
    }

    @FXML
    private void onClickedExpenditure(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("SpentMoney.fxml"));
        mainContentPane.getChildren().setAll(pane);
    }

    @FXML
    private void onClickedAddStudent(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddNewStudent.fxml"));
        mainContentPane.getChildren().setAll(pane);
    }

    @FXML
    private void onClickedYesterdayReportExp(ActionEvent event) throws IOException {
        System.out.println("You clicked ExpYesterdayReport!");
        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("ExpYesterdayReport.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("ExpYesterdayReport.fxml"));
//        mainContentPane.getChildren().setAll(pane);
    }

    @FXML
    private void onClickedThisWeekReportExp(ActionEvent event) throws IOException {
        System.out.println("You clicked ExpThisWeekReport!");
        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("ExpThisWeekReport.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("ExpThisWeekReport.fxml"));
//        mainContentPane.getChildren().setAll(pane);
    }

    @FXML
    private void onClickedLastWeekReportExp(ActionEvent event) throws IOException {
        System.out.println("You clicked ExpLastWeekReport!");
        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("ExpLastWeek.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("ExpLastWeekReport.fxml"));
//        mainContentPane.getChildren().setAll(pane);
    }

    @FXML
    private void onClickedbtnAllPatients(ActionEvent event) throws IOException {
        System.out.println("You clicked AllPatients!");
        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("AllStudents.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("AllPatients.fxml"));
//        mainContentPane.getChildren().setAll(pane);
    }

    @FXML
    private void onClickedbtnAllDoctors(ActionEvent event) throws IOException {
        System.out.println("You clicked AllPatients!");
        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("AllTeachers.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("AllDoctors.fxml"));
//        mainContentPane.getChildren().setAll(pane);
    }

    @FXML
    private void onClickedThisMonthReport(ActionEvent event) throws IOException {
        System.out.println("You clicked ThisMonthReport!");
        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("ThisMonthReport.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();
    }

    private void onClickedbtnAllHmos(ActionEvent event) throws IOException {
        System.out.println("You clicked AllHmos!");
        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("AllHmos.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("AllHmos.fxml"));
//        mainContentPane.getChildren().setAll(pane);
    }

    @FXML
    private void onClickedThisMonthReportExp(ActionEvent event) throws IOException {
        System.out.println("You clicked ExpThisMonthReport!");
        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("ExpThisMonthReport.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("ExpThisMonthReport.fxml"));
//        mainContentPane.getChildren().setAll(pane);
    }

    @FXML
    private void onClickedLastMonthReportExp(ActionEvent event) throws IOException {
        System.out.println("You clicked ExpLastMonthReport!");
        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("ExpLastMonth.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("ExpLastMonthReport.fxml"));
//        mainContentPane.getChildren().setAll(pane);
    }

    @FXML
    private void onClickedLastMonthReport(ActionEvent event) throws IOException {
        System.out.println("You clicked LastMonthReport!");
        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("LastMonth.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();
    }

    private void onClickedAddNewHMO(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddNewHMO.fxml"));
        mainContentPane.getChildren().setAll(pane);
    }

    @FXML
    private void onClickedAddTeacher(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddNewTeacher.fxml"));
        mainContentPane.getChildren().setAll(pane);
    }

    @FXML
    private void onClickedAddExpItem(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddNewExpItem.fxml"));
        mainContentPane.getChildren().setAll(pane);
    }

    @FXML
    private void onClickTodayReport(ActionEvent event) throws IOException {
        System.out.println("You clicked TodayReport!");
        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("TodayReport.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();

    }

    private void setCopyright() {
//         JLabel copyrightL = new JLabel("<html><body>&copy; 2018 TechLine Communications <br/>All Rights Reserved</body></html>");
        Label copyrightL = new Label("\u00a9 2018 Techline Communications \n All Rights Reserved");
        String strCopy = copyrightL.toString().trim();
        strCopy = strCopy.substring(33);
        copyRight.setText(strCopy);
    }

    @FXML
    private void onClickedAddNewGrade(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddNewGradeLevel.fxml"));
        mainContentPane.getChildren().setAll(pane);
    }

    @FXML
    private void onClickedAddNewSubject(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddNewSubject.fxml"));
        mainContentPane.getChildren().setAll(pane);
    }

    @FXML
    private void onClickedAddNewClassroom(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddNewClassroom.fxml"));
        mainContentPane.getChildren().setAll(pane);
    }

    @FXML
    private void onClickedAddNewScore(ActionEvent event) throws IOException {
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("AddNewScore.fxml"));
//        mainContentPane.getChildren().setAll(pane);
        System.out.println("You clicked add score!");
        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("AddNewScore.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();
    }

    @FXML
    private void onClickedbtnAllClassrooms(ActionEvent event) throws IOException {
        System.out.println("You clicked TodayReport!");
        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("AllClassrooms.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();
    }

    @FXML
    private void onClickedbtnAllGrades(ActionEvent event) throws IOException {
        System.out.println("You clicked TodayReport!");
        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("AllGrades.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();
    }

    @FXML
    private void onClickedbtnAllScores(ActionEvent event) throws IOException {
        System.out.println("You clicked TodayReport!");
        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("AllScores.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();
    }

    @FXML
    private void onClickedbtnAllSubjects(ActionEvent event) throws IOException {
        System.out.println("You clicked TodayReport!");
        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("AllSubjects.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();
    }

}
