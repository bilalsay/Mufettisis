package dev.demo;

import dev.demo.Controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main extends Application {

    public ConfigurableApplicationContext springContext;
    public BorderPane mainLayout;
    private Stage primaryStage;

    @Override
    public void start(Stage pStage) throws Exception {
        primaryStage = pStage;
        primaryStage.setTitle("Mufettisis");
        Scene scene = new Scene(mainLayout);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(true);
        primaryStage.setMaximized(true);
        primaryStage.centerOnScreen();
    }

    @Override
    public void init() throws Exception {
        SpringApplication springApplication = new SpringApplication(Main.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springContext = springApplication.run();
        FXMLLoader mainLoader = new FXMLLoader();
        mainLoader.setLocation(Main.class.getResource("/main.fxml"));
        mainLayout = mainLoader.load();
        mainLayout.autosize();
        MainController mainController = mainLoader.getController();
        mainController.setMain(this);
        mainController.isEmriMainPage();
    }

    @Override
    public void stop() throws Exception {
        springContext.stop();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(Main.class, args);
    }
}
