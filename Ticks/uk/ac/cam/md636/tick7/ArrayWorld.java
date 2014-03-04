package uk.ac.cam.md636.tick7;

public class ArrayWorld extends WorldImpl{
    
    private boolean[][] cells;

    public ArrayWorld(int width, int height) {
        super(width, height);
        cells = new boolean[getHeight()][getWidth()];
    }
    
    public ArrayWorld(ArrayWorld prev) {
        super(prev);
        cells = new boolean[getHeight()][getWidth()]; //You don't need prev.getHeight() because the super call sets all of these.
    }

    @Override
    public boolean getCell(int col, int row) {
        if (row >= 0 && row < getHeight() && col >= 0 && col < getWidth()) {
            return cells[row][col];
        } else {
            return false;
        }
    }

    @Override
    public void setCell(int col, int row, boolean alive) {
        if (row >= 0 && row < getHeight() && col >= 0 && col < getWidth()) {
            cells[row][col] = alive;
        }
    }

    @Override
    public ArrayWorld nextGeneration() {
        ArrayWorld world = new ArrayWorld(this);
        for (int row = 0; row < world.getHeight(); row++) {
            for (int col = 0; col < world.getHeight(); col++) {
                world.setCell(col, row, computeCell(col, row));
            }
        }
        return world;
    }

}
