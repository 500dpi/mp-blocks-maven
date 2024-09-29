package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.Lines;
import edu.grinnell.csc207.blocks.Matrice;
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
   * Creating a matrice with VComp.
   */
  @Test
  public void matriceVComp() {
    assertEquals(
    new String(
        "  *                 *  \n" + 
        " *                   * \n" + 
        "*  a B a B a B a B a  *\n" +
        "*    c D c D c D c    *\n" + 
        " *                   * \n" + 
        "  *                 *  " + "\n" + "\n")
 , TestUtils.toString(new Matrice(
      new VComp(HAlignment.CENTER, 
        new Lines("a B a B a B a B a"), 
        new Lines ("c D c D c D c")), 
        '*')));
  } // matriceVComp()

  /**
   * Creating a matrice with only one element.
   */
  @Test
  public void matriceSingleChar() {
    assertEquals(
      new String(
      "  * *  \n" + 
      " *   * \n" + 
      "*  L  *\n" +
      " *   * \n" + 
      "  * *  \n" + "\n"), 
      TestUtils.toString(new Matrice(new Line("L"), '*')));
  } // matriceSingleChar()

  /**
   * Creating a matrice with HFlip.
   */
  @Test
  public void matriceHFlip() {
    assertEquals(
      new String(
    "  *                 *  \n" +
    " *                   * \n" +
    "*  olleH olleh olleH  *\n" +
    " *                   * \n" +
    "  *                 *  \n" + "\n"), 
    TestUtils.toString(new Matrice(new HFlip(new Lines("Hello hello Hello")), '*')));
  } // matriceHFlip()

  /**
   * Creating a matrice with VFlip.
   */
  @Test
  public void matriceVFlip() {
    assertEquals(
      new String(
      "  *          *  \n" +
      " *            * \n" +
      "*  helloworld  *\n" +
      "*  Testing     *\n" +
      "*  Alphabet    *\n" +
      " *            * \n" +
      "  *          *  \n" + "\n"),
    TestUtils.toString(new Matrice(new VFlip(new Lines(new String[] { "Alphabet", "Testing", "helloworld" })), '*')));
  } // matriceVFlip()

} // class TestNewBlock
