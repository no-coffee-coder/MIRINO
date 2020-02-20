package com.larebshaikh.mirino;

public class UserDetails {
    private String Age;
    private String Email;
    private String Name;
    private String isAdmin;
    private String Phone;
    private String uid;

    public UserDetails(){

    }

    public UserDetails(String age, String email, String name, String phone, String uid) {
        this.Age = age;
        this.Email = email;
        this.Name = name;
        this.Phone = phone;
        this.uid = uid;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
