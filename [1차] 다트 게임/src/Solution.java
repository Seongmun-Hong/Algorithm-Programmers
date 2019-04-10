public class Solution {

//    public static void main(String[] args) {
//
//        String input = "1D#2S*3S";
//        int result = solution(input);
//        System.out.println(result + "");
//    }

    public int solution(String dartResult) {

        Dart[] darts = split(dartResult);
        int result = 0;

        for (int i = 0; i < 3; i++) {
            if(i != 0) {
               darts[i].setScore(darts[i - 1]);
            } else {
                darts[i].setScore(null);
            }
        }

        for (int i = 0; i < 3; i++) {
            result += darts[i].getScore();
        }

        return result;

    }

    public Dart[] split(String input) {

        Dart[] darts = new Dart[3];

        int num = 0;
        StringBuilder option = new StringBuilder();
        int numStartIdx = 0;
        int dartsIdx = 0;
        for (int i = 0; i < input.length(); i++) {

            if (input.charAt(i) == 'S' || input.charAt(i) == 'D' || input.charAt(i) == 'T') {
                String numStr = input.substring(numStartIdx, i);
                num = Integer.parseInt(numStr);
                for (int j = i + 1; j < input.length(); j++) {
                    StringBuilder sb = new StringBuilder();
                    if (input.charAt(j) == '#' || input.charAt(j) == '*') {
                        option.append(input.charAt(j));
                    } else {
                        break;
                    }
                }

                switch (input.charAt(i)) {
                    case 'S':
                        darts[dartsIdx] = new Dart(num, 1, option.toString());
                        break;
                    case 'D':
                        darts[dartsIdx] = new Dart(num, 2, option.toString());
                        break;
                    case 'T':
                        darts[dartsIdx] = new Dart(num, 3, option.toString());
                        break;
                }
                dartsIdx++;
                numStartIdx = i + option.toString().length() + 1;
                option = new StringBuilder();
            }

        }
        return darts;
    }

}

class Dart {
    int num;
    int power;
    String option;
    int score;

    public Dart(int num, int power, String option) {
        this.num = num;
        this.power = power;
        this.option = option;
    }

    public int setScore(Dart pre) {
        int score = (int)Math.pow(num, power);
        for (int i = 0; i < option.length(); i++) {
            switch (option.charAt(i)) {
                case '*':
                    score *= 2;
                    if(pre != null) {
                        pre.doubleScore();
                    }
                    break;
                case '#':
                    score *= -1;
                    break;
            }
        }
        this.score = score;
        return score;
    }

    public void doubleScore() {
        this.score *= 2;
    }

    public int getScore() {
        return score;
    }
}
