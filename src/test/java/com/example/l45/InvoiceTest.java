package com.example.l45;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvoiceTest {
    @Test
    public void testConstructor() {
        // Arrange
        Long id = 1L;
        InvoiceType type = InvoiceType.INCOMING;
        String product = "product1";
        int quantity = 10;
        LocalDate date = LocalDate.now();

        // Act
        Invoice invoice = new Invoice(id, type, product, quantity, date);

        // Assert
        assertEquals(id, invoice.getId());
        assertEquals(type, invoice.getType());
        assertEquals(product, invoice.getProduct());
        assertEquals(quantity, invoice.getQuantity());
        assertEquals(date, invoice.getDate());
    }
}