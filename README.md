# ThreadCheckArray_Base

## Project Overview
This Java project implements a multithreaded solution to the Subset Sum Problem using two threads and recursive backtracking.

## Algorithm Description
Two threads search concurrently for a subset of integers whose sum equals a target value. One thread includes the last element, while the other excludes it. Shared data is synchronized to coordinate the results.

## Project Structure

### SharedData.java
Stores shared information between threads, including the `ArrayList<Integer>`, target sum, boolean flag, and `boolean[] winArray`.

### ThreadCheckArray.java
Implements `Runnable` and uses recursive backtracking to find a valid subset. Synchronization is used when accessing shared data.

### TestThreadCheckArray.java
Contains the `main()` method, reads user input, starts both threads, waits for their completion, and displays the result.

## Refactoring Details
The original `int[] array` was converted to `ArrayList<Integer>`.

- `array.length` → `array.size()`
- `array[index]` → `array.get(index)`
- `winArray` remains `boolean[]`.
- The original algorithm and multithreading structure were preserved.

## How to Run
1. Open the project in IntelliJ IDEA.
2. Navigate to `src/TestThreadCheckArray.java`.
3. Run the `main()` method.
4. Enter the array size, elements, and target sum.
5. View the result in the console.

## Academic Information
**Institution:** Braude College of Engineering

**Department:** Software Engineering

**Topics:** Java, Multithreading, Refactoring, Git and GitHub.