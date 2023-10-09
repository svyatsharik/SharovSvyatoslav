package main.java.ru.mts.homework.animal;

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
