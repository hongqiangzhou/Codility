package Brackets;

// you can also use imports, for example:
import java.util.*;

class Solution {
    public int solution(String S) {

        // main idea: use "Stack" (push and pop)
        
        //special case
        if(S.length() == 0)
            return 1;
        
        // new Stack<Character>()
        Stack<Character> stack = new Stack<>();
        
        // scan the string (just one pass)
        for(int i=0; i< S.length(); i++){    
            // note: push "its pair"
            if( S.charAt(i) == '(' ){
                stack.push(')');
            }
            else if( S.charAt(i) == '[' ){
                stack.push(']');
            }
            else if( S.charAt(i) == '{' ){
                stack.push('}');
            }
            // pop and check
            else if( S.charAt(i) == ')' || S.charAt(i) == ']' || S.charAt(i) == '}'){
                // important: check if the stack is empty or not (be careful)
                if(stack.isEmpty() == true){
                    return 0;
                }
                else{
                    char temp = stack.pop(); // check if the stack is empty before pop!!!
                    if(temp != S.charAt(i)){ // not a pair
                        return 0;
                    }
                }
            }
        }
        // note: check if the stack is empty or not (be careful)
        if( !stack.isEmpty() ){
            return 0;
        }
        else{
            return 1;
        }
    }
}


// My solution.
    private static int solution(String S) {

        Map<Character, Character> map = Map.of('}', '{', ']', '[', ')', '(');
        Set<Character> set = new HashSet<>(Arrays.asList('}', ']', ')'));

        Stack<Character> stack = new Stack<>();
        for (char c : S.toCharArray()) {
            if (set.contains(c) ) {
                if (stack.isEmpty() || map.get(c) != stack.pop())
                    return 0;
            } else {
                stack.push(c);
            }

        }
        return stack.isEmpty()? 1 : 0;

    }
