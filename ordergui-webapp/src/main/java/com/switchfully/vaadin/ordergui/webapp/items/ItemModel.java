package com.switchfully.vaadin.ordergui.webapp.items;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;

import java.util.ArrayList;
import java.util.List;

public class ItemModel {

    private ItemResource itemResource;
    private List<ItemModelListener> itemModelListeners = new ArrayList<>();
    private Item activeItem;
    //private List<Item> itemList;

    public ItemModel(ItemResource itemResource) {
        this.itemResource = itemResource;
        //itemList = itemResource.getItems();
    }

    public void saveItem(Item item){
        itemResource.saveItem(item);
        this.itemModelListeners.forEach(listener -> listener.itemSaved(item));
    }

    public void addListener(ItemModelListener itemModelListener){
        this.itemModelListeners.add(itemModelListener);
    }



    public void setActiveItem() {
        this.activeItem = new Item();
        itemModelListeners.stream().forEach(listener -> listener.itemActivated(activeItem));
    }

    public interface ItemModelListener{
        void itemSaved(Item item);
        void itemActivated(Item item);
    }
}
