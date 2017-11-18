import java.util.*;

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.canvas.*;

/**
 * This is the front page of Chainreaction game which will handle user requests
 *
 */
public class FrontPage extends Application {
    
    
   // int num; 
    /**
     *  Main window to show Stuff 
     */ 
    Stage window;
    /**
     *   Main scene 
     */
    Scene scene;
    /**
     * This is main method to launch the GUI
     * @param args
     */
    public static void main(String[] args) {
        launch(args);

    }

    /*
     *start method will create buttons  for play,settings,no. of players choicebox and will handle events on pressing those buttons.
     *On clicking Play button Grid.start() will be called. 
     *On clicking Play with HD Grid Grid.start() will be called with more rows and columns
     *On clicking Settings Settings page will open by calling Settings.fillColor
     * (non-Javadoc)
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Game");
        Button btn = new Button("Play");
        Button btn2 = new Button("Play with HD grids");
        Button btn3 = new Button("Settings");
        Label l = new Label("No of Players");
        ChoiceBox<Integer> choice = new ChoiceBox<>();
        choice.getItems().add(2);
        choice.getItems().add(3);
        choice.getItems().add(4);
        choice.getItems().add(5);
        choice.getItems().add(6);
        choice.getItems().add(7);
        choice.getItems().add(8);
        choice.setValue(2);
        Pane layout = new Pane();
        choice.setLayoutX(200);
        choice.setLayoutY(25);
        l.setLayoutX(185);
        l.setLayoutY(4);
        btn.setLayoutX(160);
        btn.setLayoutY(60);
        btn2.setLayoutX(220);
        btn2.setLayoutY(60);
        btn3.setLayoutX(420);
        btn3.setLayoutY(450);    
        btn2.setOnMouseClicked(e->{
            Grid ob = new Grid(choice.getValue(),true);
            try {
                ob.start(primaryStage);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        Button bt4=new Button("Resume");
        bt4.setLayoutX(200);
        bt4.setLayoutY(100);
        
        bt4.setOnMouseClicked(e->{
            ArrayList<Cordinates> resumeList=SaveState.loadGame();
            Grid ob = new Grid(SaveState.loadPlayer(),false,resumeList);
            try {
                if(resumeList!=null) {
                    ob.resumeAvailable=true;
                }
                    ob.start(primaryStage);
            } catch (Exception e1) {}
        });
        btn.setOnMouseClicked(e->{
            Grid ob = new Grid(choice.getValue(),false);
            try {
                ob.start(primaryStage);
            } catch (Exception e1) {
            }
        });
        btn3.setOnMouseClicked(e->{
            Settings.fillcolor();
        });



        layout.getChildren().addAll(btn,choice,btn2,btn3,bt4,l);
        scene = new Scene(layout,500,500);
        window.setScene(scene);
        window.show();
    }

}

