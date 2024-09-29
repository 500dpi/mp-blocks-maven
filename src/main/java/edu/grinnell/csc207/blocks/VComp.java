package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The vertical composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Jana Vadillo
 */
public class VComp implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The blocks.
   */
  AsciiBlock[] blocks;

  /**
   * How the blocks are aligned.
   */
  HAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a vertical composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   * @param topBlock
   *   The block on the top.
   * @param bottomBlock
   *   The block on the bottom.
   */
  public VComp(HAlignment alignment, AsciiBlock topBlock,
      AsciiBlock bottomBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {topBlock, bottomBlock};
  } // VComp(HAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a vertical composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public VComp(HAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // VComp(HAlignment, AsciiBlock[])

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
   *   if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {
    // line to store string to return
    String line = "";
    int gap = 0;
    int gapLeft = 0;
    int gapRight = 0;
    int iChecked = 0;

    if ((i < 0) || (i >= this.height())) {
      throw new Exception("Invalid row " + i);
    } // if

    // loop itterating through all the blocks in your array
    for (int n = 0; n < this.blocks.length; n++) {
      for (int blockI = 0;  blockI < this.blocks[n].height(); blockI += 1) {
        if (iChecked == i) {
          line += this.blocks[n].row(blockI);
          // gap between the current block and the total block height
          gap  = this.width() - this.blocks[n].width();
          gapLeft = gap / 2;
          gapRight = this.width() - this.blocks[n].width() - gapLeft;
          break;
        } // if
        iChecked += 1;
      } // for
      // loop itterating through all of the element inthe blocks checking
      /// if i has been reached and if so updating that line with that i
      if (!line.equals("")) {
        break;
      } // if
    } // for

    // if top alligned
    if (this.align == HAlignment.LEFT) {
      return line + " ".repeat(gap);
    } // if

    // what to do if right alligned
    if (this.align == HAlignment.RIGHT) {
      return " ".repeat(gap) + line;
    } // if

    // what to do if center alligned
    if (this.align == HAlignment.CENTER) {
      return " ".repeat(gapLeft) + line + " ".repeat(gapRight);
    } else {
      throw new Exception("Invalid alignment " + this.align);
    } // if
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int height = 0;
    for (int i = 0; i < this.blocks.length; i++) {
      height += this.blocks[i].height();
    } // for
    return height;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int width = 0;
    for (int i = 0; i < this.blocks.length; i++) {
      if (this.blocks[i].width() >= width) {
        width  = this.blocks[i].width();
      } // if
    } // for
    return width;
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other
   *              The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *         false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    return ((other instanceof VComp) && (this.eqv((VComp) other)));
  } // eqv(AsciiBlock)

  /**
   * Determine if another Hblock is structurally equivalent to this grid.
   *
   * @param other
   *              The Hblock to compare to this Hblock.
   *
   * @return true if the two blocks are structurally equivalent and
   *         false otherwise.
   */
  public boolean eqv(VComp other) {
    if (!(this.align == other.align)) {
      return false;
    } // if
    for (int i = 0; i < this.blocks.length; i++) {
      if (!(this.blocks[i].eqv(other.blocks[i]))) {
        return false;
      } // if
    } // for
    return true;
  } // eqv(VComp)
} // class Grid