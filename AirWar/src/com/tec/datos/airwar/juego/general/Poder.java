package com.tec.datos.airwar.juego.general;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Poder implements Localizable {

    private int xPos;
    private int yPos;
    private Image imagen;
    String tipo;


    public Poder(int x, int y, String tipo){
        setPos(x,y);
        this.tipo = tipo;

        try {
            if (tipo.equals("misil")){
                imagen = ImageIO.read(new File("C:\\Users\\Luis\\IdeaProjects\\AirWar\\src\\com\\tec\\datos\\airwar\\resources\\misil_orb.png"));
            }else if (tipo.equals("laser")){
                imagen = ImageIO.read(new File("C:\\Users\\Luis\\IdeaProjects\\AirWar\\src\\com\\tec\\datos\\airwar\\resources\\laser_orb.png"));
            }else {
                imagen = ImageIO.read(new File("C:\\Users\\Luis\\IdeaProjects\\AirWar\\src\\com\\tec\\datos\\airwar\\resources\\shield_orb.png"));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
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

}
