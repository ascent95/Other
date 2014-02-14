package uk.ac.cam.md636.alg1;

import java.util.ArrayList;

public class MaxHeap {
    private char heapName;
    private ArrayList<Character> hp = new ArrayList<Character>();

    MaxHeap(char name) {
        heapName = name;
    }

    MaxHeap(char name, String str) {
        heapName = name;
        for (int i = 0; i < str.length(); i++) {
            insert(str.charAt(i));
        }
    }

    public void insert(char x) {
        hp.add(x); // Is this ok or is it better to use Character.valueOf here?
        for (int i = hp.size() - 1; x > hp.get((i - 1) / 2); i = (i - 1) / 2) {
            hp.set(i, hp.get((i - 1) / 2)); // Set current value to same as at i.
            hp.set((i - 1) / 2, x); // Set the parent value to x.
        }
    }

    public char getMax() { 
        char max;
        if (hp.size() == 0) {
            return '_';
        } else if (hp.size() == 1) {
            char tmp = hp.get(0);
            hp.remove(0);
            return tmp;
        } else if (hp.size() == 2) {
            char tmp = hp.get(0);
            hp.set(0, hp.get(1));
            hp.remove(1);
            return tmp;
        } else {
            max = hp.get(0);
            hp.set(0, hp.get(hp.size() - 1)); // Sets the root to the value of the last
                                              // element.]
            hp.remove(hp.size() - 1); // Removes the last element.
            int i = 0;
            while (i <= (hp.size() - 2) / 2
                    && (hp.get(i) < hp.get(2 * i + 1) || hp.get(i) < hp.get(2 * i + 2))) {
                // Tests to see whether BOTH children obey heap property. If they do then the
                // while loop terminates. Also checks whether i is less than half way through
                // the list, otherwise it won't have any children. Also checks whether list
                // length is even. If it is then we know the last parent has two children.

                if (i != (hp.size() - 2) / 2 || hp.size() % 2 == 1) { // Proceed as normal
                    if (hp.get(2 * i + 1) > hp.get(2 * i + 2)) { // If left greater than right.
                        swapL(i);
                        i = 2 * i + 1;
                    } else { // If right child is greater than left
                        swapR(i);
                        i = 2 * i + 2;
                    }
                } else {
                    if (hp.get(i) < hp.get(2 * i + 1)) {
                        // Checks whether i is parent of last element AND list length is even
                        // so it has
                        // 1 child only AND that last child needs to be swapped.
                        swapL(i);
                    }
                }
            }
            return max;
        }
    }

    public void swapL(int i) {
        char tmp = hp.get(i); // Parent value
        hp.set(i, hp.get(2 * i + 1));
        hp.set(2 * i + 1, tmp);
    }

    public void swapR(int i) {
        char tmp = hp.get(i); // Parent value
        hp.set(i, hp.get(2 * i + 2));
        hp.set(2 * i + 2, tmp);
    }

    public static void main(String[] args) {
        char c;
        MaxHeap h = new MaxHeap('h', "CAMBRIDGEALGORITHMS");
        c = h.getMax();
        System.out.println(c); // expect T
        h.insert('Z');
        h.insert('A');
        c = h.getMax();
        System.out.println(c); // expect Z
        c = h.getMax();
        System.out.println(c); // expect S
    }
}
