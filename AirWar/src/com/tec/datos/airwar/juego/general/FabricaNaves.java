package com.tec.datos.airwar.juego.general;


import com.tec.datos.airwar.juego.enemigos.Bombardero;
import com.tec.datos.airwar.juego.enemigos.Jet;
import com.tec.datos.airwar.juego.enemigos.Kamikaze;

import java.util.Random;

public class FabricaNaves {

    private static Random rd = new Random();
//crea todos los tipo de los enemigos
    public static ObjetoMovil crear_nuevo_enemigo(){

        int x = rd.nextInt(700);
        int y = rd.nextInt(30);

        String tipo = elegir_valor_aleatorio();

        if (tipo.equals("jet")){
            return new Jet(x, 0);
        }else if (tipo.equals("bombardero")){
            return new Bombardero(x, 0);
        }
        else {
            return new Kamikaze(x,0);
        }
    }

    /**
     * Elige un valor aleatorio de un array.
     * @return un valor aleatorio.
     */
    private static String elegir_valor_aleatorio() {

        String[] tipos = {"jet", "bombardero", "kamikaze"};

        int rnd = rd.nextInt(tipos.length);

        return tipos[rnd];
    }
}
