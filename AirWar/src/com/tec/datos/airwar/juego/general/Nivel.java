package com.tec.datos.airwar.juego.general;

import com.tec.datos.airwar.estructuras.List;
import com.tec.datos.airwar.estructuras.Queue;
import com.tec.datos.airwar.juego.enemigos.Jefe;
import com.tec.datos.airwar.juego.torres.Torre;

public class Nivel {

    int numero_nivel;
    private Queue<ObjetoMovil> naves_enemigas;
    private List<Torre> torres;

    private boolean logrado = false;

    public Nivel(int nivel){

        this.numero_nivel = nivel;

        naves_enemigas = new Queue<>();
        torres = new List<>();

        //cantidad de jefes cambie a 3 para test del boss

        añadir_naves_enemigas(3);
        añadir_torres(3);
    }

    public void añadir_naves_enemigas(int cantidad_enemigos){

        for (int i = 0; i < cantidad_enemigos; i++){
            naves_enemigas.enqueue(FabricaNaves.crear_nuevo_enemigo());
        }
        //DEFINIR LUEGO BIEN EL JEFE, CUANDO SE DEFINA L¿DICHA CLASE

    }

    public void añadir_torres(int cantidad_torres){

        for (int i = 0; i < cantidad_torres; i++){
            torres.addLast(FabricaTorres.crear_nueva_nave());
        }
    }

    public Queue<ObjetoMovil> getNaves_enemigas() {
        return naves_enemigas;
    }

    public List<Torre> getTorres() {
        return torres;
    }

    public boolean isLogrado() {
        return logrado;
    }

    public void setLogrado(boolean estado){
        logrado = estado;

    }

    public int get_Nivel(){
        return numero_nivel;
    }


}
