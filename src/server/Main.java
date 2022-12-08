/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package server;

import java.io.IOException;
import pipe_and_filters.FilterMovimiento;
import pipe_and_filters.FinalPipe;
import pipe_and_filters.UnionPipe;

/**
 *
 * @author Hugo Rivera
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        PatolliServer ps=new PatolliServer();
        ps.startToLisen();
        FilterMovimiento filtroMovimiento=new FilterMovimiento();
        UnionPipe pipe1=new UnionPipe(filtroMovimiento);
        FinalPipe pipe2=new FinalPipe();
        filtroMovimiento.doFilter(pipe1, pipe2);
    }
    
}
