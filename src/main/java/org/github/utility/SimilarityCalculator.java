package org.github.utility;

// 유사도 계산 클래스
public class SimilarityCalculator {

    public static int levenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
                }
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public static double similarity(String s1, String s2) {
        int maxLength = Math.max(s1.length(), s2.length());
        if (maxLength == 0) return 1.0;

        int distance = levenshteinDistance(s1, s2);
        return 1.0 - ((double) distance / maxLength);
    }

    public static boolean containsOrContained(String selectedTaskName, String theWord) {
        // selectedTaskName이 theWord를 포함하거나 theWord가 selectedTaskName을 포함하면 true 반환
        return selectedTaskName.contains(theWord) || theWord.contains(selectedTaskName);
    }

    public static boolean isSimilar(String selectedTaskName, String theWord) {
        double threshold = 0.75;  // 유사도 기준
        return similarity(selectedTaskName, theWord) >= threshold;
    }
}