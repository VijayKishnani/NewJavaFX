package newjavafx;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
public class Draw extends Application {
public static void main(String[] args) { 

}
@Override
public void start(Stage primaryStage) throws IOException {
MainWindow main = new MainWindow(primaryStage);
ImagePanel imagePanel = main.getImagePanel();
Reader reader = new InputStreamReader(System.in);
Parser parser = new Parser(reader,imagePanel,main);
parser.parseStepbyStep(main.getNextButton());
//parser.parse();
primaryStage.show();
}

}