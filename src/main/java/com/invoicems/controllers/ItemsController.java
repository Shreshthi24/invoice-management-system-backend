package com.invoicems.controllers;

import com.invoicems.models.Items;
import com.invoicems.services.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.invoicems.models.*;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:55952")
@RequestMapping("/items")
public class ItemsController {

    @Autowired
    private ItemsService itemsService;
    @PostMapping("/customer/{customerId}")
    public ResponseEntity<Items> saveOrUpdateItem(@PathVariable("customerId") Long customerId, @RequestBody Items item) {
        try {
            Items savedItem = itemsService.saveOrUpdateItem(customerId, item);
            return new ResponseEntity<>(savedItem, HttpStatus.CREATED); // Return created item with status 201
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // If customer not found, return 404
        }
    }
//--------------------------------------------------------------------
    // Get all items
  //  @GetMapping("/all")
   // public List<Items> getAllItems() {
     //   return itemsService.getAllItems();
    //}
    
    
    @GetMapping("/all")
    public ResponseEntity<List<Items>> getAllItems() {
        List<Items> itemsList = itemsService.getAllItems();
        return new ResponseEntity<>(itemsList, HttpStatus.OK);
    }
//--------------------------------------------------------------------

    // Get item by itemName
    @GetMapping("/{itemName}")
    public ResponseEntity<Items> getItemByName(@PathVariable String itemName) {
        Optional<Items> item = itemsService.getItemByName(itemName);
        if (item.isPresent()) {
            return ResponseEntity.ok(item.get());
        } else {
            return ResponseEntity.status(404).body(null); // Item not found
        }
    }
//--------------------------------------------------------------------
    // Get item by HSN
    @GetMapping("/hsn/{hsn}")
    public ResponseEntity<Items> getItemByHsn(@PathVariable String hsn) {
        Items item = itemsService.getItemByHsn(hsn);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.status(404).body(null); // Item not found
        }
    }
//--------------------------------------------------------------------
    // Delete item by itemName
    @DeleteMapping("/{itemName}")
    public ResponseEntity<String> deleteItem(@PathVariable String itemName) {
        Optional<Items> item = itemsService.getItemByName(itemName);
        if (item.isPresent()) {
            itemsService.deleteItem(itemName);
            return ResponseEntity.ok("Item deleted successfully!");
        } else {
            return ResponseEntity.status(404).body("Item not found");
        }
    }
}
