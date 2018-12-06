public class WrongInputFormatExcetion extends Throwable {
    public WrongInputFormatExcetion(String expectedFormat) {
        super("Input format is wrong! Expected: " + expectedFormat);
    }
}
