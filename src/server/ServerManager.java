/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import dominio.IServidor;
import dominio.Partida;
import dominio.Tablero;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pipe_and_filters.Sink;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Hugo Rivera
 */
public class ServerManager extends Thread implements UpdateManager,Filtrar,IServidor{
    private Socket clienteSocket=null;
    private List<Socket> listaSocketClientes;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Partida partida_actual;
    private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    boolean success;
    public ServerManager(Socket socket)  throws IOException{
        System.out.println("Constructor");
        partida_actual=new Partida();
        this.clienteSocket=socket;
        this.inputStream=new DataInputStream(this.clienteSocket.getInputStream());
        this.outputStream=new DataOutputStream(this.clienteSocket.getOutputStream());
        success=true;
        
    }
    
    public int getNumConectados(){
        return -1;
    }
    public List<Socket> getClientes(){
        return listaSocketClientes;
    }
    @Override
    public void run() {
        boolean isConnected=true;
        
        while(isConnected){
            try {
                if(success){
                    System.out.println("Conexion exitosa con: "+clienteSocket.getPort());
                    //this.outputStream.writeUTF("Se logro conectar al servidor");
                    partida_actual.setNumCasillasAspa(10);
                    partida_actual.setTablero(new Tablero());
                    enviar(partida_actual);
                    success=false;
                }

            } catch (IOException ex) {
                Logger.getLogger(ServerManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    @Override
    public void update(Partida partida) {
        partida_actual=partida;
    }

    @Override
    public void filtrar(Partida partida) {
        
    }

    @Override
    public void enviar(Partida partida)throws IOException {
            
            this.outputStream.writeUTF(ConvertirObjectoString(partida));
            outputStream.flush();
            outputStream.close();
           // this.outputStream.flush();
            
    }
    
    private String ConvertirObjectoString(Partida partida) {
        try {
            return objectMapper.writeValueAsString(partida);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(ServerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
