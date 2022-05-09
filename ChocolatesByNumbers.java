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

// This solution is similar to above, but more explainative.

    private int solution(int N, int M) {
        // find lcm (a number that can be devided by both N and M), ex N = 10, M = 4: lcm = 20 => 20 / 10 = 2 and 20 / 4 = 5;
        int lcm = N * M / gcd(N, M); 
        return lcm / M;
    }

    private int gcd(int a, int b) {
        if (a % b == 0)
            return b;
        else
            return gcd(b, a % b);
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
