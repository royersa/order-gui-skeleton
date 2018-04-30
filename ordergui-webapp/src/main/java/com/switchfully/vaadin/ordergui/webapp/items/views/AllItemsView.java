package com.switchfully.vaadin.ordergui.webapp.items.views;

import com.switchfully.vaadin.ordergui.interfaces.items.Item;
import com.switchfully.vaadin.ordergui.webapp.items.views.generators.EditButtonGenerator;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.GeneratedPropertyContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.grid.HeightMode;
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
        GeneratedPropertyContainer editContainer = new GeneratedPropertyContainer(container);
        editContainer.addGeneratedProperty("", new EditButtonGenerator() {
        });

        grid.setColumns("name", "description", "price", "amountOfStock", "");
        grid.getColumn("").setRenderer(new ButtonRenderer(e -> listeners.forEach(listener -> listener.editClicked((Item)e.getItemId()))));
        grid.setContainerDataSource(editContainer);
        resizeGrid(editContainer);
    }

    private void resizeGrid(GeneratedPropertyContainer editContainer) {
        if (editContainer.getItemIds().size() > 0){
            grid.setHeightMode(HeightMode.ROW);
            grid.setHeightByRows(editContainer.size());
        } else{
            grid.setHeightMode(HeightMode.UNDEFINED);
        }
    }

    public void addListener(AllItemsViewListener listener) {
        listeners.add(listener);
    }

    public void setSearchResults(List<Item> resultList) {
        this.items = resultList;
        populateGrid(resultList);
    }

    public void resetFilter(String filterText){
        filter.setValue(filterText);
        this.listeners.forEach(listener -> listener.searchFilterChanged(filterText));

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        this.listeners.forEach(listener -> listener.pageOpened());
    }

    public interface AllItemsViewListener {
        void pageOpened();

        void searchFilterChanged(String searchFilter);

        void itemSelected(Item item);

        void filterClicked();

        void newItemClicked();

        void editClicked(Item item);
    }
}
