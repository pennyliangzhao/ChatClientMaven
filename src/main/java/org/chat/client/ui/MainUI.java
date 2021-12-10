package org.chat.client.ui;

import cipher.CaesarCipher;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.chat.client.ui.client.SocketClient;

public class MainUI extends Application {
    private static Stage stage;
    @FXML
    public TextField username;
    @FXML
    public TextField password;
    @FXML
    public TextField ip;
    @FXML
    public TextField port;

    public static SocketClient socClient;
    public static String currentUsername;
    private CaesarCipher cc;

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(MainUI.class.getResource("signin_ui.fxml"));
        this.stage = stage;
        stage.setTitle("ClientApp");
        stage.setScene(new Scene(parent, 600, 500));
        stage.show();
    }

    @FXML
    public void startServer(MouseEvent mouseEvent) throws Exception {
        String uname = username.getText();
        String pwd = password.getText();
        String ipAdr = ip.getText();
        int portN = Integer.parseInt(port.getText());

        cc = new CaesarCipher();



        socClient = new SocketClient(ipAdr, portN);
        socClient.start();
        //This needs to be encrypted
        String msg = "REG:"+uname+":"+pwd;

        socClient.message(msg);

        //Handles the login
        //If login is success open the chat window
        currentUsername = uname;
        ChatroomController chatroomController = new ChatroomController();
        chatroomController.start(this.stage);
    }
}
