package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.model.CellType;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.Puzzle;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class PuzzleView implements FXComponent {
  private final Model model;
  private final ClassicMvcController controller;

  public PuzzleView(Model model, ClassicMvcController controller) {
    this.model = model;
    this.controller = controller;
  }

  public static Label black() {
    Label title;
    title = new Label();
    title.setBackground(
        new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    title.setPadding(new Insets(4));
    return title;
  }

  public static Label clue(int num) {
    Label title;
    title = new Label(String.valueOf(num));
    title.setBackground(
        new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    title.setTextFill(Color.WHITE);
    title.setPadding(new Insets(4));
    return title;
  }

  public static StackPane sp(Label l) {
    StackPane s = new StackPane(l);
    s.setBackground(
        new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    s.setMinSize(30, 30);
    return s;
  }

  public static StackPane lit() {
    StackPane s = new StackPane();
    s.setBackground(
        new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
    s.setMinSize(30, 30);
    return s;
  }



  public static StackPane Bulb() {
    Label l = new Label("B");
    StackPane s = new StackPane(l);
    s.setBackground(
        new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
    s.setMinSize(30, 30);
    return s;
  }

  public static StackPane Ill() {
    Label l = new Label("X");
    l.setTextFill(Color.RED);
    StackPane s = new StackPane(l);
    s.setBackground(
            new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
    s.setMinSize(30, 30);
    return s;
  }

  @Override
  public Parent render() {
    GridPane grid = new GridPane();
    int r = model.getActivePuzzle().getHeight();
    int c = model.getActivePuzzle().getWidth();
    Puzzle active = model.getActivePuzzle();
    RowConstraints rc = new RowConstraints();
    rc.setMinHeight(GridPane.USE_PREF_SIZE);
    grid.setPadding(new Insets(10, 10, 10, 10));
    grid.setAlignment(Pos.CENTER);

    for (int i = 0; i < r; i++) {
      grid.getRowConstraints().add(rc);
    }

    ColumnConstraints cc = new ColumnConstraints();
    cc.setMinWidth(GridPane.USE_PREF_SIZE);

    for (int i = 0; i < c; i++) {
      grid.getColumnConstraints().add(cc);
    }
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        if (active.getCellType(i, j) == CellType.CLUE)
          grid.add(sp(clue(active.getClue(i, j))), j, i);
        else if (active.getCellType(i, j) == CellType.WALL) grid.add(sp(black()), j, i);
        else {
          /*Button bt = new Button();
          int finalI = i;
          int finalJ = j;
          bt.setOnAction((ActionEvent event)->{
            controller.clickCell(finalI, finalJ);
          });
          bt.setVisible(true);
          grid.add(bt, i, j);
          */

          /*Image bulb = new Image("/Users/kai/comp301/a09-akari-kaiq22unc/src/main/resources/light-bulb.png");
          ImageView pic = new ImageView(bulb);
          pic.setImage(bulb);
          if(model.isLamp(i,j))
            grid.add(pic,i,j);*/
          if (model.isLit(i, j)) grid.add(lit(), j, i);
          if (model.isLamp(i, j)) grid.add(Bulb(), j, i);
          if(model.isLamp(i,j) && model.isLampIllegal(i,j)) grid.add(Ill(),j,i);
          Button bt = new Button();
          int finalI = i;
          int finalJ = j;
          bt.setOnAction(
              (ActionEvent event) -> {
                controller.clickCell(finalI, finalJ);
              });
          bt.setVisible(true);
          bt.setMinSize(30,30);
          bt.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,CornerRadii.EMPTY,Insets.EMPTY)));
          grid.add(bt, j, i);

        }
      }
    }
    grid.setGridLinesVisible(true);
    return grid;
  }
}
