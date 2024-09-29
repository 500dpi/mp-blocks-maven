package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.Lines;
import edu.grinnell.csc207.blocks.Parenthesis;
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
   * A placeholder.
   */
  @Test
  public void parenthesisVComp() {
    assertEquals(
    new String(
        "  *                 *  \n" + 
        " *                   * \n" + 
        "*  a B a B a B a B a  *\n" +
        "*    c D c D c D c    *\n" + 
        " *                   * \n" + 
        "  *                 *  " + "\n" + "\n")
 , TestUtils.toString(new Parenthesis(
      new VComp(HAlignment.CENTER, 
        new Lines("a B a B a B a B a"), 
        new Lines ("c D c D c D c")), 
        '*')));
  } // placeholder()

  @Test
  public void parenthesisSingleChar() {
    assertEquals(
      new String(
      "  * *  \n" + 
      " *   * \n" + 
      "*  L  *\n" +
      " *   * \n" + 
      "  * *  \n" + "\n"), 
      TestUtils.toString(new Parenthesis(new Line("L"), '*')));
  } // placeholder()

  @Test
  public void parenthesisHFlip() {
    assertEquals(
      new String(
    "  *                 *  \n" +
    " *                   * \n" +
    "*  olleH olleh olleH  *\n" +
    " *                   * \n" +
    "  *                 *  \n" + "\n"), 
    TestUtils.toString(new Parenthesis(new HFlip(new Lines("Hello hello Hello")), '*')));
  } // placeholder()

  @Test
  public void parenthesisVFlip() {
    assertEquals(
      new String(
      "  *          *  \n" +
      " *            * \n" +
      "*  helloworld  *\n" +
      "*  Testing     *\n" +
      "*  Alphabet    *\n" +
      " *            * \n" +
      "  *          *  \n" + "\n"),
    TestUtils.toString(new Parenthesis(new VFlip(new Lines(new String[] { "Alphabet", "Testing", "helloworld" })), '*')));
  } // placeholder()

} // class TestNewBlock
