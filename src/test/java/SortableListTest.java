import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class SortableListTest {
    private final static int TEST_SIZE = 10000;
    private ArrayList<Integer> testList = new ArrayList<>();
    private SortableList<Integer> sortableList;
    private ArrayList<Integer> sortedList;

    private interface ListStyles {
        String RANDOM = "random";
        String UNIQUE = "unique";
    }

    private void randomList() {
        for (int i=0; i<TEST_SIZE; i++) {
            testList.add((int) (Math.random()*TEST_SIZE));
        }
    }

    private void randomUniqueList() {
        for (int i=0; i<TEST_SIZE; i++) {
            int randNum = (int) (Math.random()*TEST_SIZE);
            while (testList.contains(randNum)) {
                randNum = (int) (Math.random()*TEST_SIZE);
            }
            testList.add(randNum);
        }
    }

    private void initializeList(String style) {
        switch (style) {
            case "unique":
                randomUniqueList();
                break;
            case "random":
                randomList();
                break;
        }
//        System.out.println("Initial: " + testList);
        sortableList = new SortableList<>(new ArrayList<>(testList));
    }

    private void printSortedAndSortTestList() {
//        System.out.println("Sorted: " + sortedList);
        Collections.sort(testList);
    }

    @Test
    public void selectionTestRandom() throws Exception {
        System.out.println("\n----- Selection Sort With Random List (duplications) -----");
        initializeList(ListStyles.RANDOM);
        sortedList = sortableList.selectionSort();
        printSortedAndSortTestList();
        assertEquals(testList, sortedList);
    }

    @Test
    public void selectionTestRandomUnique() throws Exception {
        System.out.println("\n----- Selection Sort With Random List (unique numbers) -----");
        initializeList(ListStyles.UNIQUE);
        sortedList = sortableList.selectionSort();
        printSortedAndSortTestList();
        assertEquals(testList, sortedList);
    }

    @Test
    public void insertionTestRandom() throws Exception {
        System.out.println("\n----- Insertion Sort With Random List (duplications) -----");
        initializeList(ListStyles.RANDOM);
        sortedList = sortableList.insertionSort();
        printSortedAndSortTestList();
        assertEquals(testList, sortedList);
    }

    @Test
    public void insertionTestRandomUnique() throws Exception {
        System.out.println("\n----- Insertion Sort With Random List (unique numbers) -----");
        initializeList(ListStyles.UNIQUE);
        sortedList = sortableList.insertionSort();
        printSortedAndSortTestList();
        assertEquals(testList, sortedList);
    }

    @Test
    public void mergeTestRandom() throws Exception {
        System.out.println("\n----- Merge Sort With Random List (duplications) -----");
        initializeList(ListStyles.RANDOM);
        sortedList = sortableList.mergeSort();
        printSortedAndSortTestList();
        assertEquals(testList, sortedList);
    }

    @Test
    public void mergeTestRandomUnique() throws Exception {
        System.out.println("\n----- Merge Sort With Random List (unique numbers) -----");
        initializeList(ListStyles.UNIQUE);
        sortedList = sortableList.mergeSort();
        printSortedAndSortTestList();
        assertEquals(testList, sortedList);
    }
}