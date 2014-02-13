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
        for (int i = hp.size()-1; x > hp.get((i-1)/2); i= (i-1)/2) {
            hp.set(i, hp.get((i - 1) / 2)); //Set the value where x currently is to the value at i.
            hp.set((i - 1) / 2, x); //Set the parent value to x.
        }
    }

    public char getMax() {
        char max;
        if (hp.size() == 0) {
            return '_';
        } else {
            max = hp.get(0);
            hp.set(0, hp.get(hp.size() - 1)); // Sets the root to the value of the last element.]
            hp.remove(hp.size() - 1); // Removes the last element.
            int i = 0;
            while (hp.get(i) < hp.get(2 * i + 1) || hp.get(i) < hp.get(2 * i + 2)) { // Tests to see
                                                                                     // whether BOTH
                                                                                     // children
                                                                                     // obey heap
                                                                                     // property.
                if (hp.get(2 * i + 1) > hp.get(2 * i + 2)) {
                    char tmp = hp.get(i); // Parent value
                    hp.set(i, hp.get(2 * i + 1));
                    hp.set(2 * i + 1, tmp);
                } else {
                    char tmp = hp.get(i); // Parent value
                    hp.set(i, hp.get(2 * i + 2));
                    hp.set(2 * i + 2, tmp);
                    // TODO: Swap i with 2i+2
                }
            }
        }
        return max;
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
