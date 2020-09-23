package com.nzhinusoft.test;

import com.nzhinusoft.test.controllers.InfosController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * Main App
 */
public class App extends Application {

    private static Stage stage;
    
    static int xPelouse;
    static int yPelouse;    

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/infos.fxml"));
        Pane root = (Pane) fxmlLoader.load();
        InfosController controller = fxmlLoader.getController();        
        controller.setLabel("Entrer les coordonnees du coin superieur droit");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Creation de la pelouse");
        stage.show();
        App.stage = stage;
    }
    
    public static Stage getStage(){
        return App.stage;
    }

    public static int getxPelouse() {
        return xPelouse;
    }

    public static void setxPelouse(int xPelouse) {
        App.xPelouse = xPelouse;
    }

    public static int getyPelouse() {
        return yPelouse;
    }

    public static void setyPelouse(int yPelouse) {
        App.yPelouse = yPelouse;
    }
    
    public static void main(String[] args) {
        launch();
    }
    
    public static Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        row = App.getyPelouse()-row;
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
}