package main.java.ru.mts.homework.animal;

public class Horse implements LandAnimal, HerbivorousAnimal {
    @Override
    public void printHerbivorousAnimal() {
        System.out.println("Лошадь - травоядная");
    }

    @Override
    public void printLangAnimal() {
        System.out.println("Лошадь - сухопутная");
    }
}
