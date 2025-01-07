// My solution, 100% score.
class Solution {
    public int solution(int[] A) {
        // write your code in Java SE 8
        if (A.length < 3) return 0;

        int[] peaks = new int[A.length];
        int numPeaks = 0;
        for (int i = 1; i < A.length-1; i++) {
            if (A[i-1] < A[i] && A[i+1] < A[i]) {
                peaks[i] = i;
                numPeaks++;
            }
        }

        if (numPeaks < 2) return numPeaks;

        int K = 2;
        while(true) {
            int numFlags = K - 1;
            int lastFlagIndex = peaks[0];
            for (int i = 1; i < numPeaks; i++) {
                if (peaks[i] - lastFlagIndex >= K) {
                    numFlags--;
                    if (numFlags == 0) break;
                    lastFlagIndex = peaks[i]
                }
            }
            if (numFlags > 0) {
                K--;
                break;
            }
            K++;
        }

        return K;
    }
}
