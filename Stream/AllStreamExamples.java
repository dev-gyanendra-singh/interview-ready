package Stream;
import java.util.*;
import java.util.stream.*;

public class AllStreamExamples {

    public static void main(String[] args) {
        // 1. Creating Streams from Collections
        List<String> fruits = Arrays.asList("apple", "banana", "cherry", "date", "elderberry", "fig", "grape");

        // 2. Stream Creation from Arrays
        Stream<String> fruitStream = Arrays.stream(new String[]{"kiwi", "lemon", "mango"});

        // 3. Stream Creation from Values (Stream.of)
        Stream<Integer> numberStream = Stream.of(10, 20, 30, 40, 50);

        // 4. Empty Stream
        Stream<Object> emptyStream = Stream.empty();

        // 5. Infinite Stream (generate)
        Stream<Double> randomNumbers = Stream.generate(Math::random).limit(5);

        // 6. Infinite Stream (iterate)
        Stream<Integer> powersOfTwo = Stream.iterate(1, n -> n * 2).limit(6);

        // Intermediate Operations Examples:
        // 7. filter - select elements that match predicate
        List<String> longFruits = fruits.stream()
                .filter(s -> s.length() > 5)
                .collect(Collectors.toList());

        // 8. map - transform elements
        List<String> upperFruits = fruits.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        // 9. flatMap - flatten nested streams
        List<List<String>> nestedList = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("c", "d", "e")
        );
        List<String> flattened = nestedList.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        // 10. distinct - remove duplicates
        List<Integer> duplicates = Arrays.asList(1, 2, 2, 3, 3, 3, 4);
        List<Integer> distinct = duplicates.stream().distinct().collect(Collectors.toList());

        // 11. sorted - natural order
        List<String> sortedFruits = fruits.stream().sorted().collect(Collectors.toList());

        // 12. sorted with Comparator
        List<String> sortedByLength = fruits.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());

        // 13. limit - restrict stream size
        List<String> first3Fruits = fruits.stream().limit(3).collect(Collectors.toList());

        // 14. skip - skip first N elements
        List<String> skip2Fruits = fruits.stream().skip(2).collect(Collectors.toList());

        // 15. peek - intermediate operation to debug or perform side effects
        fruits.stream()
                .peek(s -> System.out.println("Fruit: " + s))
                .filter(s -> s.length() > 5)
                .collect(Collectors.toList());

        // Terminal Operations Examples:
        // 16. forEach - perform action on each element
        System.out.println("All fruits:");
        fruits.stream().forEach(System.out::println);

        // 17. collect - convert stream to collection or other container
        Set<String> fruitSet = fruits.stream().collect(Collectors.toSet());

        // 18. reduce - aggregate elements to one
        Optional<String> longestFruit = fruits.stream()
                .reduce((a, b) -> a.length() > b.length() ? a : b);

        // 19. count - count elements
        long countFruits = fruits.stream().count();

        // 20. anyMatch - checks if any element matches predicate
        boolean anyStartsWithA = fruits.stream().anyMatch(s -> s.startsWith("a"));

        // 21. allMatch - checks if all elements match predicate
        boolean allHaveE = fruits.stream().allMatch(s -> s.contains("e"));

        // 22. noneMatch - checks if no elements match predicate
        boolean noneStartWithZ = fruits.stream().noneMatch(s -> s.startsWith("z"));

        // 23. findFirst - get first element wrapped in Optional
        Optional<String> firstFruit = fruits.stream().findFirst();

        // 24. findAny - get any element (useful in parallel streams)
        Optional<String> anyFruit = fruits.parallelStream().findAny();

        // Advanced Collectors Examples:
        // 25. groupingBy - group elements by classifier function
        Map<Integer, List<String>> groupedByLength = fruits.stream()
                .collect(Collectors.groupingBy(String::length));

        // 26. partitioningBy - partition elements by predicate into two groups
        Map<Boolean, List<String>> partitionedByStartsWithA = fruits.stream()
                .collect(Collectors.partitioningBy(s -> s.startsWith("a")));

        // 27. joining - join elements into a single String
        String joinedFruits = fruits.stream().collect(Collectors.joining(", ", "[", "]"));

        // 28. summarizingInt - statistics on int values extracted from elements
        IntSummaryStatistics lengthStats = fruits.stream()
                .collect(Collectors.summarizingInt(String::length));

        // 29. mapping - map elements before collecting
        Set<Integer> fruitLengths = fruits.stream()
                .collect(Collectors.mapping(String::length, Collectors.toSet()));

        // Parallel Stream Example:
        // 30. sum of random numbers (parallel)
        int sumParallel = IntStream.rangeClosed(1, 1000).parallel().sum();

        // 31. reduce with identity and accumulator
        int product = numbers().stream().reduce(1, (a, b) -> a * b);

        // 32. custom Collector example (counting elements that start with 'a')
        long countStartsWithA = fruits.stream()
                .collect(Collectors.counting());

        // Output Results:

    }

    private static List<Integer> numbers() {
        return Arrays.asList(1, 2, 3, 4, 5);
    }
}