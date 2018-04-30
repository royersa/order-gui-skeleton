package com.switchfully.vaadin.ordergui.webapp.items.views;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.webapp.items.views.forms.UpdateItemForm;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

import java.util.ArrayList;
import java.util.List;

public class UpdateItemView extends CustomComponent implements UpdateItemForm.UpdateItemFormListener, View {

    private List<UpdateItemViewListener> updateItemViewListeners = new ArrayList<>();
    private UpdateItemForm updateItemForm;

    public UpdateItemView() {
        updateItemForm = new UpdateItemForm();
        updateItemForm.addListener(this);

        VerticalLayout mainLayout = new VerticalLayout(updateItemForm);
        mainLayout.setMargin(true);
        setCompositionRoot(mainLayout);
    }


    public void addListener (UpdateItemViewListener updateItemViewListener) {
        updateItemViewListeners.add(updateItemViewListener);
    }

    public void setActiveItem (Item item){
        if (item != null){
            this.updateItemForm.setItem(item);
        }
    }


    @Override
    public void updateItemClicked(Item item) {
        updateItemViewListeners.forEach(listener -> listener.itemToBeUpdated(item));

    }

    @Override
    public void cancelClicked() {
        updateItemViewListeners.forEach(listener -> listener.itemFormCanceled());

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        String itemId = viewChangeEvent.getParameters();
        updateItemViewListeners.forEach(listener -> listener.pageOpened(itemId));

    }

    public void showNotificationForSuccessfullUpdate(){
        Notification notification = new Notification("Item has been updated, mate!",
                "Yolo.", Notification.Type.HUMANIZED_MESSAGE, true);
        notification.setDelayMsec(3000);

        notification.show(Page.getCurrent());

    }


    public interface UpdateItemViewListener {
        void pageOpened(String itemId);
        void itemToBeUpdated (Item item);
        void itemFormCanceled();
    }
}
