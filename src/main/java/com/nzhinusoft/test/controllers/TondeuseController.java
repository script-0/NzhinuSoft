package com.nzhinusoft.test.controllers;

import com.nzhinusoft.test.App;
import com.nzhinusoft.test.models.Direction;
import com.nzhinusoft.test.models.Operations;
import com.nzhinusoft.test.models.Tondeuse;
import javafx.scene.Cursor;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 *
 * @author Isaac
 */
public class TondeuseController {

    Tondeuse tondeuse;
    MainController main;
    ImageView img;
    HBox box;

    public TondeuseController(int x, int y, Direction direction, MainController main) {
        tondeuse = new Tondeuse(x, y, direction);
        this.main = main;
        box = (HBox) (HBox) App.getNodeFromGridPane(main.pelouse, x, y);
        img = new ImageView(App.class.getResource("images/chevron.png").toExternalForm());
        img.setFitHeight(30);
        img.setFitWidth(30);
        img.getStyleClass().add("direction");
        img.setOnMouseClicked((e) -> {
            main.controller = this;
            main.output.setText(this.getPosition());
        });
        if (direction == Direction.W) {
            img.setRotate(-90);
        } else if (direction == Direction.S) {
            img.setRotate(180);
        } else if (direction == Direction.E) {
            img.setRotate(90);
        }
        box.getChildren().add(img);
        box.getStyleClass().remove("case");
        box.getStyleClass().add("caseColored");
        main.output.setText(this.getPosition());
    }

    public String getPosition() {
        return "[x=" + tondeuse.getX() + ", y=" + tondeuse.getY() + " ] ";
    }

    public void doD() {
        img.setRotate(img.getRotate() + 90);
        tondeuse.updatePosition(Operations.D);
        main.output.setText(getPosition());
    }

    public void doG() {
        img.setRotate(img.getRotate() - 90);
        tondeuse.updatePosition(Operations.G);
        main.output.setText(getPosition());
    }

    public void doA() {
        System.out.println("Doing A");
        tondeuse.updatePosition(Operations.A);
        int xtmp = tondeuse.getX(), ytmp = tondeuse.getY();
        HBox boxTmp = (HBox) App.getNodeFromGridPane(main.pelouse, tondeuse.getX(), tondeuse.getY());
        if (boxTmp.getChildren().isEmpty()) {
            box.getChildren().remove(img);
            box = boxTmp;
            box.getChildren().add(img);
            box.getStyleClass().remove("case");
            box.getStyleClass().add("caseColored");
            main.output.setText(getPosition());
        } else {
            tondeuse.setX(xtmp);
            tondeuse.setY(ytmp);
        }
    }
}
