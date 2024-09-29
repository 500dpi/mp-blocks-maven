package edu.grinnell.csc207.blocks;

import java.io.PrintWriter;

/**
 * A text block surrounded by a circle.
 *
 * @author Sara Jaljaa
 * @author Jana Vadillo
 */
public class Parenthesis implements AsciiBlock {

  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The stuff in the circle.
   */
  AsciiBlock contents;

  int radius;

  char outline;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new encircled block with the specified contents.
   *
   * @param blockContents
   *   The contents of the block.
   */
  public Parenthesis(AsciiBlock blockContents, char outline) {
    this.contents = blockContents;
    this.outline = outline;
  } // Circle(AsciiBlock)

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
    }

    String outline = String.valueOf(this.outline),
        inner = "  " + outline + " ".repeat(this.width()) + outline + "  " + "\n",
        middle = " " + outline + " " + " ".repeat(this.width()) + " " + outline + " " + "\n",
        textEven = outline + "  " + this.contents.row(i) + "  " + outline;

    String top =  inner + middle;
    String bottom = middle + inner;

    if (this.height() == 1) {
      return top + textEven + "\n" + bottom;
    } else if (this.height() == 0) {
      return top + bottom;
    }
    
    if (i == 0) {
      return top + textEven;
    } else if (i == this.height() - 1) {
      return textEven + "\n" +  bottom;
    } else {
      return textEven;
    }
  }

  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);

    
    AsciiBlock a = new Lines("a B a B a B a B a");
    AsciiBlock b = new Lines ("c D c D c D c");
    AsciiBlock c = new Lines ("L");
    AsciiBlock d = new VComp(HAlignment.CENTER, a, b);
    AsciiBlock e = new Lines("**");
    // AsciiBlock.print(pen, new Parenthesis(a, '*'));
    // AsciiBlock.print(pen, new Parenthesis(b, '*'));
    // AsciiBlock.print(pen, new Parenthesis(c, '*'));
    // AsciiBlock.print(pen, new Parenthesis(d, '*'));
    // AsciiBlock.print(pen, new Parenthesis(e, '*'));
    AsciiBlock.print(pen, new Parenthesis(new VFlip(new Lines(new String[] { "Alphabet", "Testing", "helloworld" })), '*'));
    // AsciiBlock.print(pen, new Circle(d, '-'));
  }

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    if (this.contents.height() == 0) {
      return 6;
    }
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
    }
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
    return ((other instanceof Parenthesis) && (this.contents.eqv((Parenthesis) other)));
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
  public boolean eqv(Parenthesis other) {
    return ((this.outline == other.outline) && (this.contents.eqv(other.contents)));
  } // eqv(Circle)
} // class Circle

