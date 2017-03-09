package com.tec.datos.airwar.juego.enemigos;

import com.tec.datos.airwar.estructuras.List;
import com.tec.datos.airwar.juego.general.Municion;
import com.tec.datos.airwar.juego.general.ObjetoMovil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;


public class Jet extends ObjetoMovil {

    private final int RESISTENCIA = 50;
    private final int VELOCIDAD = 3;     // 1 -> slow , 5 -> fast
    private String tipo = "jet";
    private List<Municion> municion;
    private Image image;

    public void setVELOCIDAD(int s) {

    }

    public Jet(int x, int y) {
        super(x, y);

        municion = new List<>();

        try
        {
            image = ImageIO.read(new File("C:/Users/dell-pc/Desktop/AirWar/src/com/tec/datos/airwar/juego/ship3.png"));
        }
        catch(Exception e)
        {

        }
        inicializar_municion(20);
    }


    public void inicializar_municion(int cantidad){

        for (int i = 0; i < cantidad; i++){
            municion.addLast(new Municion(50,50, 5));
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

    public List<Municion> get_municion(){
        return municion;
    }


}

