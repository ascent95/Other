package uk.ac.cam.md636.tick3; 

public class StringArrayLife {
    
    
	public static boolean getCell(boolean[][] world, int col, int row) {
		 if (row < 0 || row > world.length - 1) return false;
		 if (col < 0 || col > world[row].length - 1) return false;

		 return world[row][col];
	}
	
	
	public static void setCell(boolean[][] world, int col, int row, boolean value) {
		if (row >= 0 || row < world.length || col >= 0 || col < world[row].length) {
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
		 
		 if (neighbours < 2) nextCell = false;
		 if (liveCell && (neighbours == 2 || neighbours == 3)) nextCell = true;
		 if (neighbours > 3) nextCell = false;
		 if (!liveCell && (neighbours == 3)) nextCell = true;
		 
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
	
	
	public static void play(boolean[][] world) throws Exception {
		int userResponse = 0;
		while (userResponse != 'q') {
			print(world);
			userResponse = System.in.read();
			world = nextGeneration(world);
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		String[] argSplit = args[0].split(":");
		int width = Integer.parseInt(argSplit[2]);
		int height = Integer.parseInt(argSplit[3]);
		boolean[][] world = new boolean[height][width];
		//place the long representation of the game board in the centre of "world"
		int startCol = Integer.parseInt(argSplit[4]);
		int startRow = Integer.parseInt(argSplit[5]);
		String[] newCells = argSplit[6].split(" ");
		for(int i = 0; i < newCells.length; i++) {
			char[] row = newCells[i].toCharArray();
			for(int j = 0; j < row.length; j++) {
				if (row[j] == '1') {
					world[i+startRow][j+startCol] = true;
				}
			}
		}
		play(world);
	}
}