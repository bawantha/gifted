package com.bawantha.gifted.services;

import com.bawantha.gifted.entities.Item;
import com.bawantha.gifted.repository.IItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ItemServiceTest {

    @Mock
    private IItemRepository itemRepository;

    private ItemService itemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        itemService = new ItemService(itemRepository);
    }

    @Test
    void testGetItems() {
        // Mock data
        List<Item> itemList = new ArrayList<>();
        Item item1 = new Item();
        item1.setId(1L);
        item1.setTitle("Item 1");
        item1.setDescription("Description 1");
        item1.setLink("Link 1");
        item1.setPublishDate(new Date());

        Item item2 = new Item();
        item2.setId(2L);
        item2.setTitle("Item 2");
        item2.setDescription("Description 2");
        item2.setLink("Link 2");
        item2.setPublishDate(new Date());

        itemList.add(item1);
        itemList.add(item2);

        Page<Item> itemPage = new PageImpl<>(itemList);

        // Mock repository behavior
        when(itemRepository.findAll(any(PageRequest.class))).thenReturn(itemPage);

        // Test the method
        List<Item> result = itemService.getItems(0, 10, "name", "asc");

        // Verify repository method was called with the correct parameters
        verify(itemRepository).findAll(PageRequest.of(0, 10, Sort.by("name").ascending()));

        // Verify the returned list is correct
        assertEquals(itemList, result);
    }

    @Test
    void testGetSortAscending() {
        // Test ascending sort
        String direction = "asc";
        String sort = "title";

        Sort expectedSort = Sort.by(sort).ascending();
        Sort actualSort = itemService.getSort(direction, sort);

        assertEquals(expectedSort, actualSort);
    }

    @Test
    void testGetSortDescending() {
        // Test descending sort
        String direction = "desc";
        String sort = "publishDate";

        Sort expectedSort = Sort.by(sort).descending();
        Sort actualSort = itemService.getSort(direction, sort);

        assertEquals(expectedSort, actualSort);
    }
}