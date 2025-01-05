package com.example.inventory.controller;

import com.example.inventory.model.entity.InventoryEntity;
import com.example.inventory.model.entity.ItemEntity;
import com.example.inventory.model.entity.OrderEntity;
import com.example.inventory.service.InventoryService;
import com.example.inventory.service.ItemService;
import com.example.inventory.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/sample")
public class SampleController {

    private final ItemService itemService;
    private final InventoryService inventoryService;
    private final OrderService orderService;

    public SampleController(ItemService itemService, InventoryService inventoryService, OrderService orderService) {
        this.itemService = itemService;
        this.inventoryService = inventoryService;
        this.orderService = orderService;
        populateDb();
    }

    @GetMapping("/get")
    public void getItemSample() throws JsonProcessingException {
        List<ItemEntity> items = itemService.getAll();
        List<InventoryEntity> inventories = inventoryService.getAll();
        List<OrderEntity> orders = orderService.getAll();
        System.out.println("SIZE == "+items.size());
        System.out.println("### GET ITEM "+ Arrays.toString(items.stream().map(ItemEntity::getName).toList().toArray()));
        System.out.println("SIZE == "+inventories.size());
        System.out.println("### GET INVENTORY "+ Arrays.toString(inventories.stream().map(InventoryEntity::getType).toList().toArray()));
        System.out.println("### "+new ObjectMapper().writeValueAsString(inventories));
        System.out.println("SIZE == "+orders.size());
        System.out.println("### GET ORDERS "+ Arrays.toString(orders.stream().map(OrderEntity::getOrderNo).toList().toArray()));
        System.out.println("### "+new ObjectMapper().writeValueAsString(orders));
    }

    @GetMapping("/start")
    public void populateDb(){
        itemService.populateItems();
        inventoryService.populateInventory();
        orderService.populateOrders();
    }

}
