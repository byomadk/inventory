package com.example.inventory.service;

import com.example.inventory.exception.BusinessException;
import com.example.inventory.model.entity.*;
import com.example.inventory.model.request.SearchRequest;
import com.example.inventory.model.response.OrderDetail;
import com.example.inventory.model.response.Pagination;
import com.example.inventory.model.response.PagingResponse;
import com.example.inventory.util.Utils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final ObjectMapper mapper;

    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public OrderDetail getById(String id){
        OrderEntity order = orderRepository.findById(id).orElseThrow(()->new BusinessException("Order Not Found"));
        OrderDetail detail = mapper.convertValue(order, OrderDetail.class);
        detail.setItemName(order.getItem().getName());
        detail.setTotalPrice(order.getPrice().multiply(new BigDecimal(order.getQuantity())).toString());
        return detail;
    }

    public PagingResponse<OrderDetail> getListPagination(SearchRequest input){
        Page<OrderEntity> pageEntity = orderRepository.findInventorySearch(input.getSearchValue(), Utils.constructPageable(input));
        List<OrderDetail> details = mapper.convertValue(pageEntity.getContent(), mapper.getTypeFactory().constructCollectionType(List.class, OrderDetail.class));
        int counter = 0;
        for (OrderDetail detail : details){
            OrderEntity order = pageEntity.getContent().get(counter);
            detail.setItemName(order.getItem().getName());
            detail.setTotalPrice(order.getPrice().multiply(new BigDecimal(order.getQuantity())).toString());
            counter++;
        }
        return new PagingResponse<>(details, new Pagination(input, pageEntity));
    }

    public void deleteOrder(String id){
        orderRepository.findById(id).ifPresentOrElse(
                order->{
                    orderRepository.delete(order);
                    System.out.println("order "+order.getOrderNo()+" is deleted");
                },
                ()->{
                    throw new BusinessException("Order Not Found");
                });
    }

    public List<OrderEntity> getAll(){
        return orderRepository.findAll();
    }

    public void populateOrders(){
        List<OrderEntity> orders = new ArrayList<>();
        for (int i=1; i<11; i++){
            OrderEntity order = new OrderEntity();
            order.setOrderNo("O".concat(i+""));
            order.setItem(getItem(i));
            order.setQuantity(getQuantity(i));
            order.setPrice(getPrice(i));
            orders.add(order);
        }
        orderRepository.saveAll(orders);
    }

    private long getQuantity(int i) {
        if (i<=3)
            return i+1;
        if (i > 3 && i < 7)
            return i-3;
        if (i == 7)
            return 5;
        if (i == 8)
            return 4;
        if (i == 9)
            return 2;
        if (i == 10)
            return 3;
        return 0;
    }

    private ItemEntity getItem(int i){
        ItemEntity item;
        if (i == 3){
            i = 5;
        } else if (i > 6){
            i = i-6;
        }
        item = itemRepository.findById((long) i).orElse(null);
        return item;
    }

    private String getName(int i){
        String name = "";
        switch (i){
            case 1 -> name = "Pen";
            case 2 -> name = "Book";
            case 3 -> name = "Bag";
            case 4 -> name = "Pencil";
            case 5 -> name = "Shoe";
            case 6 -> name = "Box";
            case 7 -> name = "Cap";
        }
        return name;
    }

    private BigDecimal getPrice(int i){
        BigDecimal price = BigDecimal.ZERO;
        switch (i){
            case 1 -> price = new BigDecimal(5);
            case 2 -> price = new BigDecimal(10);
            case 3 -> price = new BigDecimal(45);
            case 4 -> price = new BigDecimal(2);
            case 5 -> price = new BigDecimal(45);
            case 6 -> price = new BigDecimal(5);
            case 7 -> price = new BigDecimal(5);
            case 8 -> price = new BigDecimal(10);
            case 9 -> price = new BigDecimal(30);
            case 10 -> price = new BigDecimal(3);
        }
        return price;
    }
}
