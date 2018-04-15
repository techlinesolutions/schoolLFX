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
public class classroom {

    private String id;
    private String name;
    private String description;
    private String gradelevel;
    private String teacher1;
    private String teacher2;
    private String classroom_date;

    public classroom() {
    }

    public classroom(String id, String name, String description, String gradelevel, String teacher1, String teacher2, String classroom_date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.gradelevel = gradelevel;
        this.teacher1 = teacher1;
        this.teacher2 = teacher2;
        this.classroom_date = classroom_date;
    }

    public String getClassroom_date() {
        return classroom_date;
    }

    public void setClassroom_date(String classroom_date) {
        this.classroom_date = classroom_date;
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

    public String getGradelevel() {
        return gradelevel;
    }

    public void setGradelevel(String gradelevel) {
        this.gradelevel = gradelevel;
    }

    public String getTeacher1() {
        return teacher1;
    }

    public void setTeacher1(String teacher1) {
        this.teacher1 = teacher1;
    }

    public String getTeacher2() {
        return teacher2;
    }

    public void setTeacher2(String teacher2) {
        this.teacher2 = teacher2;
    }

}
