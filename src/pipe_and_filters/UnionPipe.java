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
public class UnionPipe implements Pipe{
    private Filter filter;
    private Partida partida;

    public UnionPipe(Filter filter) {
        this.filter = filter;
        
    }

    @Override
    public Partida getPartida() {
        return partida;
    }

    @Override
    public void setPartida(Partida partida) {
        this.partida=partida;
    }

    

    
    
}
