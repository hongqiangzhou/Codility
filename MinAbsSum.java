// This solution follows `https://codility.com/media/train/solution-min-abs-sum.pdf`
// Simple solution. Score: 63%, complexity: O(N**2*max(abs(A))), a lot of timeout exceptions.
class Solution {
    public int solution(int[] A) {
        // Implement your solution here
        int sumA = 0;
        for (int i = 0; i < A.length; i++) {
            A[i] = Math.abs(A[i]);
            sumA += A[i];
        }

        int[] dp = new int[sumA + 1];
        dp[0] = 1;
        for (int a: A) {
            for (int i = sumA; i >= 0; i--) {
                if (dp[i] == 1 && i + a < sumA) {
                    dp[i+a] = 1;
                }
            }
        }

        int result = sumA;
        for (int i = 0; i <= sumA/2; i++) {
            if (dp[i] == 1) {
                result = Math.min(result, sumA - 2 * i);
            }
        }


        return result;
    }
}

// Improved solution. Score: 100%.
import java.util.*;

class Solution {
    public int solution(int[] A) {
        // Implement your solution here
        if (A.length == 0) return 0;
        if (A.length == 1) return A[0];
        
        int maxA = 0;
        int sumA = 0;
        for (int i = 0; i < A.length; i++) {
            A[i] = Math.abs(A[i]);
            maxA = Math.max(maxA, A[i]);
            sumA += A[i];
        }

        int[] count = new int[maxA + 1];
        for (int a: A) {
            count[a] += 1;
        }

        int[] dp = new int[sumA + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int i = 0; i <= maxA; i++) {
            if (count[i] > 0) {
                for (int val = 0; val <= sumA; val++) {
                    if(dp[val] >= 0) {
                        dp[val] = count[i];
                    } else if (val >= i && dp[val-i] > 0) {
                        dp[val] = dp[val-i] - 1;
                    }
                }
            }
        }

        int result = sumA;
        for (int i = 0; i <= sumA / 2; i++) {
            if (dp[i] >= 0) {
                result = Math.min(result, sumA - 2 * i);
            }
        }

        return result;
    }
}
