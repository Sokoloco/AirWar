package com.tec.datos.airwar.juego.general;

import com.tec.datos.airwar.estructuras.*;
import com.tec.datos.airwar.juego.general.Localizable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Poder implements Localizable {

    private int xPos;
    private int yPos;
    private Image imagen;
    private String tipo;

    public Poder(int x, int y, String tipo){
        setPos(x,y);
        this.tipo = tipo;

        try {
            switch (tipo){
                case "misil":
                    imagen = ImageIO.read(new File("C:/Users/dell-pc/Desktop/AirWarGH/src/com/tec/datos/airwar/resources/misil_orb.png"));
                    break;
                case "escudo":
                    imagen = ImageIO.read(new File("C:/Users/dell-pc/Desktop/AirWarGH/src/com/tec/datos/airwar/resources/shield_orb.png"));
                    break;
                case "laser":
                    imagen = ImageIO.read(new File("C:/Users/dell-pc/Desktop/AirWarGH/src/com/tec/datos/airwar/resources/laser_orb.png"));
                    break;
            }
        }catch (IOException j){
            j.printStackTrace();
        }
    }

    public void draw(Graphics ventana){
        ventana.drawImage(imagen,getX(),getY(),40,35,null);
    }

    public void draw(Graphics ventana, int x, int y){
        ventana.drawImage(imagen,x,y,40,35,null);
    }

    @Override
    public void setPos(int x, int y) {
        xPos = x;
        yPos = y;
    }

    @Override
    public void setX(int x) {
        xPos = x;
    }

    @Override
    public void setY(int y) {
        yPos = y;
    }

    @Override
    public int getX() {
        return xPos;
    }

    @Override
    public int getY() {
        return yPos;
    }

    public String get_tipo(){
        return tipo;
    }


}
