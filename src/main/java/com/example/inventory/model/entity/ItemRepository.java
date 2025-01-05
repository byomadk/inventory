package com.example.inventory.model.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {


    @Query("""
            from ItemEntity i 
            where (:searchValue IS NULL OR UPPER(i.name) LIKE UPPER(CONCAT(CONCAT('%', :searchValue), '%'))) 
            """)
    Page<ItemEntity> findItemSearch(String searchValue, Pageable pageable);
}
