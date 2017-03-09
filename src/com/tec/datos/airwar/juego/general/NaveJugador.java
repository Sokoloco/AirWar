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

    private List<Municion> municion;
    private boolean es_invencible = false;
    private Stack<Poder> poderes;

    public NaveJugador(int x, int y)
    {
        super(x, y);

        municion = new List<>();

        puntaje = 0;
        vidas = 3;
        hp = 1000;

        try{
            imagen = ImageIO.read(new File("C:/Users/dell-pc/Desktop/AirWar/src/com/tec/datos/airwar/juego/ship.png"));
        }
        catch(Exception e){

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

    @Override
    public List<Municion> get_municion() {
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
        if (tipo.equals("jet")){
            puntaje += 250;
        }else if (tipo.equals("bombardero")){
            puntaje += 400;
        }else {
            puntaje += 500;
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

