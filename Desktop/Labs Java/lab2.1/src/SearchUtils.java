import java.sql.Time;

public class SearchUtils {
    int amount;
    public SearchUtils(int amount){
        this.amount = amount;
    }
    public Train[] findTrainsWithCommon(Train[] trains) throws NoTrainWithCommonPlacesException {
        Train [] preresult = new Train[amount];
        int i =0;
        for (Train train:trains
             ) {
            if(train.getAmountCommon()>0){
                preresult[i] =train;
                i++;
            }
        }
        if (i==0) throw new NoTrainWithCommonPlacesException("No trains with common places");

         else {
            Train result [] = new Train[i];
            for (int j = 0; j < i; j++) {
                result[j] = preresult[j];
            }
            return result;
        }
    }


    public Train[] findTrainsByDestinationAndTime(Train [] trains, String destination, String stime) throws NoMatchesException{
        String[] temp = stime.split(":");
        Time time = new Time(Integer.parseInt(temp[0])*60*60*1000+Integer.parseInt(temp[1])*60*1000);
        Train [] preresult = new Train[amount];
        int i = 0;
        for (Train train:trains
             ) {
            if(train.getDestination().equals(destination) && train.getTime().after(time)){
                preresult[i] = train;
                i++;
            }
        }

        if (i==0) throw new NoMatchesException("No matches");

         else {
            Train result [] = new Train[i];
            for (int j = 0; j < i; j++) {
                result[j] = preresult[j];
            }
            return result;
        }
    }
}
