package com.tistory.cnux9.kiosk_challenge.lv2.view;

import com.tistory.cnux9.kiosk_challenge.lv2.data.Menu;
import com.tistory.cnux9.kiosk_challenge.lv2.data.MenuItem;
import com.tistory.cnux9.kiosk_challenge.lv2.util.Format;

import java.util.ArrayList;
import java.util.List;

public class Printer {
    private static final int MENU_PROPERTY_NUM = 1;
    private static final int ORDER_PROPERTY_NUM = 2;
    private static final int ITEM_PROPERTY_NUM = 3;

    private boolean hasTitle = false;
    private String title;
    List<StringBuilder> sbList;

    public Printer(int rowNum) {
        sbList = new ArrayList<>(rowNum);
        for (int i=0;i<rowNum;i++) {
            sbList.add(i, new StringBuilder());
        }
    }

    public Printer(String title, int rowNum) {
        this(rowNum);
        this.hasTitle = true;
        this.title = title;
    }

    public static void printMenus(Menu[] menuArr) {
        new Printer("MAIN MENU", menuArr.length)
                .appendNumber(1)
                .appendMenus(menuArr)
                .print();
    }

    public static void printOrders(int start) {
        String[][] data = {
                {"Orders", "장바구니를 확인 후 주문합니다."},
                {"Cancel", "진행중인 주문을 취소합니다."}
        };

        new Printer("ORDER", ORDER_PROPERTY_NUM)
                .appendNumber(start)
                .appendData(data)
                .print();
    }

    public static void printCart(Menu cart) {
        String[][] col = cart.getItems().stream().map((MenuItem i) -> new String[]{"x"+i.getCount()}).toArray(String[][]::new);

        new Printer("CART", cart.size())
                .appendItems(cart.getItems().toArray(MenuItem[]::new))
                .appendDivisior()
                .appendData(col)
                .print();
    }

    public static void printItems(Menu menu) {
        MenuItem[] itemArr = menu.getItems().toArray(MenuItem[]::new);
        new Printer(menu.getName().toUpperCase() +" MENU", menu.size())
                .appendNumber(1)
                .appendItems(itemArr)
                .print();
    }

    public boolean hsaTitle() {
        return hasTitle;
    }

    public Printer appendMenus(Menu... menuArr) {
        int length = menuArr.length;
        String[][] data  = new String[length][MENU_PROPERTY_NUM];

        for (int i = 0; i<length; i++) {
            data[i][0] = menuArr[i].getName();
        }
        return appendData(data);
    }

    public Printer appendItems(MenuItem[] items) {
        int length = items.length;
        String[][] data  = new String[length][ITEM_PROPERTY_NUM];

        for (int i = 0; i<length; i++) {
            data[i][0] = items[i].getName();
            data[i][1] = items[i].getPrice().toString();
            data[i][2] = items[i].getDescription();
        }
        return appendData(data);
    }

    private void printTitle() {
        System.out.println(Format.yellow("[ " + title + " ]"));
    }

    public Printer appendData(String[][] data) {
        for (int col = 0;col<data[0].length;col++) {
            int maxLength = 0;
            for (int row = 0;row<data.length;row++) {
                maxLength = Math.max(maxLength, data[row][col].length());
            }

            for (int row = 0;row<data.length;row++) {
                String s = data[row][col];
                sbList.get(row).append(String.format("%-" + maxLength + "s", s));
            }

            // 구분자
            if (col!=data[0].length-1) {
                this.appendDivisior();
            }
        }
        return this;
    }

    public void print() {
        if (hasTitle) {
            printTitle();
        }
        for (int i = 0;i<sbList.size();i++) {
            System.out.println(sbList.get(i).toString());
        }
    }

    public Printer appendNumber(int start) {
        for (int i = 0; i< sbList.size(); i++) {
            sbList.get(i).append(Format.blue((i+start) + ". "));
        }
        return this;
    }

    public Printer appendDivisior() {
        for (StringBuilder stringBuilder : sbList) {
            stringBuilder.append(" " + Format.magenta("|") + " ");
        }
        return this;
    }

}
