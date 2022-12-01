package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlView implements FXComponent {

  private final ClassicMvcController controller;

  public ControlView(ClassicMvcController controller) {
    this.controller = controller;
  }

  @Override
  public Parent render() {
    HBox hbox = new HBox();
    Button prev = new Button("Prev");
    prev.setOnAction(
        (ActionEvent event) -> {
          controller.clickPrevPuzzle();
        });
    Button next = new Button("Next");
    next.setOnAction(
        (ActionEvent event) -> {
          controller.clickNextPuzzle();
        });
    Button reset = new Button("Reset");
    reset.setOnAction(
        (ActionEvent event) -> {
          controller.clickResetPuzzle();
        });
    Button rand = new Button("Random Puzzle");
    rand.setOnAction(
        (ActionEvent event) -> {
          controller.clickRandPuzzle();
        });
    hbox.getChildren().add(prev);
    hbox.getChildren().add(next);
    hbox.getChildren().add(reset);
    hbox.getChildren().add(rand);
    hbox.setAlignment(Pos.CENTER);
    hbox.setPadding(new Insets(10));
    return hbox;
  }
}
