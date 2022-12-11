/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dominio.Partida;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hugo Rivera
 */
public class PatolliServer extends Thread{
        private ServerSocket serverSocket = null;
        private Socket cliente;
        private UpdateManager updateServerManager;
        private DataInputStream in;
        private DataOutputStream out;
        private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    public PatolliServer(ServerSocket serverSocket,UpdateManager updateServerManager) throws IOException{
        this.serverSocket=serverSocket;
        this.updateServerManager=updateServerManager;
    }
    
    @Override
    public void run() {
            try {
                startToLisen();
            } catch (IOException ex) {
                Logger.getLogger(PatolliServer.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public void startToLisen() throws IOException{
            cliente = null;
            cliente = serverSocket.accept();
            PatolliServer hilo = new PatolliServer(serverSocket,updateServerManager);
            new Thread(hilo).start();
            out = new DataOutputStream(cliente.getOutputStream());
            in = new DataInputStream(cliente.getInputStream());
            notificarConexion();
            while (true) {
                try {
                    Partida partida;
                    if ((partida = convertirPartida(in.readUTF())) != null) {
                        System.out.println(partida.getTablero().getCasillas());
                        notificarMovimiento(partida);
                        
                    }
                } catch (Exception e) {
                    out.close();
                    in.close();
                    cliente.close();
                            
                      
                        
                   
                }
            }
 
    }
    private void notificarMovimiento(Partida partida) {
            updateServerManager.update(partida);
    }
    
    private String ConvertirObjectoString(Partida partida) {
        try {
            return objectMapper.writeValueAsString(partida);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ServerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    public Partida convertirPartida(String partida) {
        try {
            return objectMapper.readValue(partida, Partida.class);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public void enviarPartida(Partida partida) throws IOException {
        this.out.writeUTF(ConvertirObjectoString(partida));
    }
    private void notificarConexion() {
        updateServerManager.updateClientes(this);
    }
}
