// Don't know what's wrong. Below solution gets 27% with a lot of errors.
class Solution {
    public int solution(int[] A) {
        // write your code in Java SE 8
        if (A.length == 0) return 0;

        int dp[] = new int[A.length];
        dp[0] = Math.abs(A[0]);
        for (int i = 1; i < A.length; i++) {
            dp[i] = Math.min(Math.abs(dp[i-1] - A[i]), Math.abs(dp[i-1] + A[i]));
        }

        return dp[A.length-1];        
    }
}

// This solution follows `https://codility.com/media/train/solution-min-abs-sum.pdf`
// Score: 63%, complexity: O(N**2*max(abs(A))), a lot of timeout exceptions.
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
