package uk.ac.cam.md636.tick4;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class LoaderLife {
    public static boolean getCell(boolean[][] world, int col, int row) {
        if (row < 0 || row > world.length - 1)
            return false;
        if (col < 0 || col > world[row].length - 1)
            return false;

        return world[row][col];
    }

    public static void setCell(boolean[][] world, int col, int row,
            boolean value) {
        if (row >= 0 || row < world.length || col >= 0
                || col < world[row].length) {
            world[row][col] = value;
        }
    }

    public static int countNeighbours(boolean[][] world, int col, int row) {
        int c = 0;
        if (getCell(world, col - 1, row - 1) == true) {
            c += 1;
        }
        if (getCell(world, col, row - 1) == true) {
            c += 1;
        }
        if (getCell(world, col + 1, row - 1) == true) {
            c += 1;
        }
        if (getCell(world, col - 1, row) == true) {
            c += 1;
        }
        if (getCell(world, col + 1, row) == true) {
            c += 1;
        }
        if (getCell(world, col - 1, row + 1) == true) {
            c += 1;
        }
        if (getCell(world, col, row + 1) == true) {
            c += 1;
        }
        if (getCell(world, col + 1, row + 1) == true) {
            c += 1;
        }
        return c;
    }

    public static boolean computeCell(boolean[][] world, int col, int row) {
        boolean liveCell = getCell(world, col, row);
        int neighbours = countNeighbours(world, col, row);
        boolean nextCell = false;

        if (neighbours < 2)
            nextCell = false;
        if (liveCell && (neighbours == 2 || neighbours == 3))
            nextCell = true;
        if (neighbours > 3)
            nextCell = false;
        if (!liveCell && (neighbours == 3))
            nextCell = true;

        return nextCell;
    }

    public static boolean[][] nextGeneration(boolean[][] world) {
        boolean[][] nextWorld = new boolean[world.length][world[0].length];
        for (int row = 0; row < world.length; row++) {
            for (int col = 0; col < world[0].length; col++) {
                setCell(nextWorld, col, row, computeCell(world, col, row));
            }
        }
        return nextWorld;
    }

    public static void print(boolean[][] world) {
        System.out.println("-");
        for (int row = 0; row < world.length; row++) {
            for (int col = 0; col < world[0].length; col++) {
                System.out.print(getCell(world, col, row) ? "#" : "_");
            }
            System.out.println();
        }
    }

    public static void play(boolean[][] world) throws IOException {
        int userResponse = 0;
        while (userResponse != 'q') {
            print(world);
            userResponse = System.in.read();
            world = nextGeneration(world);
        }
    }

    public static void main(String[] args) {

        List<Pattern> results = null;

        try {
            if (args[0].startsWith("http://")) {
                results = PatternLoader.loadFromURL(args[0]);
            } else {
                results = PatternLoader.loadFromDisk(args[0]);
            }

            if (args.length == 2) {
                Pattern p = results.get(Integer.parseInt(args[1])); //Uses the index into the list to select the desired pattern.
                boolean[][] world = new boolean[p.getHeight()][p.getWidth()];
                p.initialise(world);
                play(world);
            } else {
                int i = 0; // For producing the list
                for (Pattern p : results) {
                    System.out.println(i + ")" + p.getOriginal());
                    i++;
                }

            }

        } catch (PatternFormatException e) {
            System.out.println(e.getMsg());
        } catch (IOException e) {
            System.out.println("Wrong type of file"); // This may need to change
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Bad index");
        }
    }
}