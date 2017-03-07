package com.tec.datos.airwar.juego.enemigos;

import com.tec.datos.airwar.juego.general.ObjetoMovil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;


public class Jet extends ObjetoMovil {

    private final int RESISTENCIA = 50;
    private final int VELOCIDAD = 3;     // 1 -> slow , 5 -> fast
    private String tipo = "jet";
    private Image image;

    public void setVELOCIDAD(int s) {

    }

    public Jet(int x, int y) {
        super(x, y);

        try
        {
            image = ImageIO.read(new File("C:/Users/dell-pc/Desktop/AirWar/src/com/tec/datos/airwar/juego/ship3.png"));
        }
        catch(Exception e)
        {

        }
    }

    public int getVELOCIDAD()
    {
        return VELOCIDAD;
    }

    public void draw( Graphics ventana)
    {
        ventana.drawImage(image,getX(),getY(),80,80,null);
    }

    public String get_tipo(){
        return tipo;
    }

    public String toString()
    {
        return "Jet " + super.toString() + " Speed " + getVELOCIDAD();
    }
}

