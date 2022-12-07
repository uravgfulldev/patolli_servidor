/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hugo Rivera
 */
public class PatolliServer extends ServerSocket{
        private ServerSocket serverSocket = null;
    public PatolliServer() throws IOException{
        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException ex) {
            Logger.getLogger(PatolliServer.class.getName()).log(Level.SEVERE, null, ex);
        }
	System.out.println("estoy después de crear el socket");
    }
    
    public void startToLisen() throws IOException{
        while(true){
            Socket clientSocket = null;
            clientSocket = serverSocket.accept();
            System.out.println("estoy después de aceptar un cliente");
           new Thread(new ServerManager(clientSocket)).start();
          
        
        }
    }
}
