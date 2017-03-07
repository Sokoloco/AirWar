package com.tec.datos.airwar.juego.enemigos;


import com.tec.datos.airwar.juego.general.ObjetoMovil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Bombardero extends ObjetoMovil {

    private final int VELOCIDAD = 1;
    private final int RESISTENCIA = 100;
    private String tipo = "bombardero";
    private Image imagen;

    public Bombardero(int x, int y){
        super(x, y);

        try
        {
            imagen = ImageIO.read(new File("C:/Users/dell-pc/Desktop/AirWar/src/com/tec/datos/airwar/juego/bomber.png"));
        }
        catch(Exception e)
        {

        }
    }

    public void setVELOCIDAD(int s) {

    }

    public int getVELOCIDAD() {
        return VELOCIDAD;
    }

    @Override
    public void draw(Graphics ventana) {
        ventana.drawImage(imagen,getX(),getY(),80,80,null);
    }

    public String get_tipo(){
        return tipo;
    }
}
