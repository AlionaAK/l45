package com.example.l45;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class InventoryControllerTest {
    private InventoryController inventoryController;

    @Mock
    private InvoiceService invoiceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        inventoryController = new InventoryController(invoiceService);
    }

    @Test
    public void testGetInventory() {
        // Arrange
        LocalDate date = LocalDate.now();
        Invoice incomingInvoice = new Invoice(1L, InvoiceType.INCOMING, "product1", 10, date);
        Invoice clearInvoice = new Invoice(2L, InvoiceType.CLEAR, "product2", 5, date);
        Invoice outgoingInvoice = new Invoice(3L, InvoiceType.OUTGOING, "product3", 2, date);
        List<Invoice> invoices = Arrays.asList(incomingInvoice, clearInvoice, outgoingInvoice);
        when(invoiceService.getInvoicesByDate(date)).thenReturn(invoices);

        // Act
        int inventory = inventoryController.getInventory(date.toString());

        // Assert
        assertEquals(13, inventory);
    }

    @Test
    public void testAddInvoice() {
        // Arrange
        Invoice invoice = new Invoice(1L, InvoiceType.INCOMING, "product1", 10, LocalDate.now());

        // Act
        inventoryController.addInvoice(invoice);

        // Assert
        verify(invoiceService, times(1)).addInvoice(invoice);
    }

    @Test
    public void testUpdateInvoice() {
        // Arrange
        Long id = 1L;
        int newQuantity = 20;
        Invoice invoice = new Invoice(id, InvoiceType.INCOMING, "product1", 10, LocalDate.now());
        when(invoiceService.getInvoiceById(id)).thenReturn(invoice);

        // Act
        inventoryController.updateInvoice(id, newQuantity);

        // Assert
        verify(invoiceService, times(1)).updateInvoice(invoice, newQuantity);
    }

    @Test
    public void testClearInventory() {
        // Arrange
        LocalDate date = LocalDate.now();

        // Act
        inventoryController.clearInventory(date.toString());

        // Assert
        verify(invoiceService, times(1)).clearInventory(date);
    }

    @Test
    public void testClearInventoryWithoutDate() {
        // Act
        inventoryController.clearInventory();

        // Assert
        verify(invoiceService, times(1)).clearInventory();
    }
}