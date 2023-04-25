package Task_1;

public class WordLengthCharacteristicPrinter {
    public static void print(WordLengthCharacteristic wordLengthCharacteristic)
    {
        System.out.printf("Mean: %.2f%n", wordLengthCharacteristic.getMean());
        System.out.printf("Variance: %.2f%n", wordLengthCharacteristic.getVariance());
        System.out.printf("Standard deviation: %.2f%n", wordLengthCharacteristic.getStandardDeviation());
    }
}
