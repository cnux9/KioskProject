package com.tistory.cnux9.kiosk_challenge.lv1;

public class Main {
    public static void main(String[] args) {
        System.out.println("\u001B[40m  배경 빨강   \u001B[0m"); // 배경색

        // 데이터 구조 생성
        Menu Burgers = new Menu("Burgers")
                .add(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"))
                .add(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"))
                .add(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"))
                .add(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));

        Menu Drinks = new Menu("Drinks")
                .add(new MenuItem("Coke", 2.1, "살찌지 않는 것 중에 맛있는 것은 없다"));

        Menu Desserts = new Menu("Desserts")
                .add(new MenuItem("CheeseCake", 9.9, "살찌지 않는 것 중에 맛있는 것은 없다"));

        // 키오스크 인스턴스 생성/시작
        Kiosk kiosk = new Kiosk(Burgers, Drinks, Desserts);
        kiosk.start();

    }
}