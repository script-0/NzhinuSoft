package com.nzhinusoft.test.controllers;

import com.nzhinusoft.test.App;
import com.nzhinusoft.test.models.Direction;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/*
 * Gerer l'animation de la pelouse , l'ajout des tondeuses
 */
public class MainController implements Initializable {

    @FXML
    public GridPane pelouse;

    @FXML
    private Button add;

    @FXML
    private Button doG;

    @FXML
    private Button doD;

    @FXML
    private Button doA;

    @FXML
    private Pane addPane;

    @FXML
    private TextField x0;

    @FXML
    private TextField y0;

    @FXML
    private ComboBox<Direction> direction;

    @FXML
    private Button ok;

    @FXML
    public Label output;

    @FXML
    private TextField operations;

    TondeuseController controller;

    ArrayList<TondeuseController> tondeuses = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pelouse.setMaxHeight(Double.POSITIVE_INFINITY);
        pelouse.setMaxWidth(Double.POSITIVE_INFINITY);
        HBox tmp;
        pelouse.getChildren().clear();
        
        double caseWidth = 390/App.getxPelouse();
        double caseHeight = 271/App.getyPelouse();
        
        for (int i = 0; i <= App.getyPelouse(); i++) {
            for (int j = 0; j <= App.getxPelouse(); j++) {
                tmp = new HBox();
                tmp.setMaxSize(caseWidth, caseHeight);
                tmp.setMinSize(caseWidth,caseHeight );
                tmp.setAlignment(Pos.CENTER);
                tmp.getStyleClass().add("case");
                pelouse.add(tmp, j, i);
            }
        }
       pelouse.getChildren().forEach((Node e) -> {

            GridPane.setHgrow(e, Priority.ALWAYS);
            GridPane.setVgrow(e, Priority.ALWAYS);
        });

        System.out.println("Initialising pelouse [x = " + App.getxPelouse() + ", y =" + App.getyPelouse() + "]");
        direction.getItems().addAll(Direction.N, Direction.W, Direction.S, Direction.E);
        addPane.setVisible(false);
    }

    @FXML
    public void addTondeuse() {
        addPane.setVisible(true);
    }

    @FXML
    public void initTondeuse() {
        int x = 0, y = 0;
        try {
            x = Integer.valueOf(x0.getText());
            y = Integer.valueOf(y0.getText());
        } catch (Exception e) {
            output.setText("Coordonées non valides");
            return;
        }
        if (x > App.getxPelouse() || y > App.getyPelouse()) {
            output.setText("Coordonées non valides");
            return;
        }
        if (direction.getValue() == null) {
            output.setText("Specifiez la direction");
            return;
        }
        HBox box = (HBox) App.getNodeFromGridPane(pelouse, x, y);
        this.controller = new TondeuseController(x, y, direction.getValue(), this);
        if (box.getChildren().isEmpty()) {
            tondeuses.add(this.controller);
        }
        addPane.setVisible(false);

        Timer timer;
        
        int i = 0;
        for (String op : operations.getText().split("")) {
            if (op.equals("A")) {
                timer = new Timer("Doing Operations");
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(()->{doA();});//Without Platform.runLater() , Not on FX application error
                        this.cancel();
                    }
                }, 1000 * i++);
            } else if (op.equals("G")) {
                timer = new Timer("Doing Operations");
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(()->{doG();});
                        this.cancel();
                    }
                }, 1000 * i++);
            } else if (op.equals("D")) {
                timer = new Timer("Doing Operations");
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        Platform.runLater(()->{doD();});
                        this.cancel();
                    }
                }, 1000 * i++);
            }
        }
        
        x0.setText("");
        y0.setText("");
        operations.setText("");
    }

    @FXML
    public void doA() {
       if(controller!=null) controller.doA();
    }

    @FXML
    public void doD() {
       if(controller!=null) controller.doD();
    }

    @FXML
    public void doG() {
       if(controller!=null) controller.doG();
    }
}
