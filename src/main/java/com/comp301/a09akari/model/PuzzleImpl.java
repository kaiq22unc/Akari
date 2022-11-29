package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {
  private int[][] board;
  private int width;
  private int height;

  public PuzzleImpl(int[][] board) {
    // Your constructor code here
    this.board = board;
    width = board[0].length;
    height = board.length;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r >= height || r < 0 || c >= width || c < 0) throw new IndexOutOfBoundsException();
    if (board[r][c] == 0
        || board[r][c] == 1
        || board[r][c] == 2
        || board[r][c] == 3
        || board[r][c] == 4) return CellType.CLUE;
    if (board[r][c] == 5) return CellType.WALL;
    if (board[r][c] == 6) return CellType.CORRIDOR;
    return null;
  }

  @Override
  public int getClue(int r, int c) {
    if (r >= height || r < 0 || c >= width || c < 0) throw new IndexOutOfBoundsException();
    if (getCellType(r, c) != CellType.CLUE) throw new IllegalArgumentException();
    return board[r][c];
  }
}
