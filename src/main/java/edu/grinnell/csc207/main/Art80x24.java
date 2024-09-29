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

    AsciiBlock baseWeb = new Line("/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\");
    AsciiBlock floor1 = new Line("_______");
    AsciiBlock floor2 = new Line("_____________________");

    AsciiBlock person = new VComp(HAlignment.CENTER, new AsciiBlock[] {
        new Line("\\_o_/ "),
        new Line("   )  "),
        new Line("  /\\__"),
        new Line("_ \\ __")});

    AsciiBlock floor = new HComp(VAlignment.BOTTOM, new AsciiBlock[] {
        floor1, person, floor2 });

    AsciiBlock web9 = new Lines(new String[] {
        "|", "|", "|", "|", "|", "|", "|", "|", "|"});
    AsciiBlock web12 = new Lines(new String[] {
        "|", "|", "|", "|", "|", "|", "|", "|", "|", "|", "|", "|"});
    AsciiBlock web4 = new Lines(new String[] {
        "|", "|", "|", "|"});

    AsciiBlock leftLegs = new VComp(HAlignment.CENTER, new AsciiBlock[] {
        new Line("  /"),
        new Line("\\_\\("),
        new Line(" _//"),
        new Line("  / ")});

    AsciiBlock rightLegs = new VComp(HAlignment.CENTER, new AsciiBlock[] {
        new Line(" \\  "),
        new Line(")/_/"),
        new Line("\\\\_ "),
        new Line(" \\  ")});

    AsciiBlock bodyType1 = new VComp(HAlignment.CENTER, new AsciiBlock[] {
        web12,
        new Line("_"),
        new Line("o"),
        new Line(" ")});

    AsciiBlock bodyType2 = new VComp(HAlignment.CENTER, new AsciiBlock[] {
        web9,
        new Line("_"),
        new Line("\""),
        new Line(" ")});

    AsciiBlock bodyType3 = new VComp(HAlignment.CENTER, new AsciiBlock[] {
        web4,
        new Line("_"),
        new Line("^"),
        new Line(" ")});

    AsciiBlock spider1 = new HComp(VAlignment.BOTTOM, new AsciiBlock[] {
        leftLegs, bodyType1, rightLegs, new Line("   ")});

    AsciiBlock spider2 = new HComp(VAlignment.BOTTOM, new AsciiBlock[] {
        leftLegs, bodyType2, rightLegs, new Line("   ")});

    AsciiBlock spider3 = new HComp(VAlignment.BOTTOM, new AsciiBlock[] {
        leftLegs, bodyType3, rightLegs, new Line("   ")});

    AsciiBlock spiders = new HComp(VAlignment.TOP, new AsciiBlock[] {
        spider1, spider3, spider2, new Line(" ")});
    AsciiBlock finalSpiders = new HComp(VAlignment.TOP, new AsciiBlock[] {spiders, spiders});
    AsciiBlock web = new HComp(VAlignment.TOP, new AsciiBlock[] {baseWeb, baseWeb, baseWeb});
    AsciiBlock.print(pen, new VComp(HAlignment.CENTER,
        new AsciiBlock[] {web, finalSpiders, floor}));
    pen.close();
  } // main(String[])
} // class Art80x24
