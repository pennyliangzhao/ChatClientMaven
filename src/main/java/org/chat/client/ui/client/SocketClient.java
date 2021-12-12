package org.chat.client.ui.client;

import cipher.CaesarCipher;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.chat.client.ui.MainUI;

import java.io.*;
import java.net.Socket;

public class SocketClient {
    private static TextArea textConsole;
    private TextArea textArea;
    private String serverAddress = "127.0.0.1";
    private static final int PORT = 8585;
    private TextField textField;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    public static String serverResponse = null;

    public SocketClient(String ip, int port) throws IOException {
        socket = new Socket(ip, port);
        writer = new PrintWriter(socket.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        clientService.start();
    }

    public SocketClient(TextArea textArea, TextField textField) {
       this.textArea = new TextArea();
       this.textField = new TextField();
    }

    Service clientService = new Service() {
        @Override
        protected Task<Void> createTask() {
            return new Task<>() {
                @Override
                protected Void call() {
                    listen();
                    return null;
                }
            };
        }
    };

    public void listen() {
        while(socket.isConnected()) {
            try {
                String string = reader.readLine();
                if(string != null) {
                    CaesarCipher cc = new CaesarCipher();

                    String decrypted = cc.decrypt(string,2);
                    MainUI.loginRes = decrypted;
                    System.out.println("Server: "+decrypted);
                    if(textConsole != null) {
                        textConsole.appendText(decrypted+"\n");
                    }
                }
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void message(String message) {
        System.out.println(socket);
        System.out.println("SENDING: "+message);
        CaesarCipher cc = new CaesarCipher();
        String result = cc.encrypt(message,2);;
        System.out.println("Encrypted:"+result);
        //Message sending thread
        new Thread(()-> writer.println(result)).start();
    }

    public void setTextArea(TextArea textArea) {
        textConsole = textArea;
    }

    public TextArea getTextArea() {
        return textConsole;
    }

    public void connect() throws IOException{
        try (Socket clientSocket = new Socket(serverAddress, PORT)) {
            //Send request to the server
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            writer.println(this.textField.getText());
            System.out.println(this.textField.getText());

            //Receive the response from the server
            InputStreamReader inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
            BufferedReader input = new BufferedReader(inputStreamReader);

           while(clientSocket.isConnected()){
               String msg=input.readLine();
               if(msg!=null){
                   textArea.appendText(msg);
               }
           }
        }
    }
}
