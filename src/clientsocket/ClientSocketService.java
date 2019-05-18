/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientsocket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;

/**
 *
 * @author server
 */
public class ClientSocketService extends Thread {

    private ClientForm clientformForm;
    private DataInputStream dataInPutStream;
    private DataOutputStream dataOutPutStream;
    private String ip;
    private int port;
    private Socket socket;
    private String message;

    public ClientSocketService(String ip, int port) throws IOException {
        this.ip = ip;
        this.port = port;
        this.message ="You are connected on the server";
    }

    @Override
    public void run() {        
            try { 
                this.socket = new Socket(this.ip, this.port);
                dataInPutStream = new DataInputStream(socket.getInputStream());
                dataOutPutStream = new DataOutputStream(socket.getOutputStream());
                dataOutPutStream.writeUTF(this.getMensagem());                
                setMensagem(dataInPutStream.readUTF());       
                dataInPutStream.close();
                dataOutPutStream.close();    
                socket.close();
              System.out.println("Log : "+this.getClass().getName()+" | "+new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date())+" | Your Mensage was sented");
            } catch (IOException ex) {
              System.out.println("Log : "+this.getClass().getName()+" | "+new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date())+" | "+ex.getCause()+" | "+ex.getMessage());
            }                    
    }
    
    public void setMensagem(String message){
     this.message=message+"\n";              
    }
    
    
    public String getMensagem(){
     return this.message;              
    }

}
