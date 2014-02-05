package uk.ac.cam.md636.tick4;

public class Pattern {

    private String original;
    private String name;
    private String author;
    private int width;
    private int height;
    private int startCol;
    private int startRow;
    private String cells;

    // TODO: write public "get" methods for EACH of the fields above;
    // for instance "getName" should be written as:

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getStartCol() {
        return startCol;
    }

    public int getStartRow() {
        return startRow;
    }

    public String getCells() {
        return cells;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public Pattern(String format) throws PatternFormatException {
        // TODO: initialise all fields of this class using contents of "format"
        // to determine the correct values.
        setOriginal(format); // For use later when I have to print out the
                             // pattern to select.
        String[] formatSplit = format.split(":");

        // Initial checks on length.
        if (formatSplit.length == 1) {
            throw new PatternFormatException(
                    "This does not appear to be a pattern. Please include colons to separate information.");
        } else if (formatSplit.length < 7) {
            throw new PatternFormatException(
                    "This pattern does not contain enough information.");
        }

        name = formatSplit[0];
        author = formatSplit[1];
        try { // Checking the dimensions to make sure they are integers.
            width = Integer.parseInt(formatSplit[2]);
            height = Integer.parseInt(formatSplit[3]);
        } catch (NumberFormatException e) {
            throw new PatternFormatException(
                    "The dimensions of the array are not numerical values.");
        }
        try { // Checking the start positions to make sure they are integers.
            startCol = Integer.parseInt(formatSplit[4]);
            startRow = Integer.parseInt(formatSplit[5]);
        } catch (NumberFormatException e) {
            throw new PatternFormatException(
                    "The starting coordinates of the pattern you want to enter are not numerical values.");
        }
        cells = formatSplit[6]; // This gets dealt with in the initialise
                                // method.
    }

    public void initialise(boolean[][] world) throws PatternFormatException {
        // TODO: update the values in the 2D array representing the state of
        // "world"
        // as expressed by the contents of the field "cells".
        String[] newCells = cells.split(" ");
        for (int i = 0; i < newCells.length; i++) {           
            char[] row = newCells[i].toCharArray();
            for (int j = 0; j < row.length; j++) {
                if (row[j] == '1') {
                    world[i + startRow][j + startCol] = true;
                } else if (row[j] != '0') {
                    throw new PatternFormatException(
                            "You have not specified which cells are alive correctly. The data contains values which are not either 0 or 1." + original);
                }
            }
        }
    }
}