package com.example.l45;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private InvoiceType type;

    private String product;

    private int quantity;

    private LocalDate date;
    //////////////////////////////
    public Invoice(long id, InvoiceType type, String product, int quantity, LocalDate date) {
        this.id = id;
        this.type = type;
        this.product = product;
        this.quantity = quantity;
        this.date = date;
    }

    public Invoice() {

    }
//////////////////////////////

    public InvoiceType getType() {
        return type;
    }

    public void setType(InvoiceType type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProduct() {
        return product;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}