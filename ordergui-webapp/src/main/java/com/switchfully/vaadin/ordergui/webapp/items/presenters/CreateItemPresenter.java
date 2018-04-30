package com.switchfully.vaadin.ordergui.webapp.items.presenters;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.webapp.OrderGUI;
import com.switchfully.vaadin.ordergui.webapp.items.models.CreateItemModel;
import com.switchfully.vaadin.ordergui.webapp.items.views.CreateItemView;

public class CreateItemPresenter implements CreateItemView.CreateItemViewListener, CreateItemModel.CreateItemModelListener {
    private CreateItemModel model;
    private CreateItemView view;


    public CreateItemPresenter(CreateItemModel model, CreateItemView view) {
        this.model = model;
        this.model.addListener(this);
        this.view = view;
        this.view.addListener(this);
    }

    @Override
    public void itemSaved(Item item) {
        this.view.showNotificationForSuccessfullCreation();
        this.view.getUI().getNavigator().navigateTo(OrderGUI.VIEW_ITEM_HOME);
    }

    @Override
    public void newItemActivated(Item item) {
        this.view.setActiveItem(item);
    }

    @Override
    public void itemCreated(Item item) {
        this.model.saveItem(item);

    }

    @Override
    public void itemFormCanceled() {
        this.view.getUI().getNavigator().navigateTo(OrderGUI.VIEW_ITEM_HOME);
    }

    public CreateItemView getView() {
        return view;
    }

    @Override
    public void createItemViewOpened() {
        this.model.setNewActiveItem();
    }

    public CreateItemModel getModel() {
        return model;
    }
}
