
package org.chat.client.ui;

import cipher.CaesarCipher;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.chat.client.ui.client.SocketClient;

import static org.chat.client.ui.client.SocketClient.serverResponse;

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
    public static volatile String loginRes = null;

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
        currentUsername = uname;
        socClient = new SocketClient(ipAdr, portN);
        String msg = "REG:"+uname+":"+CaesarCipher.encrypt(pwd, 2);
        System.out.println(CaesarCipher.encrypt(pwd, 2));
        socClient.message(msg);
        new Thread(() -> {
            while (loginRes == null) {
                Thread.onSpinWait();
            }
            Platform.runLater(() -> startApp());
        }).start();
    }

    private void startApp() {
        if (loginRes.equals("LOGIN:SUCCESS")) {
            ChatroomController chatroomController = new ChatroomController();
            try {
                chatroomController.start(MainUI.stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            loginRes = null;
            System.out.println("Sorry! Username or password incorrect.");
        }
    }
}