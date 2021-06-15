package controllers;

import examen.model.Player;
import examen.services.IExamenServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    private IExamenServices server;
    Stage userPageStage = new Stage();
    Stage adminPageStage = new Stage();
    Parent userParent;

    Player loggedPlayer;

    //private MainController mainController;
    public void setServer(IExamenServices s){
        server=s;
    }

    @FXML
    TextField usernameTextField;
    @FXML
    PasswordField passwordTextField;


    public void loginMethod(ActionEvent actionEvent) {
    }
}
