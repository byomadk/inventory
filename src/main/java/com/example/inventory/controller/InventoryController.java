package com.example.inventory.controller;

import com.example.inventory.model.request.SearchRequest;
import com.example.inventory.model.response.InventoryDetail;
import com.example.inventory.model.response.ItemDetail;
import com.example.inventory.model.response.PagingResponse;
import com.example.inventory.service.InventoryService;
import com.example.inventory.service.ItemService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/delete/{id}")
    public void deleteInventory(@PathVariable int id){
        inventoryService.deleteById(id);
    }


    @GetMapping("/{id}")
    public InventoryDetail getInventory(@PathVariable long id) {
        return inventoryService.getById(id);
    }

    @GetMapping("/list")
    public PagingResponse<InventoryDetail> getList(@RequestBody SearchRequest request){
        return inventoryService.getListPagination(request);
    }

}
