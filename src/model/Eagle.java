package model;

public class Eagle extends Animal{
    private String meal;

    public Eagle(String typeMovement, String typeMeal, String meal) {
        super(typeMovement, typeMeal);
        this.meal = meal;
    }
}
