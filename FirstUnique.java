// Score: 81%, a few timeout exceptions.
class Solution {
    public int solution(int[] A) {
        // write your code in Java SE 8
        Map<Integer, List<Integer>> map = IntStream.range(0, A.length).boxed()
                .collect(Collectors.groupingBy(i -> A[i], Collectors.toList()));
        Optional<Map.Entry<Integer, List<Integer>>> entry = map.entrySet().stream()
                .filter(e -> e.getValue().size() == 1)
                .max(Comparator.comparing(e -> -e.getValue().get(0)));
        if (entry.isPresent())
            return entry.get().getKey();
        else
            return -1;
    }
}

