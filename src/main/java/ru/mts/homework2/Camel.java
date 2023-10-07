package ru.mts.homework2;

public class Camel implements LandAnimal, HerbivorousAnimal {
    @Override
    public void printHerbivorousAnimal() {
        System.out.println("Верблюд - травоядный");
    }

    @Override
    public void printLangAnimal() {
        System.out.println("Верблюд - сухопутный");
    }
}
