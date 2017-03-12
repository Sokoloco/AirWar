package com.tec.datos.airwar.juego.enemigos;

import com.tec.datos.airwar.estructuras.*;
import com.tec.datos.airwar.estructuras.List;
import com.tec.datos.airwar.juego.general.Municion;
import com.tec.datos.airwar.juego.general.ObjetoMovil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Jefe extends ObjetoMovil {


    private int hp = 1000;
    private final int VELOCIDAD = 0;     // 1 -> slow , 5 -> fast
    private String tipo = "jefe";
    private Queue<Municion> municion;
    private Image image;

    public void setVELOCIDAD(int s) {

    }

    public Jefe(int x, int y, int Jefe_num) {
        super(x, y);

        municion = new Queue<>();

        try
        {
            image = ImageIO.read(new File("C:\\Users\\Luis\\IdeaProjects\\AirWar\\src\\com\\tec\\datos\\airwar\\resources\\ship3.png"));
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
        ventana.drawImage(image,getX(),getY(),120,120,null);
    }

    public String get_tipo(){
        return tipo;
    }

    public String toString()
    {
        return "Jefe " + super.toString() + " Speed " + getVELOCIDAD();
    }

    public Queue<Municion> get_municion(){
        return municion;
    }

    public int get_hp(){return hp;}

    public boolean alive(){
        if(hp <=0){
            return true;
        }
        else {
            return false;
        }
    }
}
