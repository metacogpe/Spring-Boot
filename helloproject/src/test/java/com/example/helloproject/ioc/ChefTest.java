package com.example.helloproject.ioc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChefTest {

    @Test
    void 돈가스_요리하기() {
        // 준비
        // 재료 공장으로부터 재료를 조달받기
        IngredientFactory ingredientFactory = new IngredientFactory();
        Chef chef = new Chef(ingredientFactory); // chef가 재료 공장에 대한 정보를 DI(동작에 필요한 객체를 외부에서 받아옴)
        String menu = "돈가스";

        // 수행
        String food = chef.cook(menu);

        // 예상
        String expected = "한돈 등심으로 만든 돈가스";

        // 검증
        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void 스테이크_요리하기() {
        // 준비
//        Chef chef = new Chef();
        // 위의 코드를 외부에서 식재료를 주입하는 DI로 변경
        IngredientFactory ingredientFactory = new IngredientFactory();
        Chef chef = new Chef(ingredientFactory);
        String menu = "스테이크";

        // 수행
        String food = chef.cook(menu);

        // 예상
        String expected = "한우 꽃등심으로 만든 스테이크";

        // 검증
        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void 크리스피_치킨_요리하기() {
        // 준비
        IngredientFactory ingredientFactory = new IngredientFactory();  // 공장을 만들고,
        Chef chef = new Chef(ingredientFactory);                        // 세프에게 재료 공급
        String menu = "크리스피 치킨";

        // 수행
        String food = chef.cook(menu);

        // 예상
        String expected = "국내산 10호 닭으로 만든 크리스피 치킨";

        // 검증
        assertEquals(expected, food);
        System.out.println(food);

    }

}