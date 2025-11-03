# Number Range Summarizer

A Java utility that collects a comma-delimited string of numbers and produces a summarized string, grouping sequential numbers into ranges.

This project is an implementation of an interface, built with Java 8 and Maven.

---

## Prerequisites

To build and run this project, you will need:
* Java 8 (or higher)
* Apache Maven
* Git

---

## How to Run

This project includes a `main` method for a simple demonstration.

**To test your own input:**
1.  Open the file: `src/main/java/numberrangesummarizer/NumberRangeSummarizerImpl.java`
2.  Find the `main` method at the bottom of the file.
3.  Change the `input` string variable to any comma-separated numbers you want to test.

**Steps to run from your terminal:**

1.  **Clone the repository:**
    ```sh
    git clone <your-repository-url>
    cd number-range-summarizer
    ```

2.  **Compile and package the project using Maven:**
    ```sh
    mvn clean package
    ```
    This will compile your code and create a JAR file in the `target/` directory.

3.  **Run the `main` method:**
    We execute the `main` method inside the newly created JAR file.
    ```sh
    java .\NumberRangeSummarizerImpl.java
    ```
