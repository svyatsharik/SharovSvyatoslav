import model.*;
import  java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String TYPE_HORSE = "horse";
        String TYPE_TIGER = "tiger";

        Scanner input = new Scanner(System.in);
        Animal animal = null;

        System.out.print("Введите животное ('horse', 'tiger', 'dolphin', 'eagle' или 'camel'): ");
        String a = input.nextLine();



        if (a.equals(TYPE_HORSE)) {
            animal = new Horse("land", "herbivorous");
        } else if (a.equals(TYPE_TIGER)) {
            animal = new Tiger("land", "predator", "beef");
        } else if (a.equals("dolphin")) {
            animal = new Dolphin("waterfowl", "predator", "fish");
        } else if (a.equals("eagle")) {
            animal = new Eagle("flying", "predator", "meat");
        } else if (a.equals("camel")) {
            animal = new Camel("land", "herbivorous");
        } else {
            animal = new Animal("", "");
            System.out.println("Ошибка: неправильный ввод");
            return;
        }

        System.out.print("Что вы хотите узнать о животном '" + a + "' ('type movement' или 'type meal'): ");

        String b = input.nextLine();

        if (b.equals("type movement")) {
            System.out.println(animal.getTypeMovement());
        } else if (b.equals("type meal") && animal.getTypeMeal().equals("herbivorous")) {
            System.out.println(animal.getTypeMovement() + ", grass");
        } else if (b.equals("type meal")) {
            System.out.println(animal.getTypeMovement() + ", " + animal.getTypeMeal());
        } else {
            System.out.println("Ошибка: неправильный ввод");
            return;
        }
    }

}