/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schoolline.entities;

/**
 *
 * @author Eche Michael
 */
public class score {

    private String score_id;
    private String student_name;
    private String student_id;
    private String gender;
    private String classroom;
    private String gradelevel;
    private String subject;
    private String col1;
    private String col2;
    private String col3;
    private String col4;
    private String col5;
    private String col6;
    private String col7;
    private String colavg;
    private String colsum;
    private String formated_date;
    private String scores_date;

    public score() {
    }

    public score(String score_id, String student_name, String student_id, String gender, String classroom, String gradelevel, String subject, String col1, String col2, String col3, String col4, String col5, String col6, String col7, String colavg, String colsum, String formated_date, String scores_date) {
        this.score_id = score_id;
        this.student_name = student_name;
        this.student_id = student_id;
        this.gender = gender;
        this.classroom = classroom;
        this.gradelevel = gradelevel;
        this.subject = subject;
        this.col1 = col1;
        this.col2 = col2;
        this.col3 = col3;
        this.col4 = col4;
        this.col5 = col5;
        this.col6 = col6;
        this.col7 = col7;
        this.colavg = colavg;
        this.colsum = colsum;
        this.formated_date = formated_date;
        this.scores_date = scores_date;
    }

    public String getScore_id() {
        return score_id;
    }

    public void setScore_id(String score_id) {
        this.score_id = score_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getGradelevel() {
        return gradelevel;
    }

    public void setGradelevel(String gradelevel) {
        this.gradelevel = gradelevel;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCol1() {
        return col1;
    }

    public void setCol1(String col1) {
        this.col1 = col1;
    }

    public String getCol2() {
        return col2;
    }

    public void setCol2(String col2) {
        this.col2 = col2;
    }

    public String getCol3() {
        return col3;
    }

    public void setCol3(String col3) {
        this.col3 = col3;
    }

    public String getCol4() {
        return col4;
    }

    public void setCol4(String col4) {
        this.col4 = col4;
    }

    public String getCol5() {
        return col5;
    }

    public void setCol5(String col5) {
        this.col5 = col5;
    }

    public String getCol6() {
        return col6;
    }

    public void setCol6(String col6) {
        this.col6 = col6;
    }

    public String getCol7() {
        return col7;
    }

    public void setCol7(String col7) {
        this.col7 = col7;
    }

    public String getColavg() {
        return colavg;
    }

    public void setColavg(String colavg) {
        this.colavg = colavg;
    }

    public String getColsum() {
        return colsum;
    }

    public void setColsum(String colsum) {
        this.colsum = colsum;
    }

    public String getFormated_date() {
        return formated_date;
    }

    public void setFormated_date(String formated_date) {
        this.formated_date = formated_date;
    }

    public String getScores_date() {
        return scores_date;
    }

    public void setScores_date(String scores_date) {
        this.scores_date = scores_date;
    }
    
    

}