package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectTask{

    Socket socket = null;
    public void listenMessage(){
        new Thread() {
            @Override
            public void run() {
                try {
                    // Create Socket instance
                    Socket socket = new Socket("192.168.1.100", 6000);

                } catch (UnknownHostException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public void sendMessage(final Object[] message){
        new Thread() {
            @Override
            public void run() {
                if(socket == null){
                    try {
                        socket = new Socket("192.168.1.100", 6000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                ObjectOutputStream ob = null;
                try {
                    ob = new ObjectOutputStream(socket.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    ob.writeObject(message);
                    ob.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}