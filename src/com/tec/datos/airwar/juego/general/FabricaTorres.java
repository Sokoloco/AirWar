package com.tec.datos.airwar.juego.general;


import com.tec.datos.airwar.juego.enemigos.Bombardero;
import com.tec.datos.airwar.juego.enemigos.Jet;
import com.tec.datos.airwar.juego.enemigos.Kamikaze;
import com.tec.datos.airwar.juego.torres.Torre;
import com.tec.datos.airwar.juego.torres.TorreMisiles;
import com.tec.datos.airwar.juego.torres.TorreNormal;

import java.util.Random;

public class FabricaTorres {

    private static Random rd = new Random();

    public static Torre crear_nueva_nave(){

        int x = rd.nextInt(700);
        int y = rd.nextInt(30);

        String tipo = elegir_valor_aleatorio();

        if (tipo.equals("misiles")){
            return new TorreMisiles(x, y);
        }else {
            return new TorreNormal(x,y);
        }
    }

    /**
     * Elige un valor aleatorio de un array.
     * @return un valor aleatorio.
     */
    private static String elegir_valor_aleatorio() {

        String[] tipos = {"misiles", "normal"};

        int rnd = rd.nextInt(tipos.length);

        return tipos[rnd];
    }
}

