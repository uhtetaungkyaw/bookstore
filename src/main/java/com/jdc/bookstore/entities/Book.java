package com.jdc.bookstore.entities;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Book { //one
    public Book(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 2, message = "Name must be at least 2 characters long")
    private String name;

    private String isbn;

    @Size(min = 5, message = "Description must be at least 5 characters long")
    private String description;

    private String category;

    private Double ourPrice;

    private Double listPrice;

    public Book(int id, String name, String isbn, String description, String category, Double ourPrice, Double listPrice) {
        this.id = id;
        this.name = name;
        this.isbn = isbn;
        this.description = description;
        this.category = category;
        this.ourPrice = ourPrice;
        this.listPrice = listPrice;
    }

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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getOurPrice() {
        return ourPrice;
    }

    public void setOurPrice(Double ourPrice) {
        this.ourPrice = ourPrice;
    }

    public Double getListPrice() {
        return listPrice;
    }

    public void setListPrice(Double listPrice) {
        this.listPrice = listPrice;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isbn='" + isbn + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", ourPrice=" + ourPrice +
                ", listPrice=" + listPrice +
                '}';
    }
}
