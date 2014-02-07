package uk.ac.cam.md636.tick5;

import uk.ac.cam.md636.tick5.World;
import java.io.Writer;
import java.awt.Graphics;
import java.io.PrintWriter;

public class ArrayWorld implements World {

 private int generation;
 private int width;
 private int height;
 private boolean[][] cells;

 public ArrayWorld(int w, int h) {
   width = w;
   height = h;
   // TODO: set generation equal to zero
   // TODO: set cells to reference a new rectangular two-dimensional 
   //       boolean array of size height by width  
 }

 protected ArrayWorld(ArrayWorld prev) {
   width = prev.width;
   height = prev.height;
   // TODO: set generation equal to prev.generation+1
   // TODO: set cells to reference a new rectangular two-dimensional 
   //       boolean array of size height by width  
 }

 public boolean getCell(int col, int row) { /* TODO */ }
 public void setCell(int col, int row, boolean alive) { /*TODO*/ }
 public int getWidth()  { /*TODO*/ }
 public int getHeight()  { /*TODO*/ }
 public int getGeneration()  { /*TODO*/ }
 public int getPopulation()  { return 0; }
 public void print(Writer w)  { /*TODO*/ }
 public void draw(Graphics g, int width, int height)  { /*Leave empty*/ }

 private ArrayWorld nextGeneration() {
   //Construct a new ArrayWorld object to hold the next generation:
   ArrayWorld world = new ArrayWorld(this);
   //TODO: Use for loops with "setCell" and "computeCell" to populate "world"
   return world;
 }

 public World nextGeneration(int log2StepSize)  { 
   ArrayWorld world = this;
   //TODO: repeat the statement in curly brackets 2^log2StepSize times
   {
    world = world.nextGeneration();
   }
   return world;
 }
   
 //TODO: Add any other private methods which you find helpful      
}