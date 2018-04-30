package com.switchfully.vaadin.ordergui.interfaces.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class ItemResource {

    private RestTemplate restTemplate;

    @Autowired
    public ItemResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Item> getItems() {
        Item[] items = restTemplate.getForObject("http://localhost:9000/items", Item[].class);
        return Arrays.asList(items);
    }

    public void saveItem(Item item){
        restTemplate.postForObject("http://localhost:9000/items", item, Item.class);
    }

    public void updateItem (String id, Item item){
        restTemplate.put(String.format("http://localhost:9000/items/%s", id), item);
    }

    public Item findItemBydId(String id){
        return restTemplate.getForObject(String.format("http://localhost:9000/items/%s", id), Item.class);
    }
}
