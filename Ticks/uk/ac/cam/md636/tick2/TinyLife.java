package uk.ac.cam.md636.tick2; 


public class TinyLife {
	public static boolean getCell(long world, int col, int row) {
		if (col > 7 || row > 7 || col < 0 || row < 0) {
			return false;
		}
		else {
			boolean tmp = PackedLong.get(world, (col + row * 8));
			return tmp;
		}
	}
	public static long setCell(long world, int col, int row, boolean value) {
		if (col > 7 || row > 7 || col < 0 || row < 0) {
			return world;
		}
		else {
			long tmp = PackedLong.set(world, (col + row * 8), value);
			return tmp;
		}
	}
	public static int countNeighbours(long world, int col, int row) {
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

	public static boolean computeCell(long world,int col,int row) {

		 // liveCell is true if the cell at position (col,row) in world is live
		 boolean liveCell = getCell(world, col, row);
			
		 // neighbours is the number of live neighbours to cell (col,row)
		 int neighbours = countNeighbours(world, col, row);

		 // we will return this value at the end of the method to indicate whether 
		 // cell (col,row) should be live in the next generation
		 boolean nextCell = false;
			
		 //A live cell with less than two neighbours dies (underpopulation)
		 if (neighbours < 2) {
		  nextCell = false;
		 }
		 
		 //A live cell with two or three neighbours lives (a balanced population)
		 //TODO: write a if statement to check neighbours and update nextCell
		 if (liveCell && (neighbours == 2 || neighbours == 3)) {
			 nextCell = true;
		 }

		 //A live cell with with more than three neighbours dies (overcrowding)
		 //TODO: write a if statement to check neighbours and update nextCell
		 if (neighbours > 3) {
			  nextCell = false;
		 }

		 //A dead cell with exactly three live neighbours comes alive
		 //TODO: write a if statement to check neighbours and update nextCell
		 if (!liveCell && (neighbours == 3)) {
			  nextCell = true;
		 }
		 return nextCell;
		}
	public static long nextGeneration(long world) {
		long nextWorld = 0;
		for (int row = 0; row < 8; row++) { 
			for (int col = 0; col < 8; col++) {
				nextWorld = setCell(nextWorld, col, row, computeCell(world, col, row));
			}
		}
		return nextWorld;
	}

	public static void print(long world) { 
		System.out.println("-"); 
		for (int row = 0; row < 8; row++) { 
			for (int col = 0; col < 8; col++) {
				System.out.print(getCell(world, col, row) ? "#" : "_"); 
			}
				System.out.println(); 
		} 
	}
	
	public static void play(long world) throws Exception {
		 int userResponse = 0;
		 while (userResponse != 'q') {
		  print(world);
		  userResponse = System.in.read();
		  world = nextGeneration(world);
		 }
		}
	
	public static void main(String[] args) throws Exception {
		 play(Long.decode(args[0]));
	}
}