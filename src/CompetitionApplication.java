/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import com.sun.media.sound.InvalidFormatException;
import java.io.IOException;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author al7mm
 */
public class CompetitionApplication extends Application {
    MySystem sys ;
    private static CompetitionApplication instance;

    public CompetitionApplication() {
        instance = this;
    }
// static method to get instance of view

    public static CompetitionApplication getInstance() {
        return instance;
    }
    

    public void addCompetition(String compName, String URL, LocalDate date, String choice) throws IOException { //InvalidFormatException

    }
    
    public void addStudent(String name, String id,String major, String rank) throws  IOException, NullPointerException { //InvalidFormatException
        
        
    }
    void setSys( MySystem ss ){
        sys = ss ;
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Competition");
        BorderPane pane = new BorderPane();

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
