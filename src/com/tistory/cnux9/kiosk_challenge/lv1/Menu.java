package com.tistory.cnux9.kiosk_challenge.lv1;

import java.util.ArrayList;

public class Menu {
    private String name;
    private final ArrayList<MenuItem> items = new ArrayList<>();
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

    public String getName() {
        return name;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}