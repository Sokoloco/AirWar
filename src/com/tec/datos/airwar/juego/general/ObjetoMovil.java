package com.tec.datos.airwar.juego.general;

import com.tec.datos.airwar.juego.enemigos.Bombardero;
import com.tec.datos.airwar.juego.enemigos.Jet;
import com.tec.datos.airwar.juego.enemigos.Kamikaze;

import java.awt.*;
import java.util.Random;


public abstract class ObjetoMovil implements Localizable
{
    private int xPos;
    private int yPos;
    private String tipo;

    private static Random rd = new Random();


    public ObjetoMovil(){
        xPos = 0;
        yPos = 0;
    }


    public ObjetoMovil(int x, int y){

        setPos(x, y);
    }

    public void setPos( int x, int y){

        xPos = x;
        yPos = y;
    }

    public void setX(int x){
        xPos = x;
    }


    public void setY(int y){
        yPos = y;
    }

    public int getX()
    {
        return xPos;
    }


    public int getY()
    {
        return yPos;
    }


    public abstract void setVELOCIDAD(int s );


    public abstract int getVELOCIDAD();


    public abstract void draw(Graphics ventana);

    /**
     * Establece la dirección a la que se quiere mover la nave.
     * @param direccion direccion deseada.
     */
    public void mover(String direccion)
    {
        if(direccion.equals("LEFT")){
            setX(getX() - getVELOCIDAD());
        }
        else if(direccion.equals("RIGHT")){
            setX(getX() + getVELOCIDAD());
        }
        else if(direccion.equals("UP")){
            setY(getY() + getVELOCIDAD());
        }
        else if(direccion.equals("DOWN")){
            setY(getY() - getVELOCIDAD());
        }

    }

    public void mover_aleatoriamente(){

        String[] movimiento_validos = {"LEFT", "RIGHT", "UP"};

        mover(elegir_valor_aleatorio(movimiento_validos));

    }

    /**
     * Genera cualquier tipo de nave.
     * @param x posicion en x.
     * @param y posicion en y.
     * @return una nave aleatoria.
     */
    public static ObjetoMovil generar_nave_aleatoria(int x, int y){

        String[] naves = {"jet", "bombardero", "kamikaze"};

        String nave = elegir_valor_aleatorio(naves);

        if (nave.equals("jet")){
            return new Jet(x,y);
        }else if (nave.equals("bombardero")){
            return new Bombardero(x,y);
        }else {
            return new Kamikaze(x,y);
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

    public String get_tipo(){
        return tipo;
    }

    /**
     * Summarizes a ObjetoMovil's statistics in a String
     * @return String summary of a ObjetoMovil's vital statistics
     */
    public String toString()
    {
        return "Pos " + getX() + " " + getY();
    }
}
