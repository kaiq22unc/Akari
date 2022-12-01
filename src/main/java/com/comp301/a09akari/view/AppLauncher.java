package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.ClassicMvcController;
import com.comp301.a09akari.controller.ControllerImpl;
import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {
    // TODO: Create your Model, View, and Controller instances and launch your GUI
    PuzzleLibrary lib = new PuzzleLibraryImpl();
    lib.addPuzzle(new PuzzleImpl(new int[][]{
            {6, 6, 6, 6, 1, 6, 6},
            {6, 6, 6, 5, 6, 6, 6},
            {0, 6, 6, 6, 6, 6, 6},
            {6, 5, 6, 6, 6, 4, 6},
            {6, 6, 6, 6, 6, 6, 5},
            {6, 6, 6, 2, 6, 6, 6},
            {6, 6, 5, 6, 6, 6, 6},
    }));
    lib.addPuzzle(new PuzzleImpl(new int[][]{
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
    lib.addPuzzle(new PuzzleImpl(new int[][]{
            {6, 6, 5, 6, 6, 6, 6},
            {6, 5, 6, 6, 6, 4, 6},
            {6, 6, 6, 6, 6, 6, 5},
            {6, 6, 6, 6, 6, 6, 6},
            {3, 6, 6, 6, 6, 6, 6},
            {6, 2, 6, 6, 6, 5, 6},
            {6, 6, 6, 6, 0, 6, 6},
    }));
    lib.addPuzzle(new PuzzleImpl(new int[][]{
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
    lib.addPuzzle(new PuzzleImpl(new int[][]{
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
    Pane layout = new VBox();

    PuzzleView pv = new PuzzleView(model,controller);
    ControlView cv = new ControlView(controller);
    layout.getChildren().add(pv.render());
    layout.getChildren().add(cv.render());
    Scene scene = new Scene(layout, 600, 350);
    stage.setScene(scene);
    model.addObserver((Model m)->{
      layout.getChildren().clear();
      layout.getChildren().add(pv.render());
      layout.getChildren().add(cv.render());
      scene.setRoot(layout);

    });
    stage.show();
  }
}
