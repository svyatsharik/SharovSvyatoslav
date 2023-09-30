package model;

public class Animal {
    private String typeMeal;

    public String getTypeMovement() {
        return typeMovement;
    }

    public String getTypeMeal() {
        return typeMeal;
    }

    private String typeMovement;

    public Animal(String typeMovement, String typeMeal) {
        this.typeMovement = typeMovement;
        this.typeMeal = typeMeal;
    }

}
