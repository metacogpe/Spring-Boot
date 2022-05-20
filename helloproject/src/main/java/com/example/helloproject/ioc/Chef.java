package com.example.helloproject.ioc;

import org.springframework.stereotype.Component;

@Component
public class Chef {
    // 셰프는 식재료 공장을 알고 있음
    private IngredientFactory ingredientFactory;

    // 세프가 식재료 공장과 협업하기 위한 DI
    public Chef(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;  // 초기화
    }
    public String cook(String menu) {
        // 재료 준비 : new 로 만들지 않고 DI 적용
        Ingredient ingredient = ingredientFactory.get(menu); // 메뉴 입력시 공장에서 알아서 반환

        // 요리 반환
//        return pork.getName() + "으로 만든 " + menu;
        return ingredient.getName() + "으로 만든 " + menu;
    }
}
