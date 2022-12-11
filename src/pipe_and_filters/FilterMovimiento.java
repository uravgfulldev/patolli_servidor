/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pipe_and_filters;

import dominio.Casilla;
import dominio.Ficha;
import dominio.Jugador;
import dominio.Partida;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
        int movimientos= partida.getCuantasMueve();
        LinkedList<Casilla>casillas=partida.getTablero().getCasillas();

        Ficha ficha;
        if (partida.getFichaMovimiento() != null) {

                    Ficha ficha2 = partida.getFichaMovimiento();

                    Casilla casillaActual = null;

                    for (Casilla casilla : casillas) {
                        if (casilla.getFicha() != null) {
                            if (casilla.getFicha().equals(ficha2)) {
                                casillaActual = casilla;
                                System.out.println(casilla);
                                break;
                            }
                        }
                    }
                    if (casillaActual != null) {
                        int m = partida.getCuantasMueve();
                        int n = casillas.size() - 1;
                        Casilla destino = null;

                        if ((casillaActual.getNumero() + m) <= n) {
                            destino = casillas.get(casillaActual.getNumero() + m);
                        } else {
                            int x = casillaActual.getNumero() + m - casillas.size();
                            destino = casillas.get(x);
                        }
                    
            casillas.get(casillaActual.getNumero()).setFicha(null);
            casillas.get(destino.getNumero()).setFicha(ficha2);
            
            partida.getTablero().setCasillas(casillas);
            
        
            }
      }else{
            
            Jugador jugadorTurno=partida.getJugadorTurno();
            Casilla casillaPropia = jugadorTurno.getCasillaPropia();

                            
                            ficha=new Ficha(1,jugadorTurno,true);
                            casillaPropia.setFicha(ficha);
                            casillas.set(casillas.indexOf(casillaPropia), casillaPropia);
                            
                            partida.getTablero().setCasillas(casillas);
                           
                            jugadorTurno.setMeterFicha(false);
                            
                            
                            partida.setCantidadDado(-1);
   
                        }
        output.setPartida(partida);
        }
        
    }
    

    
    
    

