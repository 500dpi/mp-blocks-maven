package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * Multiple lines of text. The lines are left aligned. Used mostly for
 * testing.
 *
 * @author Samuel A. Rebelsky
 */
public class Lines implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * All of the lines, converted to the same width (the width of the
   * widest string).
   */
  String[] lines;

  /**
   * The width.
   */
  int width;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block.
   *
   * @param contents
   *   The contents of the block.
   */
  public Lines(String[] contents) {
    this.lines = Arrays.copyOf(contents, contents.length);

    // Find the width.
    this.width = 0;
    for (String line : lines) {
      if (line.length() > width) {
        this.width = line.length();
      } // if
    } // for

    // Update the lines to make them the same width.
    for (int i = 0; i < lines.length; i++) {
      this.lines[i] =
          this.lines[i] + " ".repeat(this.width - this.lines[i].length());
    } // for
  } // Lines(String[])

  /**
   * Build a new block from a single line, splitting at newlines.
   *
   * @param contents
   *   The contents of the block.
   */
  public Lines(String contents) {
    this(contents.split("\n"));
  } // Lines(String)

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
    return this.lines[i];
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return this.lines.length;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return this.width;
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
    return ((other instanceof Lines) && this.eqv((Lines) other));
  } // eqv(AsciiBlock)

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(Lines other) {
    return (Arrays.equals(this.lines, other.lines) && (this.width == other.width));
  } // eqv(AsciiBlock)
} // class Lines
