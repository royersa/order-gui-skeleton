package com.switchfully.vaadin.ordergui.webapp.items.views;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.List;

import static com.switchfully.vaadin.ordergui.interfaces.items.Item.ItemBuilder;

public class CreateItemForm extends FormLayout {
    private TextField name = new TextField("Name");
    private TextField description = new TextField("Description");
    private TextField price = new TextField("Price");
    private TextField amountOfStock = new TextField("Amount");
    private Button createButton = new Button("Create");
    private Button cancelButton = new Button("Cancel");
    private Item item;
    private BeanFieldGroup<Item> beanFieldGroup;

    private List<CreateItemFormListener> createItemFormListeners = new ArrayList<>();

    public CreateItemForm() {
        name.setWidth("40em");
        name.setNullRepresentation("");

        description.setWidth("40em");
        description.setHeight("10em");
        description.setNullRepresentation("");

        price.setWidth("10em");
        price.setNullRepresentation("0.0");

        amountOfStock.setWidth("10em");
        amountOfStock.setNullRepresentation("0");

        createButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        createButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        createButton.addClickListener(e -> save());

        cancelButton.setStyleName(ValoTheme.BUTTON_DANGER);
        cancelButton.addClickListener(e -> cancel());

        HorizontalLayout priceLayout = new HorizontalLayout(price, amountOfStock);
        priceLayout.setSpacing(true);

        HorizontalLayout buttons = new HorizontalLayout(createButton, cancelButton);
        buttons.setSpacing(true);

        addComponents(new Label("New Item"), name, description, priceLayout, buttons);
    }

    public void addListener (CreateItemFormListener createItemFormListener){
        this.createItemFormListeners.add(createItemFormListener);
    }

    private void cancel() {
        createItemFormListeners.stream().forEach(listener -> listener.cancelClicked());
    }

    private void save() {
        try {
            beanFieldGroup.commit();
            createItemFormListeners.stream().forEach(listener -> listener.createItemClicked(item));
        } catch (FieldGroup.CommitException e) {
            e.printStackTrace();
        }
    }

    public void setItem(Item item) {
        this.item = ItemBuilder.cloneItem(item).build();
        beanFieldGroup = BeanFieldGroup.bindFieldsBuffered(this.item, this);
    }


    public interface CreateItemFormListener {
        void createItemClicked(Item item);

        void cancelClicked();
    }
}
