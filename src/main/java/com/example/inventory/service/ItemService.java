package com.example.inventory.service;

import com.example.inventory.exception.BusinessException;
import com.example.inventory.model.entity.ItemEntity;
import com.example.inventory.model.entity.ItemRepository;
import com.example.inventory.model.entity.OrderEntity;
import com.example.inventory.model.request.SearchRequest;
import com.example.inventory.model.response.ItemDetail;
import com.example.inventory.model.response.OrderDetail;
import com.example.inventory.model.response.Pagination;
import com.example.inventory.model.response.PagingResponse;
import com.example.inventory.util.Utils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;
    private final ObjectMapper mapper;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
        this.mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public List<ItemEntity> getAll(){
        return itemRepository.findAll();
    }

    public long addOne(){
        ItemEntity item = new ItemEntity();
        item.setId(1L);
        item.setName("test");
        item.setPrice(new BigDecimal(1000));
        itemRepository.save(item);
        return item.getId();
    }

    public void populateItems(){
        for (int i=1; i<8; i++){
            ItemEntity item = new ItemEntity();
            item.setId((long) i);
            item.setName(getName(i));
            item.setPrice(getPrice(i));
            itemRepository.save(item);
        }
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
            case 3 -> price = new BigDecimal(30);
            case 4 -> price = new BigDecimal(3);
            case 5 -> price = new BigDecimal(45);
            case 6 -> price = new BigDecimal(5);
            case 7 -> price = new BigDecimal(25);
        }
        return price;
    }

    public void deleteById(int id){
        itemRepository.findById((long) id).ifPresentOrElse(
                item->{
                    itemRepository.delete(item);
                    System.out.println("item "+item.getId()+" is deleted");
                },
                ()->{
                    throw new BusinessException("Item Not Found");
                });
    }

    public PagingResponse<ItemDetail> getListPagination(SearchRequest input){
        Page<ItemEntity> pageEntity = itemRepository.findItemSearch(input.getSearchValue(), Utils.constructPageable(input));
        List<ItemDetail> details = mapper.convertValue(pageEntity.getContent(), List.class);
        return new PagingResponse<>(details, new Pagination(input, pageEntity));
    }

    public ItemDetail getById(long id){
        ItemEntity item = itemRepository.findById(id).orElseThrow(()->new BusinessException("Item Not Found"));
        return mapper.convertValue(item, ItemDetail.class);
    }
}
