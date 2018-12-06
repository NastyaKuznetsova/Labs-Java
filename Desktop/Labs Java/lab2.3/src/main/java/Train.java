import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Train {
    private String destination;
    private int trainNumber;
    private Time time;
    private int amountCommon;
    private int amountPlackart;
    private int amountSV;
    private int amountKupe;
    private DateFormat formatter = new SimpleDateFormat("HH:mm");

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getAmountCommon() {
        return amountCommon;
    }

    public void setAmountCommon(int amountCommon) {
        this.amountCommon = amountCommon;
    }

    public int getAmountPlackart() {
        return amountPlackart;
    }

    public void setAmountPlackart(int amountPlackart) {
        this.amountPlackart = amountPlackart;
    }

    public int getAmountSV() {
        return amountSV;
    }

    public void setAmountSV(int amountSV) {
        this.amountSV = amountSV;
    }

    public int getAmountKupe() {
        return amountKupe;
    }

    public void setAmountKupe(int amountKupe) {
        this.amountKupe = amountKupe;
    }

    public Train(String destination, int trainNumber, Time time, int amountCommon, int amountPlackart, int amountSV, int amountKupe) {
        this.destination = destination;
        this.trainNumber = trainNumber;
        this.time = time;
        this.amountCommon = amountCommon;
        this.amountPlackart = amountPlackart;
        this.amountSV = amountSV;
        this.amountKupe = amountKupe;
    }
    public Train(){}

    @Override
    public String toString() {


        return "destination='" + destination + '\'' +
                ", trainNumber=" + trainNumber +
                ", time=" + formatter.format(time)+
                ", amountCommon=" + amountCommon +
                ", amountPlackart=" + amountPlackart +
                ", amountSV=" + amountSV +
                ", amountKupe=" + amountKupe +
                '}';
    }
}
