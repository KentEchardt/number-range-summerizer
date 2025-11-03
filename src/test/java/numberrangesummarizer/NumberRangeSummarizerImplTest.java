package numberrangesummarizer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Collection;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for NumberRangeSummarizerImpl.
 * Uses JUnit 5.
 */
class NumberRangeSummarizerImplTest {

    private NumberRangeSummarizer summarizer;

    @BeforeEach
    void setUp() {
        // Instantiate our implementation before each test
        summarizer = new NumberRangeSummarizerImpl();
    }

    // --- Tests for collect() method ---

    @Test
    @DisplayName("Should collect and sort the provided sample input")
    void testCollectSampleInput() {
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        Collection<Integer> expected = Arrays.asList(1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);
        Collection<Integer> actual = summarizer.collect(input);
        assertEquals(expected, actual, "Failed to collect sample input correctly");
    }

    @Test
    @DisplayName("Should handle unsorted input and return sorted collection")
    void testCollectWithUnsortedInput() {
        String input = "15,1,13,3,14,12";
        Collection<Integer> expected = Arrays.asList(1, 3, 12, 13, 14, 15);
        Collection<Integer> actual = summarizer.collect(input);
        assertEquals(expected, actual, "Failed to sort unsorted input");
    }

    @Test
    @DisplayName("Should handle duplicates and return distinct numbers")
    void testCollectWithDuplicates() {
        String input = "1,3,3,1,6,7,8,8";
        Collection<Integer> expected = Arrays.asList(1, 3, 6, 7, 8);
        Collection<Integer> actual = summarizer.collect(input);
        assertEquals(expected, actual, "Failed to remove duplicates");
    }

    @Test
    @DisplayName("Should ignore malformed input (letters, extra commas)")
    void testCollectWithMalformedInput() {
        String input = "1, 3, a, 6, 7, , 8,b,12,";
        Collection<Integer> expected = Arrays.asList(1, 3, 6, 7, 8, 12);
        Collection<Integer> actual = summarizer.collect(input);
        assertEquals(expected, actual, "Failed to ignore malformed input");
    }
    
    @Test
    @DisplayName("Should correctly collect negative and mixed numbers")
    void testCollectWithNegativeNumbers() {
        String input = "-3,-2,-1,2,0,5,-5";
        Collection<Integer> expected = Arrays.asList(-5, -3, -2, -1, 0, 2, 5);
        Collection<Integer> actual = summarizer.collect(input);
        assertEquals(expected, actual, "Failed to handle negative numbers");
    }

    @Test
    @DisplayName("Should return empty collection for empty string input")
    void testCollectEmptyInput() {
        String input = "";
        Collection<Integer> actual = summarizer.collect(input);
        assertTrue(actual.isEmpty(), "Empty string should return empty collection");
    }

    @Test
    @DisplayName("Should return empty collection for null input")
    void testCollectNullInput() {
        String input = null;
        Collection<Integer> actual = summarizer.collect(input);
        assertTrue(actual.isEmpty(), "Null string should return empty collection");
    }

    @Test
    @DisplayName("Should return empty collection for whitespace-only input")
    void testCollectWhitespaceInput() {
        String input = "   ,   ";
        Collection<Integer> actual = summarizer.collect(input);
        assertTrue(actual.isEmpty(), "Whitespace string should return empty collection");
    }

    // --- Tests for summarizeCollection() method ---

    @Test
    @DisplayName("Should summarize the provided sample collection")
    void testSummarizeSampleInput() {
        Collection<Integer> input = Arrays.asList(1, 3, 6, 7, 8, 12, 13, 14, 15, 21, 22, 23, 24, 31);
        String expected = "1, 3, 6-8, 12-15, 21-24, 31";
        String actual = summarizer.summarizeCollection(input);
        assertEquals(expected, actual, "Failed to summarize sample input correctly");
    }

    @Test
    @DisplayName("Should correctly summarize an unsorted input collection")
    void testSummarizeWithUnsortedInput() {
        // This tests the robustness of summarizeCollection itself
        Collection<Integer> input = Arrays.asList(15, 1, 13, 3, 14, 12, 8, 6, 7);
        String expected = "1, 3, 6-8, 12-15";
        String actual = summarizer.summarizeCollection(input);
        assertEquals(expected, actual, "Failed to summarize unsorted collection");
    }
    
    @Test
    @DisplayName("Should correctly summarize an input collection with duplicates")
    void testSummarizeWithDuplicates() {
        // This tests the robustness of summarizeCollection itself
        Collection<Integer> input = Arrays.asList(1, 3, 3, 6, 7, 7, 8);
        String expected = "1, 3, 6-8";
        String actual = summarizer.summarizeCollection(input);
        assertEquals(expected, actual, "Failed to summarize collection with duplicates");
    }

    @Test
    @DisplayName("Should handle a collection with only single numbers")
    void testSummarizeOnlySingles() {
        Collection<Integer> input = Arrays.asList(1, 3, 5, 7, 9);
        String expected = "1, 3, 5, 7, 9";
        String actual = summarizer.summarizeCollection(input);
        assertEquals(expected, actual, "Failed to summarize non-sequential numbers");
    }

    @Test
    @DisplayName("Should handle a collection with only one continuous range")
    void testSummarizeOnlyOneRange() {
        Collection<Integer> input = Arrays.asList(5, 6, 7, 8, 9, 10);
        String expected = "5-10";
        String actual = summarizer.summarizeCollection(input);
        assertEquals(expected, actual, "Failed to summarize a single continuous range");
    }

    @Test
    @DisplayName("Should correctly summarize ranges with negative numbers")
    void testSummarizeWithNegativeRanges() {
        Collection<Integer> input = Arrays.asList(-5, -4, -3, 0, 1, 3, 5, 6);
        String expected = "-5--3, 0-1, 3, 5-6";
        String actual = summarizer.summarizeCollection(input);
        assertEquals(expected, actual, "Failed to summarize negative number ranges");
    }
    
    @Test
    @DisplayName("Should correctly summarize a single number")
    void testSummarizeSingleNumber() {
        Collection<Integer> input = Collections.singletonList(5);
        String expected = "5";
        String actual = summarizer.summarizeCollection(input);
        assertEquals(expected, actual, "Failed to summarize a single-item collection");
    }

    @Test
    @DisplayName("Should return empty string for an empty collection")
    void testSummarizeEmptyCollection() {
        Collection<Integer> input = Collections.emptyList();
        String expected = "";
        String actual = summarizer.summarizeCollection(input);
        assertEquals(expected, actual, "Failed to return empty string for empty collection");
    }

    @Test
    @DisplayName("Should return empty string for a null collection")
    void testSummarizeNullCollection() {
        Collection<Integer> input = null;
        String expected = "";
        String actual = summarizer.summarizeCollection(input);
        assertEquals(expected, actual, "Failed to return empty string for null collection");
    }
}