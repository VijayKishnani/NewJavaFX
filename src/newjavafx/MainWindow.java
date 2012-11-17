/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package newjavafx;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Vijay Kishnani
 */
import java.io.IOException;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainWindow
{
  public static final int DEFAULT_WIDTH = 600;
  public static final int DEFAULT_HEIGHT = 600;

  private int width;
  private int height;
   private Stage stage;
  private Parser parser;
 

  private ImagePanel imageRegion = new ImagePanel(DEFAULT_WIDTH,DEFAULT_HEIGHT); ;
  private TextArea textarea=new TextArea();
  private HBox pictureReg2 = new HBox();
  private Button completeButton=new Button("Complete");
  private Button saveButton = new Button("Save");
  private Button closeButton=new Button("Close Window");
  private Button nextButton=new Button("Next");
  

  
  public MainWindow(Stage stage, Reader reader){
    this(stage,DEFAULT_WIDTH, DEFAULT_HEIGHT,reader);
    this.stage=stage;
    parser = new Parser(reader,imageRegion,this, stage);
  }

  public MainWindow (Stage primaryStage, int width, int height,Reader reader) {
    primaryStage.setTitle("DrawApp"); 
    Group root = new Group(); 
    Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT, Color.WHITE);
    GridPane gridpane = buildGUI();
    root.getChildren().add(gridpane);         
    primaryStage.setScene(scene); 
  }

  private GridPane buildGUI(){
    GridPane gridpane = new GridPane(); 
    gridpane.setHgap(10); 
    gridpane.setVgap(0);  
    textarea.setWrapText(true); 
    textarea.setPrefWidth(600); 
    textarea.setPrefHeight(150);
    GridPane.setHalignment(textarea, HPos.CENTER); 
    gridpane.add(textarea, 0, 1); 
    String cssDefault = "-fx-border-color: black;\n" 
                            + "-fx-border-insets: 5;\n" 
                            + "-fx-border-width: 0;\n";
    textarea.setText("Drawing Completed!!"); 
    imageRegion.setStyle(cssDefault); 
    imageRegion.setPrefHeight(400);
    gridpane.add(imageRegion, 0, 0);
        
    final HBox pictureRegion2 = new HBox(); 
    pictureRegion2.setPrefHeight(50);
    pictureRegion2.setAlignment(Pos.CENTER);
    pictureRegion2.setStyle("-fx-background-color: #E8E8E8");
    
    closeButton.setOnAction(new EventHandler<ActionEvent>() { 
        @Override
        public void handle(ActionEvent event) {
            Platform.exit();
        }
    });  

    completeButton.setOnAction(new EventHandler<javafx.event.ActionEvent>()
      {
        @Override
        public void handle(javafx.event.ActionEvent event)
        {
          parser.parse();
        }
      });
    
    nextButton.setOnAction(new EventHandler<javafx.event.ActionEvent>()
      {
        @Override
        public void handle(javafx.event.ActionEvent event)
        {
              try {
                  parser.parseStepbyStep(nextButton);
              } catch (IOException ex) {
                  Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
              }
        }
      });
 
        saveButton.setOnAction(new EventHandler<javafx.event.ActionEvent>()
      {
        @Override
        public void handle(javafx.event.ActionEvent event)
        {
              try {
                  parser.saveImage("Image");
              } catch (ParseException ex) {
                  Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
              }
        }
      });
    
 
    pictureRegion2.getChildren().add(completeButton);
    pictureRegion2.getChildren().add(nextButton);
    pictureRegion2.getChildren().add(closeButton);
     pictureRegion2.getChildren().add(saveButton);

    gridpane.add(pictureRegion2, 0, 2);
    
    return gridpane;
  }

  private EventHandler<ActionEvent> quitBtn() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                System.exit(0);
            }
        };

    }
  
  public Button getNext(){
      return nextButton;
  }
  
  public Button getComplete(){
      return completeButton;
  }
  
  public ImagePanel getImagePanel(){
    return imageRegion;
  }

  public void postMessage(final String s){
     textarea.setText(s);
  }
  
    public void setSizeTextArea(int width,int height){
      textarea.setPrefHeight(height);
      textarea.setPrefWidth(width);
  }
  
  public void setSizeImageRegion(int width,int height){
      imageRegion.setPrefHeight(height);
      imageRegion.setPrefWidth(width);
  }
  
  public void setSizePictureRegion(int width,int height){
      pictureReg2.setPrefHeight(height);
      pictureReg2.setPrefWidth(width);
  }
}