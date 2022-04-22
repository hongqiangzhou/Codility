class Solution {
    public int solution(int[] A) {
        // write your code in Java SE 8
        if (A.length == 1) return 0;

        int[] indexPeaks = new int[A.length];
        int numPeaks = 0;
        for (int i = 1; i < A.length-1; i++) {
            if (A[i-1] < A[i] && A[i+1] < A[i]) {
                indexPeaks[numPeaks] = i;
                numPeaks++;
            }
        }

        if (numPeaks == 1) return 1;

        int res = 0;
        int k = 2;
        while(true) {
            int numFlags = 1;
            int prevRes = res;
            int prevIndex = indexPeaks[0];
            for (int i = 1; i < numPeaks; i++) {
                if (indexPeaks[i] - prevIndex >= k) {
                    numFlags++;
                    prevIndex = indexPeaks[i];
                    if (numFlags == k) {
                        res = k;
                        break;
                    }
                }
            }
            if (res == prevRes) break;
            k++;
        }

        return res;        
    }
}
