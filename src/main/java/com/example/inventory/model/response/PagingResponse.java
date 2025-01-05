package com.example.inventory.model.response;

import java.util.List;

public class PagingResponse<T> {
    List<T> details;
    Pagination pagination;

    public PagingResponse() {
    }

    public PagingResponse(List<T> details, Pagination pagination) {
        this.details = details;
        this.pagination = pagination;
    }

    public List<T> getDetails() {
        return details;
    }

    public void setDetails(List<T> details) {
        this.details = details;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
