package com.tec.datos.airwar.juego.general;

import com.tec.datos.airwar.estructuras.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

public class AirWarGUI extends Canvas implements KeyListener, Runnable{

    private NaveJugador jugador;
    private Nivel nivel;

    private boolean[] keys;
    private BufferedImage fondo;
    private Timer timer_naves;

    private Graphics g_fondo;

    /**
     * Constructor; instancia todas las estructuras y demás datos del juego.
     */
    public AirWarGUI(Nivel nivel) {

        setBackground(Color.black);

        keys = new boolean[5];

        this.nivel = nivel;

        jugador = new NaveJugador(400, 450);

        jugador.get_municion().addLast(new Municion(jugador.getX(), jugador.getY(), 5));

        this.addKeyListener(this);
        new Thread(this).start();

        setVisible(true);

        iniciar_ataque();
    }

    /**
     * Dibuja las estadisticas del jugador.
     * @param g graficos en que se va a dibujar.
     */
    private void dibujar_estadisticas_jugador(Graphics g) {

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Puntaje: " + jugador.get_puntaje(), 25, 40);


        g.setFont(new Font("Arial", Font.BOLD, 20));
        if (jugador.get_vidas() <= 1){
            g.setColor(Color.RED);
        }else {
            g.setColor(Color.CYAN);
        }
        g.drawString("Vidas: " + jugador.get_vidas(), 25, 70);


        g.setColor(Color.ORANGE);
        g.setFont(new Font("Impact", Font.PLAIN, 25));
        g.drawString("Nivel: " + String.valueOf(nivel.numero_nivel), 25, 100);

        g.setColor(Color.GREEN);
        g.drawString("HP:" + String.valueOf(jugador.get_hp()), 25, 130);
    }

    /**
     * Actualiza la ventana del juego.
     * @param ventana La ventana que es actualizada.
     */
    public void update(Graphics ventana){
        paint(ventana);
    }

    /**
     * Pinta las imagenes adecuadas, cuando desaparecen de la pantalla se pinta sobre ellas.
     * @param ventana la ventana que se actualiza.
     */
    public void paint( Graphics ventana ) {

        Graphics2D twoDGraph = (Graphics2D)ventana;

        if(fondo == null) {
            fondo = (BufferedImage) (createImage(getWidth(), getHeight()));
        }

        //El fondo donde se dibuja.
        g_fondo = fondo.createGraphics();

        g_fondo.setColor(Color.BLUE);
        g_fondo.drawString("AirWar ", 25, 50 );
        g_fondo.setColor(Color.BLACK);
        g_fondo.fillRect(0,0,800,600);

        dibujar_estadisticas_jugador(g_fondo);

        verificar_accion();
        dibujar_enemigos(g_fondo, nivel.getNaves_enemigas().getHead());
        dibujar_disparos(g_fondo);
        verificar_puntaje();

        jugador.draw(g_fondo);
        twoDGraph.drawImage(fondo, null, 0, 0);
    }

    private void verificar_puntaje(){                       //Cuando se pase de nivel se detiene el timer y se genera al jefe. Cuando el jefe es vencido se reinicia el timer y se continua normal.
        if (jugador.get_puntaje() > 3000 && nivel.numero_nivel == 1){
            //Aqui debe iniciarse un nuevo nivel
            nivel.numero_nivel++;
        }
    }


    @SuppressWarnings("unchecked")
    private void dibujar_enemigos(Graphics g, Node<ObjetoMovil> nave) {

        try {

            nave.getData().draw(g);
            //<metodo de dibujar disparos aqui>

            nave.getData().mover("UP");

            detectar_colision_con_balas(nave);

        } catch (NullPointerException npe) {
            //
        }
    }

    public void detectar_colision_con_balas(Node<ObjetoMovil> nave){

        //Colision con las balas.
        Node<Municion> municion_jugador = jugador.get_municion().getHead();

        while (municion_jugador.getNext() != null) {
            if (nave.getData().getX() >= municion_jugador.getData().getX() && nave.getData().getX() <= municion_jugador.getData().getX() + 100 && nave.getData().getY() >= municion_jugador.getData().getY() && nave.getData().getY() <= municion_jugador.getData().getY() + 80) {
                nivel.getNaves_enemigas().remove(nave);
                jugador.get_municion().remove(municion_jugador);
                jugador.sumar_puntaje(nave.getData().get_tipo());
            }
            municion_jugador = municion_jugador.getNext();
        }
    }

    @SuppressWarnings("unchecked")
    private void dibujar_disparos(Graphics g){

        Node<Municion> municion_jugador = jugador.get_municion().getHead();

        while (municion_jugador.getNext() != null) {

            municion_jugador.getData().draw(g);
            municion_jugador.getData().mover("DOWN");

            municion_jugador = municion_jugador.getNext();
        }
    }

    /**
     * Verifica la dirección en la que el jugador se quiere mover o si quiere disparar.
     */
    private void verificar_accion(){

        if(keys[0]){
            if(jugador.getX() > 10){
                jugador.mover("LEFT");
            }
        }
        if(keys[1]){
            if(jugador.getX() < 700){
                jugador.mover("RIGHT");
            }
        }
        if(keys[2]){
            if(jugador.getY() > 10){
                jugador.mover("DOWN");
            }
        }
        if(keys[3]){
            if(jugador.getY() < 500){
                jugador.mover("UP");
            }
        }
        if(keys[4]){

            Municion disparo_jugador = new Municion(jugador.getX() + 28, jugador.getY(), 5);
            jugador.get_municion().addLast(disparo_jugador);

            keys[4] = false;
        }

    }

    /**
     * Verifica cual tecla se presionó y se repinta la ventana acorde a la acción.
     * @param e la tecla presionada.
     */
    public void keyPressed(KeyEvent e){

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keys[0] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keys[1] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keys[2] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[3] = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[4] = true;
        }
        repaint();
    }

    /**
     * Verifica cual tecla se liberó y repinta según.
     * @param e Key that is pressed
     */
    public void keyReleased(KeyEvent e){

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            keys[0] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            keys[1] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            keys[2] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            keys[3] = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            keys[4] = false;
        }
        repaint();
    }


    public void keyTyped(KeyEvent e){

    }

    /**
     *Inicia la aparición de naves, iniciando el timer.
     */
    private void iniciar_ataque(){

        if(timer_naves == null){

            timer_naves = new Timer(3000, new AirWarGUI.TimerHandler());
            timer_naves.start();

        }
        else{
            if(!timer_naves.isRunning()){
                timer_naves.restart();
            }
        }
    }

    @SuppressWarnings("unchecked")
    private class TimerHandler implements  ActionListener {

        Node<ObjetoMovil> nave = nivel.getNaves_enemigas().getHead();

        public void actionPerformed(ActionEvent actionEvent){

            dibujar_enemigos(g_fondo,nave);

            nivel.getNaves_enemigas().dequeue();
        }
    }

    /**
     *Ciclo que actualiza la ventana.
     */
    public void run(){
        try{
            while(true){
                Thread.currentThread().sleep(5);
                repaint();
            }
        }
        catch(Exception e){
        }
    }

}
