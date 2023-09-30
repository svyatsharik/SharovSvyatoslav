package model;

public class Dolphin extends Animal{
    private String meal;

    public Dolphin(String typeMovement, String typeMeal, String meal) {
        super(typeMovement, typeMeal);
        this.meal = meal;
    }
}
