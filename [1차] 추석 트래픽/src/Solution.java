import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Solution {

    public static void main(String[] args) throws ParseException {

//        String[] input = {"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"};
        String[] input = {"2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"};
        int maxTPS = solution(input);
        System.out.println("" + maxTPS);
    }

    public static int solution(String[] lines) throws ParseException {

        int maxTPS = 0;

        ArrayList<Long> startList = new ArrayList<>();
        ArrayList<Long> endList = new ArrayList<>();

        for (int i = 0; i < lines.length; i++) {
            long[] times = getLongTimes(lines[i]);
            startList.add(times[0]);
            endList.add(times[1]);
        }

        for (int i = 0; i < startList.size(); i++) {
            int tps = 1;
            long pivot = startList.get(i);
            for (int j = i + 1; j < startList.size(); j++) {
                if (startList.get(j) - pivot <= 999)
                    tps++;
                else {
                    break;
                }
            }
            for (int j = 0; j < i; j++) {
                if (pivot < endList.get(j))
                    tps++;
            }
            if (maxTPS < tps)
                maxTPS = tps;
        }

        for (int i = 0; i < endList.size(); i++) {
            int tps = 1;
            long pivot = endList.get(i);
            for (int j = i + 1; j < startList.size(); j++) {
                if (startList.get(j) - pivot <= 999)
                    tps++;
                else {
                    break;
                }
            }
            for (int j = 0; j < i; j++) {
                if (pivot <= endList.get(j))
                    tps++;
            }
            if (maxTPS < tps)
                maxTPS = tps;
        }

        return maxTPS;

    }

    public static long[] getLongTimes(String input) throws ParseException {

        long[] results = new long[2];
        String[] splited = input.split(" ");
        String startTime = splited[0] + " " + splited[1];
        Date startDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(startTime);
        String duringStr = splited[2].replace("s", "");
        long duringTime = Math.round(Double.parseDouble(duringStr) * 1000);

        results[0] = startDate.getTime();
        results[1] = results[0] + duringTime;

        return results;

    }

}
