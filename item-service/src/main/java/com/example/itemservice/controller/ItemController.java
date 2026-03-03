package com.example.itemservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/items")
public class ItemController {
    
    // Simple in-memory list (no database needed)
    private List<String> items = new ArrayList<>(List.of("Book", "Laptop", "Phone"));
    
    @GetMapping
    public ResponseEntity<List<String>> getItems() {
        return ResponseEntity.ok(items);
    }
    
    @PostMapping
    public ResponseEntity<Map<String, String>> addItem(@RequestBody Map<String, String> request) {
        String itemName = request.get("name");
        if (itemName == null || itemName.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Item name is required"));
        }
        items.add(itemName);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Item added: " + itemName));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getItem(@PathVariable int id) {
        if (id < 0 || id >= items.size()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Item not found"));
        }
        return ResponseEntity.ok(Map.of("id", id, "name", items.get(id)));
    }
}
