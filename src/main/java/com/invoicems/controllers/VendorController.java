package com.invoicems.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.invoicems.models.*;
import com.invoicems.models.Vendor;
import com.invoicems.services.VendorService;


@RestController
@CrossOrigin(origins = "http://localhost:55952")
@RequestMapping("/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    
    
    @PostMapping("/customer/{customerId}")
    public ResponseEntity<Vendor> saveOrUpdateVendor(@PathVariable("customerId") Long customerId, @RequestBody Vendor vendor) {
        try {
            Vendor savedVendor = vendorService.addVendor(customerId, vendor);
            return new ResponseEntity<>(savedVendor, HttpStatus.CREATED); // Return created vendor with status 201
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // If customer not found, return 404
        }
    }
    
//--------------------------------------------------------------------
    @GetMapping("/all")
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }
//--------------------------------------------------------------------
    @GetMapping("/{companyName}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable String companyName) {
        Optional<Vendor> vendor = vendorService.getVendorById(companyName);
        return vendor.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }
//--------------------------------------------------------------------
    @PutMapping("/{companyName}")
    public ResponseEntity<Vendor> updateVendor(@PathVariable String companyName, @RequestBody Vendor vendorDetails) {
        Vendor updatedVendor = vendorService.updateVendor(companyName, vendorDetails);
        if (updatedVendor != null) {
            return ResponseEntity.ok(updatedVendor);
        } else {
            return ResponseEntity.notFound().build();
        } 
    }
//--------------------------------------------------------------------
    @DeleteMapping("/{companyName}")
    public ResponseEntity<String> deleteVendor(@PathVariable String companyName) {
        vendorService.deleteVendor(companyName);
        return ResponseEntity.ok("Vendor deleted successfully.");
    }
}
