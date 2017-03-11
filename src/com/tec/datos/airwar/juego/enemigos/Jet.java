package com.tec.datos.airwar.juego.enemigos;

import com.tec.datos.airwar.estructuras.*;
import com.tec.datos.airwar.estructuras.List;
import com.tec.datos.airwar.juego.general.Municion;
import com.tec.datos.airwar.juego.general.ObjetoMovil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;


public class Jet extends ObjetoMovil {

    private final int RESISTENCIA = 50;
    private final int VELOCIDAD = 1;     // 1 -> slow , 5 -> fast
    private String tipo = "jet";
    private Queue<Municion> municion;
    private Image image;

    public void setVELOCIDAD(int s) {

    }

    public Jet(int x, int y) {
        super(x, y);

        municion = new Queue<>();

        try
        {
            image = ImageIO.read(new File("C:/Users/dell-pc/Desktop/AirWarGH/src/com/tec/datos/airwar/resources/ship3.png"));
        }
        catch(Exception e){
            e.printStackTrace();
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

    public Queue<Municion> get_municion(){
        return municion;
    }


}

