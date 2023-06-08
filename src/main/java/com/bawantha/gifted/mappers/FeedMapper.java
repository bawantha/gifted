package com.bawantha.gifted.mappers;

import com.bawantha.gifted.entities.Item;
import com.rometools.rome.feed.synd.SyndEntry;

public class FeedMapper  implements  IMapper<SyndEntry, Item> {

    @Override
    public SyndEntry from(Item item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Item to(SyndEntry syndEntry) {
        try {
            Item item = new Item();
            item.setTitle(syndEntry.getTitle());
            item.setDescription(syndEntry.getDescription().getValue());
            item.setPublishDate(syndEntry.getPublishedDate());
            return item;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
