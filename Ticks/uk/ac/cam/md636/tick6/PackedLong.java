package uk.ac.cam.md636.tick6; 
		
public class PackedLong {			
 /*
  * Unpack and return the nth bit from the packed number at index position;
  * position counts from zero (representing the least significant bit)
  * up to 63 (representing the most significant bit).
  */
 public static boolean get(long packed, int position) {
  // set "check" to equal 1 if the "position" bit in "packed" is set to 1
  packed >>= position;
  long check = packed & 1;
  
  return (check == 1);
 }

 /*
  * Set the nth bit in the packed number to the value given
  * and return the new packed number
  */
 public static long set(long packed, int position, boolean value) {
  if (value) {
    packed |= ((long) 1 << position);
   // update the value "packed" with the bit at "position" set to 1
  }
  else {
    packed &= ~((long) 1 << position);
   // update the value "packed" with the bit at "position" set to 0 
  }
  return packed;
 }
}