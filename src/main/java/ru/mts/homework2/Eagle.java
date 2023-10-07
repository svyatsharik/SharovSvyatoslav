package ru.mts.homework2;

public class Eagle implements FlyingAnimal, PredatoryAnimal {
    @Override
    public void printFlyingAnimal() {
        System.out.println("Орёл - летающий");
    }

    @Override
    public void printPredatoryAnimal() {
        System.out.println("Орёл - хищник");
    }
}
