package com.bawantha.gifted.controller;

import com.bawantha.gifted.entities.Item;
import com.bawantha.gifted.utils.Constant;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.RemoteException;
import java.util.List;

@RestController
@RequestMapping(Constant.ITEMS)
public interface IItemController {

    /**
     * Get items from the database
     *
     * @param page      page number to get default 1
     * @param size      number of items to get default 10
     * @param sort      sort by date default today
     * @param direction sort direction default desc
     * @return Page of items
     * @throws RemoteException if any error occurred
     */
    @GetMapping
    @Operation(summary = "Get items", description = "Main endpoint to get items")
    ResponseEntity<List<Item>> getItems(@RequestParam(defaultValue = "1", required = false) int page,
                                        @RequestParam(defaultValue = "10", required = false) int size,
                                        @RequestParam(defaultValue = "publishDate",required = false) String sort,
                                        @RequestParam(defaultValue = "desc", required = false) String direction) throws RemoteException;

}
