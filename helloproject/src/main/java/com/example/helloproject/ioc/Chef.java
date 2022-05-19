package com.example.helloproject.ioc;

public class Chef {
    public String cook(String menu) {
        // 요리 재료 준비
        new Pork("한돈 등심");
        Pork pork = new Pork("한돈 등심");

        // 요리 반환
        return pork.getName() + "으로 만든 " + menu;
    }
}
