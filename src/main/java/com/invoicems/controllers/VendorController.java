package com.invoicems.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.invoicems.models.Vendor;
import com.invoicems.services.VendorService;

@RestController
@RequestMapping("/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    // Add or update a vendor associated with a specific customer
    @PostMapping("/customer/{customerId}")
    public ResponseEntity<String> saveOrUpdateVendor(@PathVariable("customerId") Long customerId, @RequestBody Vendor vendor) {
        try {
            Vendor savedVendor = vendorService.addVendor(customerId, vendor);
            return new ResponseEntity<>("Vendor created successfully with ID: ", HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Customer with ID " + customerId + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    // Retrieve all vendors
    @GetMapping("/all")
    public ResponseEntity<List<Vendor>> getAllVendors() {
        List<Vendor> vendors = vendorService.getAllVendors();
        if (vendors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // No vendors found
        }
        return new ResponseEntity<>(vendors, HttpStatus.OK);
    }

    // Retrieve a vendor by company name
    @GetMapping("/{companyName}")
    public ResponseEntity<String> getVendorById(@PathVariable String companyName) {
        Optional<Vendor> vendor = vendorService.getVendorById(companyName);
        return vendor.map(value -> new ResponseEntity<>(value.toString(), HttpStatus.OK))
                     .orElseGet(() -> new ResponseEntity<>("Vendor with company name " + companyName + " not found.", HttpStatus.NOT_FOUND));
    }

    // Update a vendor's details by company name
    @PutMapping("/{companyName}")
    public ResponseEntity<String> updateVendor(@PathVariable String companyName, @RequestBody Vendor vendorDetails) {
        Vendor updatedVendor = vendorService.updateVendor(companyName, vendorDetails);
        if (updatedVendor != null) {
            return new ResponseEntity<>("Vendor with company name " + companyName + " updated successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Vendor with company name " + companyName + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    // Delete a vendor by company name
    @DeleteMapping("/{companyName}")
    public ResponseEntity<String> deleteVendor(@PathVariable String companyName) {
        try {
            vendorService.deleteVendor(companyName);
            return new ResponseEntity<>("Vendor with company name " + companyName + " deleted successfully.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Vendor with company name " + companyName + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
