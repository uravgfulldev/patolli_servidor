/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import dominio.Partida;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hugo Rivera
 */
public class ServerManager extends Thread implements UpdateManager,Filtrar{
    private Socket clienteSocket;
    private List<Socket> listaSocketClientes;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    public ServerManager(Socket socket) throws IOException{
        this.clienteSocket=socket;
        this.inputStream=new DataInputStream(this.clienteSocket.getInputStream());
        this.outputStream=new DataOutputStream(this.clienteSocket.getOutputStream());
        System.out.println("Constructor");
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
            PrintWriter out = null;
            BufferedReader in = null;

            out = new PrintWriter(outputStream, true);
            in = new BufferedReader(new InputStreamReader(
				inputStream));            

            String inputLine, outputLine;
            //Codigo que se ejecuta
            //KnockKnockProtocol kkp = new KnockKnockProtocol();

           // outputLine = kkp.processInput(null);
           // out.println(outputLine);

//        try {
//            while ((inputLine = in.readLine()) != null) {
//                //outputLine = kkp.processInput(inputLine);
//                //out.println(outputLine);
//                //if (outputLine.equals("Bye.")){
//             // isConnected=false;
//            //        break;}
//           // }
//        } catch (IOException ex) {
//            Logger.getLogger(ServerManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        }
    }

    @Override
    public void update(Partida partida) {
        
    }

    @Override
    public void filtrar(Partida partida) {
        
    }
}
