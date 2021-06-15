package examen.client;

import controllers.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import examen.services.IExamenServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartRpcClientFX extends Application {

    private Stage primaryStage;

    private static int defaultChatPort = 55555;
    private static String defaultServer = "localhost";


    public void start(Stage primaryStage) throws Exception {

        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
        IExamenServices server=(IExamenServices) factory.getBean("Service");

        FXMLLoader loader = new FXMLLoader(
                getClass().getClassLoader().getResource("login.fxml"));
        Parent root=loader.load();


        LoginController ctrl =
                loader.<LoginController>getController();
        ctrl.setServer(server);


//        FXMLLoader cloader = new FXMLLoader(
//                getClass().getClassLoader().getResource("mainWindow.fxml"));
//        Parent croot=cloader.load();
//
//
//        MainWindow mainCtrl =
//                cloader.<MainWindow>getController();
//
//        mainCtrl.setServer(server);
//
//
//
//        ctrl.setMainController(mainCtrl);
//        ctrl.setParent(croot);
        primaryStage.setTitle("texamen");
        primaryStage.setScene(new Scene(root, 232, 341));

        primaryStage.show();




    }
    public static void main(String[] args) {
        launch(args);
    }


}
