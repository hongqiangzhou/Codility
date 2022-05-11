// Score: 100%
class Solution {
    public int solution(String S) {
        // write your code in Java SE 8
        Predicate<String> myFilter = s ->
                s.matches("^[a-zA-Z0-9]*$")
                && s.replaceAll("[a-zA-Z]+", "").length() % 2 ==1
                && s.replaceAll("[0-9]+", "").length() % 2 == 0;

        String[] str = S.replaceAll("\\s+", " ").split(" ");
        return Arrays.stream(str)
          .filter(myFilter)
          .map(String::length)
          .max(Comparator.comparing(Integer::intValue))
          .orElse(-1);
    }
}
