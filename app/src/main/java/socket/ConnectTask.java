package socket;

import android.app.Activity;
import android.os.Handler;

import com.example.piyapong.drawing.MainActivity;
import com.example.piyapong.drawing.Variable;

import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import exam.Exam;

public class ConnectTask{

    Socket socket = null;
    Object[] object;
    public void getMessage(){
        new Thread() {
            @Override
            public void run() {
                try {
                    // Create Socket instance
                    if(socket == null){
                        try {
                            socket = new Socket("192.168.1.100", 6000);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    InputStream input = socket.getInputStream();

                    try {

                        while(true)
                        {
                            ObjectInputStream in = new ObjectInputStream(input);
                            try {
                                object = (Object[])in.readObject();
                                if(object==null)
                                {
                                    break;
                                }
                                else
                                {

                                            String command = object[0].toString();

                                            if(command.equals("EXAMDETAIL"))
                                            {
                                                String[][] imagemap = (String[][]) object[2];

                                                for(int i=0;i<imagemap.length;i++)
                                                {
                                                    //main.getLoadbitmap().putString(imagemap[i][0], imagemap[i][1]);
                                                    MainActivity.addDatatoCache(imagemap[i][0], imagemap[i][1]);
                                                }

                                                try {
                                                    new Exam().parse(object[1].toString());
                                                    HashMap<Integer,Exam.Examination> examinations = Variable.examinations;

                                                } catch (ParserConfigurationException e) {
                                                    e.printStackTrace();
                                                } catch (SAXException e) {
                                                    e.printStackTrace();
                                                }
								/*
								HashMap<String,String> imagemap = (HashMap<String, String>) ob[2];
								for ( String key : imagemap.keySet() ) {
								    main.getLoadbitmap().putString(key, imagemap.get(key).toString());
								}*/
                                                //main.generateExam(ob[1].toString(),text);
                                            }


                                }
                            } catch (ClassNotFoundException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                                //showToast(e.getCause().toString());
                            }
                        }


                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        //showToast(e.getCause().toString());
                    }

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
                    //ob.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}