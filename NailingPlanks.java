// Solution with score 75% and a few timeout exceptions.
class Solution {
    public int solution(int[] A, int[] B, int[] C) {
        // write your code in Java SE 8
        List<Plank> planks = IntStream.range(0, A.length).boxed()
                .map(i -> new Plank(A[i], B[i]))
                .sorted(Comparator.comparing(e -> e.x))
                .collect(Collectors.toList());

        for (int i = 0; i < C.length; i++) {
            while(planks.size() > 0) {
                int pIndex = findPlunk(C[i], planks);
                if (pIndex > -1) {
                    planks.remove(pIndex);
                } else {
                    break;
                }
            }

            if (planks.size() == 0) {
                return i + 1;
            }
        }

        return -1;        
    }

    private int findPlunk(int nail, List<Plank> planks) {
        int lower = 0;
        int upper = planks.size() -1;

        while (lower <= upper) {
            int mid = (lower + upper) / 2;
            if (planks.get(mid).x > nail) {
                upper = mid - 1;
            } else if (planks.get(mid).y < nail) {
                lower = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    private class Plank {
        public int x;
        public int y;

        Plank(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
