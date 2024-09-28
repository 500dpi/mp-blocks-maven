package edu.grinnell.csc207.blocks;

/**
 * A text block surrounded by a box.
 *
 * @author Samuel A. Rebelsky
 */
public class Boxed implements AsciiBlock {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * A backslash.
   */
  static final String BACKSLASH = "\\";

  /**
   * A slash.
   */
  static final String SLASH = "/";

  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The stuff in the box.
   */
  AsciiBlock contents;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block with the specified contents.
   *
   * @param blockContents
   *   The contents of the block.
   */
  public Boxed(AsciiBlock blockContents) {
    this.contents = blockContents;
  } // Boxed(AsciiBlock)

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
   *   if the row is invalid
   */
  public String row(int i) throws Exception {
    int h = this.contents.height();
    if (i == 0) {
      // The top of the box
      return SLASH + "-".repeat(this.contents.width()) + BACKSLASH;
    } else if (i == h + 1) {
      // The bottom of the box
      return BACKSLASH + "-".repeat(this.contents.width()) + SLASH;
    } else if ((i > 0) && (i <= h)) {
      // Stuff within the box
      return "|" + this.contents.row(i - 1) + "|";
    } else {
      throw new Exception("Invalid row " + i);
    } // if/else
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rowseqv
   */
  public int height() {
    return 2 + this.contents.height();
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return 2 + this.contents.width();
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
    return ((other instanceof Boxed) && (this.eqv((Boxed) other)));
  } // eqv(AsciiBlock)



  /**
   * Determine if another Boxed is structurally equivalent to this block.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *     false otherwise.
   */
  public boolean eqv(Boxed other) {
    return this.contents.eqv(other.contents);
  } // eqv(Boxed)
} // class Boxed
