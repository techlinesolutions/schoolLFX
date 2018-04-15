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
public class gradeLevel {

    private String id;
    private String name;
    private String description;
       private String grade_date;

    public gradeLevel() {
    }

    public gradeLevel(String id, String name, String description, String grade_date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.grade_date = grade_date;
    }

    public String getGrade_date() {
        return grade_date;
    }

    public void setGrade_date(String grade_date) {
        this.grade_date = grade_date;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
