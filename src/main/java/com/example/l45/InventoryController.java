package com.example.l45;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class InventoryController {
    @Autowired
    private InvoiceService invoiceService;

    //////////////////////////////
    @Autowired
    public InventoryController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    ////////////////////////////
    @GetMapping("/inventory/{date}")
    public int getInventory(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<Invoice> invoices = invoiceService.getInvoicesByDate(localDate);
        int inventory = 0;
        for (Invoice invoice : invoices) {
            if (invoice.getType() == InvoiceType.INCOMING || invoice.getType() == InvoiceType.CLEAR) {
                inventory += invoice.getQuantity();
            } else {
                inventory -= invoice.getQuantity();
            }
        }
        return inventory;
    }

    @PostMapping("/invoice")
    public void addInvoice(@RequestBody Invoice invoice) {
        invoiceService.addInvoice(invoice);
    }

    @PutMapping("/invoice/{id}/{newQuantity}")
    public void updateInvoice(@PathVariable Long id, @PathVariable int newQuantity) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        invoiceService.updateInvoice(invoice, newQuantity);
    }

    @PostMapping("/clearInventory/{date}")
    public void clearInventory(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        invoiceService.clearInventory(localDate);
    }

    @DeleteMapping("/clearInventory")
    public void clearInventory() {
        invoiceService.clearInventory();
    }
}

