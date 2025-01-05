package com.example.inventory.service;

public interface BaseService<T> {

    T get();

    void list();

    void save();

    void edit();

    void delete();
}
