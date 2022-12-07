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
abstract class Filter{
    abstract void filtrar();
    public void doFilter(Pipe input,Pipe output){
        filtrar();
    }
}
