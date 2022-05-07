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

// My solution, score: 100%.
class Solution {
    public int solution(String S) {
        // write your code in Java SE 8
        Map<Character, Character> map = new HashMap<Character, Character>(){{
           put(')', '(');
           put(']', '[');
           put('}', '{');
        }};
        Stack<Character> stack = new Stack<>();
        for (Character c : S.toCharArray()) {
            if (map.containsKey(c)) {
                if (stack.isEmpty() ||map.get(c) != stack.pop())
                    return 0;
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty()? 1 : 0;
    }
}
