package com.switchfully.vaadin.ordergui.webapp.items.views;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.webapp.OrderGUI;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.List;

public class AllItemsView extends CustomComponent implements View {

    private Grid grid = new Grid();
    private TextField filter = new TextField();
    private Button filterButton = new Button("Filter");
    private Button newItem = new Button("Create Item");
    List<Item> items;
    List<AllItemsViewListener> listeners = new ArrayList<>();



    public AllItemsView() {
        Label title = new Label("Items");


        populateGrid(items);

        CssLayout filtering = makeFiltering();

        grid.addSelectionListener(event -> {
            if (event.getSelected().isEmpty()) {
                listeners.forEach(l -> l.itemSelected(null));
            } else {
                listeners.forEach(l -> l.itemSelected((Item) event.getSelected().iterator().next()));
            }
        });
        grid.setSizeFull();

        newItem.addClickListener(e -> listeners.stream().forEach(listener -> listener.newItemClicked()));

        HorizontalLayout toolbar = new HorizontalLayout(filtering, newItem);
        toolbar.setSpacing(true);
        HorizontalLayout header = new HorizontalLayout(title, toolbar);
        header.setSpacing(true);
        VerticalLayout main = new VerticalLayout(header, grid);
        main.setSizeFull();
        main.setMargin(true);
        main.setSpacing(true);
        setCompositionRoot(main);


    }



    private CssLayout makeFiltering() {
        filter.setInputPrompt("Filter by name...");
        filter.addTextChangeListener(event -> listeners.forEach(l -> l.searchFilterChanged(event.getText())));
        filterButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        filterButton.addClickListener(event -> listeners.forEach(l -> l.filterClicked()));

        CssLayout filtering = new CssLayout();
        filtering.addComponent(filter);
        filtering.addComponent(filterButton);
        filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        return filtering;
    }

    private void populateGrid(List<Item> items) {
        BeanItemContainer<Item> container = new BeanItemContainer<>(Item.class, items);

        grid.setColumns("name", "description", "price", "amountOfStock");

        grid.setContainerDataSource(container);

    }

    public void addListener(AllItemsViewListener listener) {
        listeners.add(listener);
    }

    public void setSearchResults(List<Item> resultList) {
        this.items = resultList;
        populateGrid(resultList);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    }

    public interface AllItemsViewListener {

        void searchFilterChanged(String searchFilter);

        void itemSelected(Item item);

        void filterClicked();

        void newItemClicked();

        void editClicked();
    }
}