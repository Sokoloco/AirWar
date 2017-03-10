package com.tec.datos.airwar.juego.general;

import com.tec.datos.airwar.estructuras.*;
import com.tec.datos.airwar.estructuras.List;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;


public class NaveJugador extends ObjetoMovil
{
    private final int VELOCIDAD = 3;
    private Image imagen;

    private int puntaje;
    private int vidas;
    private int hp;

    private Queue<Municion> municion;
    private boolean es_invencible = false;
    private Stack<Poder> poderes;

    public NaveJugador(int x, int y)
    {
        super(x, y);

        municion = new Queue<>();

        puntaje = 0;
        vidas = 3;
        hp = 1000;

        try{
            imagen = ImageIO.read(new File("C:/Users/dell-pc/Desktop/AirWar/src/com/tec/datos/airwar/resources/ship.png"));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public void setX(int x){
        super.setX(x);
    }

    public void setY(int y){
        super.setY(y);
    }

    public void setVELOCIDAD(int s){
    }

    public int getVELOCIDAD()
    {
        return VELOCIDAD;
    }


    public void draw( Graphics ventana )
    {
        ventana.drawImage(imagen,getX(),getY(),80,80,null);
    }

    public Queue<Municion> get_municion() {
        return municion;
    }


    public String toString()
    {
        String output = "Pos " + super.getX() + " " + super.getY() + " Speed " + getVELOCIDAD();
        return output;
    }

    public int get_vidas(){
        return vidas;
    }

    public int get_puntaje(){
        return puntaje;
    }

    public void sumar_puntaje(String tipo){

        switch (tipo){
            case "jet":
                puntaje += 250;
                break;
            case "kamikaze":
                puntaje += 400;
                break;
            case "normal":
                puntaje += 100;
                break;
            case "misiles":
                puntaje += 200;
                break;
            case "bombardero":
                puntaje += 100;
                break;
        }
    }

    public void restar_vidas(){
        vidas--;
    }

    public void sumar_vidas(){
        vidas++;
    }

    public void restar_hp(double hp){
        this.hp -= hp;
    }

    public int get_hp(){
        return hp;
    }

    public boolean get_es_invencible(){
        return es_invencible;
    }
}

