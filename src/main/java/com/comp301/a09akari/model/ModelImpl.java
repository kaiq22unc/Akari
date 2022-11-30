package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {

  int index;
  private PuzzleLibrary lib;
  private Puzzle active;
  private boolean lamb[][];
  private List<ModelObserver> obs;

  public ModelImpl(PuzzleLibrary library) {
    // Your constructor code here
    lib = library;
    index = 0;
    active = lib.getPuzzle(index);
    lamb = new boolean[active.getHeight()][active.getWidth()];
    obs = new ArrayList<>();
  }

  @Override
  public void addLamp(int r, int c) {
    if (r >= active.getHeight() || r < 0 || c >= active.getWidth() || c < 0)
      throw new IndexOutOfBoundsException();
    if (active.getCellType(r, c) != CellType.CORRIDOR) throw new IllegalArgumentException();
    if (!lamb[r][c]) lamb[r][c] = true;
  }

  @Override
  public void removeLamp(int r, int c) {
    if (r >= active.getHeight() || r < 0 || c >= active.getWidth() || c < 0)
      throw new IndexOutOfBoundsException();
    if (active.getCellType(r, c) != CellType.CORRIDOR) throw new IllegalArgumentException();
    if (lamb[r][c]) lamb[r][c] = false;
  }

  @Override
  public boolean isLit(int r, int c) {
    if (r >= active.getHeight() || r < 0 || c >= active.getWidth() || c < 0)
      throw new IndexOutOfBoundsException();
    if (active.getCellType(r, c) != CellType.CORRIDOR) throw new IllegalArgumentException();
    int tr = r;
    int tc = c;
    while (active.getCellType(r, c) == CellType.CORRIDOR && r > 0 && r < active.getHeight()) {
      if (isLamp(r, c)) return true;
      r = r - 1;
    }
    r = tr;
    while (active.getCellType(r, c) == CellType.CORRIDOR && r > 0 && r < active.getHeight()) {
      if (isLamp(r, c)) return true;
      r = r + 1;
    }
    r = tr;
    while (active.getCellType(r, c) == CellType.CORRIDOR && c > 0 && c < active.getWidth()) {
      if (isLamp(r, c)) return true;
      c = c - 1;
    }
    c = tc;
    while (active.getCellType(r, c) == CellType.CORRIDOR && c > 0 && r < active.getWidth()) {
      if (isLamp(r, c)) return true;
      c = c + 1;
    }
    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (r >= active.getHeight() || r < 0 || c >= active.getWidth() || c < 0)
      throw new IndexOutOfBoundsException();
    if (active.getCellType(r, c) != CellType.CORRIDOR) throw new IllegalArgumentException();
    return lamb[r][c];
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (r >= active.getHeight() || r < 0 || c >= active.getWidth() || c < 0)
      throw new IndexOutOfBoundsException();
    if (!isLamp(r, c)) throw new IllegalArgumentException();
    int tr = r;
    int tc = c;
    if (r > 0) {
      r = r - 1;
      while (active.getCellType(r, c) == CellType.CORRIDOR && r >= 0) {
        if (isLamp(r, c)) return true;
        r = r - 1;
        if (r < 0) break;
      }
    }
    if (r < active.getHeight() - 1) {
      r = tr + 1;
      while (active.getCellType(r, c) == CellType.CORRIDOR && r < active.getHeight()) {
        if (isLamp(r, c)) return true;
        r = r + 1;
        if (r >= active.getHeight()) break;
      }
      r = tr;
    }
    if (c > 0) {
      c = c - 1;
      while (active.getCellType(r, c) == CellType.CORRIDOR && c >= 0) {
        if (isLamp(r, c)) return true;
        c = c - 1;
        if (c < 0) break;
      }
    }
    if (c < active.getWidth() - 1) {
      c = tc + 1;
      while (active.getCellType(r, c) == CellType.CORRIDOR && c < active.getWidth()) {
        if (isLamp(r, c)) return true;
        c = c + 1;
        if (c >= active.getWidth()) break;
      }
    }
    return false;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return active;
  }

  @Override
  public int getActivePuzzleIndex() {
    return index;
  }

  @Override
  public void setActivePuzzleIndex(int i) {
    if (i >= lib.size() || i < 0) throw new IndexOutOfBoundsException();
    index = i;
    active = lib.getPuzzle(index);
  }

  @Override
  public int getPuzzleLibrarySize() {
    return lib.size();
  }

  @Override
  public void resetPuzzle() {
    for (int i = 0; i < active.getHeight(); i++) {
      for (int j = 0; j < active.getWidth(); j++) {
        if (active.getCellType(i, j) == CellType.CORRIDOR) if (isLamp(i, j)) removeLamp(i, j);
      }
    }
  }

  @Override
  public boolean isSolved() {
    for (int i = 0; i < active.getHeight(); i++) {
      for (int j = 0; i < active.getWidth(); j++) {
        if (active.getCellType(i, j) == CellType.CLUE) if (!isClueSatisfied(i, j)) return false;
        if (active.getCellType(i, j) == CellType.CORRIDOR) if (!isLit(i, j)) return false;
        if (isLamp(i, j)) if (isLampIllegal(i, j)) return false;
      }
    }
    return true;
  }

  @Override
  public boolean isClueSatisfied(int i, int j) {
    if (i >= active.getHeight() || i < 0 || j >= active.getWidth() || j < 0)
      throw new IndexOutOfBoundsException();
    if (active.getCellType(i, j) != CellType.CORRIDOR) throw new IllegalArgumentException();
    int counter = 0;
    if (i + 1 < active.getHeight()) if (isLamp(i + 1, j)) counter++;
    if (i - 1 >= 0) if (isLamp(i - 1, j)) counter++;
    if (j + 1 < active.getWidth()) if (isLamp(i, j + 1)) counter++;
    if (j - 1 >= 0) if (isLamp(i, j - 1)) counter++;
    return counter <= active.getClue(i, j);
  }

  @Override
  public void addObserver(ModelObserver observer) {
    obs.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    obs.remove(observer);
  }
}
