package com.example.inventory.service;

import com.example.inventory.exception.BusinessException;
import com.example.inventory.model.entity.InventoryEntity;
import com.example.inventory.model.entity.InventoryRepository;
import com.example.inventory.model.entity.ItemEntity;
import com.example.inventory.model.entity.ItemRepository;
import com.example.inventory.model.request.SearchRequest;
import com.example.inventory.model.response.InventoryDetail;
import com.example.inventory.model.response.Pagination;
import com.example.inventory.model.response.PagingResponse;
import com.example.inventory.util.Utils;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ItemRepository itemRepository;
    private final ObjectMapper mapper;

    public InventoryService(InventoryRepository inventoryRepository, ItemRepository itemRepository) {
        this.inventoryRepository = inventoryRepository;
        this.itemRepository = itemRepository;
        this.mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public void deleteById(int id){
        inventoryRepository.findById((long) id).ifPresentOrElse(
                inventory->{
                    inventoryRepository.delete(inventory);
                    System.out.println("inventory "+inventory.getId()+" is deleted");
                },
                ()->{
                    throw new BusinessException("Inventory Not Found");
                });
    }

    public InventoryDetail getById(long id){
        InventoryEntity inv = inventoryRepository.findById(id).orElseThrow(()->new BusinessException("Inventory not found"));
        InventoryDetail detail = mapper.convertValue(inv, InventoryDetail.class);
        detail.setItemName(inv.getItem().getName());
        detail.setPrice(inv.getItem().getPrice().toString());
        return detail;
    }

    public PagingResponse<InventoryDetail> getListPagination(SearchRequest input){
        Page<InventoryEntity> pageEntity = inventoryRepository.findInventorySearch(input.getSearchValue(), Utils.constructPageable(input));
        List<InventoryDetail> details = mapper.convertValue(pageEntity.getContent(), mapper.getTypeFactory().constructCollectionType(List.class, InventoryDetail.class));
        int counter = 0;
        for (InventoryDetail detail : details){
            InventoryEntity inv = pageEntity.getContent().get(counter);
            detail.setItemName(inv.getItem().getName());
            detail.setPrice(inv.getItem().getPrice().toString());
            counter++;
        }
        return new PagingResponse<>(details, new Pagination(input, pageEntity));
    }

    public List<InventoryEntity> getAll(){
        return inventoryRepository.findAll();
    }

    public void populateInventory(){
        List<InventoryEntity> inventories = new ArrayList<>();
        for (int i=1; i<10; i++){
            InventoryEntity inventory = new InventoryEntity();
            inventory.setId((long) i);
            inventory.setItem(getItem(i));
            inventory.setQuantity(getQuantity(i));
            inventory.setType(i>8?"W":"T");
            inventories.add(inventory);
        }
        inventoryRepository.saveAll(inventories);
    }

    private ItemEntity getItem(int i){
        ItemEntity item;
        item = itemRepository.findById((long) i).orElse(null);
        switch (i){
            case 8 -> {
                item = itemRepository.findById(4L).orElse(null);
            }
            case 9 -> {
                item = itemRepository.findById(5L).orElse(null);
            }
        }
        return item;
    }

    private Long getQuantity(int i){
        long quantity = 0;
        switch (i){
            case 1 -> quantity = 5;
            case 2 -> quantity = 10;
            case 3 -> quantity = 30;
            case 4 -> quantity = 3;
            case 5 -> quantity = 45;
            case 6 -> quantity = 5;
            case 7 -> quantity = 25;
            case 8 -> quantity = 7;
            case 9 -> quantity = 10;
        }
        return quantity;
    }
}
