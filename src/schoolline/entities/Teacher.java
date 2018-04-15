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
public class Teacher {

    private String teacher_id;
    private String first_name;
    private String last_name;
    private String gender;
    private String address;
    private String age;
    private String dob;
    private String phone;
    private String email;
    private String hmo;
    private String type;
    private String subject1;
    private String subject2;
    private String subject3;
    private String subject4;
    private String name;

    private String teacher_date;

    public Teacher() {
    }

    public Teacher(String teacher_id, String first_name, String last_name, String gender, String address, String age, String dob, String phone, String email, String hmo, String type, String subject1, String subject2, String subject3, String subject4, String name, String teacher_date) {
        this.teacher_id = teacher_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.gender = gender;
        this.address = address;
        this.age = age;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.hmo = hmo;
        this.type = type;
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
        this.subject4 = subject4;
        this.name = name;
        this.teacher_date = teacher_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   

    public String getSubject1() {
        return subject1;
    }

    public void setSubject1(String subject1) {
        this.subject1 = subject1;
    }

    public String getSubject2() {
        return subject2;
    }

    public void setSubject2(String subject2) {
        this.subject2 = subject2;
    }

    public String getSubject3() {
        return subject3;
    }

    public void setSubject3(String subject3) {
        this.subject3 = subject3;
    }

    public String getSubject4() {
        return subject4;
    }

    public void setSubject4(String subject4) {
        this.subject4 = subject4;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTeacher_date() {
        return teacher_date;
    }

    public void setTeacher_date(String teacher_date) {
        this.teacher_date = teacher_date;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String Teacher_id) {
        this.teacher_id = Teacher_id;
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

    public String getHmo() {
        return hmo;
    }

    public void setHmo(String hmo) {
        this.hmo = hmo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDotor_date() {
        return teacher_date;
    }

    public void setDotor_date(String teacher_date) {
        this.teacher_date = teacher_date;
    }

}
