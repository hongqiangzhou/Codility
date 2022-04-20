package Dominator;

// you can also use imports, for example:
import java.util.*;

class Solution {
    public int solution(int[] A) {

        // Using "hashMap" for counting
        Map<Integer, Integer> map = new HashMap<>();      
        
        // 1. Counting
        // map(key, value) ---> map(number, count)
        for(int i=0; i<A.length; i++){
            if( !map.containsKey(A[i]) ){ // new number
                map.put(A[i],1);          // "put" new number
            }
            else{
                int count = map.get(A[i]); // "get" count
                map.put(A[i], count+1);    // count++
            }
        }
        
        // 2. find the max number of counts
        int max_Number =0;
        int max_Count =0; 
        // note: use "map.keySet()" in for loop 
        for( int key: map.keySet() ){
            int cur_Count = map.get(key); // get value
            if( cur_Count > max_Count){
                max_Count = cur_Count;    // update max count
                max_Number = key;
            }
        }
        
        // 3. check if there is a "dominator" or not
        if( max_Count > (A.length)/2 ){
            // then, max_Number is the "dominator"
        }
        else{
            return -1; // no dominator
        }
        
        // 4. return "any index" of "the dominator"
        for(int i=0; i<A.length; i++){
            if(A[i] == max_Number){
                return i; // return the index
            }
        }
        
        return -1;
    }
}

// My solution through Stream API. Has timeout errors as expected.
    public static int solution(int[] A) {
        if (A.length < 1) return -1;

        Integer dominator = Arrays.stream(A).boxed()
                .collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()))
                .entrySet().stream()
                .filter(entry -> entry.getValue() > A.length / 2)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        if (dominator != null) {
            return IntStream.range(0, A.length).filter(i -> A[i] == dominator).findFirst().orElse(-1);
        }
        return -1;

    }

// This solution get 100% score.
class Solution {
    public int solution(int[] A) {
        // write your code in Java SE 8
        if (A.length < 1) return -1;

        if (A.length == 1) return 0;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            if (map.keySet().contains(A[i])) {
                int count = map.get(A[i]) + 1;
                map.put(A[i], count);
                if (count > A.length / 2)
                    return i;
            } else {
                map.put(A[i], 1);
            }

        }

        return -1;        
    }
}

