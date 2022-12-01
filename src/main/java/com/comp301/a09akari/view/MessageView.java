package com.comp301.a09akari.view;

import com.comp301.a09akari.model.Model;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MessageView implements FXComponent {
  private final Model model;

  public MessageView(Model model) {
    this.model = model;
  }

  public static StackPane correct() {
    Label label = new Label("Congratulations! You solved the puzzle!");
    return new StackPane(label);
  }

  public static StackPane wrong() {
    Label label = new Label("Oops! There are mistakes in the puzzle solution.");
    return new StackPane(label);
  }

  @Override
  public Parent render() {
    VBox vb = new VBox();

    if (model.isSolved()) {
      vb.getChildren().add(correct());
    } else {
      vb.getChildren().add(wrong());
    }
    return vb;
  }

  public Button ret() {
    Button retry = new Button("retry");
    retry.setOnAction(
        (ActionEvent event) -> {
          Stage st = (Stage) retry.getScene().getWindow();
          st.close();
        });
    return retry;
  }

  public Button ok() {
    Button ok = new Button("Nice!");
    ok.setOnAction(
        (ActionEvent event) -> {
          Stage st = (Stage) ok.getScene().getWindow();
          st.close();
        });
    return ok;
  }
}
