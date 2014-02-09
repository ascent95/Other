package uk.ac.cam.md636.tick5;

import uk.ac.cam.acr31.life.World;
import java.io.Writer;
import java.awt.Graphics;
import java.io.PrintWriter;

public class TestArrayWorld implements World {

    private int generation;
    private int width;
    private int height;
    private boolean[][] cells;

    public TestArrayWorld(int w, int h) {
        width = w;
        height = h;
        generation = 0;
        cells = new boolean[height][width];
    }

    protected TestArrayWorld(TestArrayWorld prev) {
        width = prev.width;
        height = prev.height;
        generation = prev.generation + 1;
        cells = new boolean[height][width];

    }

    public boolean getCell(int col, int row) {
        if (row < 0 || row >= width)
            return false;
        if (col < 0 || col >= height)
            return false;

        return cells[row][col];
    }

    public void setCell(int col, int row, boolean alive) { // Used method from RefactorLife.
        if (row >= 0 && row < height && col >= 0 && col < width) {
            cells[row][col] = alive;
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getGeneration() {
        return generation;
    }

    public int getPopulation() {
        return 0;
    }

    public void print(Writer w) {
        PrintWriter pw = new PrintWriter(w);
        pw.println("-");
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                pw.print(getCell(col, row) ? "#" : "_");
            }
            pw.println();
        }
        pw.flush();
    }

    public void draw(Graphics g, int width, int height) { /* Leave empty */
    }

    private int countNeighbours(int col, int row) {
        int c = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (!(i == 0 && j == 0) && getCell(j + col, i + row)) {
                    c++;
                }
            }
        }
        return c;
    }

    private boolean computeCell(int col, int row) {
        boolean liveCell = getCell(col, row);
        int neighbours = countNeighbours(col, row);
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

    private TestArrayWorld nextGeneration() {
        // Construct a new TestArrayWorld object to hold the next generation:
        TestArrayWorld world = new TestArrayWorld(this);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                world.setCell(col, row, computeCell(col, row));
            }
        }
        return world;
    }

    public World nextGeneration(int log2StepSize) {
        TestArrayWorld world = this;
        int n = 1 << log2StepSize;
        for (int i = 0; i < n; i++) {
            world = world.nextGeneration();
        }
        return world;
    }
}