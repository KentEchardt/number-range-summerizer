package numberrangesummarizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Implements the NumberRangeSummarizer interface.
 *
 * This implementation uses Java 8 Streams for efficient and clean collection
 * parsing and relies on a robust iteration algorithm for summarizing.
 * It is designed to be stateless and thread-safe.
 */
public class NumberRangeSummarizerImpl implements NumberRangeSummarizer {

    /**
     * Collects numbers from a comma-delimited string.
     *
     * This method uses Java 8 Streams to:
     * 1. Handle null or empty input.
     * 2. Split the string by commas.
     * 3. Trim whitespace from each part.
     * 4. Filter out any non-numeric or empty strings.
     * 5. Parse valid strings to Integers.
     * 6. Remove any duplicates.
     * 7. Sort the final collection.
     *
     * @param input A comma-delimited string of numbers.
     * @return A sorted Collection of unique Integers.
     */
    @Override
    public Collection<Integer> collect(String input) {
        if (input == null || input.trim().isEmpty()) {
            return Collections.emptyList();
        }

        // Use Java 8 Streams for a clean, functional approach
        return Arrays.stream(input.split(",")) // Split by comma
                .map(String::trim)               // Trim whitespace
                .filter(s -> s.matches("-?\\d+")) // Filter for valid integers (positive/negative)
                .map(Integer::parseInt)          // Parse to Integer
                .distinct()                      // Remove duplicates
                .sorted()                        // Sort the numbers
                .collect(Collectors.toList());   // Collect into a List
    }

    /**
     * Summarizes a collection of integers into a range string.
     *
     * This method:
     * 1. Handles null or empty collections.
     * 2. Ensures the data is sorted and distinct (robustness).
     * 3. Iterates through the list, identifying sequential ranges.
     * 4. Builds a list of strings representing individual numbers or ranges.
     * 5. Joins the final list with ", ".
     *
     * @param input A Collection of Integers.
     * @return A comma-delimited string with sequential numbers grouped into ranges.
     */
    @Override
    public String summarizeCollection(Collection<Integer> input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        // Ensure data is sorted and distinct, regardless of the input collection's state.
        List<Integer> numbers = input.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        List<String> rangeStrings = new ArrayList<>();
        
        for (int i = 0; i < numbers.size(); i++) {
            int rangeStart = numbers.get(i);

            // Look ahead to find the end of the sequential range
            int j = i;
            while (j + 1 < numbers.size() && numbers.get(j + 1) == numbers.get(j) + 1) {
                j++;
            }

            int rangeEnd = numbers.get(j);

            // Add the formatted string
            if (rangeStart == rangeEnd) {
                // It's a single number
                rangeStrings.add(String.valueOf(rangeStart));
            } else {
                // It's a range
                rangeStrings.add(rangeStart + "-" + rangeEnd);
            }

            // Move the outer loop cursor 'i' to the end of the processed range
            i = j;
        }

        // Join all parts with a comma and space
        return String.join(", ", rangeStrings);
    }
}