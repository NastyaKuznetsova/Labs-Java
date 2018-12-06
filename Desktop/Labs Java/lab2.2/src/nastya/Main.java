package nastya;

public class Main {

    public static void main(String[] args) {
        Reader reader = new Reader();
        Printer printer = new Printer();
        printer.print("Write path for java file in format: './path/to/file/*.java'");
        String path = reader.readString();
        FileParser parser = new FileParser(path);
        boolean doWork = true;
        do {
            int choice;
            printer.print("1. Parse file");
            printer.print("2. Exit");
            printer.print("Type operation number: ");
            choice = reader.readNumber();
            switch (choice) {
                case 1:
                    parser.parseFile();
                    break;
                case 2:
                default:
                    doWork = false;
                    break;
            }
        } while (doWork);
    }
}
