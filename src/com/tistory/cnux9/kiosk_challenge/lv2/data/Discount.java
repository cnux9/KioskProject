package com.tistory.cnux9.kiosk_challenge.lv2.data;

public enum Discount {
    NATIONAL_HERO("국가유공자", 10),
    SOLDIER("군인", 5),
    STUDENT("학생", 3),
    GENERAL("일반", 0);

    public final String name;
    public final int percentage;

    Discount(String name, int percentage) {
        this.name = name;
        this.percentage = percentage;
    }

}
