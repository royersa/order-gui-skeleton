package com.switchfully.vaadin.ordergui.webapp.items.models;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;

import java.util.ArrayList;
import java.util.List;

public class UpdateItemModel {
    private ItemResource itemResource;
    private List<UpdateItemModelListener> itemModelListeners = new ArrayList<>();
    private Item activeItem;

    public UpdateItemModel(ItemResource itemResource) {
        this.itemResource = itemResource;

    }

    public void addListener(UpdateItemModelListener itemModelListener){
        this.itemModelListeners.add(itemModelListener);
    }

    public void updateItem(Item item){
        itemResource.updateItem(item.getId(), item);
        this.itemModelListeners.forEach(listener -> listener.itemUpdated(item));
    }

    public void setActiveItem(Item item) {
        this.activeItem = item;
        itemModelListeners.stream().forEach(listener -> listener.itemActivated(activeItem));
    }

    public void findItemById(String itemId){
        Item foundItem = itemResource.findItemBydId(itemId);
        setActiveItem(foundItem);

    }

    public interface UpdateItemModelListener{
        void itemUpdated(Item item);
        void itemActivated(Item item);

    }
}
