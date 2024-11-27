package com.tistory.cnux9.kiosk_challenge.lv2.data;

public class MenuItem {
    private String name;
    private double price;
    private String description;
    private int count = 1;

    public MenuItem(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        this.count++;
    }


}
