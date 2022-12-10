/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import dominio.IServidor;
import dominio.Partida;
import dominio.Tablero;
import dominio.Ficha;
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
import java.net.ServerSocket;
import java.util.ArrayList;
import pipe_and_filters.FilterMovimiento;
import pipe_and_filters.FinalPipe;
import pipe_and_filters.UnionPipe;

/**
 *
 * @author Hugo Rivera
 */
public class ServerManager implements UpdateManager,Filtrar,IServidor{
    private Socket clienteSocket=null;
    private List<PatolliServer> listaClientes;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private Partida partida_actual;
    private Sink sink;
    private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    public ServerManager()  throws IOException{
        System.out.println("Constructor");
        inicializar();
        
    }
    public void inicializar() throws IOException{
        listaClientes=new ArrayList<>();
        partida_actual=new Partida();
        startToLisen();
    }
    public int getNumConectados(){
        return listaClientes.size();
    }
    public List<PatolliServer> getClientes(){
        return listaClientes;
    }
    public void accionesConexion(PatolliServer conexion) {
        this.listaClientes.add(conexion);
        try {
            enviar(null);
        } catch (IOException ex) {
            Logger.getLogger(ServerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    public void updateClientes(PatolliServer conexion) {
        accionesConexion(conexion);
        
    }
    @Override
    public void update(Partida partida) {
        try {
           
            FilterMovimiento fm=new FilterMovimiento();
            UnionPipe pipe1=new UnionPipe(fm);
            FinalPipe pipe2=new FinalPipe(this.sink);
            pipe1.setPartida(partida);
            fm.doFilter(pipe1, pipe2);
            this.sink=pipe2.sink();
            enviar(sink.notificar());
        } catch (IOException ex) {
            Logger.getLogger(ServerManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void filtrar(Partida partida) {
        
    }

    @Override
    public void enviar(Partida partida)throws IOException {
            
                 enviarPartidaAClientes(partida);
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
    public Partida convertirPartida(String partida) {
        try {
            return objectMapper.readValue(partida, Partida.class);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
        }
        return null;
    }
//    private void actualizarPartida(){
//        try {
//            if(inputStream.readUTF()!=null){
//                update(convertirPartida(inputStream.readUTF()));
//                inputStream.close();
//            
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(ServerManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
   
    public void startToLisen() throws IOException{
  
            PatolliServer cliente1 = new PatolliServer(new ServerSocket(4444),this);
            System.out.println("Servidor iniciado");
            cliente1.run();
            
    }
        public void enviarPartidaAClientes(Partida partida) {
        for (PatolliServer cliente : listaClientes) {
            try {
                cliente.enviarPartida(partida);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
