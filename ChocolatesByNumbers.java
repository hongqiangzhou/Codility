package ChocolatesByNumbers;

class Solution {
    public int solution(int N, int M) {

        // main idea: 
        // using "gcd(M, N)"
        // the number of eaten chocolates = N / gcd(M,N)
        return N/(gcd(N,M));
    }

    // using "Euclidean Algorithm" (important)
    public static int gcd(int a,int b){
        if(a % b == 0)
            return b;            // case 1
        else
            return gcd(b,a % b); // case 2 (key point)
    }

}

// My solution, not tested

    private int solution(int A, int B) {

        boolean[] flag = new boolean[A];
        int i = 0;
        while(!flag[(i*B) % A]) {
            flag[(i*B) % A] = true;
            i++;
        }

        return i; // Since "i" starts with "0", there is no need to minus 1 hear.
    }
