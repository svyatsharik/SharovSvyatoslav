import model.*;
import  java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Animal animal = null;

        System.out.print("Введите животное ('horse', 'tiger', 'dolphin', 'eagle' или 'camel'): ");
        String a = input.nextLine();



        if (a.equals("horse")) {
            animal = new Horse("land", "herbivorous");
        } else if (a.equals("tiger")) {
            animal = new Tiger("land", "predator", "beef");
        } else if (a == "dolphin") {
            animal = new Dolphin("waterfowl", "predator", "fish");
        } else if (a == "eagle") {
            animal = new Eagle("flying", "predator", "meat");
        } else if (a == "camel") {
            animal = new Camel("land", "herbivorous");
        } else {
            animal = new Animal("", "");
            System.out.println("Ошибка: неправильный ввод");
            return;
        }

        System.out.print("Что вы хотите узнать о животном '" + a + "' ('type movement' или 'type meal'): ");

        String b = input.nextLine();

        if (b == "type movement") {
            System.out.println(animal.getTypeMovement());
        } else if (b == "type meal" && animal.typeMeal == "herbivorous") {
            System.out.println(animal.getTypeMovement() + ", grass");
        } else if (b == "type meal") {
            System.out.println(animal.getTypeMovement() + ", " + animal.typeMeal);
        } else {
            System.out.println("Ошибка: неправильный ввод");
            return;
        }
    }
}