package com.example.inventory.model.request;

import org.apache.commons.lang3.StringUtils;

public class SearchRequest {
    private String searchValue;
    private Integer pageNumber;
    private Integer pageSize;
    private String sortType;
    private String sortBy;

    public SearchRequest(String searchValue, Integer pageNumber, Integer pageSize, String sortType, String sortBy) {
        this.searchValue = StringUtils.defaultIfBlank(searchValue, null);
        this.pageNumber = pageNumber == null? 1 : pageNumber;
        this.pageSize = pageSize == null? 10 : pageSize;
        this.sortType = StringUtils.defaultIfBlank(sortType, null);
        this.sortBy = StringUtils.defaultIfBlank(sortBy, null);
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
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

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }
}
