package edu.grinnell.csc207.blocks;

/**
 * A text block surrounded by a circle.
 *
 * @author Sara Jaljaa
 * @author Jana Vadillo
 */
public class Matrice implements AsciiBlock {

  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The stuff in the circle.
   */
  AsciiBlock contents;

  /**
   * The matrice outline symbol.
   */
  char outline;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new encircled block with the specified contents.
   *
   * @param blockContents
   *   The contents of the block.
   *
   * @param outline
   *   The outline of the matrice.
   */
  public Matrice(AsciiBlock blockContents, char outline) {
    this.contents = blockContents;
    this.outline = outline;
  } // Parenthesis(AsciiBlock)

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

    String outline = String.valueOf(this.outline);
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
    return ((other instanceof Matrice) && (this.contents.eqv((Matrice) other)));
  } // eqv(AsciiBlock)

  /**
   * Determine if another Circle is structurally equivalent to this block.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *     false otherwise.
   */
  public boolean eqv(Matrice other) {
    return ((this.outline == other.outline) && (this.contents.eqv(other.contents)));
  } // eqv(Circle)
} // class Circle

