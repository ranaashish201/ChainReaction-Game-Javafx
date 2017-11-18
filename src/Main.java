
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
public class Main extends Application {
	Button Play;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Chain Reaction");
		Play =new Button("Play");
		Play.alignmentProperty();
		Play.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("HelloWorld ");
				
			}
			
		});
		AnchorPane layout=new AnchorPane();
		ComboBox combobox=new ComboBox<>();
		for(int i=1;i<=8;i++) {
			combobox.getItems().add(i);
		}
		combobox.setLayoutX(100);
		combobox.setLayoutY(200);
		Play.setMinWidth(75);
		
		Play.setLayoutX(100);
		Play.setLayoutY(50);
		layout.getChildren().add(Play);
		layout.getChildren().add(combobox);
		Scene scene=new Scene(layout,700,900);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	class playEvent implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent event) {
			// TODO Auto-generated method stub
		
		}
	}
}
