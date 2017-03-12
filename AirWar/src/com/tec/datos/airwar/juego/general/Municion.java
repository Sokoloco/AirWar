package com.tec.datos.airwar.juego.general;
import com.tec.datos.airwar.estructuras.*;
import com.tec.datos.airwar.estructuras.List;

import java.awt.*;

public class Municion extends ObjetoMovil {

    private int velocidad;

    public Municion(int x, int y, int s)
    {
        setPos(x, y); //add code
        setVELOCIDAD(s);
    }

    public void setVELOCIDAD(int s)
    {
        velocidad = s; //add code
    }


    public int getVELOCIDAD()
    {
        return velocidad;
    }

    /**
     * Dibuja las balas en la ventana.
     * @param ventana donde le objeto es dibujado.
     */
    public void draw(Graphics ventana)
    {
        ventana.setColor(Color.yellow);
        ventana.fillRect(getX(), getY(), 5, 5);
    }

    public void draw(Graphics ventana, Color color){
        ventana.setColor(color);
        ventana.fillRect(getX(), getY(), 5,5);
    }

    @Override
    public Queue<Municion> get_municion() {
        return null;
    }

    public String toString()
    {
        return "Municion " + getVELOCIDAD();
    }
}
