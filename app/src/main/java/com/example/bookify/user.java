package com.example.bookify;

public class user {
    String name;
    String contactNo;
    String email;
    String address;

    public user() {
    }

    public user(String first_name, String email, String address , String phone_no) {
        this.name =first_name;
        this.email=email;
        this.address=address;
        this.contactNo =phone_no;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
