package com.tistory.cnux9.kiosk_challenge.lv2.data;

import java.util.ArrayList;

public class Menu {
    private final String name;
    private ArrayList<MenuItem> items = new ArrayList<>();

    public Menu(String name) {
        this.name = name;
    }

    public Menu add(MenuItem item) {
        items.add(item);
        return this;
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<MenuItem> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int size() {
        return items.size();
    }


    public boolean contains(MenuItem item) {
        return items.contains(item);
    }
}
