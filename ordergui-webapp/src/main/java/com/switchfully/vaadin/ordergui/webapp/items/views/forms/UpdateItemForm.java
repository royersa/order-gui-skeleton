package com.switchfully.vaadin.ordergui.webapp.items.views.forms;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.List;

public class UpdateItemForm extends FormLayout {
    private TextField name = new TextField("Name");
    private TextArea description = new TextArea("Description");
    private TextField price = new TextField("Price");
    private TextField amountOfStock = new TextField("Amount");
    private Button updateButton = new Button("Update");
    private Button cancelButton = new Button("Cancel");
    private Item item;
    private BeanFieldGroup<Item> beanFieldGroup;

    private List<UpdateItemFormListener> updateItemFormListeners = new ArrayList<>();

    public UpdateItemForm() {
        name.setWidth("40em");
        name.setNullRepresentation("");

        description.setWidth("40em");
        description.setHeight("10em");
        description.setMaxLength(750);
        description.setNullRepresentation("");

        price.setWidth("10em");
        price.setNullRepresentation("0.0");

        amountOfStock.setWidth("10em");
        amountOfStock.setNullRepresentation("0");

        updateButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        updateButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        updateButton.addClickListener(e -> update());

        cancelButton.setStyleName(ValoTheme.BUTTON_DANGER);
        cancelButton.addClickListener(e -> cancel());

        HorizontalLayout priceLayout = new HorizontalLayout(price, amountOfStock);
        priceLayout.setSpacing(true);

        HorizontalLayout buttons = new HorizontalLayout(updateButton, cancelButton);
        buttons.setSpacing(true);

        addComponents(new Label("Update item"), name, description, priceLayout, buttons);
    }

    public void addListener (UpdateItemFormListener listener){
        this.updateItemFormListeners.add(listener);
    }

    private void cancel() {
        updateItemFormListeners.stream().forEach(listener -> listener.cancelClicked());
    }

    private void update() {
        try {
            beanFieldGroup.commit();
            updateItemFormListeners.stream().forEach(listener -> listener.updateItemClicked(item));
        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
        }
    }

    public void setItem(Item item) {
        this.item = item;
        beanFieldGroup = BeanFieldGroup.bindFieldsBuffered(this.item, this);
    }


    public interface UpdateItemFormListener {
        void updateItemClicked(Item item);

        void cancelClicked();
    }
}
