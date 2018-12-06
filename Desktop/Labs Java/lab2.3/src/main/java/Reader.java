import java.util.Scanner;

public class Reader {
    private Scanner scanner;

    public Reader() {
        scanner = new Scanner(System.in);
    }

    public String readTime() throws WrongInputFormatExcetion {
        String time = scanner.nextLine();
        if (!time.contains(":") || time.length() != 5) {
            throw new WrongInputFormatExcetion("time");
        }
        return time;
    }

    public String readDestination() throws WrongInputFormatExcetion {
        String destination = scanner.nextLine();
        if (destination.contains("1") ||
                destination.contains("2") ||
                destination.contains("3") ||
                destination.contains("4") ||
                destination.contains("5") ||
                destination.contains("6") ||
                destination.contains("7") ||
                destination.contains("8") ||
                destination.contains("9") ||
                destination.contains("0")) {
            throw new WrongInputFormatExcetion("city name");
        }
        return destination;
    }

    public int readNumber() {
        boolean isError;
        int num = -1;
        do {
            try {
                isError = false;
                String input = scanner.nextLine();
                if (input.matches("[a-zA-Z]")) {
                    throw new WrongInputFormatExcetion("number");
                }
                num = Integer.valueOf(input);
            } catch (WrongInputFormatExcetion e) {
                System.out.println("Error: " + e.getMessage());
                isError = true;
            }
        } while (isError);
        return num;
    }

    public String readString() {
        return scanner.nextLine();
    }

}
