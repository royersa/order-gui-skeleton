package com.switchfully.vaadin.ordergui.webapp.items.presenters;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.webapp.OrderGUI;
import com.switchfully.vaadin.ordergui.webapp.items.models.AllItemsModel;
import com.switchfully.vaadin.ordergui.webapp.items.views.AllItemsView;

import java.util.List;

public class AllItemsPresenter implements AllItemsModel.AllItemsModelListener, AllItemsView.AllItemsViewListener{
    private AllItemsModel model;
    private AllItemsView view;


    public AllItemsPresenter(AllItemsModel model, AllItemsView view) {
        this.model = model;
        model.addListener(this);
        this.view = view;
        view.addListener(this);
    }

    @Override
    public void resetFilterRequested(String searchFilter) {
        this.view.resetFilter(searchFilter);
    }
    @Override
    public void resultListChanged(List<Item> resultList) {
        this.view.setSearchResults(resultList);
    }

    @Override
    public void searchFilterChanged(String searchFilter) {
        this.model.setSearchFilter(searchFilter);
    }

    @Override
    public void itemSelected(Item item) {

    }

    @Override
    public void filterClicked() {
        this.model.updateResultList();

    }

    @Override
    public void newItemClicked() {
        this.view.getUI().getNavigator().navigateTo(OrderGUI.VIEW_CREATE_ITEM);
        this.model.setNewActiveItem();

    }

    @Override
    public void editClicked(Item item) {
        this.view.getUI().getNavigator().navigateTo(OrderGUI.VIEW_UPDATE_ITEM + "/" + item.getId());
    }

    public AllItemsModel getModel() {
        return model;
    }

    public AllItemsView getView() {
        return view;
    }
}
