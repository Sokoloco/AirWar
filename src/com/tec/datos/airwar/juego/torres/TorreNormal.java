package com.tec.datos.airwar.juego.torres;


import com.tec.datos.airwar.estructuras.*;
import com.tec.datos.airwar.estructuras.List;
import com.tec.datos.airwar.juego.general.Municion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class TorreNormal extends Torre {

    private final int ATAQUE = 50;
    private final int RESISTENCIA = 50;
    private Image imagen;
    private Queue<Municion> municion;
    private String tipo = "normal";

    public TorreNormal(int x, int y){
        super(x,y);

        try {
            imagen = ImageIO.read(new File("C:/Users/dell-pc/Desktop/AirWar/src/com/tec/datos/airwar/resources/turret.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        municion = new Queue<>();
    }

    @Override
    public void setVELOCIDAD(int s) {

    }

    @Override
    public int getVELOCIDAD() {
        return 1;
    }

    @Override
    public void draw(Graphics ventana) {
        ventana.drawImage(imagen,getX(),getY(),80,80,null);
    }

    public Queue<Municion> get_municion() {
        return municion;
    }

    public String get_tipo(){
        return tipo;
    }


}
