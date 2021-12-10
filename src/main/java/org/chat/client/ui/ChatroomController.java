package org.chat.client.ui;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatroomController extends Application implements Initializable {
    @FXML
    public TextField messageInput;
    @FXML
    public TextArea textConsole;
    @FXML
    public TextField receiverName;


    public ChatroomController() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MainUI.socClient.setTextArea(textConsole);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(ChatroomController.class.getResource("chat_ui.fxml"));
        stage.setTitle("Chat");
        stage.setScene(new Scene(parent, 600, 400));
        stage.show();
    }

    @FXML
    public void setSend(Event event) {

        String recipient = receiverName.getText();
        String message = messageInput.getText();
        String cmd = "MSG:"+MainUI.currentUsername+":"+recipient+":"+message;
        MainUI.socClient.message(cmd);
    }
}
