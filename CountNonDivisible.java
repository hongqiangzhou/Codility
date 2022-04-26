package CountNonDivisible;

import java.util.*;

class Solution {
    public int[] solution(int[] A) {

        // main idea:
        // using "HashMap" to count 
        
        // map1(key, value)
        HashMap<Integer, Integer> map1 = new HashMap<>();
        // key: the elements, value, count of elements
        for(int i=0; i< A.length; i++){
            if(map1.containsKey(A[i]) == false){
                map1.put(A[i], 1); // add new element
            }
            else{
                 map1.put(A[i], map1.get(A[i])+1 ); // count++
            }
        }
        
        // map2(key, value)
        HashMap<Integer, Integer> map2 = new HashMap<>();
        // key: the elements, value, count of "number of non-divisors" of elements
        for( int n : map1.keySet() ){            
            int numDivisors =0;
            // find divisors from 1 to sqrt(n)
            int sqrtN = (int)Math.sqrt(n);  
            for(int i=1; i<=sqrtN; i++ ){
                if( n % i == 0){ // means: i could be a divisor
                    int anotherDivisor = n/i; 
    
                    if(map1.containsKey(i) == true ){
                        numDivisors = numDivisors + map1.get(i);
                    }
                    if(anotherDivisor != i){ // avoid double count (be careful)
                        if(map1.containsKey(anotherDivisor) == true){
                            numDivisors = numDivisors + map1.get(anotherDivisor);
                        }
                    }
                }
            }
            
            int numNonDivisors = A.length - numDivisors;
            map2.put(n, numNonDivisors); 
        }
        
        // results: number of non-divisors
        int[] results = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            results[i] = map2.get(A[i]);
        }

        return results;
    }
}

// My solution, O(N*2), 66% score.
class Solution {
    public int[] solution(int[] A) {
        // write your code in Java SE 8
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            int a = A[i];
            if (map.containsKey(a)) {
                List<Integer> list = map.get(a);
                list.add(i);
                map.put(a, list);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(a, list);
            }
        }

        int[] res = new int[A.length];
        for (Integer key : map.keySet()) {
            int count = map.entrySet().stream()
                .filter(e -> key % e.getKey() != 0)
                .map(e -> e.getValue().size())
                .collect(Collectors.reducing(0, Integer::sum));
            map.get(key).forEach(i -> res[i] = count);
        }

        return res;        
    }
}
