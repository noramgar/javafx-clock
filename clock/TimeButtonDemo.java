import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;

import java.util.Calendar;
import javafx.scene.control.Label;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.scene.input.KeyCode;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Date;
import javafx.scene.text.Text;
import javafx.scene.layout.*;

public class TimeButtonDemo extends Application {

  protected BorderPane getPane() {
    
    BorderPane pane = new BorderPane(); // pane for containing buttons and clock  
  
    HBox paneForButtons = new HBox(50); // pane for containing buttons
    paneForButtons.setAlignment(Pos.CENTER);
    paneForButtons.setStyle("-fx-border-color: green");
     
    ImageView usa = new ImageView(new Image("usa.jpg"));
    ImageView eu = new ImageView(new Image("eu.jpg"));

    usa.setFitWidth(70);
    usa.setPreserveRatio(true);
    eu.setFitWidth(70);
    eu.setPreserveRatio(true);

    // write code for buttons 
    Button btn12 = new Button("12 hr", usa);
    Button btn24 = new Button("24 hr", eu);

    paneForButtons.getChildren().addAll(btn12, btn24);
    pane.setBottom(paneForButtons);
       
    Text clock = DigitalClock.getClockText();  // clock to be added to pane
    pane.setCenter(clock);

    // handle button clicks with lambdas
    btn12.setOnAction(e -> {
        DigitalClock.changeFormat12();
    });

    btn24.setOnAction(e -> {
        DigitalClock.changeFormat24();
    });

    // handle keyboard presses with lambdas
    pane.setOnKeyPressed(e -> {          
        switch (e.getCode()) {
            case UP: clock.setFill(Color.RED); break;
            case DOWN: clock.setFill(Color.CYAN); break;
            case ENTER: clock.setFill(Color.BLACK); break;
            default: break;
        }
    });
    
    return pane;
  }
  
  public void start(Stage primaryStage) {
    // Create a scene and place it in the stage
    BorderPane pane = getPane();
    Scene scene = new Scene(pane,350, 250);
    primaryStage.setTitle("ClockApplication"); // Set the stage title
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
  }

  public static void main(String[] args) {
    launch(args);
  }
}

class DigitalClock extends Label {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static Timeline animation;
    private static Calendar time;

    public static Text getClockText() {
        // get time and set text with lambda
        time = Calendar.getInstance();
        Text text = new Text(dateFormat.format(time.getTime()));   
        
        // change text font here
        text.setFont(Font.font("Arial", 30));
        
        // set animation here
        EventHandler<ActionEvent> eventHandler = e -> {
            Date date = new Date();
            text.setText(dateFormat.format(date));
        };
        animation = new Timeline(new KeyFrame(Duration.seconds(1), eventHandler));
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.play();

        return text;
    }
    
    public static void changeFormat24(){
        // write code here for changing to 24 hour clock
        dateFormat = new SimpleDateFormat("HH:mm:ss");
    }
    
    public static void changeFormat12(){
        // write code here for changing to 12 hour clock
        dateFormat = new SimpleDateFormat("hh:mm:ss a");
    }           
}
