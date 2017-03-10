package com.tec.datos.airwar.juego.enemigos;

import com.tec.datos.airwar.estructuras.*;
import com.tec.datos.airwar.estructuras.List;
import com.tec.datos.airwar.juego.general.Municion;
import com.tec.datos.airwar.juego.general.ObjetoMovil;

import java.awt.*;

public class Jefe extends ObjetoMovil {


    @Override
    public void setVELOCIDAD(int s) {

    }

    @Override
    public int getVELOCIDAD() {
        return 0;
    }

    @Override
    public void draw(Graphics ventana) {

    }

    public Queue<Municion> get_municion() {
        return null;
    }
}
