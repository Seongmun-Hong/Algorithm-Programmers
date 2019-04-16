import java.util.*;

public class Solution {

    public static void main(String[] args) {
        int n = 2;
        int t = 10;
        int m = 5;
        String []timetable = {"09:10", "09:10", "09:10", "09:09", "09:01"};
        System.out.println(solution(n, t, m, timetable));
    }

    public static String solution(int n, int t, int m, String[] timetable) {

        ArrayList<Integer> intTimeTable = stringToInt(timetable);

        Queue<Integer> busQueue = new LinkedList<>();
        int suttleTime = (9 * 60);
        int lastTime = 0;
        String result = "";
        boolean checker = false;

        for (int i = 0; i < n; i++) {
            if(checker) {
                break;
            }
            for (int j = 0; j < m; j++) {
                if (i  == n - 1) {
                    if(intTimeTable.isEmpty()) {
                        checker = true;
                        break;
                    }
                    if(intTimeTable.get(0) <= suttleTime) {
                        Integer temp = intTimeTable.remove(0);
                        busQueue.add(temp);
                        lastTime = temp - 1;
                    } else {
                        lastTime = suttleTime;
                        break;
                    }
                } else {
                    if (intTimeTable.get(0) <= suttleTime) {
                        Integer temp = intTimeTable.remove(0);
                    } else if(intTimeTable.isEmpty()) {
                        checker = true;
                        break;
                    }
                }
            }
            suttleTime += t;
        }

        if (checker) {
            suttleTime -= t;
            result = toResult(suttleTime);
            return result;
        }

        result = toResult(lastTime);
        return result;
    }

    public static ArrayList<Integer> stringToInt(String[] stringTimetable) {

        Integer[] intTimeTable = new Integer [stringTimetable.length];

        for (int i = 0; i < stringTimetable.length; i++) {
            String[] times = stringTimetable[i].split(":");
            intTimeTable[i] = (Integer.parseInt(times[0]) * 60) + Integer.parseInt(times[1]);
        }

        Arrays.sort(intTimeTable);

        ArrayList<Integer> result = new ArrayList<Integer>(Arrays.asList(intTimeTable));

        return result;

    }

    public static String toResult(int num) {

        String result = "";

        if(num < 0) {
            num = 24 * 60 + num;
        }

        int hour = num / 60;
        int min = num % 60;

        if(hour < 10)
            result = "0" + hour + ":";
        else
            result = hour + ":";
        if(min == 0)
            result += "00";
        else if(min < 10)
            result += "0" + min;
        else
            result += min;

        return result;
    }

}
