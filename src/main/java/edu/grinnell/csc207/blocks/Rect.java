package edu.grinnell.csc207.blocks;

/**
 * A mutable rectangular block of one repeated character.
 *
 * @author Samuel A. Rebelsky
 */
public class Rect implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * One row of the rectangle.
   */
  String row;

  /**
   * The height of the rectangle.
   */
  int height;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a rectangle.
   *
   * @param ch
   *   The character from which we build the rectangle.
   *
   * @param rectWidth
   *   The width of the rectangle.
   *
   * @param rectHeight
   *   The height of the rectangle.
   */
  public Rect(char ch, int rectWidth, int rectHeight)
      throws Exception {
    // Sanity check
    if (rectWidth <= 0) {
      throw new Exception("Rectangle width must be positive");
    } else if (rectHeight <= 0) {
      throw new Exception("Rectangle height must be positive");
    } // if/else
    // Set up the fields
    this.height = rectHeight;
    this.row = new String(new char[] {ch}).repeat(rectWidth);
  } // Rect(String)

  // +--------------------+------------------------------------------
  // | AsciiBlock methods |
  // +--------------------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   *
   * @return row i.
   *
   * @exception Exception
   *   if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    if ((i < 0) || (i >= this.height())) {
      throw new Exception("Invalid row " + i);
    } // if
    return this.row;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return this.height;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return this.row.length();
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
    return ((other instanceof Rect) && (this.eqv((Rect) other)));
  } // eqv(AsciiBlock)

  /**
   * Determine if another Rect block is structurally equivalent to this
   * Rect block.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two Rect blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(Rect other) {
    return (this.row.equals(other.row) && (this.height == other.height));
  } // eqv(AsciiBlock)

  // +---------------+-----------------------------------------------
  // | Other methods |
  // +---------------+

  /**
   * Make the rectangle wider.
   */
  public void wider() {
    this.row = this.row + this.row.substring(0, 1);
  } // wider()

  /**
   * Make the rectangle narrower.
   *
   * @pre this.width() >= 2
   */
  public void narrower() {
    if (this.row.length() > 1) {
      this.row = this.row.substring(1);
    } // if
  } // narrower()

  /**
   * Make the rectangle taller.
   */
  public void taller() {
    this.height += 1;
  } // taller()

  /**
   * Make the rectangle horter.
   *
   * @pre this.height() >= 2
   */
  public void shorter() {
    if (this.height >= 2) {
      this.height -= 1;
    } // if
  } // shorter()

} // class Rect
