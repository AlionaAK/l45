package com.example.l45;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Invoice> getInvoicesByDate(LocalDate date) {
        return invoiceRepository.findByDate(date);
    }

    public void addInvoice(Invoice invoice) {
        invoiceRepository.save(invoice);


    }

    public void updateInvoice(Invoice invoice, int newQuantity) {
        if (newQuantity < invoice.getQuantity()) {
            invoice.setType(InvoiceType.OUTGOING);
        } else {
            invoice.setType(InvoiceType.INCOMING);
        }
        invoice.setQuantity(newQuantity);
        invoiceRepository.save(invoice);
    }

    public void clearInventory(LocalDate date) {
        List<Invoice> invoices = invoiceRepository.findByDate(date);
        for (Invoice invoice : invoices) {
            invoice.setType(InvoiceType.CLEAR);
            invoice.setQuantity(0);
        }
        invoiceRepository.saveAll(invoices);
    }

    public void clearInventory() {
        invoiceRepository.deleteAll();
    }

    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }
}
