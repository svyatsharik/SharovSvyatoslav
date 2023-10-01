package ru.mts.homework2;

public class Tiger extends Animal{
    private String meal;
    public Tiger(String typeMovement, String typeMeal, String meal) {
        super(typeMovement, typeMeal);
        this.meal = meal;
    }
}
