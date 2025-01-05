package com.example.inventory.model.response;

import com.example.inventory.model.request.SearchRequest;
import org.springframework.data.domain.Page;

import java.io.Serializable;

public class Pagination implements Serializable {
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalPages;
    private Integer totalRecords;

    public Pagination() {
    }

    public <T> Pagination(SearchRequest input, Page<T> pageEntity) {
        this.pageNumber = input.getPageNumber();
        this.pageSize = input.getPageSize();
        this.totalPages = pageEntity.getTotalPages();
        this.totalRecords = (int) pageEntity.getTotalElements();
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(Integer totalRecords) {
        this.totalRecords = totalRecords;
    }
}
