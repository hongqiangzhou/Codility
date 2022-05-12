
class Solution {
    public int solution(int[] A) {
        // write your code in Java SE 8
        int indexHighest = 0;
        int indexLowest = 0;
        int ans = 0;
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[indexHighest]) {
                int depth = A[indexHighest] - A[indexLowest];
                ans = Math.max(ans, depth);
                indexHighest = i;
                indexLowest = i;
            } else if (A[i] > A[indexLowest]) {
                ans = Math.max(ans, A[i] - A[indexLowest]);
            } else {
                if (A[i] < A[indexLowest])
                    indexLowest = i;
            }
        }

        return ans;
    }
}
