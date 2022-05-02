package OddOccurrencesInArray;

class Solution {
    public int solution(int[] A) {
        // write your code in Java SE 8
        
        // Using the concept of "XOR" (^)
        // when there is a pair A and B 
        // A^B will be zero 
        // A^B^C (where C is not paired), 
        // then A^B^C = C
        
        // special case
        if(A.length == 0)
            return 0;
        
        int unpaired;
        unpaired = A[0]; // initial
        
        for(int i=1; i< A.length; i++){    
            unpaired = unpaired ^ A[i]; // xor    
        }
        
        return unpaired; // return the unpaired value
    }
}


// My solution with score of 88%.
class Solution {
    public int solution(int[] A) {
        // write your code in Java SE 8
        Map<Integer, Long> map = Arrays.stream(A).boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        return map.entrySet().stream()
                .filter(e -> e.getValue() % 2 != 0)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(-1);
    }
}
