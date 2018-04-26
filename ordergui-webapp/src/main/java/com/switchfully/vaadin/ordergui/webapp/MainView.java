package com.switchfully.vaadin.ordergui.webapp;

import com.switchfully.vaadin.ordergui.interfaces.items.ItemResource;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

public class MainView extends CustomComponent implements View {


    private ItemResource itemResource ;

    public List<MainViewListener> listeners = new ArrayList<>();

    public MainView(ItemResource itemResource) {
        this.itemResource = itemResource;
        VerticalLayout mainLayout = new VerticalLayout();
        addTitleLabel(mainLayout);
        renderItems(mainLayout);

        mainLayout.addComponent(new Button("Create Item", e ->
        {getUI().getNavigator().navigateTo(OrderGUI.VIEW_CREATE_ITEM);
        listeners.stream().forEach(listener -> listener.createItemClicked());}));
        setCompositionRoot(mainLayout);
    }

    private void renderItems(VerticalLayout mainLayout) {
        itemResource.getItems()
                .forEach(item ->
                        mainLayout.addComponent(
                                new HorizontalLayout(
                                        new Label("--> " + item.getName() + " €" + item.getPrice()))));
    }

    private void addTitleLabel(VerticalLayout mainLayout) {
        mainLayout.addComponent(
                new HorizontalLayout(
                        new Label("ITEMS:")
                )
        );
    }

    public void addListener(MainViewListener listener){
        this.listeners.add(listener);

    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    public interface MainViewListener {
        void createItemClicked();

    }
}
