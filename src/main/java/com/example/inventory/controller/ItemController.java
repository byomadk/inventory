package com.example.inventory.controller;

import com.example.inventory.model.request.SearchRequest;
import com.example.inventory.model.response.ItemDetail;
import com.example.inventory.model.response.PagingResponse;
import com.example.inventory.service.ItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/{id}")
    public ItemDetail getItem(@PathVariable long id){
        return itemService.getById(id);
    }

    @GetMapping("/list")
    public PagingResponse<ItemDetail> getList(@RequestBody SearchRequest request){
        return itemService.getListPagination(request);
    }

    @PostMapping("/delete/{id}")
    public void deleteItem(@PathVariable int id){
        itemService.deleteById(id);
    }
}
