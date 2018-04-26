package com.switchfully.vaadin.ordergui.webapp.items.views;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.webapp.MainView;
import com.switchfully.vaadin.ordergui.webapp.items.ItemModel;

public class CreateItemPresenter implements MainView.MainViewListener, CreateItemView.CreateItemViewListener, ItemModel.ItemModelListener {
    private ItemModel model;
    private CreateItemView view;
    private MainView mainView;

    public CreateItemPresenter(ItemModel model, CreateItemView view, MainView mainView) {
        this.model = model;
        this.model.addListener(this);
        this.view = view;
        this.view.addListener(this);
        this.mainView = mainView;
        this.mainView.addListener(this);
    }

    @Override
    public void itemSaved(Item item) {
        // go back to main view //

    }

    @Override
    public void itemActivated(Item item) {
        this.view.setActiveItem(item);
    }

    @Override
    public void itemCreated(Item item) {
        this.model.saveItem(item);

    }

    @Override
    public void itemFormCanceled() {
        // go back to main page//
    }

    @Override
    public void createItemClicked() {
        this.model.setActiveItem();
    }
}
