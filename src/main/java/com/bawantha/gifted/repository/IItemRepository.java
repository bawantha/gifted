package com.bawantha.gifted.repository;

import com.bawantha.gifted.dao.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemRepository  extends JpaRepository<Item, Long> {
    Page<Item> findAll(Pageable pageable);
}
