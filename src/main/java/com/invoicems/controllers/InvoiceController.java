package com.invoicems.controllers;

import com.invoicems.models.Invoice;
import com.invoicems.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;
  //--------------------------------------------------------------------
    // Get all invoices
    @GetMapping("/all")
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        if (invoices.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // No invoices found
        }
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }
  //--------------------------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Object> getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id)
                .map(invoice -> ResponseEntity.ok((Object) invoice))  // Invoice found, cast to Object
                .orElse(new ResponseEntity<>((Object) "Invoice not found with ID " + String.valueOf(id), HttpStatus.NOT_FOUND));  // Invoice not found, safely concatenate
    }
  //--------------------------------------------------------------------

    // Create a new invoice
    @PostMapping("/addNew/{customerId}")
    public ResponseEntity<Object> createInvoice(@RequestBody Invoice invoice, @PathVariable("customerId") Long customerId) {
        try {
            Invoice savedInvoice = invoiceService.createInvoice(invoice, customerId);
            return new ResponseEntity<>(savedInvoice, HttpStatus.CREATED); // Invoice created successfully
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Failed to create invoice: " + e.getMessage(), HttpStatus.BAD_REQUEST); // Error during creation
        }
    }
  //--------------------------------------------------------------------
    // Update an existing invoice
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoice) {
        try {
            Invoice updatedInvoice = invoiceService.updateInvoice(id, invoice);
            return ResponseEntity.ok(updatedInvoice);  // Invoice updated successfully
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Invoice not found with ID " + id, HttpStatus.NOT_FOUND);  // Invoice not found
        }
    }
  //--------------------------------------------------------------------
    // Delete an invoice
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteInvoice(@PathVariable Long id) {
        try {
            invoiceService.deleteInvoice(id);
            return ResponseEntity.ok("Invoice with ID " + id + " deleted successfully");  // Invoice deleted successfully
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Invoice not found with ID " + id, HttpStatus.NOT_FOUND);  // Invoice not found
        }
    }
}
