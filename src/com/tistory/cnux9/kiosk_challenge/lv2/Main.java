package com.tistory.cnux9.kiosk_challenge.lv2;

import com.tistory.cnux9.kiosk_challenge.lv2.data.Menu;
import com.tistory.cnux9.kiosk_challenge.lv2.data.MenuItem;

public class Main {
    public static void main(String[] args) {

        // 데이터 구조 생성
        Menu Burgers = new Menu("Burgers")
                .add(new MenuItem("JustBurger", 6.9, "무엇을 바라셨습니까? 버거는 버거입니다"))
                .add(new MenuItem("WhiteFetaBurger", 8.9, "화이트 페타 치즈가 듬뿍 들어간 신메뉴"))
                .add(new MenuItem("CheeseBurger", 6.9, "영화 더 메뉴의 그 맛 그대로인 치즈버거"))
                .add(new MenuItem("ChickenBurger", 5.4, "우리가 어떤 민족입니까? 치킨의 바삭함이 그대로인 버거"));

        Menu Drinks = new Menu("Drinks")
                .add(new MenuItem("CokeGorilla", 2.1, "살찌지 않는 것 중에 맛있는 것은 없다"))
                .add(new MenuItem("ZeroCoke", 2.0, "건강하지 않다면 맛있는 것도 먹지 못한다"))
                .add(new MenuItem("7*Cyder", 2.0, "신토불이를 사랑합시다"));

        Menu Desserts = new Menu("Desserts")
                .add(new MenuItem("CheeseCake", 9.9, "품이 많이 들기 때문에 주방장이 좋아하지 않습니다"))
                .add(new MenuItem("SaltedCaramelIceCream", 5.8, "극강의 단 맛, 극강의 짠 맛"));

        // 키오스크 인스턴스 생성/시작
        Kiosk kiosk = new Kiosk(Burgers, Drinks, Desserts);
        kiosk.start();

    }
}