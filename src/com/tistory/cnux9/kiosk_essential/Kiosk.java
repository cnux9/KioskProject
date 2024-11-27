package com.tistory.cnux9.kiosk_essential;
//import com.tistory.cnux9.kiosk.Format.*;

import java.util.ArrayList;
import java.util.Scanner;

import static com.tistory.cnux9.kiosk_essential.Format.*;

public class Kiosk {
    Scanner sc = new Scanner(System.in);
    Menu[] menuArr;
    ArrayList<MenuItem> cart = new ArrayList<>();
    private int start;
    Printer printer;

    public Kiosk(Menu... menuArr) {
        this.menuArr = menuArr;
        this.printer = new Printer();
    }

    public void start() {
        while (openMainMenu()) {
//            pass;
        }
        System.out.println("프로그램을 종료합니다.");

//        for (Menu menu : menuArr) {
//            printItems(menu.getName().toUpperCase() +" MENU", menu);
//        }
    }

    private boolean openMainMenu() {
        printer.printMainMenu(menuArr);
        if (!cart.isEmpty()) {
            printOrderMenu();
            printer.printItems("CART", cart.toArray(MenuItem[]::new));
        }
        System.out.println(blue("0. ") + "exit      " + magenta("|") + " exit");

        int input = nextInput(menuArr.length + 2);
        if (input==-1) {
            return false;
        } else if (input==menuArr.length) {
            System.out.println("주문하기");
        } else if (input==menuArr.length+1) {
            System.out.println("취소하기");
        } else {
            openSubMenu(menuArr[input]);
        }
        return true;
    }

    private void printOrderMenu() {
        String[][] data = {
            {"Orders", "장바구니를 확인 후 주문합니다."},
            {"Cancel", "진행중인 주문을 취소합니다."}
        };
        printer.printFormattedData("ORDER", data, menuArr.length);
    }

    private int nextInput(int end) {
        int input = 0;
        while (true) {
            try {
                input = sc.nextInt();

                if(0 <= input && input <= end) {
                    break;
                } else {
                    System.out.println("\u001B[2A 0-" + end + " 사이의 수를 입력해주세요.");
                }
            } catch (Exception e) {
                System.out.println("숫자를 입력해주세요!");
            } finally {
                sc.nextLine();
            }
        }
        return input-1;
    }

    private void openSubMenu(Menu menu) {
        MenuItem[] itemArr = menu.getItems().toArray(MenuItem[]::new);
        printer.printItems(menu.getName().toUpperCase() +" MENU", itemArr);

        int input = nextInput(itemArr.length);
        if (input>=0) {
            cart.add(menu.getItems().get(input));
        }
    }

    private void printCart() {
        printer.printItems("CART", cart.toArray(MenuItem[]::new));
    }


}
