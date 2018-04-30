package com.switchfully.vaadin.ordergui.webapp.items.views.generators;

import com.vaadin.data.Item;
import com.vaadin.data.util.PropertyValueGenerator;
import com.vaadin.ui.Button;

public class EditButtonGenerator extends PropertyValueGenerator<String> {
    @Override
    public String getValue(Item item, Object o, Object o1) {
        return "Edit";
    }

    @Override
    public Class<String> getType() {
        return String .class;
    }
}
