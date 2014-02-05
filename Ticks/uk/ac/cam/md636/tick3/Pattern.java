package uk.ac.cam.md636.tick3;

public class Pattern {

	 private String name;
	 private String author;
	 private int width;
	 private int height;
	 private int startCol;
	 private int startRow;
	 private String cells;
	 
	 public String getName() {return name;}
	 public String getAuthor() {return author;}
	 public int getWidth() {return width;}
	 public int getHeight() {return height;}
	 public int getStartCol() {return startCol;}
	 public int getStartRow() {return startRow;}
	 public String getCells() {return cells;}
	 
	 
	 public Pattern(String format) {
		 String[] formatSplit = format.split(":");
		 name = formatSplit[0];
		 author = formatSplit[1];
		 width = Integer.parseInt(formatSplit[2]);
		 height = Integer.parseInt(formatSplit[3]);
		 startCol = Integer.parseInt(formatSplit[4]);
		 startRow = Integer.parseInt(formatSplit[5]);
		 cells = formatSplit[6];
	 }

	 
	 public void initialise(boolean[][] world) {
		 String[] newCells = cells.split(" ");
			for(int i = 0; i < newCells.length; i++) {
				char[] row = newCells[i].toCharArray();
				for(int j = 0; j < row.length; j++) {
					if (row[j] == '1') {
						world[i+startRow][j+startCol] = true;
					}
				}
			}
	 }
	} 