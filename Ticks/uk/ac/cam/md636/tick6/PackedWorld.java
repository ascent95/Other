package uk.ac.cam.md636.tick6;

public class PackedWorld extends WorldImpl {

    private long cells;

    public PackedWorld() {
        super(8, 8);
        cells = 0;
    }

    public PackedWorld(PackedWorld prev) {
        super(prev);
        cells = 0;
    }

    @Override
    public boolean getCell(int col, int row) {
        if (row >= 0 && row < getHeight() && col >= 0 && col < getWidth()) {
            return (cells >> (row * 8 + col) & 1) == 1;
        } else {
            return false;
        }
    }

    @Override
    public void setCell(int col, int row, boolean alive) {
        if (row >= 0 && row < 8 && col >= 0 && col < 8) {
            if (alive) {
                cells |= ((long) 1 << (8 * row + col));
                // update cells with the bit at the right position set to 1
            } else {
                cells &= ~((long) 1 << (8 * row + col));
                // update cells with the bit at the right position set to 0
            }

        }
    }

    @Override
    protected WorldImpl nextGeneration() {
        PackedWorld world = new PackedWorld(this);
        for (int row = 0; row < getHeight(); row++) {
            for (int col = 0; col < getWidth(); col++) {
                world.setCell(col, row, computeCell(col, row));
            }
        }
        return world;
    }

}
