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
public class Students {

    private String student_id;
    private String first_name;
    private String last_name;
    private String address;
    private String age;
    private String dob;
    private String phone;
    private String email;
    private String type;
    private String student_date;
    private String fathers_first_name;
    private String fathers_last_name;
    private String mothers_first_name;
    private String mothers_last_name;
    private String gender;
    private String classroom;
    private String grade;
    private String prev_school;
    private String hostel;
    private String remarks;
    private String name;
        private String mother;
    private String father;

    public Students(String student_id, String first_name, String last_name, String address, String age, String dob, String phone, String email, String type, String student_date, String fathers_first_name, String fathers_last_name, String mothers_first_name, String mothers_last_name, String gender, String classroom, String grade, String prev_school, String hostel, String remarks, String name, String mother, String father) {
        this.student_id = student_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.age = age;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.type = type;
        this.student_date = student_date;
        this.fathers_first_name = fathers_first_name;
        this.fathers_last_name = fathers_last_name;
        this.mothers_first_name = mothers_first_name;
        this.mothers_last_name = mothers_last_name;
        this.gender = gender;
        this.classroom = classroom;
        this.grade = grade;
        this.prev_school = prev_school;
        this.hostel = hostel;
        this.remarks = remarks;
        this.name = name;
        this.mother = mother;
        this.father = father;
    }

    

    public Students() {
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFathers_first_name() {
        return fathers_first_name;
    }

    public void setFathers_first_name(String fathers_first_name) {
        this.fathers_first_name = fathers_first_name;
    }

    public String getFathers_last_name() {
        return fathers_last_name;
    }

    public void setFathers_last_name(String fathers_last_name) {
        this.fathers_last_name = fathers_last_name;
    }

    public String getMothers_first_name() {
        return mothers_first_name;
    }

    public void setMothers_first_name(String mothers_first_name) {
        this.mothers_first_name = mothers_first_name;
    }

    public String getMothers_last_name() {
        return mothers_last_name;
    }

    public void setMothers_last_name(String mothers_last_name) {
        this.mothers_last_name = mothers_last_name;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPrev_school() {
        return prev_school;
    }

    public void setPrev_school(String prev_school) {
        this.prev_school = prev_school;
    }

    public String getHostel() {
        return hostel;
    }

    public void setHostel(String hostel) {
        this.hostel = hostel;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStudent_date() {
        return student_date;
    }

    public void setStudent_date(String student_date) {
        this.student_date = student_date;
    }

}
