package com.bawantha.gifted.services;

import com.bawantha.gifted.entities.Item;
import com.bawantha.gifted.mappers.FeedMapper;
import com.bawantha.gifted.repository.IItemRepository;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.io.XmlReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;

import java.io.IOException;
import java.net.URL;
import java.util.List;


/**
 * Created by Bawantha on 6/Jun/2023
 * Description: Serve the RSS feed to the client
 **/
@Service
public class ItemService implements IItemService{

    private final IItemRepository itemRepository;

    @Autowired
    public ItemService(IItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getItems(int page, int size,String sort, String direction) {
        // Create PageRequest
        PageRequest pageRequest = PageRequest.of(page, size);


        // Return items
        return itemRepository.findAll(pageRequest.withSort(getSort(direction, sort))).getContent();

    }

    @Override
    @Scheduled(fixedDelay = 300000)
    public void feed() {
        String url = "https://rss.nytimes.com/services/xml/rss/nyt/HomePage.xml";
        SyndFeed feed;
        try {
            // Read the RSS feed from the URL
            feed = new SyndFeedInput().build(new XmlReader(new URL(url)));
        } catch (FeedException | IOException e) {
            // Handle any exceptions that occur during feed retrieval
            throw new RuntimeException("Failed to retrieve the RSS feed.", e);
        }

        FeedMapper feedMapper = new FeedMapper();

        // Save the feed to the database
        for (SyndEntry entry : feed.getEntries()) {
            Item item = feedMapper.to(entry);
            System.out.println(item.toString());
            itemRepository.save(item);
        }
    }

    public Sort getSort(String direction, String sort) {
        return  direction.equals("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending();
    }
}
