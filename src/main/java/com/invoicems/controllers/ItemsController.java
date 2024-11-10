package com.invoicems.controllers;

import com.invoicems.models.Items;
import com.invoicems.services.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional; 

@RestController
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    // Add or update an item associated with a specific customer
    @PostMapping("/customer/{customerId}")
    public ResponseEntity<String> saveOrUpdateItem(@PathVariable("customerId") Long customerId, @RequestBody Items item) {
        try {
            Items savedItem = itemsService.saveOrUpdateItem(customerId, item);
            return new ResponseEntity<>("Item created successfully with ID: " , HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Customer with ID " + customerId + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    // Get all items
    @GetMapping("/all")
    public ResponseEntity<List<Items>> getAllItems() {
        List<Items> itemsList = itemsService.getAllItems();
        if (itemsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // No items found
        }
        return new ResponseEntity<>(itemsList, HttpStatus.OK);
    }

    // Get item by itemName
    @GetMapping("/{itemName}")
    public ResponseEntity<String> getItemByName(@PathVariable String itemName) {
        Optional<Items> item = itemsService.getItemByName(itemName);
        return item.map(value -> new ResponseEntity<>(value.toString(), HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>("Item with name " + itemName + " not found.", HttpStatus.NOT_FOUND));
    }

    // Get item by HSN
    @GetMapping("/hsn/{hsn}")
    public ResponseEntity<String> getItemByHsn(@PathVariable String hsn) {
        Items item = itemsService.getItemByHsn(hsn);
        if (item != null) {
            return new ResponseEntity<>(item.toString(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Item with HSN " + hsn + " not found.", HttpStatus.NOT_FOUND);  // Item not found
        }
    }

    // Soft-delete an item by name
    @DeleteMapping("/softDelete/{itemName}")
    public ResponseEntity<String> softDeleteItem(@PathVariable String itemName) {
        Optional<Items> item = itemsService.getItemByName(itemName);
        if (item.isPresent()) {
            itemsService.softDeleteItem(itemName);
            return new ResponseEntity<>("Item with name " + itemName + " soft-deleted successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Item with name " + itemName + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    // Get all soft-deleted items (for admin access)
    @GetMapping("/getSoftDeleted")
    public ResponseEntity<List<Items>> getAllSoftDeletedItems() {
        List<Items> deletedItems = itemsService.getAllSoftDeletedItems();
        if (deletedItems.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // No soft-deleted items
        }
        return new ResponseEntity<>(deletedItems, HttpStatus.OK);
    }

    // Get all items except soft-deleted items
    @GetMapping("/allItemExceptSoftDeleted")
    public ResponseEntity<List<Items>> getAllNonDeletedItems() {
        List<Items> itemsList = itemsService.getAllNonDeletedItems();
        if (itemsList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // No non-deleted items
        }
        return new ResponseEntity<>(itemsList, HttpStatus.OK);
    }
}
