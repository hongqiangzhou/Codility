package MaxNonoverlappingSegments;

class Solution {
    public int solution(int[] A, int[] B) {

        // main idea:
        // Using "greedy" method to find non-overlapping segments
        
        // because the segments are sorted by their rightEnds
        // we use "for loop" from rightEnd to left 
        // and just need to keep the "value of leftEnd" (key point)

        // spcial case
        if(A.length==0)
            return 0;
        
        int N = A.length;
        // keep the value of leftEnd: A[i]
        // the 1st segment: A[N-1]
        int currentLeftEnd = A[N-1];
        int numNonOverlap =1;
        
        for(int i=N-2; i >=0; i--){
            // if "rightEnd < leftEnd", nonOverlap++
            // and update the value of leftEnd
            if(B[i] < currentLeftEnd){
                numNonOverlap++;
                currentLeftEnd = A[i];
            }
            // if "leftnEnd is shorter", 
            // update the value of leftEnd (important)
            if(A[i] > currentLeftEnd){
                currentLeftEnd = A[i];
            }
        }
        
        return numNonOverlap;
    }
}

// My solution, score: 90%, one timeout exception
class Solution {
    public int solution(int[] A, int[] B) {
        // write your code in Java SE 8
        if (A.length == 0) return 0;

        List<Segment> segments = IntStream.range(0, A.length).boxed()
                .map(i -> new Segment(A[i], B[i]))
                .collect(Collectors.toList());
        int ans = 1;
        Segment currSegment = segments.get(0);
        for (int i = 1; i < segments.size(); i++) {
            if (segments.get(i).x1 > currSegment.x2) {
                currSegment = segments.get(i);
                ans++;
            }
        }

        return ans;
    }

    private class Segment {
        public int x1;
        public int x2;

        Segment(int x1, int x2) {
            this.x1 = x1;
            this.x2 = x2;
        }
    }
}
