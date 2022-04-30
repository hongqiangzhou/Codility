// O(N * log(N)) 
class Solution {
    public int solution(int[] A) {
        // write your code in Java SE 8
        Arrays.sort(A);

        int res = Integer.MAX_VALUE;
        int left = 0;
        int right = A.length - 1;
        while(left <= right) {
            int sum = A[left] + A[right];
            res = Math.min(res, Math.abs(sum));
            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }
        return res;        
    }
}
