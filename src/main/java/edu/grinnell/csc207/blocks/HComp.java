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
    String line = "";// line to store string to return
    for (int n = 0; n< this.blocks.length; n++){ //loop itterating through all the blocks in your array
      int gap  = this.height() - this.blocks[n].height(); // gap between the current block and the total block height

      if (this.align == VAlignment.TOP){ // what to do if top alligned
        if ((i>=0) && (i<this.blocks[n].height())){
          line += this.blocks[n].row(i);
        } // if you are within the bounds of where your current block fits return the same i line
        else if ((i>= 0) && (i < this.height())){
          line += (" ".repeat(this.blocks[n].width()));
        } // if you are still within the larger block return " "
        else{
          throw new Exception("Invalid row " + i);
        }// ptherwise the row must be invalid
      }// top alligned 
      if (this.align == VAlignment.BOTTOM){
        if ((i>=0) && (i< gap)){
          line += (" ".repeat(this.blocks[n].width())); // if you are bellow where the block should start, return a " " spacer"
        }
        else if ((i>= 0) && (i < this.height())){
          line += this.blocks[n].row(i - gap);
        }// otherwise throw the respective i line shifted by the gap
        else{
          throw new Exception("Invalid row " + i);
        }// otherwise it must be out of bounds
      }// bottom alligned
      if (this.align == VAlignment.CENTER){
        int End = gap/2 + this.blocks[n].height();// keeps track of where we should end the transfer of the middle
        int Start = gap/2; // keeps track of where we should start the middle transfer
        if ((i>= Start) && (i < End)){
          line += this.blocks[n].row(i-Start);
        } // of you are in the correct range give up the shifted values
        else if ((i>= 0) && (i < this.height())){
          line += (" ".repeat(this.blocks[n].width()));
        } // otherwise return a filller
        else{
          throw new Exception("Invalid row " + i);
        }// otherwise raise an error
      }// center alligned
    }//end of loop for itterating through blocks
    return line;  //return the line built
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
      }//
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
   *              The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *         false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    return ((other instanceof HComp) && (this.eqv((HComp) other)));
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
  public boolean eqv(HComp other) {
    return (this.blocks == other.blocks) && (this.align == other.align);
  } // eqv(Grid)
} // class Grid
