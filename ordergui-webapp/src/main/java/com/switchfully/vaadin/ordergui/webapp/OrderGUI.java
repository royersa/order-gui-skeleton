package com.switchfully.vaadin.ordergui.webapp;

import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.switchfully.vaadin.ordergui.webapp.items.ItemModel;
import com.switchfully.vaadin.ordergui.webapp.items.views.CreateItemPresenter;
import com.switchfully.vaadin.ordergui.webapp.items.views.CreateItemView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@Theme("valo")
public class OrderGUI extends UI {

    private ItemResource itemResource;
    private Navigator navigator;

    public static final String VIEW_ITEM_HOME = "";
    public static final String VIEW_CREATE_ITEM = "item_creation";


    @Autowired
    public OrderGUI(ItemResource itemResource) {
        this.itemResource = itemResource;
    }

    @Override
    protected void init(VaadinRequest request) {
        navigator = new Navigator(this, this);
        MainView mainView = new MainView(itemResource);
        CreateItemView createItemView = new CreateItemView();
        new CreateItemPresenter(new ItemModel(itemResource), createItemView, mainView);

        navigator.addView(VIEW_ITEM_HOME, mainView);
        navigator.addView(VIEW_CREATE_ITEM, createItemView);

    }

}