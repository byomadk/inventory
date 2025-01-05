package com.example.inventory.model.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {

    @Query("""
            from InventoryEntity ie 
            where (:searchValue IS NULL OR UPPER(ie.item.name) LIKE UPPER(CONCAT(CONCAT('%', :searchValue), '%'))) 
            """)
    Page<InventoryEntity> findInventorySearch(String searchValue, Pageable pageable);
}
