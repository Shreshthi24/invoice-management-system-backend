package com.invoicems.services;

import com.invoicems.models.Customer;
import com.invoicems.models.Invoice;
import com.invoicems.models.Items;
import com.invoicems.repositories.CustomerRepository;
import com.invoicems.repositories.InvoiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Autowired
    private CustomerRepository customerRepository;

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

   // @Transactional
    //public Invoice createInvoice(Invoice invoice) {
      //  calculateSubtotalAndTotal(invoice);
        //return invoiceRepository.save(invoice);
    //}
    
    @Transactional
    public Invoice createInvoice(Invoice invoice, Long customerId) {
        // Fetch customer if it exists
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            invoice.setCustomer(customerOpt.get());  // Set the customer in the invoice
        } else {
            throw new RuntimeException("Customer not found.");
        }

        // Set invoice reference and customer reference in each item
        for (Items item : invoice.getItems()) {
            item.setInvoice(invoice);  // Link item to its parent invoice
            item.setCustomer(invoice.getCustomer());  // Set the customer reference in each item
        }

        // Save the invoice (this will also save the items due to cascading if configured)
        Invoice savedInvoice = invoiceRepository.save(invoice);  // This will automatically cascade to save the items

        return savedInvoice;  // Return the saved invoice
    }


    @Transactional
    public Invoice updateInvoice(Long id, Invoice updatedInvoice) {
        return invoiceRepository.findById(id)
            .map(invoice -> {
                invoice.setClientName(updatedInvoice.getClientName());
                invoice.setInvoiceDate(updatedInvoice.getInvoiceDate());
                invoice.setDueDate(updatedInvoice.getDueDate());
                invoice.setPoNo(updatedInvoice.getPoNo());
                invoice.setPaymentTerms(updatedInvoice.getPaymentTerms());
                invoice.setItems(updatedInvoice.getItems());
                invoice.setShippingCharges(updatedInvoice.getShippingCharges());
                invoice.setDiscount(updatedInvoice.getDiscount());
                invoice.setTermsConditions(updatedInvoice.getTermsConditions());
                invoice.setPrivateNotes(updatedInvoice.getPrivateNotes());

                calculateSubtotalAndTotal(invoice);

                return invoiceRepository.save(invoice);
            })
            .orElseThrow(() -> new RuntimeException("Invoice not found"));
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }

    private void calculateSubtotalAndTotal(Invoice invoice) {
        float subtotal = 0.0f;
        
        for (Items item : invoice.getItems()) {
            subtotal += item.getSaleUnitPrice() * item.getQuantity();
        }

        float discountAmount = (invoice.getDiscount() != null) ? invoice.getDiscount() : 0.0f;
        float shippingAmount = (invoice.getShippingCharges() != null) ? invoice.getShippingCharges() : 0.0f;

        invoice.setSubtotal(subtotal);
        invoice.setTotal(subtotal + shippingAmount - discountAmount);
    }
}
