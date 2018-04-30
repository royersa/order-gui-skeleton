package com.switchfully.vaadin.ordergui.webapp.items.views;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.webapp.OrderGUI;
import com.switchfully.vaadin.ordergui.webapp.items.views.forms.CreateItemForm;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
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
        createItemViewListeners.forEach(listener -> listener.createItemViewOpened());

    }

    public void showNotificationForSuccessfullCreation(){
        Notification notification = new Notification("Item Creation Success",
                "The item has been successfully created in the database.", Notification.Type.HUMANIZED_MESSAGE, true);
         notification.setDelayMsec(3000);

         notification.show(Page.getCurrent());

    }


    public interface CreateItemViewListener {
        void createItemViewOpened();
        void itemCreated (Item item);
        void itemFormCanceled();
    }
}
