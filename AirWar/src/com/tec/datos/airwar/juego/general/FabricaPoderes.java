package com.tec.datos.airwar.juego.general;

import java.util.Random;

public class FabricaPoderes {

    private static Random rd = new Random();

    public static Poder crear_nuevo_poder(){

        int x = rd.nextInt(700);
        int y = rd.nextInt(550);

        String tipo = elegir_valor_aleatorio();

        if (tipo.equals("misil")){
            return new Poder(x,y, tipo);
        }else if (tipo.equals("escudo")){
            return new Poder(x,y,tipo);
        }else {
            return new Poder(x,y,tipo);
        }

    }

    /**
     * Elige un valor aleatorio de un array.
     * @return un valor aleatorio.
     */
    private static String elegir_valor_aleatorio() {

        String[] tipos = {"escudo", "misil", "laser"};

        int rnd = rd.nextInt(tipos.length);

        return tipos[rnd];
    }

}
