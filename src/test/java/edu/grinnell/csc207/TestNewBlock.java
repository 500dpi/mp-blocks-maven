package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.Lines;
import edu.grinnell.csc207.blocks.Parenthesis;
import edu.grinnell.csc207.blocks.VComp;

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
        "*  a B a B a B a B a  *\n" +"\n" +
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
  public void parenthesisHComp() {
    assertEquals("hello", "hello");
  } // placeholder()

  @Test
  public void parenthesisEmpty() {
    assertEquals("hello", "hello");
  } // placeholder()

  @Test
  public void parenthesisSingleton() {
    assertEquals("hello", "hello");
  } // placeholder()

  @Test
  public void parenthesisHFlip() {
    assertEquals("hello", "hello");
  } // placeholder()

  @Test
  public void parenthesisVFlip() {
    assertEquals("hello", "hello");
  } // placeholder()

} // class TestNewBlock
