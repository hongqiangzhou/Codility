package MaxProfit;

class Solution {
    public int solution(int[] A) {
        // main idea (One Pass Solution):
        // We can maintain two variables 
        // 1) minprice (key point~!!)
        // 2) maxprofit (corresponding to the smallest valley)
        
        // special case 
        if(A.length <= 1)
            return 0; // no profit
        
        // two variables (and initial setting)
        int minPrice = A[0];
        int maxProfit =0;
        
        // one pass solution
        for(int i=1; i<A.length; i++){
            if(A[i] < minPrice) // case 1: from high to low
                minPrice = A[i];
            else{               // case 2: from low to high
                int curProfit = A[i] - minPrice;
                if(curProfit > maxProfit) // update max profit
                    maxProfit = curProfit;
            }            
        }
        
        return maxProfit;
    }
}


// My solution.
class Solution {
    public static void main(String[] args) {

        int[] A = {23171, 21011, 21123, 21366, 21013, 21367};
        System.out.println(solution(A));
    }

    private static int solution(int[] A) {
        if (A.length <= 1) return 0;

        int min = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int a : A) {
            min = Math.min(min, a);
            maxProfit = Math.max(maxProfit, a - min);
        }

        return maxProfit;

    }

}
