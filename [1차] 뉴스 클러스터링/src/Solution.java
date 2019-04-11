import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class Solution {

    public int solution(String str1, String str2) {

        int result = 0;

        Map<String, Integer> str1Map = new HashMap<>();
        Map<String, Integer> str2Map = new HashMap<>();
        HashSet union = new HashSet();

        for (int i = 0; i < str1.length() - 1; i++) {
            String subStr = str1.substring(i, i + 2);
            subStr = subStr.toUpperCase();
            if (isAlpha(subStr)) {
                Integer num = str1Map.get(subStr);
                union.add(subStr);
                if (num == null) {
                    num = 0;
                }
                str1Map.put(subStr, num + 1);
            }
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            String subStr = str2.substring(i, i + 2);
            subStr = subStr.toUpperCase();
            if (isAlpha(subStr)) {
                Integer num = str2Map.get(subStr);
                union.add(subStr);
                if (num == null) {
                    num = 0;
                }
                str2Map.put(subStr, num + 1);
            }
        }

        Iterator it = union.iterator();
        int uniCount = 0;
        int intersection = 0;

        if(union.size() == 0)
            return 65536;

        while (it.hasNext()) {
            String subStr = (String) it.next();
            Integer str1Count = str1Map.get(subStr);
            Integer str2Count = str2Map.get(subStr);

            if (str1Count == null) {
                str1Count = 0;
            }
            if (str2Count == null) {
                str2Count = 0;
            }

            uniCount += Integer.max(str1Count, str2Count);
            intersection += Integer.min(str1Count, str2Count);
        }

        if(intersection == 0)
            return 0;

        Double tempResult = (double) intersection / uniCount * 65536;
        result = tempResult.intValue();
        return result == 0 ? 1 : result;

    }

    public boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }

}
