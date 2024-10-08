package edu.grinnell.csc207.blocks;

/**
 * A text block Matrix.
 *
 * @author Sara Jaljaa
 * @author Jana Vadillo
 */
public class Matrix implements AsciiBlock {

  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The stuff in the Matrix.
   */
  AsciiBlock contents;

  /**
   * The Matrix outline.
   */
  char paren;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new matrix block with the specified contents.
   *
   * @param blockContents
   *   The contents of the block.
   *
   * @param outline
   *   The outline of the matrix.
   */
  public Matrix(AsciiBlock blockContents, char outline) {
    this.contents = blockContents;
    this.paren = outline;
  } // Matrix(AsciiBlock)

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
    if ((i < 0) || (i == this.height())) {
      throw new Exception("Invalid row " + i);
    } // if

    String outline = String.valueOf(this.paren);
    String inner = "  " + outline + " ".repeat(this.width()) + outline + "  " + "\n";
    String middle = " " + outline + " " + " ".repeat(this.width()) + " " + outline + " " + "\n";
    String textEven = outline + "  " + this.contents.row(i) + "  " + outline;

    String top =  inner + middle;
    String bottom = middle + inner;

    if (this.height() == 1) {
      return top + textEven + "\n" + bottom;
    } else if (this.height() == 0) {
      return top + bottom;
    } // if

    if (i == 0) {
      return top + textEven;
    } else if (i == this.height() - 1) {
      return textEven + "\n" + bottom;
    } else {
      return textEven;
    } // if
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    if (this.contents.height() == 0) {
      return 6;
    } // if
    return this.contents.height();
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    if (this.contents.width() == 0) {
      return 6;
    } // if
    return this.contents.width();
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
    return ((other instanceof Matrix) && (this.contents.eqv((Matrix) other)));
  } // eqv(AsciiBlock)

  /**
   * Determine if another Matrix is structurally equivalent to this block.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *     false otherwise.
   */
  public boolean eqv(Matrix other) {
    return ((this.paren == other.paren) && (this.contents.eqv(other.contents)));
  } // eqv(Matrix)
} // class Matrix

