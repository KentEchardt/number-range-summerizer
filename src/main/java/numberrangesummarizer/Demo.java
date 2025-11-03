package numberrangesummarizer;

import java.util.Collection;

public class Demo {

    public static void main(String[] args) {
        // 1. Create an instance of your implementation
        NumberRangeSummarizer summarizer = new NumberRangeSummarizerImpl();

        // 2. Define your sample input
        String input = "1,3,6,7,8,12,13,14,15,21,22,23,24,31";
        System.out.println("Input String: " + input);

        // 3. Call the collect method
        Collection<Integer> numbers = summarizer.collect(input);
        System.out.println("Collected Nums: " + numbers);

        // 4. Call the summarize method
        String summary = summarizer.summarizeCollection(numbers);
        System.out.println("Result Summary: " + summary);

        System.out.println("---");

        // Test another case
        String input2 = "1,3,2,2,4,6,5,7,9,8";
        System.out.println("Input String: " + input2);
        Collection<Integer> numbers2 = summarizer.collect(input2);
        System.out.println("Collected Nums: " + numbers2);
        String summary2 = summarizer.summarizeCollection(numbers2);
        System.out.println("Result Summary: " + summary2);
    }
}