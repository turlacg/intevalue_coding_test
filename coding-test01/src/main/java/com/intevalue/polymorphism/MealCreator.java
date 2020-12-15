package com.intevalue.polymorphism;

import java.util.List;

public class MealCreator {
    private List<MealProducer> mealProducer;
    public MealCreator(List<MealProducer> mealProducer) {
        this.mealProducer = mealProducer;
    }
    public void createMeals() {
        mealProducer.forEach(meal -> meal.produceMeal());
    }
}
