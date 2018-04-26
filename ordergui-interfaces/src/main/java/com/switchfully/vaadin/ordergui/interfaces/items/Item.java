package com.switchfully.vaadin.ordergui.interfaces.items;

public class Item {
    private String id;
    private String name;
    private String description;
    private float price;
    private int amountOfStock;
    private String stockUrgency;

    public Item() {
    }

    public Item(ItemBuilder itemBuilder) {
        this.id = itemBuilder.id;
        this.name = itemBuilder.name;
        this.description = itemBuilder.description;
        this.price = itemBuilder.price;
        this.amountOfStock = itemBuilder.amountOfStock;
        this.stockUrgency = itemBuilder.stockUrgency;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public int getAmountOfStock() {
        return amountOfStock;
    }

    public String getStockUrgency() {
        return stockUrgency;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setAmountOfStock(int amountOfStock) {
        this.amountOfStock = amountOfStock;
    }

    public void setStockUrgency(String stockUrgency) {
        this.stockUrgency = stockUrgency;
    }

    public static class ItemBuilder{
        public String id;
        public String name;
        public String description;
        public float price;
        public int amountOfStock;
        public String stockUrgency;

        public static ItemBuilder item(){
            return new ItemBuilder();
        }

        private ItemBuilder() {
        }

        public static ItemBuilder cloneItem(Item item){
            return new ItemBuilder()
                    .withId(item.getId())
                    .withName(item.getName())
                    .withDescription(item.getDescription())
                    .withPrice(item.getPrice())
                    .withAmountOfStock(item.getAmountOfStock())
                    .withStockUrgency(item.getStockUrgency());
        }

        public ItemBuilder withId(String id) {
            this.id = id;
            return this;
        }

        public ItemBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ItemBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public ItemBuilder withPrice(float price) {
            this.price = price;
            return this;
        }

        public ItemBuilder withAmountOfStock(int amountOfStock) {
            this.amountOfStock = amountOfStock;
            return this;
        }

        public ItemBuilder withStockUrgency(String stockUrgency) {
            this.stockUrgency = stockUrgency;
            return this;
        }

        public Item build(){
            return new Item(this);
        }
    }
}
