package uk.ac.cam.md636.tick4;

public class Repeat {
    public static void main(String[] args) {
        System.out.println(parseAndRep(args));
    }

    public static String parseAndRep(String[] args) {
        if (args.length != 2) {
            return "Error: insufficient arguments";
        }
        try {
            int rep = Integer.parseInt(args[1]);
            if (rep < 1) {
                return "Error: second argument is not a positive integer";
            }
            String repeated = "";
            for (int i = 0; i < rep; i++) {
                if (i == rep - 1) {
                    repeated += args[0]; // Prevents an extra space being placed
                                         // on the end. somechange
                } else {
                    repeated += (args[0] + " ");
                }
            }
            return repeated;
        } catch (NumberFormatException error) {
            return "Error: second argument is not a positive integer";
        }

    }
}