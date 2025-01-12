// Solution with score 87% with one timeout exception.

import java.util.*;

class Solution {
    public int solution(int[] A, int[] B, int[] C) {
        // Implement your solution here
        int[][] nails = new int[C.length][2];
        for (int i = 0; i < C.length; i++) {
            nails[i][0] = C[i];
            nails[i][1] = i;
        }
        Arrays.sort(nails, Comparator.comparingInt(nail -> nail[0]));

        int globalIndex = 0;
        for (int i = 0; i < A.length; i++) {
            int bestIndex = findBestIndex(A[i], B[i], nails, globalIndex);
            if (bestIndex == -1) {
                return -1;
            }
            globalIndex = Math.max(globalIndex, bestIndex);
        }

        return globalIndex + 1;
    }


    private int findBestIndex(int start, int end, int[][] nails, int globalIndex) {

        int low = 0;
        int high = nails.length - 1;
        int idx = -1;
        while(low < nails.length && low <= high) {
            int mid = (low + high) / 2;
            if (nails[mid][0] < start) {
                low = mid + 1;
            } else if (nails[mid][0] > end) {
                high = mid - 1;
            } else {
                idx = mid;
                high = mid - 1;
            }
        }

        if (idx == -1) return -1;
        if (nails[idx][1] < globalIndex) return nails[idx][1];

        int bestIndex = nails[idx][1];
        for (int i = idx+1; i < nails.length; i++) {
            if (nails[i][0] > end) break;
            if (nails[i][1] < bestIndex) bestIndex = nails[i][1];
            if (bestIndex < globalIndex) return bestIndex;
        }

        return bestIndex;
    }
}
