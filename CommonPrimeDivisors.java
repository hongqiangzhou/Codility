class Solution {
    public int solution(int[] A, int[] B) {
        // write your code in Java SE 8
        int ans = 0;
        for (int i = 0; i < A.length; i++) {
            int gcdValue = gcd(A[i], B[i]);
            if (hasSamePrimeDivisors(A[i], B[i])) ans++;
        }
        return ans;
    }

    private int gcd(int a, int b) {
       if (a%b == 0)
           return b;
       else
           return gcd(b, a%b);
    }
    
    private boolean hasSamePrimeDivisors(int a, int b) {
        int gcdValue = gcd(a, b);

        while(a != 1) {
            int gcdA = gcd(a, gcdValue);
            if (gcdA == 1)
                break;
            a = a / gcdA;
        }

        if (a != 1)
            return false;

        while(b != 1) {
            int gcdB = gcd(b, gcdValue);
            if (gcdB == 1)
                break;
            b = b / gcdB;
        }

        return b == 1;
    }

}


// Straightfoward solution, score: 53%, OutOfMemoryExceptions
class Solution {
    public int solution(int[] A, int[] B) {
        // write your code in Java SE 8
        int maxA = Arrays.stream(A).max().getAsInt();
        int maxB = Arrays.stream(B).max().getAsInt();
        int max = Math.max(maxA, maxB);
        int sqrtN = (int) Math.ceil(Math.sqrt(max));
        Boolean[] primeArray = new Boolean[1+max];
        Arrays.fill(primeArray, true);
        primeArray[0] = false;
        primeArray[1] = false;

        for (int i = 2; i < sqrtN; i++) {
            if (primeArray[i]) {
                for (int j = 2*i; j <= max; j = j+i) {
                    primeArray[j] = false;
                }
            }
        }

        List<Integer> primesList = IntStream.rangeClosed(0, max).boxed()
            .filter(i -> primeArray[i])
            .collect(Collectors.toList());

        int ans = 0;
        for (int i = 0; i < A.length; i++) {
           Set<Integer> setA = primeDivisors(A[i], primesList);
           Set<Integer> setB = primeDivisors(B[i], primesList);
           if (setA.containsAll(setB) && setB.containsAll(setA))
               ans++;
        }

        return ans;
    }

    private Set<Integer> primeDivisors(int a, List<Integer> primesList) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < primesList.size() && primesList.get(i) <= a; i++) {
           if (a % primesList.get(i) == 0) set.add(primesList.get(i));
        }

        return set;
    }
}
