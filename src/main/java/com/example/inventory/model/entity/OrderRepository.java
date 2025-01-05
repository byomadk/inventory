package com.example.inventory.model.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {

    @Query("""
            from OrderEntity oe 
            where (:searchValue IS NULL OR UPPER(oe.item.name) LIKE UPPER(CONCAT(CONCAT('%', :searchValue), '%'))) 
            """)
    Page<OrderEntity> findInventorySearch(String searchValue, Pageable pageable);
}
