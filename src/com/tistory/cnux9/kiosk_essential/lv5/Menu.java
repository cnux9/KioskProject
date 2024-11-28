package com.tistory.cnux9.kiosk_essential.lv5;

import java.util.ArrayList;

public class Menu {
    private final ArrayList<MenuItem> items = new ArrayList<>();
    private final String name;

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
}
