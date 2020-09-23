package com.nzhinusoft.test.controllers;

import com.nzhinusoft.test.App;
import com.nzhinusoft.test.models.Direction;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

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

    TondeuseController controller;

    ArrayList<TondeuseController> tondeuses = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pelouse.setMaxHeight(Double.POSITIVE_INFINITY);
        pelouse.setMaxWidth(Double.POSITIVE_INFINITY);
        HBox tmp;
        pelouse.getChildren().clear();
        for (int i = 0; i <= App.getyPelouse(); i++) {
            for (int j = 0; j <= App.getxPelouse(); j++) {
                tmp = new HBox();
                tmp.setAlignment(Pos.CENTER);
                tmp.getStyleClass().add("case");
                pelouse.add(tmp, j, i);
            }
        }
        pelouse.getChildren().forEach((Node e)->{
            
                GridPane.setHgrow(e, Priority.ALWAYS);
                GridPane.setVgrow(e, Priority.ALWAYS);
        });
        
        System.out.println("Initialising pelouse [x = "+App.getxPelouse()+", y ="+App.getyPelouse()+"]");
        direction.getItems().addAll(Direction.N, Direction.W, Direction.S, Direction.E);
        addPane.setVisible(false);
    }

    @FXML
    public void addTondeuse() {
        addPane.setVisible(true);
    }

    @FXML
    public void initTondeuse() {
        int x = Integer.valueOf(x0.getText());
        int y = Integer.valueOf(y0.getText());
        if (x <= App.getxPelouse() && y <= App.getyPelouse()) {
            HBox box = (HBox)App.getNodeFromGridPane(pelouse, x,y);
            this.controller = new TondeuseController(x, y, direction.getValue(), this);
            if (box.getChildren().isEmpty()) {
                tondeuses.add(this.controller);
            }
        }
        addPane.setVisible(false);
    }

    @FXML
    public void doA() {
        controller.doA();
    }
    
    @FXML
    public void doD() {
        controller.doD();
    }
    
    @FXML
    public void doG() {
        controller.doG();
    }
}
