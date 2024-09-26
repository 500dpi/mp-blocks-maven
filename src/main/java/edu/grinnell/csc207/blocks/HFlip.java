package edu.grinnell.csc207.blocks;

import java.io.PrintWriter;

/**
 * A horizontally flipped ASCII block.
 *
 * @author Samuel A. Rebelsky
 * @author Sara Jaljaa
 */
public class HFlip implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The original block.
   */
  AsciiBlock block;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block with the specified contents.
   *
   * @param original
   *   The original block.
   */
  public HFlip(AsciiBlock original) {
    this.block = original;
  } // HFlip(AsciiBlock)

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   *
   * @return row i.
   *
   * @exception Exception
   *   If the row is invalid.
   */
  public String row(int i) throws Exception {
    int w = this.width();
    PrintWriter pen = new PrintWriter(System.out, true);
    if (i > 0 && i < w) {
      char[] flip = this.block.row(i).toCharArray();
      int length = this.block.row(i).length();
      char current;
      for (int index = 0; index < length / 2; ) {
        current = this.block.row(i).charAt(index);
        flip[index] = flip[(length - 1) - index];
        flip[(length - 1) - index] = current;
        pen.println(flip.toString());
      }
      return flip.toString();
    } else {
      throw new Exception("Invalid row " + i);
    }
  } // row(int)

  // public static void main(String[] args) {
  //   PrintWriter pen = new PrintWriter(System.out, true);

  //   HFlip line = new HFlip(new Line("Hello"));
  //   // HFlip(line.row(0));
  //   try {
  //   AsciiBlock.print(pen, line);
  //   } catch (Exception e) {
  //     System.err.println("Didn't work.");
  //   }
  //   // AsciiBlock.print(pen, (line.row(0)));
  // }

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return this.block.height();
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return this.block.width();
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    return false;       // STUB
  } // eqv(AsciiBlock)
} // class HFlip
