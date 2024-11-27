package com.tistory.cnux9.kiosk_challenge.lv1;

import java.util.Scanner;
import static com.tistory.cnux9.kiosk_challenge.lv1.Format.*;

public class Kiosk {
    Scanner sc = new Scanner(System.in);
    Menu[] menuArr;
    Menu cart = new Menu("Cart");
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
            printer.printItems("CART", cart.getItems().toArray(MenuItem[]::new));
        }
        System.out.println(blue("0. ") + "exit      " + magenta("|") + " exit");

        int input = nextInput(menuArr.length + (!cart.isEmpty() ? 2 : 0));
        if (input==-1) {
            return false;
        } else if (input==menuArr.length) {
            System.out.println("주문하기");
            openOrderScreen();
        } else if (input==menuArr.length+1) {
            System.out.println("취소하기");
        } else {
            openSubMenu(menuArr[input]);
        }
        return true;
    }

    private void openOrderScreen() {
        printCart();
        printer.printTitle("Total");
        double total = cart.getItems().stream().mapToDouble(MenuItem::getPrice).sum();
        System.out.println("￦ " + total);
        System.out.println("위의 상품들을 구매하시려면 1번, 뒤로 돌아가시려면 0번을 입력햇주세요:");
        int input = nextInput(1);  // 0 or -1
        if (input==0) {
            System.out.println("주문이 완료되었습니다!! 금액은 " + total + "원 입니다.");
        }
    }

    private void printOrderMenu() {
        String[][] data = {
            {"Orders", "장바구니를 확인 후 주문합니다."},
            {"Cancel", "진행중인 주문을 취소합니다."}
        };
        printer.printTitle("ORDER");
        printer.printFormattedData(data, menuArr.length);
    }

    private int nextInput(int end) {
        int input = 0;
        while (true) {
            try {
                input = sc.nextInt();

                if(0 <= input && input <= end) {
                    break;
                } else {
                    System.out.println("0-" + end + " 사이의 수를 입력해주세요.");
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
            openOrderCheck(menu.getItems().get(input));
        }
    }

    private void openOrderCheck(MenuItem item) {
        printer.printItems("선택하신 메뉴", new MenuItem[]{item});
        System.out.println(Format.green("장바구니에 넣으시려면 1번, 취소하시려면 0번을 입력해주세요."));
        int input = nextInput(1); // 0 or -1
        if (input==0) {
            cart.add(item);
            System.out.println(item.getName() + "이(가) 추가되었습니다!!");
        }
    }

    private void printCart() {
        printer.printItems("CART", cart.getItems().toArray(MenuItem[]::new));
    }


}
