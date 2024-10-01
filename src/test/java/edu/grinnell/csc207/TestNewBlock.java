package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.Lines;
import edu.grinnell.csc207.blocks.Matrix;
import edu.grinnell.csc207.blocks.VComp;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.HFlip;
import edu.grinnell.csc207.blocks.VFlip;

/**
 * Tests of the new block.
 */
public class TestNewBlock {
  // +-------+-------------------------------------------------------
  // | Tests |
  // +-------+

  /**
   * Creating a matrix with VComp.
   */
  @Test
  public void matrixVComp() {
    assertEquals(
    new String(
        "  *                 *  \n" +
        " *                   * \n" +
        "*  a B a B a B a B a  *\n" +
        "*    c D c D c D c    *\n" +
        " *                   * \n" +
        "  *                 *  " + "\n" + "\n")
 , TestUtils.toString(new Matrix(
      new VComp(HAlignment.CENTER,
        new Lines("a B a B a B a B a"),
        new Lines ("c D c D c D c")),
        '*')));
  } // matrixVComp()

  /**
   * Creating a matrix with only one element.
   */
  @Test
  public void matrixSingleChar() {
    assertEquals(
      new String(
      "  * *  \n" +
      " *   * \n" +
      "*  L  *\n" +
      " *   * \n" +
      "  * *  \n" + "\n"),
      TestUtils.toString(new Matrix(new Line("L"), '*')));
  } // matrixSingleChar()

  /**
   * Creating a matrix with HFlip.
   */
  @Test
  public void matrixHFlip() {
    assertEquals(
      new String(
    "  *    *  \n" +
    " *      * \n" +
    "*  4  3  *\n" +
    "*  6  5  *\n" +
    " *      * \n" +
    "  *    *  \n" + "\n"),
    TestUtils.toString(new Matrix(new HFlip(new Lines("3  4\n5  6\n")), '*')));
  } // matrixHFlip()

  /**
   * Creating a matrix with VFlip.
   */
  @Test
  public void matrixVFlip() {
    assertEquals(
      new String(
      "  *          *  \n" +
      " *            * \n" +
      "*  helloworld  *\n" +
      "*  Testing     *\n" +
      "*  Alphabet    *\n" +
      " *            * \n" +
      "  *          *  \n" + "\n"),
    TestUtils.toString(new Matrix(new VFlip(new Lines(new String[] { "Alphabet", "Testing", "helloworld" })), '*')));
  } // matrixVFlip()
} // class TestNewBlock
