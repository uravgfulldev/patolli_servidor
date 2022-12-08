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
public class FilterMovimiento extends Filter{
    
    Partida partida;
    public FilterMovimiento(){
        
    }

    @Override
    void filtrar(Pipe input, Pipe output) {

        Partida partida=input.getPartida();
        output.setPartida(partida);
        
    }
    
    
}
