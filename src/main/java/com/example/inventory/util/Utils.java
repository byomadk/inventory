package com.example.inventory.util;

import com.example.inventory.model.entity.InventoryEntity;
import com.example.inventory.model.entity.ItemEntity;
import com.example.inventory.model.request.SearchRequest;
import com.example.inventory.model.response.Pagination;
import com.example.inventory.model.response.PagingResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class Utils {

    private Utils() throws IllegalAccessException {
        throw new IllegalAccessException("Utils class");
    }

    public static Pageable constructPageable(SearchRequest input){
        return PageRequest.of(input.getPageNumber() - 1, input.getPageSize());
    }

    public static <T> Pagination setPagination(SearchRequest input, Page<T> pageEntity){
        Pagination pagination = new Pagination();
        pagination.setPageNumber(input.getPageNumber());
        pagination.setPageSize(input.getPageSize());
        pagination.setTotalPages(pageEntity.getTotalPages());
        pagination.setTotalRecords((int) pageEntity.getTotalElements());
        return pagination;
    }
}
