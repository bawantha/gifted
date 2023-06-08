package com.bawantha.gifted.services;

import com.bawantha.gifted.entities.Item;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Created by Bawantha on 6/Jun/2023
 * Description: Serve the RSS feed to the client
 **/
@Component
public interface IItemService {

     List<Item> getItems(int page, int size,String sort, String direction);


     void feed() throws IOException;
}
