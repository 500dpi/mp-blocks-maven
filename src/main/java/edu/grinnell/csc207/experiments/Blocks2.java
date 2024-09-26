package edu.grinnell.csc207.experiments;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.Boxed;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.Line;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.Surrounded;
import edu.grinnell.csc207.blocks.VAlignment;

import java.io.PrintWriter;

public class Blocks2 {
    /**
     * Print a separator.
     *
     * @param pen
     *            What we use to print the separator.
     */
    static void separator(PrintWriter pen) {
        pen.printf("\n%s\n\n", "=".repeat(60));
    } // separator(PrintWriter)

    /**
     * Print a single AsciiBlock with a separator and a caption.
     *
     * @param pen
     *                The PrintWriter to use for printing.
     * @param caption
     *                The caption to print.
     * @param block
     *                The block to print.
     */
    static void figure(PrintWriter pen, String caption, AsciiBlock block) {
        separator(pen);
        pen.println(caption);
        pen.println("-".repeat(caption.length()));
        pen.println();
        AsciiBlock.print(pen, block);
    } // figure

    /**
     * Run the experiments.
     *
     * @param args
     *             The command-line parameters (ignored).
     */

    public static void main(String[] args) throws Exception {
        PrintWriter pen = new PrintWriter(System.out, true);

        Line line = new Line("Hello");
        Rect exes = new Rect('X', 3, 3);
        Rect a = new Rect('a', 5, 2);
        Rect b = new Rect('b', 3, 3);
        Rect c = new Rect('c', 2, 6);
        AsciiBlock boxedLine = new Boxed(line);
        AsciiBlock boxedboxedLine = new Boxed(boxedLine);
        AsciiBlock boxedExes = new Boxed(exes);
        AsciiBlock SurroundedXY =  new Boxed(new Surrounded(boxedExes, 'y'));
        AsciiBlock HAligmentTest = new HComp(VAlignment.CENTER, new AsciiBlock[] {a, b, c});
    
        pen.println("Original Values");
        figure(pen, "line", line);
        figure(pen, "exes", exes);
        figure(pen, "boxedLine", boxedLine);
        figure(pen, "boxedboxedLine", boxedboxedLine);
        figure(pen, "boxedExes", boxedExes);
        figure(pen, "Surrounded boxed exes", SurroundedXY);
        figure(pen, "Surrounded boxed exes", HAligmentTest);


    }

}
