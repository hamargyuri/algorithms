import java.util.ArrayList;
import java.util.Stack;

class SortableList<T extends Comparable<T>> {
    private int steps;
    private ArrayList<T> list;

    SortableList(ArrayList<T> listToSort) {
        list = listToSort;
    }

    private int indexOfMin(int startIndex) {
        int indexOfMin = startIndex;
        T minValue = list.get(startIndex);
        for (int i=startIndex+1; i<list.size(); i++) {
            steps++;
            if (list.get(i).compareTo(minValue) < 0) {
                indexOfMin = i;
                minValue = list.get(i);
            }
        }
        return indexOfMin;
    }

    private void swap(int aIndex, int bIndex) {
        T buffer = list.get(aIndex);
        list.set(aIndex, list.get(bIndex));
        list.set(bIndex, buffer);
    }

    ArrayList<T> selectionSort() {
        for (int i=0; i<list.size(); i++) {
            steps++;
            swap(i, indexOfMin(i));
        }
        System.out.println("Steps: " + steps);
        return list;
    }

    private void insert(int currentIndex, T currentValue) {
        int i;
        for (i=currentIndex-1; i>=0 && list.get(i).compareTo(currentValue) > 0; i--) {
            list.set(i+1, list.get(i));
            steps++;
        }
        list.set(i+1, currentValue);
    }

    ArrayList<T> insertionSort() {
        for (int i=0; i<list.size(); i++) {
            steps++;
            insert(i, list.get(i));
        }
        System.out.println("Steps: " + steps);
        return list;
    }

    private ArrayList<T> merge(ArrayList<T> leftSide, ArrayList<T> rightSide) {
        ArrayList<T> mergedList = new ArrayList<>();

        Stack<T> leftStack = new Stack<>();
        for (int i=leftSide.size()-1; i>=0; i--) {
            steps++;
            leftStack.push(leftSide.get(i));
        }
        Stack<T> rightStack = new Stack<>();
        for (int i=rightSide.size()-1; i>=0; i--) {
            steps++;
            rightStack.push(rightSide.get(i));
        }

        while (!leftStack.isEmpty() && !rightStack.isEmpty()) {
            steps++;
            if (leftStack.peek().compareTo(rightStack.peek()) < 0) {
                mergedList.add(leftStack.pop());
            } else {
                mergedList.add(rightStack.pop());
            }
        }
        while (!leftStack.isEmpty()) {
            steps++;
            mergedList.add(leftStack.pop());
        }
        while (!rightStack.isEmpty()) {
            steps++;
            mergedList.add(rightStack.pop());
        }

        return mergedList;
    }

    private ArrayList<T> mergeSort(ArrayList<T> listToMerge, boolean printSteps) {
        if (listToMerge.size() <= 1) return listToMerge;

        ArrayList<T> leftSide = new ArrayList<>();
        ArrayList<T> rightSide = new ArrayList<>();
        for (int i=0; i<listToMerge.size()/2; i++) {
            steps++;
            leftSide.add(listToMerge.get(i));
        }
        for (int i=listToMerge.size()/2; i<listToMerge.size(); i++) {
            steps++;
            rightSide.add(listToMerge.get(i));
        }

        leftSide = new ArrayList<>(mergeSort(leftSide, false));
        rightSide = new ArrayList<>(mergeSort(rightSide, false));
        steps++;
        if (printSteps) System.out.println("Steps: " + steps);
        return merge(leftSide, rightSide);
    }

    ArrayList<T> mergeSort() {
        steps++;
        return mergeSort(list, true);
    }
}
