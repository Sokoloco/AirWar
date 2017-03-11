package com.tec.datos.airwar.juego.general;

import com.tec.datos.airwar.estructuras.*;

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

    public void mover_hacia(int x, int y){

        if (getY() < y && getX() > x){
            yPos += 3;
            xPos -= 3;
        }else if (getY() < y && getX() < x){
            yPos += 3;
            xPos += 3;
        }else {
            yPos += 3;
        }
    }

    public abstract Queue<Municion> get_municion();

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
