package helpers;

public class RandomGenerator {
    public static int randomFunction(int minNumber, int maxNumber) {
        return (int) (Math.random() * (maxNumber - minNumber) + 1);

    }

    public static int randomFunction(int range) {
        return (int) (Math.random() * range + 1);
    }
}
