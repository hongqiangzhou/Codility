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
// Score: 54%, complexity: O(N**2*max(abs(A))), a lot of timeout exceptions.
class Solution {
    public int solution(int[] A) {
        // write your code in Java SE 8
        List<Integer> aList = Arrays.stream(A).boxed().map(Math::abs).collect(Collectors.toList());
        int sum = aList.stream().collect(Collectors.summingInt(Integer::intValue));

        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int a : aList) {
            for (int i = sum; i >= 0; i--) {
                if (dp[i] && a + i <= sum)
                    dp[a + i] = true;
            }
        }

        int ans = sum;
        for (int i = 0; i < sum / 2 + 1; i++) {
            if (dp[i])
                ans = Math.min(ans, sum - 2 * i);
        }

        return ans;
    }
}
