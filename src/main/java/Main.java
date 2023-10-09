import main.java.ru.mts.homework.animal.Dolphin;
import main.java.ru.mts.homework.animal.Eagle;
import main.java.ru.mts.homework.animal.*;

public class Main {
    public static void main(String[] args) {
        Eagle eagle = new Eagle();
        Dolphin dolphin = new Dolphin();

        eagle.printFlyingAnimal();
        dolphin.printPredatoryAnimal();
    }
}