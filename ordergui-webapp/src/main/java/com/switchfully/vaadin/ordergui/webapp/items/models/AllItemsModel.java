package com.switchfully.vaadin.ordergui.webapp.items.models;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AllItemsModel {
    private ItemResource itemResource;

    private List<AllItemsModelListener> listeners = new ArrayList<>();
    private List<Item> items;
    private List<Item> resultList;
    private String searchFilter;

    public AllItemsModel(ItemResource itemResource) {


        this.itemResource = itemResource;
        this.items = itemResource.getItems();

        setSearchFilter("");
    }

    public void setSearchFilter(String searchFilter){
        this.searchFilter = searchFilter;
    }

    public void askForFilterReset(String searchFilter){
        listeners.forEach(listener -> listener.resetFilterRequested(searchFilter));
    }

    public void addListener(AllItemsModelListener listener){
        this.listeners.add(listener);
    }

    public void updateResultList(){
        resultList = itemResource.getItems().stream()
                .filter(i -> i.getName().toLowerCase().contains(searchFilter.toLowerCase()))
                .collect(Collectors.toList());
        listeners.forEach(l -> l.resultListChanged(resultList));
    }


    public interface AllItemsModelListener{
        void resultListChanged(List<Item> resultList);
        void resetFilterRequested(String searchFilter);
    }
}
