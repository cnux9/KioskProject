package com.tistory.cnux9.kiosk_essential.lv5;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int totalSteps = 50; // 프로그래스 바의 길이 (50칸)
        int durationMs = 2000; // 총 시간 (2초)
        int stepDuration = durationMs / totalSteps; // 한 칸당 시간

//        for (int i = 0; i <= totalSteps; i++) {
//            int percentage = (i * 100) / totalSteps;
//
//            // 프로그래스 바 생성
//            StringBuilder progressBar = new StringBuilder();
//            for (int j = 0; j < totalSteps; j++) {
//                if (j < i) {
//                    // 채워진 부분 (배경색 초록)
//                    progressBar.append("\u001B[42m \u001B[0m");
//                } else {
//                    // 빈 부분 (배경색 없음, `-` 문자)
//                    progressBar.append("-");
//                }
//            }
//
//            // 진행률 출력
//            System.out.printf("\r[%s] %3d%%", progressBar, percentage);
//
//            Thread.sleep(stepDuration); // 다음 단계까지 대기
//        }

        System.out.println();

        System.out.println("\u001B[34m  파란 글자   \u001B[0m"); // 파란색
        System.out.println("\u001B[40m  배경 빨강   \u001B[0m"); // 배경색 빨강
        System.out.println("\u001B[2J\u001B[H"); // 화면 클리어

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