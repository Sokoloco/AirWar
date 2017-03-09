package com.tec.datos.airwar.juego.enemigos;


import com.tec.datos.airwar.juego.general.Municion;
import com.tec.datos.airwar.juego.general.ObjetoMovil;
import com.tec.datos.airwar.estructuras.List;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Bombardero extends ObjetoMovil {

    private final int VELOCIDAD = 1;
    private final int RESISTENCIA = 100;
    private String tipo = "bombardero";
    private List<Municion> municion;
    private Image imagen;

    public Bombardero(int x, int y){
        super(x, y);

        municion = new List<>();

        try{
            imagen = ImageIO.read(new File("C:/Users/dell-pc/Desktop/AirWar/src/com/tec/datos/airwar/juego/bomber.png"));
        }
        catch(Exception e){

        }
        inicializar_municion(20);
    }

    public void inicializar_municion(int cantidad){

        for (int i = 0; i < cantidad; i++){
            municion.addLast(new Municion(50,50, 5));
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

    public List<Municion> get_municion(){
        return municion;
    }
}
