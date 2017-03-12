package com.tec.datos.airwar.juego.general;

import com.tec.datos.airwar.juego.enemigos.Jefe;

import java.awt.*;

/**
 * Created by Luis on 11-Mar-17.
 */
public class JefeGui{

    Graphics fondo;
    Jefe Jefe_actual;

    public JefeGui(Graphics fondo, int num_nivel){
        this.fondo = fondo;

        Jefe_actual = new Jefe(0,0, num_nivel);

        Jefe_actual.draw(fondo);



    }

    public boolean timer(int x){
        if(x<=0){
            return true;
        }
        else{
            return false;
        }
    }
    /*public void movimiento_Jefe(){
        while(Jefe_actual.alive()) {
            while (timer(50)) {
                Jefe_actual.mover("UP");
            }
            while (timer(50)) {
                Jefe_actual.mover("DOWN");
            }
            while (timer(50)) {
                Jefe_actual.mover("RIGHT");
            }
            while (timer(50)) {
                Jefe_actual.mover("LEFT");
            }
        }
    }*/
}
