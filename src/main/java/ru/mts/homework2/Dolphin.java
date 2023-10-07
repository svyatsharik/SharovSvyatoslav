package ru.mts.homework2;

public class Dolphin implements WaterAnimal, PredatoryAnimal {
    @Override
    public void printPredatoryAnimal() {
        System.out.println("Дельфин - хищник");
    }

    @Override
    public void printWaterAnimal() {
        System.out.println("Дельфин - водоплавающий (ест только рыбу)");
    }
}
