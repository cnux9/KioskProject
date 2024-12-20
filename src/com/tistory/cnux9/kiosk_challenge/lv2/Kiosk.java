package com.tistory.cnux9.kiosk_challenge.lv2;

import com.tistory.cnux9.kiosk_challenge.lv2.data.Discount;
import com.tistory.cnux9.kiosk_challenge.lv2.data.Menu;
import com.tistory.cnux9.kiosk_challenge.lv2.data.MenuItem;
import com.tistory.cnux9.kiosk_challenge.lv2.view.Printer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.tistory.cnux9.kiosk_challenge.lv1.Format.*;

public class Kiosk {
    Scanner sc = new Scanner(System.in);
    Menu[] menuArr;
    Menu cart = new Menu("Cart");
    private int start;

    // Menu 배열을 매개변수로 받아 인스턴스 생성
    public Kiosk(Menu... menuArr) {
        this.menuArr = menuArr;
    }

    // 메인 키오스크 루프
    public void start() {
        while (openMainMenu()) {
            // 메인 메뉴가 false를 리턴할 때까지 반복
        }
        System.out.println(green("프로그램을 종료합니다."));
    }

    // 메인 메뉴 카테고리 화면 + 장바구니가 있다면 주문/조회 기능
    private boolean openMainMenu() {
        // 메인 메뉴 출력
        Printer.printMenus(menuArr);

        if (!cart.isEmpty()) {
            // 오더 출력
            Printer.printOrders(menuArr.length + 1);
            // 카트 출력
            Printer.printCart(cart);
        }
        System.out.println(blue("0. ") + "exit      " + magenta("|") + " exit");

        // 입력 처리
        int input = nextInput(menuArr.length + (!cart.isEmpty() ? 2 : 0));
        if (input == -1) {
            return false;
        } else if (input == menuArr.length) {
            // 주문하기
            openOrderScreen();
        } else if (input == menuArr.length + 1) {
            // 취소하기
            cart = new Menu("Cart");
        } else {
            // 카테고리의 메뉴들을 보여주는 화면
            openSubMenu(menuArr[input]);
        }
        return true;
    }

    // 장바구니 주문 화면
    private void openOrderScreen() {
        Printer.printCart(cart);
        new Printer("Total", 0).print();

        double total = cart.getItems().stream().mapToDouble(i -> i.getPrice() * i.getCount()).sum();
        System.out.println("￦ " + total);
        System.out.println(blue("1. ") + "구매 " + blue("2. ") + "제거 " + blue("0. ") + "취소");

        int input = nextInput(2);  // 0 or -1
        if (input == 0) {
            openDiscountScreen(total);
        } else if (input == 1) {
            openDeleteScreen();
        }
    }

    // 장바구니 항목 제거 화면
    private void openDeleteScreen() {
        Printer.printItems(cart);
        int input = nextInput(cart.size());
        if (input >= 0) {
            ArrayList<MenuItem> items = cart.getItems();
            // 도전 lv2 요구사항: 장바구니에서 항목을 제거할 때 스트림을 활용
            cart.setItems(items.stream().filter(item -> !item.equals(items.get(input))).collect(Collectors.toCollection(ArrayList::new)));
        }
    }

    // 할인 혜택을 선택하는 화면
    private void openDiscountScreen(double total) {
        System.out.println(green("할인 정보를 입력해주세요."));
        String[][] data = Arrays.stream(Discount.values()).map(d -> new String[]{d.name, blue(Integer.toString(d.percentage)) + " %"}).toArray(String[][]::new);
        new Printer(4)
                .appendNumber(1)
                .appendData(data)
                .print();
        int input = nextInput(Discount.values().length);
        int percentage = switch (input) {
            case 0 -> Discount.NATIONAL_HERO.percentage;
            case 1 -> Discount.SOLDIER.percentage;
            case 2 -> Discount.STUDENT.percentage;
            case 3 -> Discount.GENERAL.percentage;
            default -> {
                throw new IllegalStateException("Unexpected value: " + input);
            }
        };
        cart = new Menu("Cart");
        System.out.println(green("주문이 완료되었습니다!! 금액은 " + total * (100 - percentage) / 100) + green("원 입니다."));
    }

    // 다음 정수 입력 + 유효성 검사
    private int nextInput(int end) {
        int input = 0;
        while (true) {
            System.out.print("0-" + end + green(" 사이의 수를 입력해주세요: "));
            try {
                input = sc.nextInt();

                if (0 <= input && input <= end) {
                    break;
                }
            } catch (Exception e) {
                System.out.println(green("숫자를 입력해주세요."));
            } finally {
                sc.nextLine();
            }
        }
        return input - 1;
    }

    // 카테고리별 하위 메뉴 선택 화면 처리
    private void openSubMenu(Menu menu) {
        Printer.printItems(menu);

        int input = nextInput(menu.size());
        // input 값이 -1이면 반환
        if (input >= 0) {
            // input 값에 해당하는 메뉴 선택 확인
            openOrderCheck(menu.getItems().get(input));
        }
    }

    // 주문 확인 화면
    private void openOrderCheck(MenuItem item) {
        Menu menu = new Menu("SELECTED").add(item);
        Printer.printItems(menu);
        System.out.println(blue("1. ") + "장바구니에 넣기 " + blue("2. ") + "취소");
        int input = nextInput(1); // 0 or -1
        if (input == 0) {
            if (cart.contains(item)) {
                item.incrementCount();
            } else {
                cart.add(item);
            }
            System.out.println(green(item.getName() + "이(가) 추가되었습니다!!"));
        }
    }

}
