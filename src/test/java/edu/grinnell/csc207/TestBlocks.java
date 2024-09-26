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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Tests of the various ASCII Blocks.
 */
public class TestBlocks {

  // +-------------------------+-------------------------------------
  // | Globals for HComp tests |
  // +-------------------------+

  /** A 1x6 block. */
  static AsciiBlock ab1x6;

  /** A 2x5 block. */
  static AsciiBlock ab2x5;

  /** A 3x4 block. */
  static AsciiBlock ab3x4;

  /** A 1x3 block. */
  static AsciiBlock ab1x3;

  /** A 2x2 block. */
  static AsciiBlock ab2x2;

  /** A 3x1 block. */
  static AsciiBlock ab3x1;

  // +-------------------------+-------------------------------------
  // | Globals for VComp tests |
  // +-------------------------+

  /** Two lines. You can guess what they are. */
  static AsciiBlock helloworld;

  /** Just one line, a bit longer. */
  static AsciiBlock goodbye;

  /** A four-by-three grid of x's. */
  static AsciiBlock exes;

  /** The single letter A. */
  static AsciiBlock justA;

  /** Mind your P's and Q's. */
  static AsciiBlock pq;

  // +----------------+----------------------------------------------
  // | Initialization |
  // +----------------+

  /**
   * Set up the globals.
   */
  @BeforeAll
  public static void setup() throws Exception {
    try {
      // Globals for HComp tests
      ab1x6 = new Rect('6', 1, 6);
      ab2x5 = new Rect('5', 2, 5);
      ab3x4 = new Rect('4', 3, 4);
      ab1x3 = new Rect('3', 1, 3);
      ab2x2 = new Rect('2', 2, 2);
      ab3x1 = new Rect('1', 3, 1);
      // Globals for VComp tests
      helloworld =
          new VComp(HAlignment.LEFT, new Line("Hello"), new Line("World"));
      goodbye = new Line("Goodbye");
      exes = new Rect('x', 4, 3);
      justA = new Line("A");
      pq = new Line("PQ");
    } catch (Exception e) {
      // Do nothing; we shouldn't get exceptions.
    } // try/catch

  } // setup()

  // +-------+-------------------------------------------------------
  // | Empty |
  // +-------+

  /**
   * Do we successfully build the empty block?
   */
  @Test
  public void testEmpty() {
    AsciiBlock empty = new Empty();
    assertEquals(0, empty.width(),
        "R: Empty block has no width");
    assertEquals(0, empty.height(),
        "R: Empty block has no height");
    assertEquals("", TestUtils.toString(empty),
        "R: Empty block has no contents");
  } // testEmpty()

  // +-------+-------------------------------------------------------
  // | Lines |
  // +-------+

  /**
   * Do we successfully build lines?
   */
  @Test
  public void testLine() throws Exception {
    Line line = new Line("Hello");
    assertEquals(5, line.width(),
        "R: Basic line has appropriate width");
    assertEquals(1, line.height(),
        "R: Basic line has appropriate height");
    assertEquals("Hello", line.row(0),
        "R: Basic line has appropriate contents");
  } // testLine()

  /**
   * Do we succesfully change lines?
   */
  @Test
  public void testLineChange() throws Exception {
    Line line = new Line("hi");
    assertEquals(2, line.width());
    assertEquals(1, line.height());
    assertEquals("hi", line.row(0));
    line.update("Good day");
    assertEquals(8, line.width());
    assertEquals(1, line.height());
    assertEquals("Good day", line.row(0));
  } // testLineChange()

  // +------------+--------------------------------------------------
  // | Rectangles |
  // +------------+

  /**
   * Do we successfully build rectangles?
   */
  @Test
  public void testRect() throws Exception {
    assertEquals("X\n", TestUtils.toString(new Rect('X', 1, 1)),
        "R: 1x1 rectangle");
    assertEquals("""
                 YYY
                 YYY
                 """,
        TestUtils.toString(new Rect('Y', 3, 2)),
        "R: 3x2 rectangle");
  } // testRect()

  /**
   * Can we successfully change rectangles?
   */
  @Test
  public void testRectChange() throws Exception {
    Rect rect = new Rect('!', 3, 4);
    assertEquals("!!!\n!!!\n!!!\n!!!\n", TestUtils.toString(rect),
        "R: original 3x4 rectangle");

    rect.wider();
    assertEquals("!!!!\n!!!!\n!!!!\n!!!!\n", TestUtils.toString(rect),
        "M: wider version of rectangle, now 4x4");

    rect.wider();
    assertEquals("!!!!!\n!!!!!\n!!!!!\n!!!!!\n", TestUtils.toString(rect),
        "M: even wider version of rectangle, now 5x4");

    rect.taller();
    assertEquals("!!!!!\n!!!!!\n!!!!!\n!!!!!\n!!!!!\n",
        TestUtils.toString(rect),
        "M: taller version of rectangle, now 5x5");

    rect.shorter();
    rect.shorter();
    rect.shorter();
    assertEquals("!!!!!\n!!!!!\n", TestUtils.toString(rect),
        "M: much shorter version of rectangle, now 5x2");

    rect.shorter();
    assertEquals("!!!!!\n", TestUtils.toString(rect),
        "M: shortest version of rectangle, now 5x1");

    rect.shorter();
    assertEquals("!!!!!\n", TestUtils.toString(rect),
        "E: cannot shorten rectangle below 5x1");

    rect.taller();
    assertEquals("!!!!!\n!!!!!\n", TestUtils.toString(rect),
        "E: can make rectangle taller after shortening to base, now 5x2");

    rect.narrower();
    assertEquals("!!!!\n!!!!\n", TestUtils.toString(rect),
        "M: narrower version rectangle, now 4x2");

    rect.narrower();
    rect.narrower();
    rect.narrower();
    assertEquals("!\n!\n", TestUtils.toString(rect),
        "M: very narrow rectangle, now 1x2");

    rect.narrower();
    assertEquals("!\n!\n", TestUtils.toString(rect),
        "E: cannot make rectangle narrower than 1 column, still 1x2");

    rect.wider();
    assertEquals("!!\n!!\n", TestUtils.toString(rect),
        "E: widening after narrowing, now 2x2");

  } // testRectChange()

  // +-------+-------------------------------------------------------
  // | Boxed |
  // +-------+

  /**
   * Testing of basic boxed structures.
   */
  @Test
  void TestBoxedBasic() throws Exception {
    assertNotNull(new Boxed(new Line("Hello")),
        "R: Can box text lines");
    assertNotNull(new Boxed(new Rect('B', 3, 4)),
        "R: Can box rectangles");
  } // TestBoxedBasic()

  /**
   * Testing of basic boxed lines.
   */
  @Test
  void TestBoxedLine() {
    AsciiBlock boxedLine = new Boxed(new Line("Hello"));
    assertEquals(7, boxedLine.width(),
        "M: Correct width for a boxed line");
    assertEquals(3, boxedLine.height(),
        "M: Correct height for a boxed line");
    assertEquals("/-----\\\n|Hello|\n\\-----/\n", TestUtils.toString(boxedLine),
        "M: Correct contents for a boxed line");
  } // TestBoxedLine()

  /**
   * Testing of basic boxed rectangles.
   */
  @Test
  void TestBoxedRect() throws Exception {
    AsciiBlock boxedRect = new Boxed(new Rect('B', 3, 4));
    assertEquals(5, boxedRect.width(),
        "M: Correct width for a boxed rectangle");
    assertEquals(6, boxedRect.height(),
        "M: Correct height for a boxed rectangle");
    assertEquals("""
                 /---\\
                 |BBB|
                 |BBB|
                 |BBB|
                 |BBB|
                 \\---/
                 """,
        TestUtils.toString(boxedRect),
        "M: Correct contents for a boxed rectangle");
  } // TestBoxedRect()

  /**
   * Test of boxed lines that have been narrowed.
   */
  @Test
  public void testBoxedLineNarrower() {
    Line line = new Line("Hello");
    AsciiBlock boxedLine = new Boxed(line);

    line.update("Hi");
    assertEquals(4, boxedLine.width(),
        "E: Correct width after making boxed line narrower");
    assertEquals(3, boxedLine.height(),
        "E: Correct height after making boxed line narrower");
    assertEquals("/--\\\n|Hi|\n\\--/\n", TestUtils.toString(boxedLine),
        "E: Correct contents for narrowed boxed line");
  } // testBoxedLineNarrower()

  /**
   * Test of boxed lines that have been made wider.
   */
  @Test
  public void testBoxedLineWider() {
    Line line = new Line("Bye");
    AsciiBlock boxedLine = new Boxed(line);

    line.update("Goodbye");
    assertEquals(9, boxedLine.width(),
        "E: Correct width after making boxed line wider");
    assertEquals(3, boxedLine.height(),
        "E: Correct height after making boxed line wider");
    assertEquals("/-------\\\n|Goodbye|\n\\-------/\n", TestUtils.toString(boxedLine),
        "E: Correct contents for widened boxed line");
  } // testBoxedLineWider()

  /**
   * Test of boxed rects that change. We do this as ab3x1 test so that
   * we can stack changes.
   */
  @Test
  public void testBoxedRectChange() throws Exception {
    Rect rect = new Rect('Q', 3, 4);
    AsciiBlock boxedRect = new Boxed(rect);
    rect.wider();

    assertEquals(6, boxedRect.width(),
        "E: Correct width for a wider boxed rectangle");
    assertEquals(6, boxedRect.height(),
        "E: Correct height for a wider boxed rectangle");
    assertEquals("""
                 /----\\
                 |QQQQ|
                 |QQQQ|
                 |QQQQ|
                 |QQQQ|
                 \\----/
                 """,
        TestUtils.toString(boxedRect),
        "E: Correct contents for a wider boxed rectangle");

    rect.narrower();
    rect.narrower();

    assertEquals(4, boxedRect.width(),
        "E: Correct width for a narrower boxed rectangle");
    assertEquals(6, boxedRect.height(),
        "E: Correct height for a narrower boxed rectangle");
    assertEquals("""
                 /--\\
                 |QQ|
                 |QQ|
                 |QQ|
                 |QQ|
                 \\--/
                 """,
        TestUtils.toString(boxedRect),
        "E: Correct contents for a narrower boxed rectangle");

    rect.shorter();
    rect.shorter();

    assertEquals(4, boxedRect.width(),
        "E: Correct width for a shorter boxed rectangle");
    assertEquals(4, boxedRect.height(),
        "E: Correct height for a shorter boxed rectangle");
    assertEquals("""
                 /--\\
                 |QQ|
                 |QQ|
                 \\--/
                 """,
        TestUtils.toString(boxedRect),
        "E: Correct contents for a shorter boxed rectangle");

    rect.taller();

    assertEquals(4, boxedRect.width(),
        "E: Correct width for a taller boxed rectangle");
    assertEquals(5, boxedRect.height(),
        "E: Correct height for a taller boxed rectangle");
    assertEquals("""
                 /--\\
                 |QQ|
                 |QQ|
                 |QQ|
                 \\--/
                 """,
        TestUtils.toString(boxedRect),
        "E: Correct contents for a taller boxed rectangle");
  } // testBoxedRectChange()

  /**
   * Make sure that we can box the empty block.
   */
  @Test
  public void boxedEmptyBlock() throws Exception {
    AsciiBlock boxed = new Boxed(new Empty());
    assertEquals(2, boxed.width(),
        "E: Correct width for a boxed empty block");
    assertEquals(2, boxed.height(),
        "E: Correct height for a boxed empty block");
    assertEquals("/\\", boxed.row(0),
        "E: Correct row 0 of boxed empty block");
    assertEquals("\\/", boxed.row(1),
        "E: Correct row 1 of boxed empty block");
  } // boxedEmptyBlock()

  // +------------+--------------------------------------------------
  // | Surrounded |
  // +------------+

  /**
   * Testing of basic surrounded structures.
   */
  @Test
  void TestSurroundedBasic() throws Exception {
    assertNotNull(new Surrounded(new Line("Hello"), '-'),
        "R: Can surround text lines");
    assertNotNull(new Surrounded(new Rect('B', 3, 4), '!'),
        "R: Can surround rectangles");
  } // TestSurroundedBasic()

  /**
   * Testing of basic surrounded lines.
   */
  @Test
  void TestSurroundedLine() {
    AsciiBlock surroundedLine = new Surrounded(new Line("Hello"), 'x');
    assertEquals(7, surroundedLine.width(),
        "M: Correct width for a surrounded line");
    assertEquals(3, surroundedLine.height(),
        "M: Correct height for a surrounded line");
    assertEquals("xxxxxxx\nxHellox\nxxxxxxx\n", TestUtils.toString(surroundedLine),
        "M: Correct contents for a surrounded line");
  } // TestSurroundedLine()

  /**
   * Testing of basic surrounded rectangles.
   */
  @Test
  void TestSurroundedRect() throws Exception {
    AsciiBlock surroundedRect = new Surrounded(new Rect('v', 3, 4), '^');
    assertEquals(5, surroundedRect.width(),
        "M: Correct width for a surrounded rectangle");
    assertEquals(6, surroundedRect.height(),
        "M: Correct height for a surrounded rectangle");
    assertEquals("""
                 ^^^^^
                 ^vvv^
                 ^vvv^
                 ^vvv^
                 ^vvv^
                 ^^^^^
                 """,
        TestUtils.toString(surroundedRect),
        "M: Correct contents for a surrounded rectangle");
  } // TestSurroundedRect()

  /**
   * Test of surrounded lines that have been narrowed.
   */
  @Test
  public void testSurroundedLineNarrower() {
    Line line = new Line("Hello");
    AsciiBlock surroundedLine = new Surrounded(line, '|');

    line.update("Hi");
    assertEquals(4, surroundedLine.width(),
        "E: Correct width after making surrounded line narrower");
    assertEquals(3, surroundedLine.height(),
        "E: Correct height after making surrounded line narrower");
    assertEquals("||||\n|Hi|\n||||\n", TestUtils.toString(surroundedLine),
        "E: Correct contents for narrowed surrounded line");
  } // testSurroundedLineNarrower()

  /**
   * Test of surrounded lines that have been made wider.
   */
  @Test
  public void testSurroundedLineWider() {
    Line line = new Line("X");
    AsciiBlock surroundedLine = new Surrounded(line, 'x');

    line.update("ABCDE");
    assertEquals(7, surroundedLine.width(),
        "E: Correct width after making surrounded line wider");
    assertEquals(3, surroundedLine.height(),
        "E: Correct height after making surrounded line wider");
    assertEquals("xxxxxxx\nxABCDEx\nxxxxxxx\n",
        TestUtils.toString(surroundedLine),
        "E: Correct contents for widened surrounded line");
  } // testSurroundedLineWider()

  /**
   * Test of surrounded rects that change. We do this as ab3x1 test so that
   * we can stack changes.
   */
  @Test
  public void testSurroundedRectChange() throws Exception {
    Rect rect = new Rect('X', 3, 4);
    AsciiBlock surroundedRect = new Surrounded(rect, 'O');
    rect.wider();

    assertEquals(6, surroundedRect.width(),
        "E: Correct width for a wider surrounded rectangle");
    assertEquals(6, surroundedRect.height(),
        "E: Correct height for a wider surrounded rectangle");
    assertEquals("""
                 OOOOOO
                 OXXXXO
                 OXXXXO
                 OXXXXO
                 OXXXXO
                 OOOOOO
                 """,
        TestUtils.toString(surroundedRect),
        "E: Correct contents for a wider surrounded rectangle");

    rect.narrower();
    rect.narrower();

    assertEquals(4, surroundedRect.width(),
        "E: Correct width for a narrower surrounded rectangle");
    assertEquals(6, surroundedRect.height(),
        "E: Correct height for a narrower surrounded rectangle");
    assertEquals("""
                 OOOO
                 OXXO
                 OXXO
                 OXXO
                 OXXO
                 OOOO
                 """,
        TestUtils.toString(surroundedRect),
        "E: Correct contents for a narrower surrounded rectangle");

    rect.shorter();
    rect.shorter();

    assertEquals(4, surroundedRect.width(),
        "E: Correct width for a shorter surrounded rectangle");
    assertEquals(4, surroundedRect.height(),
        "E: Correct height for a shorter surrounded rectangle");
    assertEquals("""
                 OOOO
                 OXXO
                 OXXO
                 OOOO
                 """,
        TestUtils.toString(surroundedRect),
        "E: Correct contents for a shorter surrounded rectangle");

    rect.taller();

    assertEquals(4, surroundedRect.width(),
        "E: Correct width for a taller surrounded rectangle");
    assertEquals(5, surroundedRect.height(),
        "E: Correct height for a taller surrounded rectangle");
    assertEquals("""
                 OOOO
                 OXXO
                 OXXO
                 OXXO
                 OOOO
                 """,
        TestUtils.toString(surroundedRect),
        "E: Correct contents for a taller surrounded rectangle");
  } // testSurroundedRectChange()

  /**
   * Make sure that we can surround the empty block.
   */
  @Test
  public void surroundedEmptyBlock() throws Exception {
    AsciiBlock surrounded = new Surrounded(new Empty(), 's');
    assertEquals(2, surrounded.width(),
        "E: Correct width for a surrounded empty block");
    assertEquals(2, surrounded.height(),
        "E: Correct height for a surrounded empty block");
    assertEquals("ss", surrounded.row(0),
        "E: Correct row 0 of surrounded empty block");
    assertEquals("ss", surrounded.row(1),
        "E: Correct row 1 of surrounded empty block");
  } // surroundedEmptyBlock()

  // +-------+-------------------------------------------------------
  // | Grids |
  // +-------+

  /**
   * Testing of basic grid structures.
   */
  @Test
  void TestGridBasic() throws Exception {
    assertNotNull(new Grid(new Line("Hello"), 3, 2),
        "R: Can grid text lines");
    assertNotNull(new Grid(new Rect('B', 3, 4), 2, 3),
        "R: Can grid rectangles");
  } // TestGridBasic()

  /**
   * Testing of basic grid lines.
   */
  @Test
  void TestGridLine() {
    AsciiBlock gridLine = new Grid(new Line("Hello"), 3, 4);
    assertEquals(15, gridLine.width(),
        "M: Correct width for a grid line");
    assertEquals(4, gridLine.height(),
        "M: Correct height for a grid line");
    assertEquals("""
                 HelloHelloHello
                 HelloHelloHello
                 HelloHelloHello
                 HelloHelloHello
                 """,
        TestUtils.toString(gridLine),
        "M: Correct contents for a gridded line");
  } // TestGridLine()

  /**
   * Testing of gridding boxes.
   */
  @Test
  void TestGridBox() throws Exception {
    AsciiBlock gridBox = new Grid(new Boxed(new Empty()), 3, 2);
    assertEquals(6, gridBox.width(),
        "M: Correct width for a gridded empty box");
    assertEquals(4, gridBox.height(),
        "M: Correct height for a gridded empty box");
    assertEquals("""
                 /\\/\\/\\
                 \\/\\/\\/
                 /\\/\\/\\
                 \\/\\/\\/
                 """,
        TestUtils.toString(gridBox),
        "M: Correct contents for a gridded empty box");
  } // TestGridRect()

  /**
   * Testing of nested grids.
   */
  @Test
  void TestGridNested() throws Exception {
    AsciiBlock innermost = new Grid(new Surrounded(new Line("A"), 'B'), 3, 2);
    assertEquals(9, innermost.width(),
        "M: Correct width for gridded surrounded line");
    assertEquals(6, innermost.height(),
        "M: Correct height for gridded surrounded line");
    assertEquals("""
                 BBBBBBBBB
                 BABBABBAB
                 BBBBBBBBB
                 BBBBBBBBB
                 BABBABBAB
                 BBBBBBBBB
                 """,
                 TestUtils.toString(innermost),
        "M: Correct contents for gridded surrounded line");
    AsciiBlock outer = new Grid(new Surrounded(innermost, 'C'), 2, 3);
    assertEquals(22, outer.width(),
        "E: Correct width for gridded surrounded gridded surrounded line");
    assertEquals(24, outer.height(),
        "E: Correct width for gridded surrounded gridded surrounded line");
    for (int i : new int[] {0, 7, 8, 15, 16, 23}) {
      assertEquals("CCCCCCCCCCCCCCCCCCCCCC", outer.row(i),
        "E: Correct row " + i + " in gridded surrounded gridded surround line");
    } // for
    for (int i : new int[] {1, 3, 4, 6, 9, 11, 12, 14, 17, 19, 20, 22}) {
      assertEquals("CBBBBBBBBBCCBBBBBBBBBC", outer.row(i),
        "E: Correct row " + i + " in gridded surrounded gridded surround line");
    } // for
    for (int i : new int[] {2, 5, 10, 13, 18, 21}) {
      assertEquals("CBABBABBABCCBABBABBABC", outer.row(i),
        "E: Correct row " + i + " in gridded surrounded gridded surround line");
    } // for
  } // TestGridNested()

  /**
   * Test of grid lines that have been modified.
   */
  @Test
  public void testGridLineChange () {
    Line line = new Line("Hello");
    AsciiBlock gridLine = new Grid(line, 4, 5);

    line.update("Hi");
    assertEquals(8, gridLine.width(),
        "E: Correct width after making line narrower");
    assertEquals(5, gridLine.height(),
        "E: Correct height after making line narrower");
    assertEquals("""
                 HiHiHiHi
                 HiHiHiHi
                 HiHiHiHi
                 HiHiHiHi
                 HiHiHiHi
                 """,
                 TestUtils.toString(gridLine));

    line.update("abcdef");
    assertEquals(24, gridLine.width(),
        "E: Correct width after making line wider");
    assertEquals(5, gridLine.height(),
        "E: Correct height after making line wider");
    assertEquals("""
                 abcdefabcdefabcdefabcdef
                 abcdefabcdefabcdefabcdef
                 abcdefabcdefabcdefabcdef
                 abcdefabcdefabcdefabcdef
                 abcdefabcdefabcdefabcdef
                 """,
                 TestUtils.toString(gridLine));
  } // testGridLineChange ()

  /**
   * Test of grid rects that change. We do this as ab3x1 test so that
   * we can stack changes.
   */
  @Test
  public void testGridRectChange() throws Exception {
    Rect rect = new Rect('X', 2, 4);
    AsciiBlock gridRect = new Grid(rect, 2, 2);
    rect.wider();

    assertEquals(6, gridRect.width(),
        "E: Correct width for a wider grided rectangle");
    assertEquals(8, gridRect.height(),
        "E: Correct height for a wider grided rectangle");
    assertEquals("""
                 XXXXXX
                 XXXXXX
                 XXXXXX
                 XXXXXX
                 XXXXXX
                 XXXXXX
                 XXXXXX
                 XXXXXX
                 """,
        TestUtils.toString(gridRect),
        "E: Correct contents for a wider grided rectangle");

    rect.narrower();
    rect.narrower();

    assertEquals(2, gridRect.width(),
        "E: Correct width for a narrower grided rectangle");
    assertEquals(8, gridRect.height(),
        "E: Correct height for a narrower grided rectangle");
    assertEquals("""
                 XX
                 XX
                 XX
                 XX
                 XX
                 XX
                 XX
                 XX
                 """,
        TestUtils.toString(gridRect),
        "E: Correct contents for a narrower grided rectangle");

    rect.shorter();
    rect.shorter();

    assertEquals(2, gridRect.width(),
        "E: Correct width for a shorter grided rectangle");
    assertEquals(4, gridRect.height(),
        "E: Correct height for a shorter grided rectangle");
    assertEquals("""
                 XX
                 XX
                 XX
                 XX
                 """,
        TestUtils.toString(gridRect),
        "E: Correct contents for a shorter grided rectangle");

    rect.taller();

    assertEquals(2, gridRect.width(),
        "E: Correct width for a taller grided rectangle");
    assertEquals(6, gridRect.height(),
        "E: Correct height for a taller grided rectangle");
    assertEquals("""
                XX
                XX
                XX
                XX
                XX
                XX
                 """,
        TestUtils.toString(gridRect),
        "E: Correct contents for a taller grided rectangle");
  } // testGridRectChange()

  /**
   * Make sure that we can grid the empty block.
   */
  @Test
  public void gridEmptyBlock() throws Exception {
    AsciiBlock grid = new Grid(new Empty(), 4, 4);
    assertEquals(0, grid.width(),
        "E: Correct width for a grid empty block");
    assertEquals(0, grid.height(),
        "E: Correct height for a grid empty block");
  } // gridEmptyBlock()

  // +------------------------+--------------------------------------
  // | Horizontal composition |
  // +------------------------+

  /**
   * Make sure that the constructor doesn't throw an error.
   */
  @Test
  public void testHCompConstructor() {
    assertNotNull(new HComp(VAlignment.TOP,
        new AsciiBlock[] {new Line("A"), new Line("B"), new Line("C")}),
        "R: Can build a horizontal composition");
  } // testHCompConstructor()

  /**
   * Horizontal composition, top aligned, spaces at the right.
   */
  @Test
  public void testHCompTopSpacesRight() {
    AsciiBlock topDecreasing = new HComp(VAlignment.TOP,
        new AsciiBlock[] {ab1x6, ab2x5, ab3x4, ab1x3, ab2x2, ab3x1});
    assertEquals(12, topDecreasing.width(),
                 "M: Correct width of top-aligned hcomp with spaces at right");
    assertEquals(6, topDecreasing.height(),
                 "M: Correct height of top-aligned hcomp with spaces at right");
    assertEquals(
        ""
            + "655444322111\n"
            + "655444322   \n"
            + "6554443     \n"
            + "655444      \n"
            + "655         \n"
            + "6           \n",
        TestUtils.toString(topDecreasing),
        "M: Correct contents of top-aligned hcomp with spaces at right");
  } // testHCompTopSpacesRight

  /**
   * Horizontal composition, top aligned, spaces at the left.
   */
  @Test
  public void testHCompTopSpacesLeft() {
    AsciiBlock topIncreasing = new HComp(VAlignment.TOP,
        new AsciiBlock[] {ab3x1, ab2x2, ab1x3, ab3x4, ab2x5, ab1x6});
    assertEquals(12, topIncreasing.width(),
                 "M: Correct width of top-aligned hcomp with spaces at left");
    assertEquals(6, topIncreasing.height(),
                 "M: Correct height of top-aligned hcomp with spaces at left");
    assertEquals(
        ""
            + "111223444556\n"
            + "   223444556\n"
            + "     3444556\n"
            + "      444556\n"
            + "         556\n"
            + "           6\n",
        TestUtils.toString(topIncreasing),
        "M: Correct contents of top-aligned hcomp with spaces at left");
  } // testHCompTopSpacesLeft()

  /**
   * Horizontal composition, center aligned, offset by 2.
   */
  @Test
  public void testHCompCenterNormal() {
    AsciiBlock center1 = new HComp(VAlignment.CENTER,
        new AsciiBlock[] {ab1x6, ab3x4, ab2x2});
    assertEquals(6, center1.width(),
                 "M: Correct width of center-aligned hcomp all even height");
    assertEquals(6, center1.height(),
                 "M: Correct height of center-aligned hcomp all even height");
    assertEquals(
        ""
            + "6     \n"
            + "6444  \n"
            + "644422\n"
            + "644422\n"
            + "6444  \n"
            + "6     \n",
        TestUtils.toString(center1),
        "M: Correct contents of center-aligned hcomp all even height");

    AsciiBlock center2 = new HComp(VAlignment.CENTER,
        new AsciiBlock[] {ab3x1, ab2x5, ab1x3});
    assertEquals(6, center2.width(),
        "M: Correct width of center-aligned hcomp all odd height");
    assertEquals(5, center2.height(),
        "M: Correct height of center-aligned hcomp all odd height");
    assertEquals(
        ""
            + "   55 \n"
            + "   553\n"
            + "111553\n"
            + "   553\n"
            + "   55 \n",
        TestUtils.toString(center2),
        "M: Correct contents of center-aligned hcomp all odd height");
  } // testHCompCenterNormal()

  /**
   * Horizontal composition, center aligned, various offsets.
   */
  @Test
  public void testHCompCenterUneven() {
    AsciiBlock center3 = new HComp(VAlignment.CENTER,
        new AsciiBlock[] {ab2x5, ab2x2});
    assertEquals(4, center3.width(),
        "M: Correct width of center aligned hcomp with mixed heights");
    assertEquals(5, center3.height(),
        "M: Correct height of center aligned hcomp with mixed heights");
    assertEquals(
        ""
            + "55  \n"
            + "5522\n"
            + "5522\n"
            + "55  \n"
            + "55  \n",
        TestUtils.toString(center3),
        "E: Correct contents of center-aligned hcomp w/ odd diff in heights");

    AsciiBlock center4 = new HComp(VAlignment.CENTER,
        new AsciiBlock[] {ab3x1, ab1x6, ab2x2, ab2x5, ab1x3, ab3x4});
    assertEquals(12, center4.width(),
        "M: Correct width of center-aligned hcomp with mixed heights");
    assertEquals(6, center4.height(),
        "M: Correct height of center-aligned hcomp with mixed heights");
    assertEquals(
        ""
            + "   6  55    \n"
            + "   6  553444\n"
            + "111622553444\n"
            + "   622553444\n"
            + "   6  55 444\n"
            + "   6        \n",
        TestUtils.toString(center4),
        "E: Correct contents of center-aligned hcomp with mixed heights");
  } // testHCompCenterUneven

  /**
   * Horizontal composition at the bottom with spaces in the middle.
   */
  @Test
  public void testHCompBottomSpacesCenter() {
    AsciiBlock bottom = new HComp(VAlignment.BOTTOM,
        new AsciiBlock[] {ab1x6, ab3x4, ab2x2, ab3x1, ab1x3, ab2x5});
    assertEquals(12, bottom.width(),
        "M: Correct width of bottom-aligned hcomp with center spaces");
    assertEquals(6, bottom.height(),
        "M: Correct height of bottom-aligned hcomp with center spaces");
    assertEquals(
        ""
            + "6           \n"
            + "6         55\n"
            + "6444      55\n"
            + "6444     355\n"
            + "644422   355\n"
            + "644422111355\n",
        TestUtils.toString(bottom),
        "M: Correct contents of bottom-aligned hcomp with center spaces");
  } // testHCompBottomSpacesCenter()

  /**
   * Make sure that the horizontal composition of empty blocks
   * works correctly.
   *
   * @throws Exception
   *   When one of the rect constructors fails (which they shouldn't).
   */
  @Test
  public void testHCompEmpty() throws Exception {
    AsciiBlock empty = new HComp(VAlignment.TOP,
        new AsciiBlock[] { new Empty(), new Empty(), new Empty()});
    assertEquals(0, empty.height(),
        "E: Width of horizontal composition of empty blocks is 0");
    assertEquals(0, empty.width(),
        "E: Height of horizontal composition of empty blocks is 0");

    AsciiBlock emptyPlus = new HComp(VAlignment.TOP,
        new AsciiBlock[] { new Empty(), new Rect('A', 3, 2), new Empty(),
            new Rect('B', 3, 4), new Empty(), new Empty() });
    assertEquals(6, emptyPlus.width(),
       "E: Width of horizontal composition with empty blocks");
    assertEquals(4, emptyPlus.height(),
       "E: Height of horizontal composition with empty blocks");
    assertEquals(
        ""
            + "AAABBB\n"
            + "AAABBB\n"
            + "   BBB\n"
            + "   BBB\n",
        TestUtils.toString(emptyPlus),
        "E: Contents of horizontal composition with empty blocks");
  } // testHCompEmpty()

  /**
   * Make sure that horizontal composition of mutated blocks works
   * correctly.
   *
   * @throws Exception
   *   WHen one of the rect constructors fails (which they shouldn't).
   */
  @Test
  public void testHCompChange() throws Exception {
    Rect a = new Rect('A', 4, 2);
    Rect b = new Rect('B', 3, 3);
    Rect c = new Rect('C', 2, 4);
    AsciiBlock block = new HComp(VAlignment.TOP, new AsciiBlock[] {a, b, c});

    // Check the original
    assertEquals(9, block.width(),
        "M: Correct width of block");
    assertEquals(4, block.height(),
        "M: Correct height of block");
    assertEquals("AAAABBBCC\nAAAABBBCC\n    BBBCC\n       CC\n",
        TestUtils.toString(block),
        "M: Correct contents of original block");

    // Make them all wider
    a.wider();
    b.wider();
    c.wider();
    assertEquals(12, block.width(),
        "E: Correct width after widening");
    assertEquals(4, block.height(),
        "E: Correct height after widening");
    assertEquals("AAAAABBBBCCC\nAAAAABBBBCCC\n     BBBBCCC\n         CCC\n",
        TestUtils.toString(block),
        "E: Correct contents after widening");

    // Make the shorter ones taller, which shouldn't affect the overall height.
    a.taller();
    b.taller();
    assertEquals(12, block.width(),
        "E: Correct width after making smaller taller");
    assertEquals(4, block.height(),
        "E: Correct height after making smaller taller");
    assertEquals("AAAAABBBBCCC\nAAAAABBBBCCC\nAAAAABBBBCCC\n     BBBBCCC\n",
        TestUtils.toString(block),
        "E: Correct contents after making saller taller");

    // Make the last one shorter.
    c.shorter();
    c.shorter();
    assertEquals(12, block.width(),
        "E: Correct width after making c shorter");
    assertEquals(4, block.height(),
        "E: Correct height after making c shorter");
    assertEquals("AAAAABBBBCCC\nAAAAABBBBCCC\nAAAAABBBB   \n     BBBB   \n",
        TestUtils.toString(block),
        "E: Correct contents after making c shorter");

    // Make the middle one even taller and everything a bit narrower.
    b.taller();
    a.narrower();
    a.narrower();
    b.narrower();
    b.narrower();
    c.narrower();
    c.narrower();
    assertEquals(6, block.width(),
        "E: Correct width after making narrower and taller");
    assertEquals(5, block.height(),
        "E: Correct height after making narrower and taller");
    assertEquals("AAABBC\nAAABBC\nAAABB \n   BB \n   BB \n",
        TestUtils.toString(block),
        "E: Correct contents after making narrower and taller");

    // Make the b's shorter again.
    b.shorter();
    b.shorter();
    b.shorter();
    assertEquals(6, block.width(),
        "E: Correct width after making b much shorter");
    assertEquals(3, block.height(),
        "E: Correct height after making b much shorter");
    assertEquals("AAABBC\nAAABBC\nAAA   \n",
        TestUtils.toString(block),
        "E: Correct contents after making b much shorter");
  } // testHCompChange()

  // +----------------------+----------------------------------------
  // | Vertical composition |
  // +----------------------+

  /**
   * Make sure that the constructor doesn't throw an error.
   */
  @Test
  public void testVCompConstructor() {
    assertNotNull(new VComp(HAlignment.LEFT,
        new AsciiBlock[] {new Line("A"), new Line("B"), new Line("C")}),
        "R: Can build a vertical composition");
  } // testVCompConstructor()

  /**
   * Vertical composition, left aligned.
   */
  @Test
  public void testVCompLeft() {
    AsciiBlock block = new VComp(HAlignment.LEFT,
        new AsciiBlock[] {goodbye, helloworld, exes, justA, pq});
    assertEquals(7, block.width(),
        "M: Correct width of left-aligned vertical composition");
    assertEquals(8, block.height(),
        "M: Correct height of left-aligned vertical composition");
    assertEquals(
        ""
            + "Goodbye\n"
            + "Hello  \n"
            + "World  \n"
            + "xxxx   \n"
            + "xxxx   \n"
            + "xxxx   \n"
            + "A      \n"
            + "PQ     \n",
        TestUtils.toString(block),
        "M: Correct contents of left-aligned vertical composition");
  } // testVCompLeft()

  /**
   * Vertical composition, right aligned.
   */
  @Test
  public void testVCompRight () {
    AsciiBlock block = new VComp(HAlignment.RIGHT,
        new AsciiBlock[] {goodbye, helloworld, justA, exes, justA, pq});
    assertEquals(7, block.width(),
        "M: Correct width of right-aligned vertical composition");
    assertEquals(9, block.height(),
        "M: Correct height of right-aligned vertical composition");
    assertEquals(
        ""
            + "Goodbye\n"
            + "  Hello\n"
            + "  World\n"
            + "      A\n"
            + "   xxxx\n"
            + "   xxxx\n"
            + "   xxxx\n"
            + "      A\n"
            + "     PQ\n",
        TestUtils.toString(block),
        "M: Correct contents of right-aligned vertical composition");
  } // testVCompRight()

  /**
   * Vertical composition, center aligned.
   */
  @Test
  public void testVCompCenter() {
    AsciiBlock block = new VComp(HAlignment.CENTER,
        new AsciiBlock[] {pq, exes, goodbye, helloworld, justA});
    assertEquals(7, block.width(),
        "M: Correct width of center-aligned vertical composition");
    assertEquals(8, block.height(),
        "M: Correct height of center-aligned vertical composition");
    assertEquals(
        ""
            + "  PQ   \n"
            + " xxxx  \n"
            + " xxxx  \n"
            + " xxxx  \n"
            + "Goodbye\n"
            + " Hello \n"
            + " World \n"
            + "   A   \n",
        TestUtils.toString(block),
        "M: Correct contents of center-aligned vertical composition");
  } // testVCompCenter()

  /**
   * Make sure that vertical compositions successfully change when
   * their components change.
   */
  @Test
  public void testVCompChange() throws Exception {
    Rect a = new Rect('A', 4, 2);
    Rect b = new Rect('B', 3, 3);
    Rect c = new Rect('C', 2, 4);
    AsciiBlock block = new VComp(HAlignment.RIGHT, new AsciiBlock[] {a, b, c});

    // Check the original
    assertEquals(4, block.width(),
        "M: Correct width of block");
    assertEquals(9, block.height(),
        "M: Correct height of block");
    assertEquals("""
                 AAAA
                 AAAA
                  BBB
                  BBB
                  BBB
                   CC
                   CC
                   CC
                   CC
                 """,
        TestUtils.toString(block),
        "M: Correct contents of original block");

    // Make them all wider
    a.wider();
    b.wider();
    c.wider();
    assertEquals(5, block.width(),
        "E: Correct width after widening all three");
    assertEquals(9, block.height(),
        "E: Correct height after widening all three");
    assertEquals("""
                 AAAAA
                 AAAAA
                  BBBB
                  BBBB
                  BBBB
                   CCC
                   CCC
                   CCC
                   CCC
                 """,
        TestUtils.toString(block),
        "E: Correct contents after widening all three");

    // Make the widest one even wider
    a.wider();
    a.wider();
    assertEquals(7, block.width(),
        "E: Correct width after widening just a");
    assertEquals(9, block.height(),
        "E: Correct height after widening just a");
    assertEquals("""
                 AAAAAAA
                 AAAAAAA
                    BBBB
                    BBBB
                    BBBB
                     CCC
                     CCC
                     CCC
                     CCC
                 """,
        TestUtils.toString(block),
        "E: Correct contents after widening just a");

    // Make the shorter ones wider, which shouldn't affect the overall width.
    b.wider();
    c.wider();
    assertEquals(7, block.width(),
        "E: Correct width after widening b and c");
    assertEquals(9, block.height(),
        "E: Correct height after widening b and c");
    assertEquals("""
                 AAAAAAA
                 AAAAAAA
                   BBBBB
                   BBBBB
                   BBBBB
                    CCCC
                    CCCC
                    CCCC
                    CCCC
                 """,
        TestUtils.toString(block),
        "E: Correct contents after widening b and c");


    // Make the last one shorter.
    c.shorter();
    c.shorter();
    c.shorter();
    assertEquals(7, block.width(),
        "E: Correct width after making c shorter");
    assertEquals(6, block.height(),
        "E: Correct height after making c shorter");
    assertEquals("""
                 AAAAAAA
                 AAAAAAA
                   BBBBB
                   BBBBB
                   BBBBB
                    CCCC
                 """,
        TestUtils.toString(block),
        "E: Correct contents after making c shorter");

    // Make the middle one even taller and everything a bit narrower.
    a.taller();
    b.taller();
    a.narrower();
    a.narrower();
    b.narrower();
    b.narrower();
    c.narrower();
    c.narrower();
    assertEquals(5, block.width(),
        "E: Correct width after making narrower and taller");
    assertEquals(8, block.height(),
        "E: Correct height after making narrower and taller");
    assertEquals("""
                 AAAAA
                 AAAAA
                 AAAAA
                   BBB
                   BBB
                   BBB
                   BBB
                    CC
                 """,
        TestUtils.toString(block),
        "E: Correct contents after making narrower and taller");

    // Make the b's shorter again.
    b.shorter();
    b.shorter();
    b.shorter();
    assertEquals(5, block.width(),
        "E: Correct width after making b much shorter");
    assertEquals(5, block.height(),
        "E: Correct height after making b much shorter");
    assertEquals("""
                 AAAAA
                 AAAAA
                 AAAAA
                   BBB
                    CC
                 """,
        TestUtils.toString(block),
        "E: Correct contents after making b much shorter");
  } // testVCompChange()

  /**
   * Test vertical composition with the empty block.
   */
  @Test
  public void testVCompEmpty() {
    AsciiBlock empty =
        new VComp(HAlignment.LEFT, new AsciiBlock[] { new Empty() });
    assertEquals(0, empty.width(),
        "E: Correct width for vertical composition of one empty");
    assertEquals(0, empty.height(),
        "E: Correct height for vertical composition of one empty");

    AsciiBlock nothing = new VComp(HAlignment.CENTER, new AsciiBlock[] { });
    assertEquals(0, nothing.width(),
        "E: Correct width for vertical composition of nothing");
    assertEquals(0, empty.height(),
        "E: Correct height for vertical composition of nothing");

    AsciiBlock block = new VComp(HAlignment.RIGHT,
        new AsciiBlock[] { new Empty(), helloworld, new Empty(), helloworld,
            new Empty(), goodbye, new Empty(), new Empty() });
    assertEquals(5, block.height(),
        "E: Correct height for vertical composition that includes empty");
    assertEquals(7, block.width(),
        "E: Correct height for vertical composition that includes empty");
    assertEquals("""
                   Hello
                   World
                   Hello
                   World
                 Goodbye
                 """,
        TestUtils.toString(block),
        "E: Correct contents for vertical composition that includes empty");
  } // testVCompEmpty()

  // +-------+-------------------------------------------------------
  // | HFlip |
  // +-------+

  /**
   * Horizontal flips of the empty block.
   */
  @Test
  public void testHFlipEmpty() {
    AsciiBlock empty = new HFlip(new Empty());
    assertEquals(0, empty.width(),
        "M: Correct width for hflipped empty");
    assertEquals(0, empty.height(),
        "M: Correct height for hflipped empty");
  } // testHFlipEmpty()

  /**
   * Horizontal flips of a single line.
   */
  @Test
  public void testHFlipLine() {
    AsciiBlock line = new Line("Hello");

    AsciiBlock flipped = new HFlip(line);
    assertEquals(5, flipped.width(),
        "M: Correct width for hflipped hello");
    assertEquals(1, flipped.height(),
        "M: Correct height for hflipped hello");
    assertEquals("olleH\n", TestUtils.toString(flipped),
        "M: Correct contents for hflipped hello");

    AsciiBlock flipflop = new HFlip(flipped);
    assertEquals(5, flipflop.width(),
        "M: Correct width for doubly hflipped hello");
    assertEquals(1, flipflop.height(),
        "M: Correct height for doubly hflipped hello");
    assertEquals("Hello\n", TestUtils.toString(flipflop),
        "M: Correct contents for doubly hflipped hello");
  } // testHFlipLine()

  /**
   * Horizontal flip of various stuff.
   */
  @Test
  public void testHFlipStuff() {
    AsciiBlock abcd = 
        new VComp(HAlignment.LEFT, new AsciiBlock[] { new Line("alfa"), 
            new Line("bravo"), new Line("charlie"), new Line("delta") });

    AsciiBlock flipped = new HFlip(abcd);
    assertEquals(7, flipped.width(),
        "M: Correct width for hflipped abcd");
    assertEquals(4, flipped.height(),
        "M: Correct height for hflipped abcd");
    assertEquals("   afla\n  ovarb\neilrahc\n  atled\n",
        TestUtils.toString(flipped),
        "M: Correct contents for hflipped abcd");

    AsciiBlock flipflop = new HFlip(flipped);
    assertEquals(7, flipflop.width(),
        "M: Correct width for doubly hflipped abcd");
    assertEquals(4, flipflop.height(),
        "M: Correct height for doubly hflipped abcd");
    assertEquals("alfa   \nbravo  \ncharlie\ndelta  \n",
        TestUtils.toString(flipflop),
        "M: Correct contents for doubly hflipped abcd");

    AsciiBlock mirror = 
        new HComp(VAlignment.TOP, new AsciiBlock[] { flipped, abcd });
    assertEquals(14, mirror.width(),
        "E: Correct width for mirrored text");
    assertEquals(4, mirror.height(),
        "E: Correct height for mirrored text");
    assertEquals(
        "   aflaalfa   \n  ovarbbravo  \neilrahccharlie\n  atleddelta  \n",
        TestUtils.toString(mirror),
        "E: Correct contents for mirrored text");
    
    AsciiBlock rorrim = new HFlip(mirror);
    assertEquals(mirror.width(), rorrim.width(),
        "E: Correct width for hflipped mirror");
    assertEquals(mirror.height(), rorrim.height(),
        "E: Correct height for hflipped mirror");
    assertEquals(TestUtils.toString(mirror), TestUtils.toString(rorrim),
        "E: Correct contents for hflipped mirror");
  } // testHFlipStuff

  /**
   * HFlips with stuff that changes.
   */
  @Test
  public void testHFlipChange() {
    Line a = new Line("alfa");
    Line b = new Line("bravo");
    Line c = new Line("charlie");
    Line d = new Line("delta");
    AsciiBlock abcd = new VComp(HAlignment.LEFT, new AsciiBlock[] {a, b, c, d});

    AsciiBlock flipped = new HFlip(abcd);

    c.update("see");
    assertEquals(5, flipped.width(),
        "E: Correct width of flipped abcd after shortening");
    assertEquals(4, flipped.height(),
        "E: Correct height of flipped abcd after shortening");
    assertEquals(" afla\novarb\n  ees\natled\n",
        TestUtils.toString(flipped),
        "E: Correct contents of flipped abcd after shortening");

    a.update("alphabet");
    assertEquals(8, flipped.width(),
        "E: Correct width of flipped abcd after widening");
    assertEquals(4, flipped.height(),
        "E: Correct height of flipped abcd after widening");
    assertEquals("tebahpla\n   ovarb\n     ees\n   atled\n",
        TestUtils.toString(flipped),
        "E: Correct contents of flipped abcd after widening");
  } // testHFlipChange()

  // +-------+-------------------------------------------------------
  // | VFlip |
  // +-------+

  /**
   * Vertical flips of the empty block.
   */
  @Test
  public void testVFlipEmpty() {
    AsciiBlock empty = new VFlip(new Empty());
    assertEquals(0, empty.width(),
        "M: Correct width for vflipped empty");
    assertEquals(0, empty.height(),
        "M: Correct height for vflipped empty");
  } // testVFlipEmpty()

  /**
   * Vertical flips of a single line.
   */
  @Test
  public void testVFlipLine() {
    AsciiBlock line = new Line("Hello");

    AsciiBlock flipped = new VFlip(line);
    assertEquals(5, flipped.width(),
        "M: Correct width for vflipped hello");
    assertEquals(1, flipped.height(),
        "M: Correct height for vflipped hello");
    assertEquals("Hello\n", TestUtils.toString(flipped),
        "M: Correct contents for vflipped hello");

    AsciiBlock flipflop = new VFlip(flipped);
    assertEquals(5, flipflop.width(),
        "M: Correct width for doubly vflipped hello");
    assertEquals(1, flipflop.height(),
        "M: Correct height for doubly vflipped hello");
    assertEquals("Hello\n", TestUtils.toString(flipflop),
        "M: Correct contents for doubly vflipped hello");
  } // testVFlipLine()

  /**
   * Vertical flip of various stuff.
   */
  @Test
  public void testVFlipStuff() {
    AsciiBlock abcd = 
        new VComp(HAlignment.LEFT, new AsciiBlock[] { new Line("alfa"), 
            new Line("bravo"), new Line("charlie"), new Line("delta") });

    AsciiBlock flipped = new VFlip(abcd);
    assertEquals(7, flipped.width(),
        "M: Correct width for vflipped abcd");
    assertEquals(4, flipped.height(),
        "M: Correct height for vflipped abcd");
    assertEquals("delta  \ncharlie\nbravo  \nalfa   \n",
        TestUtils.toString(flipped),
        "M: Correct contents for vflipped abcd");

    AsciiBlock flipflop = new VFlip(flipped);
    assertEquals(7, flipflop.width(),
        "M: Correct width for doubly vflipped abcd");
    assertEquals(4, flipflop.height(),
        "M: Correct height for doubly vflipped abcd");
    assertEquals("alfa   \nbravo  \ncharlie\ndelta  \n",
        TestUtils.toString(flipflop),
        "M: Correct contents for doubly vflipped abcd");
  } // testVFlipStuff

  /**
   * VFlips with stuff that changes.
   */
  @Test
  public void testVFlipChange() {
    Line a = new Line("alfa");
    Line b = new Line("bravo");
    Line c = new Line("charlie");
    Line d = new Line("delta");
    AsciiBlock abcd = new VComp(HAlignment.LEFT, new AsciiBlock[] {a, b, c, d});

    AsciiBlock flipped = new VFlip(abcd);

    c.update("see");
    assertEquals(5, flipped.width(),
        "E: Correct width of flipped abcd after shortening");
    assertEquals(4, flipped.height(),
        "E: Correct height of flipped abcd after shortening");
    assertEquals("delta\nsee  \nbravo\nalfa \n",
        TestUtils.toString(flipped),
        "E: Correct contents of flipped abcd after shortening");

    a.update("alphabet");
    assertEquals(8, flipped.width(),
        "E: Correct width of flipped abcd after widening");
    assertEquals(4, flipped.height(),
        "E: Correct height of flipped abcd after widening");
    assertEquals("delta   \nsee     \nbravo   \nalphabet\n",
        TestUtils.toString(flipped),
        "E: Correct contents of flipped abcd after widening");
  } // testVFlipChange()

} // class TestBlocks
