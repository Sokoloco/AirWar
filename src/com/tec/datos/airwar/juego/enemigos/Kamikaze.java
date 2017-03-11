package com.tec.datos.airwar.juego.enemigos;

import com.tec.datos.airwar.estructuras.*;
import com.tec.datos.airwar.estructuras.List;
import com.tec.datos.airwar.juego.general.Municion;
import com.tec.datos.airwar.juego.general.ObjetoMovil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;


public class Kamikaze extends ObjetoMovil {

    private final int VELOCIDAD = 20;
    private final int RESISTENCIA = 50;
    private String tipo = "kamikaze";
    private Image imagen;

    public Kamikaze(int x, int y){
        super(x, y);

        try {
            imagen = ImageIO.read(new File("C:/Users/dell-pc/Desktop/AirWarGH/src/com/tec/datos/airwar/resources/kamikaze.png"));
        }
        catch(Exception e) {
            e.printStackTrace();
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

    public Queue<Municion> get_municion() {
        return null;
    }
}
