package com.intevalue.polymorphism;

import java.util.Arrays;

public class MealCreatorApp {
    public static void main(String[] args) {
        MealCreator mealCreator = new MealCreator(Arrays.asList(new BreakfastProducer(),
                new LunchProducer(), new DinnerProducer()));
        mealCreator.createMeals();
    }
}
