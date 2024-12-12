package apps.employee;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@SpringBootConfiguration
public class Main extends Application {
    private ConfigurableApplicationContext context;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Initialize Spring Context
        context = SpringApplication.run(Main.class);
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EmployeeUI.fxml"));
        loader.setControllerFactory(context::getBean); // Injects Spring-managed beans
        Parent root = loader.load();

        // Set the scene and stage
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Employees Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
