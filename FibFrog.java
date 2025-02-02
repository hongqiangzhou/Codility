package FibFrog;

import java.util.*;

class Solution {
    public int solution(int[] A) {
        // Implement your solution here
        ArrayList<Integer> fibonacci = new ArrayList<>();
        fibonacci.add(0); // note: f(0) = 0 (as in the quesion)
        fibonacci.add(1);
        // note: using "while" is better than "for" (avoid errors)
        while(true){
            int temp1 = fibonacci.get( fibonacci.size()-1 );
            int temp2 = fibonacci.get( fibonacci.size()-2 );
            fibonacci.add( temp1 + temp2 ); 
            
            // if already bigger than length, then break;
            if(temp1 + temp2 > A.length){ 
                break;
            }
        }
        
        // reverse "List": from big to small
        Collections.reverse(fibonacci);
        
        // use "queue" with "point"
        // point(x,y) = point("position", "number of steps")
        ArrayList<Point> queue = new ArrayList<>(); 
        queue.add( new Point(-1, 0) ); // position:-1, steps:0
        
        // index: the current index for queue element 
        int index=0; 
        while(true){
            // cannot take element from queue anymore
            if(index == queue.size() ){ 
                return -1;
            }
            
            // take element from queue
            Point current = queue.get(index); 
            
            // from big to small 
            for(Integer n: fibonacci){
                int nextPosition = current.x + n;
                
                // case 1: "reach the other side" 
                if(nextPosition == A.length){ 
                    // return the number of steps
                    return current.y + 1; 
                }
                
                // case 2: "cannot jump"
                // note: nextPosition < 0 (overflow, be careful)
                else if( (nextPosition > A.length) || (nextPosition < 0)|| (A[nextPosition]==0) ){
                    // note: do nothing 
                }
                
                // case 3: "can jump" (othe cases)
                else{
                    // jump to next position, and step+1
                    Point temp = new Point(nextPosition, current.y + 1); 
                    // add to queue
                    queue.add(temp);  
                    
                    A[nextPosition] = 0; // key point: for high performance~!!
                } 
            }
            
            index++; // take "next element" from queue
        }
    }

    class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

// My revision of above solution, score = 100%.
// The key point is to use a Queue instead of Stack.
import java.util.*;

class Solution {
    public int solution(int[] A) {
        // Implement your solution here
        List<Integer> fabs = new ArrayList<>();
        fabs.add(1);
        fabs.add(2);
        int i = 2;
        int temp = fabs.get(i-1) + fabs.get(i-2);
        while(temp <= A.length+1) {
            fabs.add(temp);
            i++;
            temp = fabs.get(i-1) + fabs.get(i-2);
        }
        Collections.reverse(fabs);

        Queue<Jump> queue = new LinkedList<>();
        queue.add(new Jump(-1, 0));
        while (!queue.isEmpty()) {
            Jump jump = queue.remove();
            for (int fab: fabs) {
                int position = jump.position + fab;
                if (position == A.length) {
                    return jump.steps + 1;
                } else if (position < A.length && A[position] == 1) {
                    queue.add(new Jump(position, jump.steps + 1));
                    A[position] = 0; // This way we prevent other possiblities which land on the same point through more steps.
                }
            }
        }

        return -1;
    }

    class Jump {
        int position;
        int steps;

        Jump(int position, int steps) {
            this.position = position;
            this.steps = steps;
        }

    }
}


// My solution, correctness = 83%, performance = 33%.
// Consider a case when A = [0, 1, 1, 0, 0, 1]. Below solution yields 3 (A[2], A[5], then 5 + 1). 
// In fact, the answer is 2 (A[1], then 1 + 5).
import java.util.*;

class Solution {
    public int solution(int[] A) {
        // Implement your solution here
        List<Integer> fabs = new ArrayList<>();
        fabs.add(1);
        fabs.add(2);
        int i = 2;
        int temp = fabs.get(i-1) + fabs.get(i-2);
        while(temp <= A.length) {
            fabs.add(temp);
            i++;
            temp = fabs.get(i-1) + fabs.get(i-2);
        }

        Stack<Jump> stack = new Stack<>();
        stack.push(new Jump(-1, 0));
        boolean crossed = false;
        while(!crossed && !stack.isEmpty()) {
            Jump jump = stack.pop();
            for (int fab: fabs) {
                int pos = jump.position + fab;
                if (pos == A.length) {
                    stack.push(new Jump(pos, jump.steps + 1));
                    crossed = true;
                    break;
                } else if (pos > A.length) {
                    break;
                } else if (A[pos] == 1) {
                    stack.push(new Jump(pos, jump.steps + 1));
                }
            }
        }

        if (!stack.isEmpty()) {
            return stack.pop().steps;
        } else {
            return -1;
        }

    }

    class Jump {
        int position;
        int steps;

        Jump(int position, int steps) {
            this.position = position;
            this.steps = steps;
        }

    }
    
}

