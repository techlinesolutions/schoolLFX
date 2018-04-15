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
public class subject {
      private String id;
    private String name;
    private String description;
    private String subject_date;


    public subject() {
    }

    public subject(String id, String name, String description, String subject_date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.subject_date = subject_date;
    }

    public String getSubject_date() {
        return subject_date;
    }

    public void setSubject_date(String subject_date) {
        this.subject_date = subject_date;
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
