import java.sql.Time;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Train[] trains;
    private static SearchUtils searchUtil;

    public static void main(String [] args){
        String destination = "";
        String time = "";
        Reader reader = new Reader();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press 1 for manual entering. Press 2 for automatic insertion");
        if (scanner.nextInt()==1){
            manualEnter();
        } else {
            automaticEnter();
        }

        printTrains(trains);
        scanner.nextLine();
        System.out.println("Search of trains with common places");

        try {
            printTrains(searchUtil.findTrainsWithCommon(trains));
        }catch (NoTrainWithCommonPlacesException exc) {
            System.out.println(exc.getMessage());
        }
            System.out.println("-------------------------");

        boolean isError;
        do {
            try {
                System.out.println("Enter the train's destination");
                isError = false;
                time = reader.readDestination();
            } catch (WrongInputFormatExcetion wrongInputFormatExcetion) {
                System.out.println(wrongInputFormatExcetion.getMessage());
                isError  = true;
            }
        } while (isError);
        do {
            try {
                System.out.println("Enter the minimum time");
                isError = false;
                time = reader.readTime();
            } catch (WrongInputFormatExcetion wrongInputFormatExcetion) {
                System.out.println(wrongInputFormatExcetion.getMessage());
                isError  = true;
            }
        } while (isError);
        try {
            printTrains(searchUtil.findTrainsByDestinationAndTime(trains, destination, time));
        }catch (NoMatchesException exc){
            System.out.println(exc.getMessage());
        }
        System.out.println("-------------------------");
    }

    private static void printTrains(Train[] trains) {
        int i = 1;
        if (trains!=null){
            for (Train train:trains) {
                System.out.format("%2d",i);
                i++;
                for (String s:train.toString().split(",")) {
                    System.out.format("|    " + "%30s",s + "    |");
                }
                System.out.println();
            }
        }
    }


    private static void automaticEnter() {
        int trainsAmount = 10;
        searchUtil = new SearchUtils(trainsAmount);
        trains = new Train[trainsAmount];
        Random random = new Random();

        String[] destination = {"Kyiv","Lviv","Kharkiv", "Dnepr", "Rivne"};

        for (int i = 0; i < trainsAmount; i++) {
            trains[i] = new Train();
            trains[i].setDestination(destination[random.nextInt(5)]);
            trains[i].setTrainNumber(random.nextInt(1000));
            trains[i].setAmountSV(random.nextInt(100));
            trains[i].setAmountKupe(random.nextInt(240));
            trains[i].setAmountPlackart(random.nextInt(600));
            trains[i].setAmountCommon(random.nextInt(1000));
            trains[i].setTime(new Time(random.nextInt(24*60*60*1000)));
        }
    }

    private static void manualEnter() {
        Scanner scanner = new Scanner(System.in);
        Reader reader = new Reader();
        System.out.println("Enter amount of trains");
        int amount = reader.readNumber();
        trains = new Train[amount];
        searchUtil = new SearchUtils(amount);

        for (int i = 0; i < amount; i++) {
            trains[i] = new Train();
            System.out.println("You are entering values for train " + (i + 1));

            System.out.println("Enter destination");
            trains[i].setDestination(reader.readString());

            System.out.println("Enter train number");
            trains[i].setTrainNumber(reader.readNumber());

            System.out.println("Enter amount of common places");
            trains[i].setAmountCommon(reader.readNumber());

            System.out.println("Enter amount of plackart places");
            trains[i].setAmountPlackart(reader.readNumber());

            System.out.println("Enter amount of kupe places");
            trains[i].setAmountKupe(reader.readNumber());

            System.out.println("Enter amount of SV places");
            trains[i].setAmountSV(reader.readNumber());

            System.out.println("Enter time in HH:MM format");
            String[] temp = scanner.nextLine().split(":");
            trains[i].setTime(new Time(Integer.parseInt(temp[0])*60*60*1000+Integer.parseInt(temp[1])*60*1000));
        }
    }
}