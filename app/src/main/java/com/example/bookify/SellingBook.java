package com.example.bookify;


import android.graphics.Bitmap;

public class SellingBook {

    //unique IDs
     String name;
     String price;
     String quantity;
     String description;
     String delivery;
     String id;

    String author;
     String contact;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SellingBook() {
    }

    public SellingBook(String name, String price,String author, String contact, String quantity, String description, String delivery, String id) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.delivery = delivery;
        this.id = id;
        this.author=  author;
        this.contact=contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public String getDelivery() {
        return delivery;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
