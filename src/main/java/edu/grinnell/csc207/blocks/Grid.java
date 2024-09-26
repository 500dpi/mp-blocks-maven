package edu.grinnell.csc207.blocks;

/**
 * A grid of a single text block.
 *
 * @author Samuel A. Rebelsky
 * @author Jana and Sarah :)
 */
public class Grid implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * One element of the grid.
   */
  AsciiBlock element;

  /**
   * The number of times the element is repeated horizontally.
   */
  int hreps;

  /**
   * The number of times the element is repeated vertically.
   */
  int vreps;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new grid with the specified arrangement.
   *
   * @param gridElement
   *                         The element in the grid.
   * @param horizRepetitions
   *                         The number of horizontal repetitions in the grid.
   * @param vertRepetitions
   *                         THe number of vertical repetitions in the grid.
   */
  public Grid(AsciiBlock gridElement, int horizRepetitions,
      int vertRepetitions) {
    this.element = gridElement;
    this.hreps = horizRepetitions;
    this.vreps = vertRepetitions;
  } // Grid(AsciiBlock, int, int)

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
   *                      If the row is invalid.
   */
  public String row(int i) throws Exception {
    if ((i >= 0) && (i < this.height())) {
      int elementI = i % (this.element.height()); // find the remainder of i and the width of a block to determine what line of the blocks to pull
      return this.element.row(elementI).repeat(this.hreps); // repeat the blocsk as many itterations as you have


    } else {
      throw new Exception("Invalid row " + i);

    }// exception

  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return this.element.height()*this.vreps; // STUB
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return this.element.width()*this.hreps; // STUB
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other
   *              The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *         false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    return ((other instanceof Grid) && (this.eqv((Grid) other)));
  } // eqv(AsciiBlock)

  /**
   * Determine if another grid is structurally equivalent to this grid.
   *
   * @param other
   *              The grid to compare to this grid.
   *
   * @return true if the two blocks are structurally equivalent and
   *         false otherwise.
   */
  public boolean eqv(Grid other) {
    return (this.hreps == other.hreps) && (this.vreps == other.hreps)
        && (this.element.eqv(other.element));
  } // eqv(Grid)
} // class Grid