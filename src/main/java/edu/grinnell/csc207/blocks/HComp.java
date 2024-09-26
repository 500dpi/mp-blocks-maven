package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The horizontal composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Your Name Here
 */
public class HComp implements AsciiBlock {
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
  VAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a horizontal composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   * @param leftBlock
   *   The block on the left.
   * @param rightBlock
   *   The block on the right.
   */
  public HComp(VAlignment alignment, AsciiBlock leftBlock,
      AsciiBlock rightBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {leftBlock, rightBlock};
  } // HComp(VAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a horizontal composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public HComp(VAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // HComp(Alignment, AsciiBLOCK[])

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
    String line = "";
    for (int n = 0; n< this.blocks.length; n++){
      int gap  = this.height() - this.blocks[n].height();

      if (this.align == VAlignment.TOP){
        if ((i>=0) && (i<this.blocks[n].height())){
          line += this.blocks[n].row(i);
        }
        else if ((i>= 0) && (i < this.height())){
          line += (" ".repeat(this.blocks[n].width()));
        }
        else{
          throw new Exception("Invalid row " + i);
        }
      }
      if (this.align == VAlignment.BOTTOM){
        if ((i>=0) && (i< gap)){
          line += (" ".repeat(this.blocks[n].width()));
        }
        else if ((i>= 0) && (i < this.height())){
          line += this.blocks[n].row(i - gap);
        }
        else{
          throw new Exception("Invalid row " + i);
        }
      }
      if (this.align == VAlignment.CENTER){
        int End = gap/2 + this.blocks[n].height();
        int Start = gap/2;
        if ((i>= Start) && (i < End)){
          line += this.blocks[n].row(i-Start);
        }
        else if ((i>= 0) && (i < this.height())){
          line += (" ".repeat(this.blocks[n].width()));
        }
        else{
          throw new Exception("Invalid row " + i);
        }
      }
    }
    return line;  // STUB
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int height = 0;
    for (int i = 0; i< this.blocks.length; i++){
      if (this.blocks[i].height() > height){
        height  = this.blocks[i].height();
      }
    }
    return height; 
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int width = 0;
    for (int i = 0; i< this.blocks.length; i++){
      width += this.blocks[i].width();
    }
    return width;   // STUB
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    return false;       // STUB
  } // eqv(AsciiBlock)
} // class HComp
