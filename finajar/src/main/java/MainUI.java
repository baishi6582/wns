import java.net.URL;

import com.woniu.findjar.controller.MainUIController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 这是本软件的主入口,要运行本软件请直接运行本类就可以了,不用传入任何参数
 * 本软件要求jkd版本大于1.8.0.40
 */
public class MainUI extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		URL url = Thread.currentThread().getContextClassLoader().getResource("fxml/MainUI.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader(url);
		Parent root = fxmlLoader.load();
		primaryStage.setResizable(true);
		primaryStage.setScene(new Scene(root));
		primaryStage.show();

//		MainUIController controller = fxmlLoader.getController();
//		controller.setPrimaryStage(primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
