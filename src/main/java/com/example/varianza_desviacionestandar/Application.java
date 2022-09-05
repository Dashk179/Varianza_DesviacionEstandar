package com.example.varianza_desviacionestandar;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Gui.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 500);
        //stage.setResizable(false);
        stage.setOpacity(0.9);

        stage.setTitle("Proyecto Final Probabilidad Y Estadistica");
        stage.setScene(scene);
        stage.show();
        Image icono = new Image(getClass().getResourceAsStream("Logo.png"));
        stage.getIcons().add(icono);

    }

    public static void main(String[] args) {
        launch();
    }
}