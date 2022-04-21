package MinPerimeterRectangle

class Solution {
    public int solution(int N) {

        // main idea:
        // try to find the one "closest to sqrt(N)"
        
        int sqrtN = (int) Math.sqrt(N);
        int perimeter = (1 * 2) + (N * 2); // perimeter = (A*2)+(B*2)
        
        for(int i = sqrtN; i > 0; i--){ // from the one closest to sqrt(N)
            if( N % i ==0){             // key point: "N % i ==0"
                int A = i;
                int B = N/i;
                perimeter = (A * 2) + (B * 2);
                break;                  // be careful: break from the for-loop
            }
        }
        
        return perimeter;
     }
}

// My solution
    public static int solution(int N) {
        int sqrtN = (int) Math.sqrt(N);
        if (sqrtN * sqrtN == N) return 4 * sqrtN;

        Integer minParameter = IntStream.rangeClosed(1, sqrtN).boxed()
                .filter(i -> N % i == 0)
                .map(i -> 2 * (i + N / i))
                .min(Comparator.comparing(Integer::intValue))
                .get();


        return (int) minParameter;
    }
