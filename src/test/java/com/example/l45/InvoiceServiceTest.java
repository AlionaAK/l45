package com.example.l45;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceService invoiceService;

    @Test
    public void testGetInvoicesByDate() {
        LocalDate date = LocalDate.of(2022, 1, 1);
        Invoice invoice1 = new Invoice(1L, InvoiceType.INCOMING, "Product 1", 10, date);
        Invoice invoice2 = new Invoice(2L, InvoiceType.OUTGOING, "Product 2", 5, date);
        List<Invoice> expectedInvoices = Arrays.asList(invoice1, invoice2);
        when(invoiceRepository.findByDate(date)).thenReturn(expectedInvoices);

        List<Invoice> actualInvoices = invoiceService.getInvoicesByDate(date);

        assertEquals(expectedInvoices, actualInvoices);
        verify(invoiceRepository).findByDate(date);
    }

    @Test
    public void testAddInvoice() {
        Invoice invoice = new Invoice(1L, InvoiceType.INCOMING, "Product 1", 10, LocalDate.now());
        invoiceService.addInvoice(invoice);
        verify(invoiceRepository).save(invoice);
    }

    @Test
    public void testUpdateInvoice() {
        Invoice invoice = new Invoice(1L, InvoiceType.INCOMING, "Product 1", 10, LocalDate.now());
        int newQuantity = 20;
        invoiceService.updateInvoice(invoice, newQuantity);
        assertEquals(InvoiceType.INCOMING, invoice.getType());
        assertEquals(newQuantity, invoice.getQuantity());
        verify(invoiceRepository).save(invoice);
    }

}


