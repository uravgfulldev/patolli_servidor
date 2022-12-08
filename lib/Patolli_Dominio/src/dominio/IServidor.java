/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dominio;

import java.io.IOException;

/**
 *
 * @author Hugo Rivera
 */
public interface IServidor {
    public void enviar(Partida partida)throws IOException;
}
