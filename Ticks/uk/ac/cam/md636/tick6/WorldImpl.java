package uk.ac.cam.md636.tick6;

import java.awt.Color;
import java.awt.Graphics;
//TODO: insert other appropriate "import" statements here
import java.io.PrintWriter;
import java.io.Writer;

import uk.ac.cam.acr31.life.World;

public abstract class WorldImpl implements World {

    private int width;
    private int height;
    private int generation;

    protected WorldImpl(int width, int height) {
        this.width = width;
        this.height = height;
        this.generation = 0;
    }

    protected WorldImpl(WorldImpl prev) {
        this.width = prev.width;
        this.height = prev.height;
        this.generation = prev.generation + 1;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getGeneration() {
        return this.generation;
    }

    public int getPopulation() {
        return 0;
    }

    protected String getCellAsString(int col, int row) {
        return getCell(col, row) ? "#" : "_";
    }

    protected Color getCellAsColour(int col, int row) {
        return getCell(col, row) ? Color.BLACK : Color.WHITE;
    }

    public void draw(Graphics g, int width, int height) {
        int worldWidth = getWidth();
        int worldHeight = getHeight();

        double colScale = (double) width / (double) worldWidth;
        double rowScale = (double) height / (double) worldHeight;

        for (int col = 0; col < worldWidth; ++col) {
            for (int row = 0; row < worldHeight; ++row) {
                int colPos = (int) (col * colScale);
                int rowPos = (int) (row * rowScale);
                int nextCol = (int) ((col + 1) * colScale);
                int nextRow = (int) ((row + 1) * rowScale);

                if (g.hitClip(colPos, rowPos, nextCol - colPos, nextRow - rowPos)) {
                    g.setColor(getCellAsColour(col, row));
                    g.fillRect(colPos, rowPos, nextCol - colPos, nextRow - rowPos);
                }
            }
        }
    }

    public WorldImpl nextGeneration(int log2StepSize) {
        // Remember to call nextGeneration 2^log2StepSize times
        WorldImpl world = this;
        int n = 1 << log2StepSize;
        for (int i = 0; i < n; i++) {
            world = world.nextGeneration();
        }
        return world;
    }

    public void print(Writer w) {
        // Use getCellAsString to get text representation of the cell
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

    protected int countNeighbours(int col, int row) {
        // Compute the number of live neighbours
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

    protected boolean computeCell(int col, int row) {
        // Compute whether this cell is alive or dead in the next generation
        // using "countNeighbours"
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

    // Will be implemented by child class. Return true if cell (col,row) is alive.
    public abstract boolean getCell(int col, int row);

    // Will be implemented by child class. Set a cell to be live or dead.
    public abstract void setCell(int col, int row, boolean alive);

    // Will be implemented by child class. Step forward one generation.
    protected abstract WorldImpl nextGeneration();
}