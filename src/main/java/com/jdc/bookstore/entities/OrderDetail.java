package com.jdc.bookstore.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class OrderDetail { //many
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Date dateCreate;
    private Double price;
    private int quantity;
    public OrderDetail(){}

    public OrderDetail(String name, Date dateCreate, Double price, int quantity) {
        this.name = name;
        this.dateCreate = dateCreate;
        this.price = price;
        this.quantity = quantity;
    }
    @ManyToOne
    private Book book;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
