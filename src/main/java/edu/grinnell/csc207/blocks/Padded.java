package edu.grinnell.csc207.blocks;

/**
 * A padded ASCII block.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class Padded implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The original block.
   */
  AsciiBlock block;

  /**
   * The character used for padding.
   */
  String pad;

  /**
   * How is the original block horizontally aligned within the padding?
   */
  HAlignment halign;

  /**
   * How is the original block vertically aligned within the padding?
   */
  VAlignment valign;

  /**
   * How wide is the padded block?
   */
  int width;

  /**
   * How tall is the padded block.
   */
  int height;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block with the specified contents.
   *
   * @param original
   *   The original block.
   * @param ch
   *   The character we use for padding.
   * @param horiz
   *   How the original block is horizontally aligned within the padding.
   * @param vert
   *   How the original block is vertically aligned within the padding.
   * @param paddedWidth
   *   The width of the padded block.
   * @param paddedHeight
   *   The height of the padded block.
   */
  public Padded(AsciiBlock original, char ch, HAlignment horiz,
      VAlignment vert, int paddedWidth, int paddedHeight) {
    this.block = original;
    this.pad = new String(new char[] {ch});
    this.halign = horiz;
    this.valign = vert;
    this.width = paddedWidth;
    this.height = paddedHeight;
  } // Padded(AsciiBlock, char, HAlignment, VAlignment, int, int)

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
    throw new Exception("Not yet implemented");
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return 0;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return 0;
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
    return false;
  } // eqv(AsciiBlock)
} // class Padded
