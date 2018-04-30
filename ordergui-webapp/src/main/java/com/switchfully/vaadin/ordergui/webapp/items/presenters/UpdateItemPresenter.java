package com.switchfully.vaadin.ordergui.webapp.items.presenters;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.webapp.OrderGUI;
import com.switchfully.vaadin.ordergui.webapp.items.models.UpdateItemModel;
import com.switchfully.vaadin.ordergui.webapp.items.views.UpdateItemView;

public class UpdateItemPresenter implements UpdateItemView.UpdateItemViewListener, UpdateItemModel.UpdateItemModelListener {
    private UpdateItemModel model;
    private UpdateItemView view;


    public UpdateItemPresenter(UpdateItemModel model, UpdateItemView view) {
        this.model = model;
        this.model.addListener(this);
        this.view = view;
        this.view.addListener(this);
    }

    @Override
    public void itemActivated(Item item) {
        this.view.setActiveItem(item);

    }

    @Override
    public void itemToBeUpdated(Item item) {
        this.model.updateItem(item);
    }

    @Override
    public void itemUpdated(Item item) {
        this.view.showNotificationForSuccessfullUpdate();
        this.view.getUI().getNavigator().navigateTo(OrderGUI.VIEW_ITEM_HOME);

    }

    @Override
    public void itemFormCanceled() {
        this.view.getUI().getNavigator().navigateTo(OrderGUI.VIEW_ITEM_HOME);

    }


    @Override
    public void pageOpened(String itemId) {
        this.model.findItemById(itemId);
    }

    public UpdateItemModel getModel() {
        return model;
    }

    public UpdateItemView getView() {
        return view;
    }
}
