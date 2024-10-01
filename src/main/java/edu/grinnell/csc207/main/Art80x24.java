package edu.grinnell.csc207.main;

import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.VComp;
import edu.grinnell.csc207.blocks.Lines;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.HAlignment;
import java.io.PrintWriter;

/**
 * Create and print an amazing 80x24 ASCII artwork.
 *
 * @author Sara Jaljaa
 * @author Jana Vadillo
 */
public class Art80x24 {

  /**
   * Creates ASCII art of a spider web descending.
   *
   * @param repetitions
   *   The height of the web.
   *
   * @return
   *   An AsciiBlock representing a spider web.
   */
  public static AsciiBlock web(int repetitions) {
    AsciiBlock hangingL = new Lines(new String[] {"\\\\\\", " \\\\", "  \\"});
    AsciiBlock hangingR = new Lines(new String[] {"///", "//", "/"});
    String s = "|\n".repeat(repetitions);
    return new HComp(VAlignment.TOP, new AsciiBlock[] {hangingL, new Lines(s), hangingR});
  } // web(int)

    /**
   * Creates ASCII art of a spider.
   *
   * @param head
   *   The head of the spider.
   *
   * @return
   *   An AsciiBlock representing a spider.
   */
  public static AsciiBlock spider(char head) {
    AsciiBlock leftLegs = new VComp(HAlignment.CENTER, new AsciiBlock[] {
      new Lines(" ( \n\\_\\(\n _//\n  ( ")});
    AsciiBlock rightLegs = new VComp(HAlignment.CENTER, new AsciiBlock[] {
      new Lines("  ) \n)/_/\n\\\\_ \n )  ")});
    AsciiBlock center = new VComp(HAlignment.CENTER, new AsciiBlock[] {
      new Line("|"),
      new Line("_"),
      new Line(String.valueOf(head)),
      new Line(" ")});
    return new HComp(VAlignment.TOP, new AsciiBlock[] {
      leftLegs,
      center,
      rightLegs});
  } // spider(char)

  /**
   * Creates artwork depicting a human trapped in a spider web.
   *
   * @param args
   *   Command-line arguments (currently ignored).
   *
   * @exception Exception
   *   If something goes wrong with one of the underlying classes.
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);

    AsciiBlock baseWeb = new Line("/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\");
    AsciiBlock floorLeft = new Line("_______");
    AsciiBlock floorRight = new Line("_____________________");
    AsciiBlock person = new Lines("\\_o_/ \n   )  \n  /\\__\n_ \\ __\n");

    AsciiBlock floor = new HComp(VAlignment.BOTTOM, new AsciiBlock[] {
        floorLeft, person, floorRight});

    AsciiBlock spider1 = new VComp(HAlignment.CENTER, web(9), spider('o'));
    AsciiBlock spider2 = new VComp(HAlignment.CENTER, web(14), spider('\"'));
    AsciiBlock spider3 = new VComp(HAlignment.CENTER, web(6), spider('^'));

    AsciiBlock finalSpiders = new HComp(VAlignment.TOP, new AsciiBlock[] {
      new Lines(" // \n// \n/  "),
        spider3, spider2, spider1, spider3,
        spider2, spider1, spider2, spider3,
      new Lines(" \\\\\n  \\\\\n   \\")});

    AsciiBlock base = new HComp(VAlignment.TOP, new AsciiBlock[] {
        baseWeb, baseWeb, baseWeb, baseWeb});

    AsciiBlock.print(pen, new VComp(HAlignment.CENTER, new AsciiBlock[] {
        base, finalSpiders, floor}));
    pen.close();
  } // main(String[])
} // class Art80x24
