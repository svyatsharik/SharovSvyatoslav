package ru.mts.homework2;

public class Tiger implements LandAnimal, PredatoryAnimal {
    @Override
    public void printLangAnimal() {
        System.out.println("Тигр - сухопутный");
    }

    @Override
    public void printPredatoryAnimal() {
        System.out.println("Тигр - хищник");
    }
}
