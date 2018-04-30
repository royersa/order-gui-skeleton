package com.switchfully.vaadin.ordergui.webapp;

import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.switchfully.vaadin.ordergui.webapp.items.models.AllItemsModel;
import com.switchfully.vaadin.ordergui.webapp.items.models.CreateItemModel;
import com.switchfully.vaadin.ordergui.webapp.items.models.UpdateItemModel;
import com.switchfully.vaadin.ordergui.webapp.items.presenters.AllItemsPresenter;
import com.switchfully.vaadin.ordergui.webapp.items.presenters.CreateItemPresenter;
import com.switchfully.vaadin.ordergui.webapp.items.presenters.UpdateItemPresenter;
import com.switchfully.vaadin.ordergui.webapp.items.views.AllItemsView;
import com.switchfully.vaadin.ordergui.webapp.items.views.CreateItemView;
import com.switchfully.vaadin.ordergui.webapp.items.views.UpdateItemView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class OrderGUI extends UI {

    private ItemResource itemResource;
    private Navigator navigator;
    private CreateItemPresenter createItemPresenter;
    private AllItemsPresenter allItemsPresenter;
    private UpdateItemPresenter updateItemPresenter;

    public static final String VIEW_ITEM_HOME = "";
    public static final String VIEW_CREATE_ITEM = "item_creation";
    public static final String VIEW_UPDATE_ITEM = "item_update";


    @Autowired
    public OrderGUI(ItemResource itemResource) {
        this.itemResource = itemResource;
    }

    @Override
    protected void init(VaadinRequest request) {
        navigator = new Navigator(this, this);
        createItemPresenter = new CreateItemPresenter(new CreateItemModel(itemResource), new CreateItemView());
        updateItemPresenter = new UpdateItemPresenter(new UpdateItemModel(itemResource), new UpdateItemView());
        allItemsPresenter = new AllItemsPresenter(new AllItemsModel(itemResource), new AllItemsView());
        allItemsPresenter.getModel().updateResultList();


        navigator.addView(VIEW_ITEM_HOME, allItemsPresenter.getView());
        navigator.addView(VIEW_CREATE_ITEM,createItemPresenter.getView());
        navigator.addView(VIEW_UPDATE_ITEM, updateItemPresenter.getView());
    }

}