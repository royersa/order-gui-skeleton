package com.switchfully.vaadin.ordergui.webapp.items.views;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

public class CreateItemView extends CustomComponent implements CreateItemForm.CreateItemFormListener, View {

    private List<CreateItemViewListener> createItemViewListeners = new ArrayList<>();
    private final CreateItemForm createItemForm;

    public CreateItemView() {
        createItemForm = new CreateItemForm();
        createItemForm.addListener(this);

        VerticalLayout mainLayout = new VerticalLayout(createItemForm);
        mainLayout.setMargin(true);
        setCompositionRoot(mainLayout);
    }

    public void addListener (CreateItemViewListener createItemViewListener) {
        createItemViewListeners.add(createItemViewListener);
    }

    public void setActiveItem (Item item){
        if (item != null){
            this.createItemForm.setItem(item);
        }
    }

    @Override
    public void createItemClicked(Item item) {
        createItemViewListeners.stream().forEach(listener -> listener.itemCreated(item));
    }

    @Override
    public void cancelClicked() {
        createItemViewListeners.stream().forEach(listener -> listener.itemFormCanceled());

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }


    public interface CreateItemViewListener {
        void itemCreated (Item item);
        void itemFormCanceled();
    }
}