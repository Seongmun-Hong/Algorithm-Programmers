public class Solution {

    public String[] solution(int n, int[] arr1, int[] arr2) {

        String[] answer = {};

        boolean [][] arr1Map = new boolean [n][n];
        boolean [][] arr2Map = new boolean [n][n];

        for (int i = 0; i < n; i++) {
            int arr1Quotient = arr1[i];
            int arr2Quotient = arr2[i];
            for (int j = n-1; j >= 0; j--) {
                int arr1Remainder = arr1Quotient % 2;
                int arr2Remainder = arr2Quotient % 2;
                arr1Map[i][j] = arr1Remainder != 0;
                arr2Map[i][j] = arr2Remainder != 0;
                arr1Quotient /= 2;
                arr2Quotient /= 2;
            }
        }

        answer = getMap(arr1Map, arr2Map, n);

        return answer;
    }

    public String[] getMap(boolean[][] arr1Map, boolean[][] arr2Map, int n) {
        String [] result = new String [n];
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (arr1Map[i][j] || arr2Map[i][j]) {
                    sb.append("#");
                } else {
                    sb.append(" ");
                }
            }
            result[i] = sb.toString();
        }
        return result;
    }

}
