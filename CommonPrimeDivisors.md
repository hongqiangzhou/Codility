class Solution {
    public int solution(int[] A, int[] B) {
        // write your code in Java SE 8
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            int gcdValue = gcd(A[i], B[i]);
            if (removeCommonPrimeDivisors(A[i], gcdValue) == 1
                    && removeCommonPrimeDivisors(B[i], gcdValue) == 1)
                count++;
        }
        return count;
    }

    private int gcd(int a, int b) {
               if (a%b == 0)
           return b;
       else
           return gcd(b, a%b);
    }

    private int removeCommonPrimeDivisors(int x, int y) {
                while(x != 1) {
            int gcdValue = gcd(x, y);
            if (gcdValue == 1)
                break;
            x = x/gcdValue;
        }
        return x;
    }
}
