package MaxSliceSum;

// much elegant solution
// not using "Math.max( 0, maxEndingHere + A[i])"
// Instead, using "Math.max( A[i], maxEndingPrevious + A[i] )"

class Solution {
    public int solution(int[] A) {
        
        // initial setting A[0]
        int maxEndingPrevious = A[0];
        int maxEndingHere = A[0];
        int maxSoFar = A[0];
 
        // note: for i=0, it will return A[0] (also for "one element" cases)  
           
        for(int i = 1; i < A.length; i++){
            maxEndingHere = Math.max(A[i], maxEndingPrevious + A[i]); // <--- key point~!!
            maxEndingPrevious = maxEndingHere;
            maxSoFar = Math.max(maxSoFar, maxEndingHere); // update the max (be careful)
        }
        
        return maxSoFar; // can be used for "all negative" cases 
    }
}


// My solution.
class Solution {
    public int solution(int[] A) {
        // write your code in Java SE 8
        if (A.length == 1) return A[0];

        int res = A[0];
        int preSum = Math.max(0, A[0]);
        for (int i = 1; i < A.length; i++) {
            int a = A[i];
            int currSum = preSum + a;

            if (currSum < 0) {
                preSum = 0;
            } else {
                preSum = currSum;
            }
            res = Math.max(currSum, res);

        }

        return res;

    }
}
