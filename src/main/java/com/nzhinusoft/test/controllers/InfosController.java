/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nzhinusoft.test.controllers;

import com.nzhinusoft.test.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Isaac
 */
public class InfosController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private TextField y;

    @FXML
    private TextField x;

    @FXML
    private Button next;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    public void setLabel(String text){
        label.setText(text);
    }
    
    @FXML
    public void next() throws IOException{
        App.setxPelouse( Integer.valueOf(x.getText()) );
        App.setyPelouse( Integer.valueOf(y.getText()) );
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("views/main.fxml"));
        Pane root = (Pane) fxmlLoader.load();
        App.getStage().setScene(new Scene(root));
    }

}
