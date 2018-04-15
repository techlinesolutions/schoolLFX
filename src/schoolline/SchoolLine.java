/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolline;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Eche Michael
 */
public class SchoolLine extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        checkStudent_tbl();
        checkAppointment_tbl();
        checkTeachers_tbl();
        checkExpAmount_tbl();
        checkClassroom_tbl();
        checkSubjects_tbl();
        checkGrades_tbl();
        checkScores_tbl();
        createDownloadsDir();
        //checkusersTables();
        Parent root = FXMLLoader.load(getClass().getResource("SchoolLine.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void checkStudent_tbl() {

        String sqlCreateExpItems = "CREATE TABLE IF NOT EXISTS Student_tbl ("
                + "student_id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
                + "first_name	TEXT,"
                + "last_name	TEXT,"
                + "gender	TEXT,"
                + "address	TEXT,"
                + "dob	TEXT,"
                + "age	TEXT,"
                + "phone	TEXT,"
                + "email	TEXT,"
                + "image	BLOB,"
                + "type	TEXT,"
                + "mother_first_name	TEXT,"
                + "mother_last_name	TEXT,"
                + "father_first_name	TEXT,"
                + "father_last_name	TEXT,"
                + "remarks	TEXT,"
                + "prev_school	TEXT,"
                + "hostel	TEXT,"
                + "classroom	TEXT,"
                + "grade	TEXT,"
                + "student_date	TEXT"
                + ")";

        //String CleanExpTbl = "DELETE FROM Patient_tbl WHERE itemName = \"\"";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            stmt = conn.createStatement();
            stmt.execute(sqlCreateExpItems);
            //stmt.execute(CleanExpTbl);
//		conn.commit();
//		conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    private void checkAppointment_tbl() {

        String sqlCreateExpItems = "CREATE TABLE IF NOT EXISTS Appointment_tbl ("
                + "appointment_id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
                + "student_id	TEXT,"
                + "full_name	TEXT,"
                + "appointment_date	TEXT,"
                + "appointment_name	TEXT,"
                + "amount	TEXT,"
                + "notes	TEXT,"
                + "txnDay	TEXT,"
                + "txnMonth	TEXT,"
                + "txnYear	TEXT,"
                + "invoiceNo	TEXT,"
                + "classroom	TEXT,"
                + "gradelevel	TEXT,"
                + "date_time	TEXT"
                + ")";

        // String CleanExpTbl = "DELETE FROM expItems WHERE itemName = \"\"";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            stmt = conn.createStatement();
            stmt.execute(sqlCreateExpItems);
            //stmt.execute(CleanExpTbl);
//		conn.commit();
//		conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    private void checkTeachers_tbl() {
        String sqlCreateExpItems = "CREATE TABLE IF NOT EXISTS Teachers_tbl ("
                + "teacher_id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
                + "first_name	TEXT,"
                + "last_name	TEXT,"
                + "gender	TEXT,"
                + "address	TEXT,"
                + "phone	TEXT,"
                + "email	TEXT,"
                + "type	TEXT,"
                + "subject1	TEXT,"
                + "subject2	TEXT,"
                + "subject3	TEXT,"
                + "subject4	TEXT,"
                + "teacher_date	TEXT"
                + ")";

        //String CleanExpTbl = "DELETE FROM expItems WHERE itemName = \"\"";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            stmt = conn.createStatement();
            stmt.execute(sqlCreateExpItems);
            //stmt.execute(CleanExpTbl);
            //		conn.commit();
            //		conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    private void checkusersTables() {
        String sqlDropUsers = "drop table if exists users";
        String sqlCreateUsers = "CREATE TABLE IF NOT EXISTS users ("
                + "username	TEXT,"
                + "password	TEXT,"
                + "PRIMARY KEY(username)"
                + ")";
        String sqlInsertUsers = "INSERT INTO users (username,password) VALUES ('admin','admin')";
        System.out.println(sqlInsertUsers);
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            stmt = conn.createStatement();
            stmt.execute(sqlDropUsers);
            stmt.execute(sqlCreateUsers);
            stmt.execute(sqlInsertUsers);
            stmt.close();
            //conn.commit();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    private void checkExpAmount_tbl() {
        String sqlCreateExpItems = "CREATE TABLE IF NOT EXISTS ExpAmount_tbl ("
                + "amount_id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
                + "amount_name	TEXT,"
                + "amount_details	TEXT,"
                + "amount_date	TEXT,"
                + "txnDay	TEXT,"
                + "txnMonth	TEXT,"
                + "txnYear	TEXT,"
                + "formated_date	TEXT,"
                + "amount	TEXT"
                + ")";

        // String CleanExpTbl = "DELETE FROM expItems WHERE itemName = \"\"";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            stmt = conn.createStatement();
            stmt.execute(sqlCreateExpItems);
            //stmt.execute(CleanExpTbl);
//		conn.commit();
//		conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    private void checkSubjects_tbl() {
        String sqlCreateSubjects = "CREATE TABLE IF NOT EXISTS Subjects_tbl ("
                + "id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
                + "name	TEXT,"
                + "description	TEXT,"
                + "formated_date	TEXT,"
                + "subject_date	TEXT"
                + ")";

        //String CleanExpTbl = "DELETE FROM expItems WHERE itemName = \"\"";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            stmt = conn.createStatement();
            stmt.execute(sqlCreateSubjects);
            //stmt.execute(CleanExpTbl);
            //		conn.commit();
            //		conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    private void checkGrades_tbl() {
        String sqlCreateGrades = "CREATE TABLE IF NOT EXISTS Grades_tbl ("
                + "id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
                + "name	TEXT,"
                + "description	TEXT,"
                + "formated_date	TEXT,"
                + "grades_date	TEXT"
                + ")";

        //String CleanExpTbl = "DELETE FROM expItems WHERE itemName = \"\"";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            stmt = conn.createStatement();
            stmt.execute(sqlCreateGrades);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    private void checkScores_tbl() {
        String sqlCreateScores = "CREATE TABLE IF NOT EXISTS Scores_tbl ("
                + "score_id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
                + "student_name	TEXT,"
                + "student_id	TEXT,"
                + "gender	TEXT,"
                + "classroom	TEXT,"
                + "gradelevel	TEXT,"
                + "subject	TEXT,"
                + "col1	TEXT,"
                + "col2	TEXT,"
                + "col3	TEXT,"
                + "col4	TEXT,"
                + "col5	TEXT,"
                + "col6	TEXT,"
                + "col7	TEXT,"
                + "colavg	TEXT,"
                + "colsum	TEXT,"
                + "formated_date	TEXT,"
                + "scores_date	TEXT"
                + ")";

        //String CleanExpTbl = "DELETE FROM expItems WHERE itemName = \"\"";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            stmt = conn.createStatement();
            stmt.execute(sqlCreateScores);
            //stmt.execute(CleanExpTbl);
            //		conn.commit();
            //		conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    private void checkClassroom_tbl() {
        String sqlCreateGrades = "CREATE TABLE IF NOT EXISTS Classrooms_tbl ("
                + "id	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
                + "name	TEXT,"
                + "description	TEXT,"
                + "gradelevel	TEXT,"
                + "teacher1	TEXT,"
                + "teacher2	TEXT,"
                + "formated_date	TEXT,"
                + "classroom_date	TEXT"
                + ")";

        //String CleanExpTbl = "DELETE FROM expItems WHERE itemName = \"\"";
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:dbschoolline.db");
            stmt = conn.createStatement();
            stmt.execute(sqlCreateGrades);

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void createDownloadsDir() {
        try {
            String directoryName = "C:\\Downloads\\";
            directoryName = directoryName.replace("\\", "/");
            File directory = new File(directoryName);
            if (!directory.exists()) {
                directory.mkdir();
                System.out.println("Directory does not exist");
                System.out.println("Directory has been created");
            } else {
                System.out.println("Directory existe");
                System.out.println("Doing nothing");
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

}
