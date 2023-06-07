package com.bawantha.gifted.controller;

import com.bawantha.gifted.dao.Item;
import com.bawantha.gifted.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ItemController implements IItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public ResponseEntity<List<Item>> getItems(int page, int size, String sort, String direction) {
        return ResponseEntity.ok(itemService.getItems(page, size, sort, direction));
    }
}
