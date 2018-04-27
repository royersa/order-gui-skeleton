package com.switchfully.vaadin.ordergui.webapp.items.models;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;

import java.util.ArrayList;
import java.util.List;

public class CreateItemModel {

    private ItemResource itemResource;
    private List<CreateItemModelListener> itemModelListeners = new ArrayList<>();
    private Item activeItem;

    public CreateItemModel(ItemResource itemResource) {
        this.itemResource = itemResource;

    }

    public void addListener(CreateItemModelListener itemModelListener){
        this.itemModelListeners.add(itemModelListener);
    }

    public void saveItem(Item item){
        itemResource.saveItem(item);
        this.itemModelListeners.forEach(listener -> listener.itemSaved(item));
    }

    public void setNewActiveItem() {
        this.activeItem = new Item();
        itemModelListeners.stream().forEach(listener -> listener.newItemActivated(activeItem));
    }

    public interface CreateItemModelListener{
        void itemSaved(Item item);
        void newItemActivated(Item item);
    }
}
