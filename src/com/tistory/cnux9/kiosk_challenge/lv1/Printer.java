package com.tistory.cnux9.kiosk_challenge.lv1;

public class Printer {

    public void printMainMenu(Menu... menuArr) {
        int PROPERTIES_NUM = 1;
        int length = menuArr.length;
        String[][] data = new String[length][PROPERTIES_NUM];

        for (int i = 0; i < length; i++) {
            data[i][0] = menuArr[i].getName();
        }

        printFormattedData("MAIN MENU", data);
    }

    public void printItems(String title, MenuItem[] items) {
        int PROPERTIES_NUM = 3;
        int length = items.length;
        String[][] data = new String[length][PROPERTIES_NUM];

        for (int i = 0; i < length; i++) {
            data[i][0] = items[i].getName();
            data[i][1] = items[i].getPrice().toString();
            data[i][2] = items[i].getDescription();
        }

        printFormattedData(title, data);

    }

    public void printNumberedData(String[][] data) {
        String[][] newData = new String[data.length][data[0].length + 1];

    }

    public void printFormattedData(String title, String[][] data) {
        printTitle(title);
        printFormattedData(data, 0);
    }

    public void printTitle(String title) {
        System.out.println(Format.gray("[ " + title + " ]"));
    }

    public void printFormattedData(String[][] data, int start) {
        StringBuilder[] sbArr = new StringBuilder[data.length];

        // 번호
        for (int i = 0; i < data.length; i++) {
            sbArr[i] = new StringBuilder();
            sbArr[i].append(Format.blue((i + 1 + start) + ". "));
        }

        for (int col = 0; col < data[0].length; col++) {
            int maxLength = 0;
            for (int row = 0; row < data.length; row++) {
                maxLength = Math.max(maxLength, data[row][col].length());
            }

            for (int row = 0; row < data.length; row++) {
                String s = data[row][col];
                sbArr[row].append(String.format("%-" + maxLength + "s", s));
            }

            // 구분자
            if (col != data[0].length - 1) {
                for (int i = 0; i < data.length; i++) {
                    sbArr[i].append(" " + Format.magenta("|") + " ");
                }
            }
        }

        for (int i = 0; i < data.length; i++) {
            System.out.println(sbArr[i].toString());
//            sbArr[i].append(blue((i+1) + ". "));
        }

//        System.out.println();

    }


}
