package com.tec.datos.airwar.juego.general;

import com.tec.datos.airwar.estructuras.*;
import com.tec.datos.airwar.estructuras.List;
import com.tec.datos.airwar.juego.torres.Torre;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class AirWar extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private Nivel nivel_actual;
    private int numero_nivel = 1;

    /**
     * Instanciacion de un nuevo juego.
     */
    public AirWar() {
        super("Air War");
        setSize(WIDTH, HEIGHT);

        nivel_actual = new Nivel(numero_nivel);

        AirWarGUI AirWarGame = new AirWarGUI(nivel_actual);

        ((Component) AirWarGame).setFocusable(true);

        getContentPane().add(AirWarGame);

        setVisible(true);

        setResizable(false);
    }


    /**
     * Musica de fondo.
     */
    public void play_music(){

        String file_path = "C:/Users/dell-pc/Desktop/AW Music/mf.wav";

        try {
            InputStream in = new FileInputStream(file_path);

            AudioStream audio = new AudioStream(in);

            AudioPlayer.player.start(audio);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException x){
            x.printStackTrace();
        }
    }

    public static void main(String[] args) {

        System.out.print(1000 % 100);

        AirWar airWar = new AirWar();
        airWar.play_music();

    }
}
