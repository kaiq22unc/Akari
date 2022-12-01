package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.Model;

import java.util.Random;

public class ControllerImpl implements ClassicMvcController {

  private Model model;

  public ControllerImpl(Model model) {
    // Constructor code goes here
    this.model = model;
  }

  @Override
  public void clickNextPuzzle() {
    int index = model.getActivePuzzleIndex();
    if (index < model.getPuzzleLibrarySize() - 1) model.setActivePuzzleIndex(index + 1);
  }

  @Override
  public void clickPrevPuzzle() {
    int index = model.getActivePuzzleIndex();
    if (index > 0) model.setActivePuzzleIndex(index - 1);
  }

  @Override
  public void clickRandPuzzle() {
    Random rand = new Random();
    int index = rand.nextInt(model.getPuzzleLibrarySize());
    model.setActivePuzzleIndex(index);
  }

  @Override
  public void clickResetPuzzle() {
    model.resetPuzzle();
  }

  @Override
  public void clickCell(int r, int c) {
    if (model.isLamp(r, c)) model.removeLamp(r, c);
    else model.addLamp(r, c);
  }
}
