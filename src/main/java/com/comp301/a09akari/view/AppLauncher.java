package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // TODO: Create your Model, View, and Controller instances and launch your GUI
    PuzzleLibrary lib = new PuzzleLibraryImpl();
    lib.addPuzzle(
        new PuzzleImpl(
            new int[][] {
              {6, 6, 6, 6, 1, 6, 6},
              {6, 6, 6, 5, 6, 6, 6},
              {0, 6, 6, 6, 6, 6, 6},
              {6, 5, 6, 6, 6, 4, 6},
              {6, 6, 6, 6, 6, 6, 5},
              {6, 6, 6, 2, 6, 6, 6},
              {6, 6, 5, 6, 6, 6, 6},
            }));
    lib.addPuzzle(
        new PuzzleImpl(
            new int[][] {
              {5, 6, 6, 5, 6, 6, 6, 6, 6, 5},
              {6, 6, 6, 6, 6, 6, 6, 5, 6, 6},
              {6, 3, 6, 6, 6, 6, 0, 6, 6, 6},
              {6, 6, 2, 6, 6, 5, 6, 6, 6, 1},
              {6, 6, 6, 1, 0, 5, 6, 6, 6, 6},
              {6, 6, 6, 6, 1, 5, 5, 6, 6, 6},
              {5, 6, 6, 6, 2, 6, 6, 2, 6, 6},
              {6, 6, 6, 5, 6, 6, 6, 6, 5, 6},
              {6, 6, 1, 6, 6, 6, 6, 6, 6, 6},
              {0, 6, 6, 6, 6, 6, 1, 6, 6, 0},
            }));
    lib.addPuzzle(
        new PuzzleImpl(
            new int[][] {
              {6, 6, 5, 6, 6, 6, 6},
              {6, 5, 6, 6, 6, 4, 6},
              {6, 6, 6, 6, 6, 6, 5},
              {6, 6, 6, 6, 6, 6, 6},
              {3, 6, 6, 6, 6, 6, 6},
              {6, 2, 6, 6, 6, 5, 6},
              {6, 6, 6, 6, 0, 6, 6},
            }));
    lib.addPuzzle(
        new PuzzleImpl(
            new int[][] {
              {6, 1, 6, 6, 6, 6, 5, 6, 6, 6},
              {6, 6, 6, 6, 6, 6, 6, 6, 6, 5},
              {6, 6, 5, 5, 6, 6, 6, 2, 6, 6},
              {2, 6, 6, 5, 6, 6, 1, 5, 6, 6},
              {6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
              {6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
              {6, 6, 5, 2, 6, 6, 0, 6, 6, 1},
              {6, 6, 2, 6, 6, 6, 5, 1, 6, 6},
              {2, 6, 6, 6, 6, 6, 6, 6, 6, 6},
              {6, 6, 6, 5, 6, 6, 6, 6, 5, 6},
            }));
    lib.addPuzzle(
        new PuzzleImpl(
            new int[][] {
              {6, 6, 5, 6, 6, 6},
              {6, 5, 6, 6, 6, 3},
              {6, 6, 6, 6, 6, 6},
              {6, 6, 6, 6, 6, 6},
              {3, 6, 6, 6, 6, 6},
              {6, 2, 6, 6, 6, 6},
              {6, 6, 6, 6, 0, 6},
            }));
    Model model = new ModelImpl(lib);
    ClassicMvcController controller = new ControllerImpl(model);

    stage.setTitle("Akari");
    VBox layout = new VBox();

    PuzzleView pv = new PuzzleView(model, controller);
    ControlView cv = new ControlView(controller);
    MessageView mv = new MessageView(model);

    Button retry = new Button("retry");
    retry.setOnAction(
        (ActionEvent event) -> {
          Stage st = (Stage) retry.getScene().getWindow();
          st.close();
        });

    Button done = new Button("Done");
    done.setOnAction(
        (ActionEvent event) -> {
          Stage st = new Stage();
          VBox vb = new VBox();
          Button re = mv.ret();
          Button ok = mv.ok();
          vb.getChildren().add(mv.render());
          if (!model.isSolved()) vb.getChildren().add(re);
          if (model.isSolved()) vb.getChildren().add(ok);
          vb.setAlignment(Pos.CENTER);
          Scene s = new Scene(vb, 350, 350);

          st.setScene(s);
          st.show();
        });
    Label index =
        new Label(
            "Puzzle "
                + String.valueOf(model.getActivePuzzleIndex() + 1)
                + " of "
                + String.valueOf(model.getPuzzleLibrarySize()));

    layout.getChildren().add(index);
    layout.getChildren().add(pv.render());
    layout.getChildren().add(cv.render());
    layout.getChildren().add(done);
    layout.setAlignment(Pos.CENTER);
    Scene scene = new Scene(layout);
    stage.setScene(scene);
    stage.sizeToScene();
    model.addObserver(
        (Model m) -> {
          Label index1 =
              new Label(
                  "Puzzle "
                      + String.valueOf(model.getActivePuzzleIndex() + 1)
                      + " of "
                      + String.valueOf(model.getPuzzleLibrarySize()));
          layout.getChildren().clear();
          layout.getChildren().add(index1);
          layout.getChildren().add(pv.render());
          layout.getChildren().add(cv.render());
          layout.getChildren().add(done);
          layout.setAlignment(Pos.CENTER);
          // scene.setRoot(layout);
          stage.sizeToScene();
          if (model.isSolved()) {
            Stage st = new Stage();
            VBox vb = new VBox();
            Button re = mv.ret();
            Button ok = mv.ok();
            vb.getChildren().add(mv.render());
            if (!model.isSolved()) vb.getChildren().add(re);
            if (model.isSolved()) vb.getChildren().add(ok);
            vb.setAlignment(Pos.CENTER);
            Scene s = new Scene(vb, 350, 350);

            st.setScene(s);
            st.show();
          }
        });
    stage.show();
  }
}
