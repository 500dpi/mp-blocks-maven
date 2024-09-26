package edu.grinnell.csc207;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Boxed;
import edu.grinnell.csc207.blocks.Empty;
import edu.grinnell.csc207.blocks.Grid;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.HFlip;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Padded;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.Surrounded;
import edu.grinnell.csc207.blocks.Trimmed;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.VComp;
import edu.grinnell.csc207.blocks.VFlip;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests of the equivalence of various ASCII blocks.
 */
public class TestBlockEquiv {
  // +-------+-------------------------------------------------------
  // | Hacks |
  // +-------+

  /**
   * Create rectangles without throwing exceptions.
   */
  static final AsciiBlock newRect(char ch, int width, int height) {
    try {
      return new Rect(ch, width, height);
    } catch (Exception e) {
      return new Line(String.format("Rect(%c, %d, %d)", ch, width, height));
    } // try/catch
  } // newRect

  // +-------+-------------------------------------------------------
  // | Notes |
  // +-------+

  /*

  We have a bunch of tests that compare 6x4 blocks of the letter X. We'll
  build those blocks in a variety of different ways and make sure that
  (a) they are all `equal` to each other and (b) except when we intend
  them to be the same, the are all not `eqv` to each other.

  We name each `exesTYPE#a` or `exesTYPE#b`, such as `exesRect0a` or
  `exesGrid3b`.

  Elements that end with `b` should be equivalent to elements that end
  with `a` and have the same prefix (or vice versa).

  All of the a variables of each type should be put in the array 
  `exesTYPEsA`. All of the b variables of each type should be put in
  the array `exesTYPEsB`.

  */

  // +----------------+----------------------------------------------
  // | Exes with Rect |
  // +----------------+

  /** Using Rect */
  static AsciiBlock exesRect0a = newRect('X', 6, 4);

  /** Using Rect again. */
  static AsciiBlock exesRect0b = newRect('X', 6, 4);

  /** Rects, A side. */
  static AsciiBlock[] exesRectsA =
    new AsciiBlock[] {exesRect0a};

  /** Rects, B side. */
  static AsciiBlock[] exesRectsB =
    new AsciiBlock[] {exesRect0b };

  // +----------------+----------------------------------------------
  // | Exes with Grid |
  // +----------------+

  /** Using Grid. */
  static AsciiBlock exesGrid0a = new Grid(new Line("X"), 6, 4);

  /** Using Grid again. */
  static AsciiBlock exesGrid0b = new Grid(new Line("X"), 6, 4);

  /** Using Grid and Rect0a. */
  static AsciiBlock exesGrid1a = new Grid(exesRect0a, 1, 1);

  /** Using Grid and Rect0b. */
  static AsciiBlock exesGrid1b = new Grid(exesRect0b, 1, 1);

  /** Using a different Grid. */
  static AsciiBlock exesGrid2a = new Grid(newRect('X', 3, 2), 2, 2);

  /** And another. */
  static AsciiBlock exesGrid3a = new Grid(new Grid(new Line("X"), 3, 2), 2, 2);

  /** Grids, A side. */
  static AsciiBlock[] exesGridsA =
      new AsciiBlock[] {exesGrid0a, exesGrid1a, exesGrid2a, exesGrid3a };

  /** Grids, B side. */
  static AsciiBlock[] exesGridsB =
      new AsciiBlock[] {exesGrid0b, null, null, null };

  // +-----------------+---------------------------------------------
  // | Exes with HComp |
  // +-----------------+

  /** Something to help with our horizontal composition */
  static AsciiBlock tmp1 = newRect('X', 2, 4);

  /** Using horizontal composition. */
  static AsciiBlock exesHComp0a =
      new HComp(VAlignment.TOP, new AsciiBlock[] {tmp1, tmp1, tmp1});

  /** Using an equivalent horizontal composition. */
  static AsciiBlock exesHComp0b =
      new HComp(VAlignment.TOP,
          new AsciiBlock[] {
              newRect('X', 2, 4), newRect('X', 2, 4), newRect('X', 2, 4)});

  /** Using horizontal composition of exesComp0a with empty. */
  static AsciiBlock exesHComp1a =
      new HComp(VAlignment.TOP,
          new AsciiBlock[] {exesHComp0a, new Empty()});

  /** Using horizontal composition of exesComp0b with empty. */
  static AsciiBlock exesHComp1b =
      new HComp(VAlignment.TOP,
          new AsciiBlock[] {exesHComp0b, new Empty()});

  /** Using horizontal composition of the 0a blocks and a diff alignment. */
  static AsciiBlock exesHComp2a =
      new HComp(VAlignment.CENTER,
          new AsciiBlock[] {exesHComp0a, new Empty()});

  /** Similar. */
  static AsciiBlock exesHComp2b =
      new HComp(VAlignment.CENTER,
          new AsciiBlock[] {exesHComp0b, new Empty()});

  /** Using horizontal composition of the 0a blocks and YA alignment. */
  static AsciiBlock exesHComp3a =
      new HComp(VAlignment.BOTTOM,
          new AsciiBlock[] {exesHComp0a, new Empty()});

  /** Similar. */
  static AsciiBlock exesHComp3b =
      new HComp(VAlignment.BOTTOM,
          new AsciiBlock[] {exesHComp0b, new Empty()});

  /** A lot like 3a, except the empty is on the other side. */
  static AsciiBlock exesHComp4a =
      new HComp(VAlignment.BOTTOM,
          new AsciiBlock[] {new Empty(), exesHComp0a});

   /** Ditto, but with 3a. */
  static AsciiBlock exesHComp4b =
      new HComp(VAlignment.BOTTOM,
          new AsciiBlock[] {new Empty(), exesHComp0a});

  /** Horizontal composition of the 4a block with empty. */
  static AsciiBlock exesHComp5a =
      new HComp(VAlignment.CENTER,
          new AsciiBlock[] {new Empty(), exesHComp4a, new Empty()});

  /** Horizontal composition of the 4b block with empty. */
  static AsciiBlock exesHComp5b =
      new HComp(VAlignment.CENTER,
          new AsciiBlock[] {new Empty(), exesHComp4b, new Empty()});

  /** Like 0a, but with a different alignment. */
  static AsciiBlock exesHComp6a =
      new HComp(VAlignment.BOTTOM, new AsciiBlock[] {tmp1, tmp1, tmp1});

  /** Using an equivalent horizontal composition. */
  static AsciiBlock exesHComp6b =
      new HComp(VAlignment.BOTTOM,
          new AsciiBlock[] {
              newRect('X', 2, 4), newRect('X', 2, 4), newRect('X', 2, 4)});

  /** A lot like 5a, but using a different subblock. **/
  static AsciiBlock exesHComp7a =
      new HComp(VAlignment.CENTER,
          new AsciiBlock[] {new Empty(), exesHComp3a, new Empty()});

  /** A lot like 5b, but using a different subblock. **/
  static AsciiBlock exesHComp7b =
      new HComp(VAlignment.CENTER,
          new AsciiBlock[] {new Empty(), exesHComp3b, new Empty()});

  /** HComps, A side. */
  static AsciiBlock[] exesHCompsA =
    new AsciiBlock[] {exesHComp0a, exesHComp1a, exesHComp2a, exesHComp3a,
        exesHComp4a, exesHComp5a, exesHComp6a, exesHComp7a };

  /** HComps, B side. */
  static AsciiBlock[] exesHCompsB =
    new AsciiBlock[] {exesHComp0b, exesHComp1b, exesHComp2b, exesHComp3b,
        exesHComp4b, exesHComp5b, exesHComp6b, exesHComp7b };

  // +-----------------+---------------------------------------------
  // | Exes with VComp |
  // +-----------------+

  /** Something to help with our vertical composition */
  static AsciiBlock tmp2 = new Line("XXXXXX");

  /** Using vertical composition. */
  static AsciiBlock exesVComp0a =
      new VComp(HAlignment.LEFT, new AsciiBlock[] {tmp2, tmp2, tmp2, tmp2});

  /** Using an equivalent vertical composition. */
  static AsciiBlock exesVComp0b =
      new VComp(HAlignment.LEFT,
          new AsciiBlock[] {
              new Line("XXXXXX"), new Line("XXXXXX"),
              new Line("XXXXXX"), new Line("XXXXXX") });

  /** Using vertical composition of exesComp0a with empty. */
  static AsciiBlock exesVComp1a =
      new VComp(HAlignment.LEFT,
          new AsciiBlock[] {exesVComp0a, new Empty()});

  /** Using vertical composition of exesComp0b with empty. */
  static AsciiBlock exesVComp1b =
      new VComp(HAlignment.LEFT,
          new AsciiBlock[] {exesVComp0b, new Empty()});

  /** Using vertical composition of the 0a blocks and a diff alignment. */
  static AsciiBlock exesVComp2a =
      new VComp(HAlignment.CENTER,
          new AsciiBlock[] {exesVComp0a, new Empty()});

  /** Similar. */
  static AsciiBlock exesVComp2b =
      new VComp(HAlignment.CENTER,
          new AsciiBlock[] {exesVComp0b, new Empty()});

  /** Using vertical composition of the 0a blocks and YA alignment. */
  static AsciiBlock exesVComp3a =
      new VComp(HAlignment.RIGHT,
          new AsciiBlock[] {exesVComp0a, new Empty()});

  /** Similar. */
  static AsciiBlock exesVComp3b =
      new VComp(HAlignment.RIGHT,
          new AsciiBlock[] {exesVComp0b, new Empty()});

  /** A lot like 3a, except the empty is on the other side. */
  static AsciiBlock exesVComp4a =
      new VComp(HAlignment.RIGHT,
          new AsciiBlock[] {new Empty(), exesVComp0a});

   /** Ditto, but with 3a. */
  static AsciiBlock exesVComp4b =
      new VComp(HAlignment.RIGHT,
          new AsciiBlock[] {new Empty(), exesVComp0a});

  /** Horizontal composition of the 4a block with empty. */
  static AsciiBlock exesVComp5a =
      new VComp(HAlignment.CENTER,
          new AsciiBlock[] {new Empty(), exesVComp4a, new Empty()});

  /** Horizontal composition of the 4b block with empty. */
  static AsciiBlock exesVComp5b =
      new VComp(HAlignment.CENTER,
          new AsciiBlock[] {new Empty(), exesVComp4b, new Empty()});

  /** Like 0a, but with a different alignment. */
  static AsciiBlock exesVComp6a =
      new VComp(HAlignment.RIGHT, new AsciiBlock[] {tmp2, tmp2, tmp2, tmp2});

  /** Using an equivalent vertical composition. */
  static AsciiBlock exesVComp6b =
      new VComp(HAlignment.RIGHT,
          new AsciiBlock[] {
              new Line("XXXXXX"), new Line("XXXXXX"),
              new Line("XXXXXX"), new Line("XXXXXX")});

  /** A lot like 5a, but using a different subblock. **/
  static AsciiBlock exesVComp7a =
      new VComp(HAlignment.CENTER,
          new AsciiBlock[] {new Empty(), exesVComp3a, new Empty()});

  /** A lot like 5b, but using a different subblock. **/
  static AsciiBlock exesVComp7b =
      new VComp(HAlignment.CENTER,
          new AsciiBlock[] {new Empty(), exesVComp3b, new Empty()});

  /** Nesting! */
  static AsciiBlock exesVComp8a = 
      new VComp(HAlignment.CENTER,
          new AsciiBlock[] {
              new VComp(HAlignment.RIGHT, new AsciiBlock[] { tmp2, tmp2 }),
              new VComp(HAlignment.RIGHT, new AsciiBlock[] { tmp2, tmp2 })});

  /** Similar nesting! */
  static AsciiBlock exesVComp8b = 
      new VComp(HAlignment.CENTER,
          new AsciiBlock[] {
              new VComp(HAlignment.RIGHT, new AsciiBlock[] { tmp2, tmp2 }),
              new VComp(HAlignment.RIGHT, 
                  new AsciiBlock[] { tmp2, new Line("XXXXXX") })});

  /** Similar to 8a, but with a modified subblock. */
  static AsciiBlock exesVComp9a = 
      new VComp(HAlignment.CENTER,
          new AsciiBlock[] {
              new VComp(HAlignment.RIGHT, new AsciiBlock[] { tmp2, tmp2 }),
              new VComp(HAlignment.LEFT, new AsciiBlock[] { tmp2, tmp2 })});

  /** Similar */
  static AsciiBlock exesVComp9b = 
      new VComp(HAlignment.CENTER,
          new AsciiBlock[] {
              new VComp(HAlignment.RIGHT,
                  new AsciiBlock[] { tmp2, new Line("XXXXXX") }),
              new VComp(HAlignment.LEFT, new AsciiBlock[] { tmp2, tmp2 })});

  /** VComps, A side. */
  static AsciiBlock[] exesVCompsA =
      new AsciiBlock[] {exesVComp0a, exesVComp1a, exesVComp2a, exesVComp3a,
          exesVComp4a, exesVComp5a, exesVComp6a, exesVComp7a, exesVComp8a,
          exesVComp9a};

  /** VComps, B side. */
  static AsciiBlock[] exesVCompsB =
      new AsciiBlock[] {exesVComp0b, exesVComp1b, exesVComp2b, exesVComp3b,
          exesVComp4b, exesVComp5b, exesVComp6b, exesVComp7b, exesVComp8b,
          exesVComp9b};

  // +-----------------+---------------------------------------------
  // | Exes with HFlip |
  // +-----------------+

  /** Flip something. */
  static AsciiBlock exesHFlip0a = new HFlip(exesVComp9a);

  /** Flip its pair. */
  static AsciiBlock exesHFlip0b = new HFlip(exesVComp9b);

  /** Flip again. */
  static AsciiBlock exesHFlip1a = new HFlip(exesHFlip0a);

  /** Flip again. */
  static AsciiBlock exesHFlip1b = new HFlip(exesHFlip0b);

  /** And again. */
  static AsciiBlock exesHFlip2a = new HFlip(exesHFlip1a);

  /** And again. */
  static AsciiBlock exesHFlip2b = new HFlip(new HFlip(exesHFlip0b));

  /** There are never too many flips, are there? */
  static AsciiBlock exesHFlip3a = new HFlip(exesHFlip2a);

  /** And again. */
  static AsciiBlock exesHFlip3b = new HFlip(new HFlip(new HFlip(exesHFlip0b)));

  /** Once more, for good luck. */
  static AsciiBlock exesHFlip4a = new HFlip(exesHFlip3a);

  /** And again. */
  static AsciiBlock exesHFlip4b = new HFlip(new HFlip(new HFlip(new HFlip(exesHFlip0b))));

  /** Now, let's slightly modify the base value. */
  static AsciiBlock exesHFlip5a = 
      new HFlip(new HFlip(new HFlip(new HFlip(new HFlip(
          new Grid(exesVComp9a, 1, 1))))));

  //** Do it again. */
  static AsciiBlock exesHFlip5b = 
      new HFlip(new HFlip(new HFlip(new HFlip(new HFlip(
          new Grid(exesVComp9b, 1, 1))))));

  /** HFlips, A side. */
  static AsciiBlock[] exesHFlipsA =
      new AsciiBlock[] {exesHFlip0a, exesHFlip1a, exesHFlip2a, exesHFlip3a,
          exesHFlip4a, exesHFlip5a};

  /** HFlips, B Side. */
  static AsciiBlock[] exesHFlipsB =
      new AsciiBlock[] {exesHFlip0b, exesHFlip1b, exesHFlip2b, exesHFlip3b,
          exesHFlip4b, exesHFlip5b};
  
  // +-----------------+---------------------------------------------
  // | Exes with VFlip |
  // +-----------------+

  /** Flip something. */
  static AsciiBlock exesVFlip0a = new VFlip(exesVComp9a);

  /** Flip its pair. */
  static AsciiBlock exesVFlip0b = new VFlip(exesVComp9b);

  /** Flip again. */
  static AsciiBlock exesVFlip1a = new VFlip(exesVFlip0a);

  /** Flip again. */
  static AsciiBlock exesVFlip1b = new VFlip(exesVFlip0b);

  /** And again. */
  static AsciiBlock exesVFlip2a = new VFlip(exesVFlip1a);

  /** And again. */
  static AsciiBlock exesVFlip2b = new VFlip(new VFlip(exesVFlip0b));

  /** There are never too many flips, are there? */
  static AsciiBlock exesVFlip3a = new VFlip(exesVFlip2a);

  /** And again. */
  static AsciiBlock exesVFlip3b = new VFlip(new VFlip(new VFlip(exesVFlip0b)));

  /** Once more, for good luck. */
  static AsciiBlock exesVFlip4a = new VFlip(exesVFlip3a);

  /** And again. */
  static AsciiBlock exesVFlip4b = new VFlip(new VFlip(new VFlip(new VFlip(exesVFlip0b))));

  /** Now, let's slightly modify the base value. */
  static AsciiBlock exesVFlip5a = 
      new VFlip(new VFlip(new VFlip(new VFlip(new VFlip(
          new Grid(exesVComp9a, 1, 1))))));

  //** Do it again. */
  static AsciiBlock exesVFlip5b = 
      new VFlip(new VFlip(new VFlip(new VFlip(new VFlip(
          new Grid(exesVComp9b, 1, 1))))));

  /** VFlips, A side. */
  static AsciiBlock[] exesVFlipsA =
      new AsciiBlock[] {exesVFlip0a, exesVFlip1a, exesVFlip2a, exesVFlip3a,
          exesVFlip4a, exesVFlip5a};

  /** VFlips, B Side. */
  static AsciiBlock[] exesVFlipsB =
      new AsciiBlock[] {exesVFlip0b, exesVFlip1b, exesVFlip2b, exesVFlip3b,
          exesVFlip4b, exesVFlip5b};
  
  // +-------+-------------------------------------------------------
  // | Empty |
  // +-------+

  /**
   * Make sure two empty blocks are equivalent.
   */
  @Test
  public void testEqvEmpty() {
    assertTrue((new Empty()).eqv(new Empty()),
        "R: Empty blocks are equivalent");
  } // testEqvEmpty

  /**
   * Make sure that an empty block is not equivalent to any other
   * type of block.
   */
  @Test
  public void testNotEqvEmpty() throws Exception {
    AsciiBlock empty = new Empty();
    assertFalse(empty.eqv(new Line("Hello")),
        "M: Empty block is not equivalent to a line");
    assertFalse(empty.eqv(new Line("Hello")),
        "E: Empty block is not equivalent to an empty line");
    assertFalse(empty.eqv(new Rect('x', 2, 5)),
        "M: Empty block is not equivalent to a rectangle");
    assertFalse(empty.eqv(new Boxed(new Rect('x', 2, 5))),
        "M: Empty block is not equivalent to a boxed rectangle");
    assertFalse(empty.eqv(new Surrounded(new Line("Whatever"), 'x')),
        "M: Empty block is not equivalent to a surrounded line");
    assertFalse(empty.eqv(new Grid(empty, 1, 1)),
        "E: Empty block is not equivalent to a 1x1 grid of the empty block");
    assertFalse(empty.eqv(new HComp(VAlignment.CENTER, new AsciiBlock[] {})),
        "E: Empty block is not equivalent to an empty horiz composition");
    assertFalse(
        empty.eqv(new HComp(VAlignment.BOTTOM,
            new AsciiBlock[] {new Line("One")})),
        "M: Empty block is not equivalent to a singleton hcomp");
    assertFalse(empty.eqv(new VComp(HAlignment.LEFT, new AsciiBlock[] {})),
        "E: Empty block is not equivalent to an empty vertical composition");
    assertFalse(
         empty.eqv(new VComp(HAlignment.RIGHT,
             new AsciiBlock[] {new Line("One")})),
        "M: Empty block is not equivalent to a singleton vcomp");
    assertFalse(empty.eqv(new HFlip(empty)),
        "M: Empty block is not equivalent to horizontally flipped empty block");
    assertFalse(empty.eqv(new VFlip(empty)),
        "M: Empty block is not equivalent to vertically flipped empty block");
    assertFalse(
        empty.eqv(new Trimmed(new Line("Hello"), HAlignment.LEFT,
            VAlignment.TOP, 0, 0)),
        "E: Empty block is not equivalent to a trimmed empty block");
    assertFalse(
        empty.eqv(new Padded(empty, 'x', HAlignment.LEFT, VAlignment.TOP,
            0, 0)),
        "E: Empty block is not equivalent to a padded empty block");
  } // testNotEqvEmpty()

  // +-------+-------------------------------------------------------
  // | Lines |
  // +-------+

  /**
   * Make sure two equivalent lines are equivalent.
   */
  @Test
  public void testEqvLine() {
    assertTrue((new Line("")).eqv(new Line("")),
        "M: Empty lines are equivalent");
    assertTrue((new Line("Hello")).eqv(new Line("Hello")),
        "M: Lines with the same string are equivalent");
  } // testEqvLine

  /**
   * Make sure that lines become equivalent after changing.
   */
  @Test
  public void testEqvLineChange() {
    Line line1 = new Line("Hello");
    Line line2 = new Line("Goodbye");
    line2.update("Hello");
    assertTrue(line1.eqv(line2),
        "E: Updated lines are equivalent");
    line1.update("Hi");
    line2.update("Hi");
    assertTrue(line2.eqv(line2),
        "E: Re-updated lines are equivalent");
  } // testEqvLineChange()

  /**
   * Make sure that a line is not equivalent to any other
   * type of block.
   */
  @Test
  public void testNotEqvLine() throws Exception {
    AsciiBlock line = new Line("OOO");
    assertFalse(line.eqv(new Line("Hello")),
        "M: Line OOO is not equivalent to a different line");
    assertFalse(line.eqv(new Rect('x', 2, 5)),
        "M: Line OOO is not equivalent to a rectangle");
    assertFalse(line.eqv(new Rect('O', 3, 1)),
        "E: Line OOO is not equivalent a rectangle that appears the same");
    assertFalse(line.eqv(new Boxed(new Rect('O', 3, 1))),
        "M: Line OOO is not equivalent to a boxed rectangle");
    assertFalse(line.eqv(new Surrounded(new Line("O"), 'O')),
        "M: Line OOO is not equivalent to a surrounded line");
    assertFalse(line.eqv(new Grid(line, 1, 1)),
        "E: Line OOO is not equivalent to a 1x1 grid of the same line");
    assertFalse(line.eqv(new Grid(new Line("O"), 3, 1)),
        "E: Line OOO is not equivalent to a 3x1 grid of O");
    assertFalse(line.eqv(new HComp(VAlignment.CENTER, new AsciiBlock[] {})),
        "E: Line OOO is not equivalent to an empty horiz composition");
    assertFalse(
        line.eqv(new HComp(VAlignment.BOTTOM, new AsciiBlock[] {line})),
        "M: Line OOO is not equivalent to a singleton hcomp of that line");
    assertFalse(
        line.eqv(new HComp(VAlignment.BOTTOM,
            new AsciiBlock[] {line, new Empty()})),
        "M: Line OOO is not equivalent to an hcomp of that line and empty");
    assertFalse(
        line.eqv(new HComp(VAlignment.BOTTOM,
            new AsciiBlock[] {new Line("O"), new Line("O"), new Line("O")})),
        "M: Line OOO is not equivalent to an hcomp of three O's");
    assertFalse(line.eqv(new VComp(HAlignment.LEFT, new AsciiBlock[] {})),
        "E: Line OOO is not equivalent to an empty vertical composition");
    assertFalse(
         line.eqv(new VComp(HAlignment.RIGHT, new AsciiBlock[] {line})),
        "M: Line OOO is not equivalent to a singleton vcomp of the same line");
    assertFalse(line.eqv(new HFlip(line)),
        "M: Line OOO is not equivalent to horizontally flipped line block");
    assertFalse(line.eqv(new VFlip(line)),
        "M: Line OOO is not equivalent to vertically flipped line block");
    assertFalse(
        line.eqv(new Trimmed(new Line("OOOhhhh"), HAlignment.LEFT,
            VAlignment.TOP, 3, 1)),
        "E: Line OOO is not equivalent to a trimmed line block");
    assertFalse(
        line.eqv(new Padded(line, 'x', HAlignment.LEFT, VAlignment.TOP,
            3, 0)),
        "E: Line OOO is not equivalent to a padded line block");
  } // testNotEqvLine()

  // +------------+--------------------------------------------------
  // | Rectangles |
  // +------------+

  /**
   * Make sure two rectangles are equivalent.
   *
   * @throws Exception
   *   If the `Rect` constructor happens to do so.
   */
  @Test
  public void testEqvRect() throws Exception {
    assertTrue((new Rect('x', 1, 1)).eqv(new Rect('x', 1, 1)),
        "M: Two 1x1 rectangles are eqivalent");
    assertTrue((new Rect('y', 3, 5)).eqv(new Rect('y', 3, 5)),
        "M: Two 3x5 rectangles are eqivalent");
  } // testEqvRect

  /**
   * Make sure that two updated rectangles are equivalent.
   *
   * @throws Exception
   *   If the `Rect` constructor happens to do so.
   */
  @Test
  public void testEqvRectChange() throws Exception {
    Rect rect1 = new Rect('R', 3, 2);
    Rect rect2 = new Rect('R', 3, 3);
    Rect rect3 = new Rect('R', 3, 1);

    rect2.shorter();
    assertTrue(rect1.eqv(rect2),
        "M: Equivalent after shortening");
    assertTrue(rect2.eqv(rect1),
        "M: Equivalent after shortening/inverse");

    rect3.taller();
    assertTrue(rect2.eqv(rect3),
        "M: One rectangle shorter, one taller");
    assertTrue(rect3.eqv(rect2),
        "M: One rectangle taller, one shorter");

    rect1.narrower();
    rect2.narrower();
    assertTrue(rect1.eqv(rect2),
        "M: Both rectangles narrower");
  } // testEqvRectChange()

  /**
   * Make sure that a rectangle is not equivalent to any other
   * type of block.
   *
   * @throws Exception
   *   If the `Rect` constructor happens to do so.
   */
  @Test
  public void testNotEqvRect() throws Exception {
    AsciiBlock rect = new Rect('R', 3, 2);
    assertFalse(rect.eqv(new Line("RRRRRR")),
        "M: Sample rect is not equivalent to a line");
    assertFalse(rect.eqv(new Rect('r', 3, 2)),
        "M: Sample rect is not eqv to a diff rectangle of same dimensions");
    assertFalse(rect.eqv(new Rect('R', 2, 3)),
        "M: Sample rect is not equivalent to a rectangle of inverted dimensions");
    assertFalse(rect.eqv(new Boxed(rect)),
        "M: Sample rect is not equivalent to a boxed rectangle");
    assertFalse(rect.eqv(new Surrounded(new Empty(), 'X')),
        "E: Sample rect is not eqv to a surrounded empty");
    assertFalse(rect.eqv(new Grid(rect, 1, 1)),
        "E: Sample rect is not equivalent to a 1x1 grid of the rect block");
    assertFalse(rect.eqv(new HComp(VAlignment.CENTER, new AsciiBlock[] {})),
        "E: Sample rect is not equivalent to an empty horiz composition");
    assertFalse(rect.eqv(new HComp(VAlignment.BOTTOM, new AsciiBlock[] {rect})),
        "M: Sample rect is not equivalent to a singleton hcomp");
    AsciiBlock rr = new Rect('R', 1, 2);
    assertFalse(
        rect.eqv(new HComp(VAlignment.TOP, new AsciiBlock[] {rr, rr, rr})),
        "E: Sample rect is not equivalent to a similar hcomp");
    assertFalse(rect.eqv(new VComp(HAlignment.LEFT, new AsciiBlock[] {})),
        "E: Sample rect is not equivalent to an empty vertical composition");
    assertFalse(
        rect.eqv(new VComp(HAlignment.RIGHT,
             new AsciiBlock[] {new Line("RRR")})),
        "M: Sample rect is not equivalent to a singleton vcomp");
    assertFalse(
        rect.eqv(new VComp(HAlignment.LEFT,
             new AsciiBlock[] {new Line("RRR"), new Line("RRR")})),
        "E: Sample rect is not equivalent to a similar-appearing vcomp");
    assertFalse(rect.eqv(new HFlip(rect)),
        "M: Sample rect is not equivalent to horizontally flipped version");
    assertFalse(rect.eqv(new VFlip(rect)),
        "M: Sample rect is not equivalent to vertically flipped version");
    assertFalse(
        rect.eqv(new Trimmed(rect, HAlignment.LEFT, VAlignment.TOP, 3, 2)),
        "E: Sample rect is not equivalent to the same rect, trimmed");
    assertFalse(
        rect.eqv(new Trimmed(new Rect('R', 10, 10), HAlignment.LEFT,
            VAlignment.TOP, 3, 2)),
        "E: Sample rect is not equivalent to a similar trimmed rect");
    assertFalse(
        rect.eqv(new Padded(new Empty(), 'R', HAlignment.LEFT, VAlignment.TOP,
            3, 2)),
        "E: Sample rect is not equivalent to a padded empty block");
    assertFalse(
        rect.eqv(new Padded(rect, 'R', HAlignment.LEFT, VAlignment.TOP,
            3, 2)),
        "E: Sample rect is not equivalent to a 0-padded version of the same block");
  } // testNotEqvRect()

  // +-------+-------------------------------------------------------
  // | Boxed |
  // +-------+

  /**
   * Make sure two equivalent boxed blocks are equivalent.
   */
  @Test
  public void testEqvBoxed() throws Exception {
    assertTrue((new Boxed(new Empty())).eqv(new Boxed(new Empty())),
        "M: Boxed empty blocks are equivalent");
    assertTrue((new Boxed(new Line(""))).eqv(new Boxed(new Line(""))),
        "M: Boxed empty lines are equivalent");
    assertTrue((new Boxed(new Line("Foo"))).eqv(new Boxed(new Line("Foo"))),
        "M: Boxed nonempty lines are equivalent");
    assertTrue((new Boxed(new Rect('@', 3, 2))).eqv(
        new Boxed(new Rect('@', 3, 2))),
        "M: Boxed equivalent rectangles are equivalent");
    assertTrue((new Boxed(new Boxed(new Line("Foo")))).eqv(
        new Boxed(new Boxed(new Line("Foo")))),
        "M: Doubly boxed lines are equivalent");
  } // testEqvBoxed

  /**
   * Make sure that a box is not equivalent to any other
   * type of block.
   */
  @Test
  public void testNotEqvBoxed() throws Exception {
    AsciiBlock box = new Boxed(new Line("line"));
    assertFalse((new Boxed(new Line("alpha"))).eqv(new Boxed(new Line("beta"))),
        "M: Two different boxed lines are different");
    assertFalse((new Boxed(new Rect('$', 3, 2))).eqv(
        new Boxed(new Rect('$', 2, 3))),
        "M: Two different boxed rects are different");
    assertFalse(box.eqv(new Line("line")),
        "M: Sample box is not equivalent to a line");
    assertFalse(box.eqv(new Rect('x', 2, 5)),
        "M: Sample box is not equivalent to a rectangle");
    assertFalse(box.eqv(new Surrounded(new Line("box"), '"')),
        "M: Sample box is not equivalent to a surrounded box");
    assertFalse(box.eqv(new Grid(box, 1, 1)),
        "M: Sample box is not equivalent to a 1x1 grid of the same box");
    assertFalse(box.eqv(new Grid(new Line("O"), 3, 1)),
        "M: Sample box is not equivalent to a 3x1 grid of O");
    assertFalse(box.eqv(new HComp(VAlignment.CENTER, new AsciiBlock[] {})),
        "M: Sample box is not equivalent to an empty horiz composition");
    assertFalse(
        box.eqv(new HComp(VAlignment.BOTTOM, new AsciiBlock[] {box})),
        "M: Sample box is not equivalent to a singleton hcomp of that box");
    assertFalse(
        box.eqv(new HComp(VAlignment.BOTTOM,
            new AsciiBlock[] {box, new Empty()})),
        "M: Sample box is not equivalent to an hcomp of that box and empty");
    assertFalse(
        box.eqv(new HComp(VAlignment.BOTTOM,
            new AsciiBlock[] {new Line("O"), new Line("O"), new Line("O")})),
        "M: Sample box is not equivalent to an hcomp of three O's");
    assertFalse(box.eqv(new VComp(HAlignment.LEFT, new AsciiBlock[] {})),
        "M: Sample box is not equivalent to an empty vertical composition");
    assertFalse(
         box.eqv(new VComp(HAlignment.RIGHT, new AsciiBlock[] {box})),
        "M: Sample box is not equivalent to a singleton vcomp of the same box");
    assertFalse(box.eqv(new HFlip(box)),
        "M: Sample box is not equivalent to horizontally flipped box block");
    assertFalse(box.eqv(new VFlip(box)),
        "M: Sample box is not equivalent to vertically flipped box block");
    assertFalse(
        box.eqv(new Trimmed(new Line("OOOhhhh"), HAlignment.LEFT,
            VAlignment.TOP, 3, 1)),
        "M: Sample box is not equivalent to a trimmed box block");
    assertFalse(
        box.eqv(new Padded(box, 'x', HAlignment.LEFT, VAlignment.TOP,
            3, 0)),
        "M: Sample box is not equivalent to a padded box block");
  } // testNotEqvBox()

  // +------------+--------------------------------------------------
  // | Surrounded |
  // +------------+

  /**
   * Make sure two equivalent surrounded blocks are equivalent.
   */
  @Test
  public void testEqvSurrounded() throws Exception {
    assertTrue(
        (new Surrounded(new Empty(), ' ')).eqv(
            new Surrounded(new Empty(), ' ')),
        "M: Surrounded empty blocks are equivalent");
    assertTrue(
        (new Surrounded(new Line(""), 'a')).eqv(
            new Surrounded(new Line(""), 'a')),
        "M: Surrounded empty lines are equivalent");
    assertTrue(
        (new Surrounded(new Line("Foo"), 'b')).eqv(
            new Surrounded(new Line("Foo"), 'b')),
        "M: Surrounded nonempty lines are equivalent");
    assertTrue(
        (new Surrounded(new Rect('@', 3, 2), 'd')).eqv(
            new Surrounded(new Rect('@', 3, 2), 'd')),
        "M: Surrounded equivalent rectangles are equivalent");
    assertTrue(
        (new Surrounded(new Surrounded(new Line("Foo"), ' '), '*')).eqv(
            new Surrounded(new Surrounded(new Line("Foo"), ' '), '*')),
        "M: Doubly surrounded lines are equivalent");
  } // testEqvSurrounded

  /**
   * Make sure that different surrounded boxes are different.
   */
  @Test
  public void testNotEqvDiffSurrounded() throws Exception {
    assertFalse(
       (new Surrounded(new Empty(), ' ')).eqv(
           new Surrounded(new Line(""), ' ')),
       "M: Surrounded empty is not the same as a surrounded line");
    assertFalse(
       (new Surrounded(new Line("a"), 'b')).eqv(
           new Surrounded(new Line("b"), 'b')),
       "M: Surrounded different lines are different");
    assertFalse(
       (new Surrounded(new Rect('c', 3, 2), 'd')).eqv(
           (new Surrounded(new Rect('c', 3, 3), 'd'))),
       "M: Surrounded different rectangles are different");
    assertFalse(
       (new Surrounded(new Surrounded(new Line ("a"), ' '), ' ')).eqv(
           (new Surrounded(new Line("a"), ' '))),
       "M: Doubly surrounded is not the same as singly surrounded.");
    assertFalse(
       (new Surrounded(new Line("a"), ' ')).eqv(
           (new Surrounded(new Surrounded(new Line ("a"), ' '), ' '))),
       "M: Singly surrounded is not the same as doubly surrounded.");
  } // testNotEqvDiffSurrounded()

  /**
   * Make sure that a box is not equivalent to any other
   * type of block.
   */
  @Test
  public void testNotEqvSurrounded() throws Exception {
    AsciiBlock block = new Surrounded(new Line("A"), 'A');
    assertFalse(block.eqv(new Line("line")),
        "M: Sample surround is not equivalent to a line");
    assertFalse(block.eqv(new Rect('A', 3, 3)),
        "M: Sample surround is not equivalent to a similar rectangle");
    assertFalse(block.eqv(new Grid(block, 1, 1)),
        "E: Sample surround is not equivalent to a 1x1 grid of the same block");
    assertFalse(block.eqv(new Grid(new Line("A"), 3, 3)),
        "E: Sample surround is not equivalent to a 3x3 grid of A");
    assertFalse(block.eqv(new HComp(VAlignment.CENTER, new AsciiBlock[] {})),
        "M: Sample surround is not equivalent to an empty horiz composition");
    assertFalse(
        block.eqv(new HComp(VAlignment.BOTTOM, new AsciiBlock[] {block})),
        "E: Sample surround is not eqv to a singleton hcomp of that block");
    assertFalse(
        block.eqv(new HComp(VAlignment.BOTTOM,
            new AsciiBlock[] {block, new Empty()})),
        "M: Sample surround is not eqv to an hcomp of that block and empty");
    assertFalse(
        block.eqv(new HComp(VAlignment.BOTTOM,
            new AsciiBlock[] {new Line("O"), new Line("O"), new Line("O")})),
        "M: Sample surround is not equivalent to an hcomp of three O's");
    assertFalse(block.eqv(new VComp(HAlignment.LEFT, new AsciiBlock[] {})),
        "M: Sample surround is not eqv to an empty vertical composition");
    assertFalse(
         block.eqv(new VComp(HAlignment.RIGHT, new AsciiBlock[] {block})),
        "E: Sample surround is not eqv a singleton vcomp of the block");
    assertFalse(block.eqv(new HFlip(block)),
        "M: Sample surround is not eqv to horizontally flipped surround");
    assertFalse(block.eqv(new VFlip(block)),
        "M: Sample surround is not eqv to vertically flipped surround");
    assertFalse(
        block.eqv(new Trimmed(block, HAlignment.LEFT,
            VAlignment.TOP, 3, 3)),
        "E: Sample surround is not equivalent to a trimmed surround");
    assertFalse(
        block.eqv(new Trimmed(new Surrounded(block, 'A'), HAlignment.CENTER,
            VAlignment.CENTER, 3, 3)),
        "E: Sample surround is not equivalent to a trimmed bigger surround");
    assertFalse(
        block.eqv(new Padded(block, 'x', HAlignment.LEFT, VAlignment.TOP,
            3, 3)),
        "E: Sample surround is not equivalent to a padded box block");
    assertFalse(
        block.eqv(new Padded(new Line("A"), 'A', HAlignment.LEFT,
            VAlignment.TOP, 3, 3)),
        "E: Sample surround is not eqv to a similar looking padded box");
  } // testNotEqvSurrounded()

  // +-------+-------------------------------------------------------
  // | Grids |
  // +-------+

  /**
   * Make sure two simple grids are equivalent.
   *
   * @throws Exception
   *   If the `Rect` constructor throws an exception.
   */
  @Test
  public void testEqvGrid() throws Exception {
    assertTrue(
        (new Grid(new Line("XYZ"), 1, 1)).eqv(new Grid(new Line("XYZ"), 1, 1)),
        "M: Two 1x1 grids are eqivalent");
    assertTrue(
        (new Grid(new Rect('x', 3, 5), 4, 4).eqv(
            new Grid(new Rect('x', 3, 5), 4, 4))),
        "M: Two 4x4 grids are eqivalent");
  } // testEqvGrid

  /**
   * Make sure that a grid is not equivalent to any other
   * type of block.
   *
   * @throws Exception
   *   If the `Grid` constructor happens to do so.
   */
  @Test
  public void testNotEqvGrid() throws Exception {
    AsciiBlock grid = new Grid(new Line("G"), 3, 2);
    assertFalse(grid.eqv(new Line("GGGGGGGGGGGG")),
        "M: Sample grid is not equivalent to a line");
    assertFalse(grid.eqv(new Grid(new Line("g"), 3, 2)),
        "M: Sample grid is not eqv to a diff grid of same dimensions");
    assertFalse(grid.eqv(new Grid(new Line("G"), 2, 3)),
        "M: Sample grid is not equivalent to a grid of inverted dimensions");
    assertFalse(grid.eqv(new Boxed(grid)),
        "M: Sample grid is not equivalent to a boxed grid");
    assertFalse(grid.eqv(new Surrounded(new Empty(), 'X')),
        "E: Sample grid is not eqv to a surrounded empty");
    assertFalse(grid.eqv(new Grid(grid, 1, 1)),
        "E: Sample grid is not equivalent to a 1x1 grid of the grid");
    assertFalse(grid.eqv(new HComp(VAlignment.CENTER, new AsciiBlock[] {})),
        "E: Sample grid is not equivalent to an empty horiz composition");
    assertFalse(grid.eqv(new HComp(VAlignment.BOTTOM, new AsciiBlock[] {grid})),
        "M: Sample grid is not equivalent to a singleton hcomp");
    AsciiBlock rg = new Rect('G', 1, 2);
    assertFalse(
        grid.eqv(new HComp(VAlignment.TOP, new AsciiBlock[] {rg, rg, rg})),
        "E: Sample grid is not equivalent to a similar hcomp");
    assertFalse(grid.eqv(new VComp(HAlignment.LEFT, new AsciiBlock[] {})),
        "E: Sample grid is not equivalent to an empty vertical composition");
    assertFalse(
        grid.eqv(new VComp(HAlignment.RIGHT,
             new AsciiBlock[] {new Line("GGG")})),
        "M: Sample grid is not equivalent to a singleton vcomp");
    assertFalse(
        grid.eqv(new VComp(HAlignment.LEFT,
             new AsciiBlock[] {new Line("GGG"), new Line("GGG")})),
        "E: Sample grid is not equivalent to a similar-appearing vcomp");
    assertFalse(grid.eqv(new HFlip(grid)),
        "M: Sample grid is not equivalent to horizontally flipped version");
    assertFalse(grid.eqv(new VFlip(grid)),
        "M: Sample grid is not equivalent to vertically flipped version");
    assertFalse(
        grid.eqv(new Trimmed(grid, HAlignment.LEFT, VAlignment.TOP, 3, 2)),
        "E: Sample grid is not equivalent to the same grid, trimmed");
    assertFalse(
        grid.eqv(new Trimmed(new Rect('G', 10, 10), HAlignment.LEFT,
            VAlignment.TOP, 3, 2)),
        "E: Sample grid is not equivalent to a similar trimmed rect");
    assertFalse(
        grid.eqv(new Trimmed(new Grid(new Line("G"), 10, 10), HAlignment.RIGHT,
            VAlignment.BOTTOM, 3, 2)),
        "E: Sample grid is not equivalent to a similar trimmed grid");
    assertFalse(
        grid.eqv(new Padded(new Empty(), 'G', HAlignment.LEFT, VAlignment.TOP,
            3, 2)),
        "E: Sample grid is not equivalent to a padded empty block");
    assertFalse(
        grid.eqv(new Padded(grid, 'G', HAlignment.LEFT, VAlignment.TOP,
            3, 2)),
        "E: Sample grid is not eqv to a 0-padded version of the same grid");
  } // testNotEqvGrid()

  // +----------------------+----------------------------------------
  // | The exes comparisons |
  // +----------------------+

  /**
   * Compare two arrays that contain elements that should be equivalent.
   *
   * @param aSide
   *   The first collection of elements to compare.
   * @param bSide
   *   The second set of elements to compare.
   * @param type
   *   The type of elements (e.g., "Rect" or "HComp"). Used for messages.
   */
  void exesCompareSame(AsciiBlock[] blocksA, AsciiBlock[] blocksB,
      String type) {
    for (int i = 0; i < blocksA.length; i++) {
      if (null != blocksB[i]) {
        assertTrue(AsciiBlock.equal(blocksA[i], blocksB[i]),
          String.format("*: equal(exes%s%da, exes%s%db)",
              type, i, type, i));
        assertTrue(AsciiBlock.equal(blocksB[i], blocksA[i]),
          String.format("*: equal(exes%s%db, exes%s%da)",
              type, i, type, i));
        assertTrue(AsciiBlock.eqv(blocksA[i], blocksB[i]),
          String.format("M: eqv(exes%s%da, exes%s%db)",
              type, i, type, i));
        assertTrue(AsciiBlock.eqv(blocksB[i], blocksA[i]),
          String.format("M: eqv(exes%s%db, exes%s%da)",
              type, i, type, i));
      } // if
    } // for
  } // exesCompareSame

  /**
   * Compare different blocks in the same type for equivalence.
   * The assumption is that all pairs should be different.
   *
   * @param blocks
   *   The collection of blocks to compare.
   *
   * @param type
   *   The type of elements (e.g., "Rect" or "HComp"). Used for messages.
   */
  void exesCompareDifferent(AsciiBlock[] blocks, String type) {
    for (int i = 0; i < blocks.length; i++) {
      for (int j = i + 1; j < blocks.length; j++) {
        assertTrue(AsciiBlock.equal(blocks[i], blocks[j]),
          String.format("*: equal(exes%s%da, exes%s%da)",
              type, i, type, j));
        assertTrue(AsciiBlock.equal(blocks[j], blocks[i]),
          String.format("*: equal(exes%s%da, exes%s%da)",
              type, j, type, i));
        assertFalse(AsciiBlock.eqv(blocks[i], blocks[j]),
          String.format("M: eqv(exes%s%da, exes%s%da)",
              type, i, type, j));
        assertFalse(AsciiBlock.eqv(blocks[j], blocks[i]),
          String.format("M: eqv(exes%s%da, exes%s%da)",
              type, j, type, i));
      } // for j
    } // for i
  } // exesCompareDifferent(AsciiBlock[], String)

  /**
   * Compare different blocks in the different type for equivalence.
   * The assumption is that all pairs should be different.
   *
   * @param blocksOne
   *   The first collection of blocks to compare.
   * @param typeOne
   *   The type of elements in blocksOne (e.g., "Rect" or "HComp").
   *   Used for messages.
   * @param blocksTwo
   *   The second collection of blocks to compare.
   * @param typeOne
   *   The type of elements in blocksTwo (e.g., "Rect" or "HComp").
   *   Used for messages.
   */
  void exesCompareDifferent(AsciiBlock[] blocksOne, String typeOne,
      AsciiBlock[] blocksTwo, String typeTwo) {
    for (int i = 0; i < blocksOne.length; i++) {
      for (int j = 0; j < blocksTwo.length; j++) {
        assertTrue(AsciiBlock.equal(blocksOne[i], blocksTwo[j]),
          String.format("*: equal(exes%s%da, exes%s%da)",
              typeOne, i, typeTwo, j));
        assertTrue(AsciiBlock.equal(blocksTwo[j], blocksOne[i]),
          String.format("*: equal(exes%s%da, exes%s%da)",
              typeTwo, j, typeOne, i));
        assertFalse(AsciiBlock.eqv(blocksOne[i], blocksTwo[j]),
          String.format("M: eqv(exes%s%da, exes%s%da)",
              typeOne, i, typeTwo, j));
        assertFalse(AsciiBlock.eqv(blocksTwo[j], blocksOne[i]),
          String.format("M: eqv(exes%s%da, exes%s%da)",
              typeTwo, j, typeOne, i));
      } // for j
    } // for i
  } // exesCompareDifferent(AsciiBlock[], String)

  /**
   * Rectangles vs rectangles that should be the same.
   */
  @Test
  public void exesRectVsRectSame() {
    exesCompareSame(exesRectsA, exesRectsB, "Rect");
  } // exesRectvsRectSame
  /**
   * Rectangles vs rectangles that should be different.
   */
  @Test
  public void exesRectVsRectDiff() {
    exesCompareDifferent(exesRectsA, "Rect");
  } // exesRectVsRectDiff

  /**
   * Rectangles vs grids (and vice versa).
   */
  @Test
  public void exesRectVsGrid() {
    exesCompareDifferent(exesRectsA, "Rect", exesGridsA, "Grid");
  } // exesRectVsGrid

  /**
   * Grids vs grids that are the same.
   */
  @Test
  public void exesGridVsGridSame() {
    exesCompareSame(exesGridsA, exesGridsB, "Grid");
  } // exesGridVsGridSame()

  /**
   * Grids vs grids that are different.
   */
  @Test
  public void exesGridVsGridDiff() {
    exesCompareDifferent(exesGridsA, "Grid");
  } // exesGridvsGridDiff

  /**
   * Rectangles vs HComps.
   */
  @Test
  public void exesRectVsHComp() {
    exesCompareDifferent(exesRectsA, "Rect", exesHCompsA, "HComp");
  } // exesRectVsHComp()

  /**
   * Grids vs HComps.
   */
  @Test
  public void exesGridVsHComp() {
    exesCompareDifferent(exesGridsA, "Grid", exesHCompsA, "HComp");
  } // exesGridVsHComp()

  /**
   * HComp vs HComp where they should be the same.
   */
  @Test
  public void exesHCompVsHCompSame() {
    exesCompareSame(exesHCompsA, exesHCompsB, "HComp");
  } // exesHCompVsHCompSame()

  /**
   * HComp vs HComp where they should be different.
   */
  @Test
  public void exesHCompVsHCompDiff() {
    exesCompareDifferent(exesHCompsA, "HComp");
  } // exesHCompVsHCompDiff()

  /** Rectangles vs VComps. */
  @Test
  public void exesRectVsVComp() {
    exesCompareDifferent(exesRectsA, "Rect", exesVCompsA, "VComp");
  } // exesRectsVsVComp()

  /** Grids vs VComps. */
  @Test
  public void exesGridVsVComp() {
    exesCompareDifferent(exesGridsA, "Grid", exesVCompsA, "VComp");
  } // exesGridVsVComp()

  /** HComps vs VComps. */
  @Test
  public void exesHCompVsVComp() {
    exesCompareDifferent(exesHCompsA, "HComp", exesVCompsA, "VComp");
  } // exesHCompVsVComp()

  /**
   * VComps vs VComps (which should be the same).
   */
  @Test
  public void exesVCompVsVCompSame() {
    exesCompareSame(exesVCompsA, exesVCompsB, "VComp");
  } // exesVCompVsVCompSame()

  /**
   * VComp vs VComp where they should be different.
   */
  @Test
  public void exesVCompVsVCompDiff() {
    exesCompareDifferent(exesVCompsA, "VComp");
  } // exesVCompVsVCompDiff()

  /** Rectangles vs HFlips. */
  @Test
  public void exesRectVsHFlip() {
    exesCompareDifferent(exesRectsA, "Rect", exesHFlipsA, "HFlip");
  } // exesRectsVsHFlip()

  /** Grids vs HFlips. */
  @Test
  public void exesGridVsHFlip() {
    exesCompareDifferent(exesGridsA, "Grid", exesHFlipsA, "HFlip");
  } // exesGridVsHFlip()

  /** HComps vs HFlips. */
  @Test
  public void exesHCompVsHFlip() {
    exesCompareDifferent(exesHCompsA, "HComp", exesHFlipsA, "HFlip");
  } // exesHCompVsHFlip()

  /** VComps vs HFlips. */
  @Test
  public void exesVCompVsHFlip() {
    exesCompareDifferent(exesVCompsA, "VComp", exesHFlipsA, "HFlip");
  } // exesVCompVsHFlip()

  /**
   * HFlips vs HFlips (which should be the same).
   */
  @Test
  public void exesHFlipVsHFlipSame() {
    exesCompareSame(exesHFlipsA, exesHFlipsB, "HFlip");
  } // exesHFlipVsHFlipSame()

  /**
   * HFlip vs HFlip where they should be different.
   */
  @Test
  public void exesHFlipVsHFlipDiff() {
    exesCompareDifferent(exesHFlipsA, "HFlip");
  } // exesHFlipVsHFlipDiff()

  /** Rectangles vs VFlips. */
  @Test
  public void exesRectVsVFlip() {
    exesCompareDifferent(exesRectsA, "Rect", exesVFlipsA, "VFlip");
  } // exesRectsVsVFlip()

  /** Grids vs VFlips. */
  @Test
  public void exesGridVsVFlip() {
    exesCompareDifferent(exesGridsA, "Grid", exesVFlipsA, "VFlip");
  } // exesGridVsVFlip()

  /** HComps vs VFlips. */
  @Test
  public void exesHCompVsVFlip() {
    exesCompareDifferent(exesHCompsA, "HComp", exesVFlipsA, "VFlip");
  } // exesHCompVsVFlip()

  /** VComps vs VFlips. */
  @Test
  public void exesVCompVsVFlip() {
    exesCompareDifferent(exesVCompsA, "VComp", exesVFlipsA, "VFlip");
  } // exesVCompVsVFlip()

  /** HFlips vs VFlips. */
  @Test
  public void exesHFlipVsVFlip() {
    exesCompareDifferent(exesHFlipsA, "HFlip", exesVFlipsA, "VFlip");
  } // exesHFlipVsVFlip()

  /**
   * VFlips vs VFlips (which should be the same).
   */
  @Test
  public void exesVFlipVsVFlipSame() {
    exesCompareSame(exesVFlipsA, exesVFlipsB, "VFlip");
  } // exesVFlipVsVFlipSame()

  /**
   * VFlip vs VFlip where they should be different.
   */
  @Test
  public void exesVFlipVsVFlipDiff() {
    exesCompareDifferent(exesVFlipsA, "VFlip");
  } // exesVFlipVsVFlipDiff()

} // class TestBlockEquiv
