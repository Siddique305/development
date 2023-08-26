import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TimeCompare {
    public static void main(String[] args) {
        String timeStr1 = "1h 3min 2sec";
        String timeStr2 = "1:02:37";
        
        List<Integer> timeDifference = compareTime(timeStr1, timeStr2);
        

        if (timeDifference==null) {
            System.out.println("Time is same.");
        } else {
            System.out.println("Time is different. Time Difference: "+ timeDifference);
        }
    }

    public static List<Integer> compareTime(String timeStr1, String timeStr2) {
        int[] time1 = extractTime(timeStr1);
        int[] time2 = extractTime(timeStr2);
        
        if(time1[0]==time2[0] && time1[1]==time2[1] && time1[2]==time2[2]) {
        	return null;
        }
        else {
        	return getTimeDifference(time1, time2);
        }

        
    }
    
    public static int[] extractTime(String timeStr) {
        if (timeStr.contains("h")) {
            String[] time = timeStr.split(" ");
            int hours = Integer.parseInt(time[0].replace("h",""));
            int minutes = Integer.parseInt(time[1].replace("min", ""));
            int seconds = Integer.parseInt(time[2].replace("sec", ""));
            return  new int[]{hours, minutes, seconds};
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm:ss");
            LocalTime localTime = LocalTime.parse(timeStr, formatter);
            int hours= localTime.getHour();
            int minutes =  localTime.getMinute();
            int seconds = localTime.getSecond();
            
           return new int[]{hours,minutes,seconds};
        }
    }
    
    public static List<Integer> getTimeDifference(int[] time1, int[] time2){
    	int totalSecDifference = Math.abs((time2[0]*3600 + time2[1]*60 + time2[2]) -
    									(time1[0]*3600 + time1[1]*60 + time1[2]));
    	int hours = totalSecDifference/3600;
    	int minutes = (totalSecDifference % 3600) / 60;
        int seconds = totalSecDifference % 60;

        return List.of(hours, minutes, seconds);
    	
    }
}
