package com.tec.datos.airwar.juego.torres;

import com.tec.datos.airwar.juego.general.ObjetoMovil;
import java.util.Random;

public abstract class Torre extends ObjetoMovil {

    private static Random rd = new Random();

    public Torre(int x, int y) {
        setPos(x, y);
    }


    /**
     * Genera cualquier tipo de nave.
     * @param x posicion en x.
     * @param y posicion en y.
     * @return una nave aleatoria.
     */
    public static Torre generar_nave_aleatoria(int x, int y){

        String[] torres = {"normal", "misiles"};

        String nave = elegir_valor_aleatorio(torres);

        if (nave.equals("normal")){
            return new TorreNormal(x,y);
        }else{
            return new TorreMisiles(x,y);
        }
    }

    /**
     * Elige un valor aleatorio de un array.
     * @param array array donde se quiere elegir.
     * @return un valor aleatorio.
     */
    private static String elegir_valor_aleatorio(String[] array) {

        int rnd = rd.nextInt(array.length);

        return array[rnd];
    }


}

