/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pipe_and_filters;

import dominio.Partida;

/**
 *
 * @author Hugo Rivera
 */
public class FinalPipe implements Pipe{
    private Sink sink;
    Partida partida;
    public FinalPipe() {
    }

    public FinalPipe(Sink sink) {
        this.sink = new Sink(partida);
    }
    public Sink sink(){
        return sink;
    }

    @Override
    public Partida getPartida() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setPartida(Partida partida) {
        this.partida=partida;
        this.sink=new Sink(partida);
    }
}
