package com.joey.lingo;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LingoApp extends Application {

    private Stage primaryStage;
    private static BorderPane rootLayout;   
    
    public static void main(String[] args) {
        launch(args);
    }    

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Lingo Game");
        primaryStage.setResizable(false);

        initRootLayout();
        showLingoMainMenuView();
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LingoApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the Lingo View inside the root layout.
     */
    public void showLingoMainMenuView() {
        try {
            // Load lingo overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LingoApp.class.getResource("view/LingoMainMenuView.fxml"));
            AnchorPane LingoView = (AnchorPane) loader.load();
            
            // Set lingo overview into the center of root layout.
            rootLayout.setCenter(LingoView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void showLingoView() {
        try {
            // Load lingo overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LingoApp.class.getResource("view/LingoView.fxml"));
            AnchorPane LingoView = (AnchorPane) loader.load();
            
            // Set lingo overview into the center of root layout.
            rootLayout.setCenter(LingoView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
    
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
