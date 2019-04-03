import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    public static void main(String[] args) {

        String [] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        int cacheSize = 3;

        int time = solution(cacheSize, cities);
        System.out.println("" + time);
    }

    public static int solution(int cacheSize, String[] cities) {

        int answer = 0;

        Queue<String> citiesQueue = new LinkedList<>();

        if(cacheSize == 0) {
            return cities.length * 5;
        }

        for (int i = 0; i < cities.length; i++) {
            cities[i] = cities[i].toUpperCase();
//            System.out.println("word : " + cities[i]);
            boolean contains = citiesQueue.contains(cities[i]);
            if (contains) {
                citiesQueue.remove(cities[i]);
                citiesQueue.add(cities[i]);
                answer += 1;
            } else {
                if(citiesQueue.size() >= cacheSize) {
                    citiesQueue.poll();
                }
                citiesQueue.add(cities[i]);
                answer += 5;
            }
//            System.out.println(citiesQueue.toString());
        }

        return answer;
    }

}
