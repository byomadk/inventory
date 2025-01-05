package com.example.inventory.controller;

import com.example.inventory.model.request.SearchRequest;
import com.example.inventory.model.response.ItemDetail;
import com.example.inventory.model.response.OrderDetail;
import com.example.inventory.model.response.PagingResponse;
import com.example.inventory.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public OrderDetail getItem(@PathVariable String id){
        return orderService.getById(id);
    }

    @GetMapping("/list")
    public PagingResponse<OrderDetail> getList(@RequestBody SearchRequest request){
        return orderService.getListPagination(request);
    }

    @PostMapping("/delete/{id}")
    public void deleteOrder(@PathVariable String id){
        orderService.deleteOrder(id);
    }
}
