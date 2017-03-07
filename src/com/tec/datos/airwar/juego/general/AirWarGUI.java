package com.tec.datos.airwar.juego.general;

import com.tec.datos.airwar.estructuras.*;
import com.tec.datos.airwar.estructuras.List;
import com.tec.datos.airwar.juego.enemigos.Jet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;

public class AirWarGUI extends Canvas implements KeyListener, Runnable{

    private NaveJugador jugador;

    private Queue<ObjetoMovil> naves_enemigas;
    private List<Municion> disparos;
    private List<Municion> disparos_enemigos;

    private boolean[] keys;
    private BufferedImage fondo;
    private final int ATAQUE_ENEMIGO = 2000;//2 seconds
    private Timer timer_enemigo;
    private int nivel;

    private Random rd = new Random();

    /**
     * Constructor; instancia todas las estructuras y demás datos del juego.
     */
    public AirWarGUI() {

        setBackground(Color.black);

        keys = new boolean[5];

        jugador = new NaveJugador(400, 450);
        naves_enemigas = new Queue<>();
        naves_enemigas.enqueue(new Jet(250,50));

        nivel = 1;

        disparos = new List<>();
        disparos_enemigos = new List<>();
        disparos.addLast(new Municion(jugador.getX(), jugador.getY(), 5));

        this.addKeyListener(this);
        new Thread(this).start();

        setVisible(true);

        cargar_disparos_enemigos();

        iniciar_ataque();
        generar_naves_enemigas();

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
        g.drawString("Nivel: " + String.valueOf(nivel), 25, 100);

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
        Graphics g_fondo = fondo.createGraphics();

        g_fondo.setColor(Color.BLUE);
        g_fondo.drawString("AirWar ", 25, 50 );
        g_fondo.setColor(Color.BLACK);
        g_fondo.fillRect(0,0,800,600);

        dibujar_estadisticas_jugador(g_fondo);

        verificar_accion();
        dibujar_enemigos(g_fondo);
        dibujar_disparos(g_fondo);
        verificar_puntaje();

        jugador.draw(g_fondo);
        twoDGraph.drawImage(fondo, null, 0, 0);
    }

    private void verificar_puntaje(){                       //Cuando se pase de nivel se detiene el timer y se genera al jefe. Cuando el jefe es vencido se reinicia el timer y se continua normal.
        if (jugador.get_puntaje() > 3000 && nivel == 1){
            nivel++;
        }
    }

    @SuppressWarnings("unchecked")
    private void dibujar_enemigos(Graphics g){

        try {
            Node<ObjetoMovil> nave = naves_enemigas.getHead();
            while (nave.getNext() != null) {

                nave.getData().draw(g);
                dibujar_disparos_enemigos(g, nave.getData().getX() + 30, nave.getData().getY() + 30);
                if (nave.getData().getX() <= 1000) {
                    if (nave.getData().getX() > 900)
                        nave.getData().setX(-20);
                }

                // ARREGLAR EL MOVIMIENTO DE LAS NAVES (Debe ser alrededor de toda la pantalla).
                nave.getData().mover("UP");

                //Colision con las balas.
                Node<Municion> municion_jugador = disparos.getHead();

                while (municion_jugador.getNext() != null) {
                    if (nave.getData().getX() >= municion_jugador.getData().getX() && nave.getData().getX() <= municion_jugador.getData().getX() + 100 && nave.getData().getY() >= municion_jugador.getData().getY() && nave.getData().getY() <= municion_jugador.getData().getY() + 80) {
                        naves_enemigas.remove(nave);
                        disparos.remove(municion_jugador);
                        jugador.sumar_puntaje(nave.getData().get_tipo());
                    }

                    municion_jugador = municion_jugador.getNext();
                }
                nave = nave.getNext();
            }
        }catch (NullPointerException npe){
            //
        }
    }

    @SuppressWarnings("unchecked")
    private void dibujar_disparos(Graphics g){

        Node<Municion> municion_jugador = disparos.getHead();
        while (municion_jugador.getNext() != null) {

            municion_jugador.getData().draw(g);
            municion_jugador.getData().mover("DOWN");

            municion_jugador = municion_jugador.getNext();
        }
    }


    @SuppressWarnings("unchecked")
    private void dibujar_disparos_enemigos(Graphics g, int x, int y){

        cambiar_posicion_disparos_enemigos(x,y);

        Node<Municion> municion_enemiga = disparos_enemigos.getHead();

        ObjetoMovil nave_enemiga = (ObjetoMovil) naves_enemigas.getHead().getData();

        while (municion_enemiga.getNext() != null){

            municion_enemiga.getData().draw(g);
            municion_enemiga.getData().mover("UP");

            if (jugador.getX() >= municion_enemiga.getData().getX() && jugador.getX() <= municion_enemiga.getData().getX() + 100 && jugador.getY() >= municion_enemiga.getData().getY() && jugador.getY() <= municion_enemiga.getData().getY() + 80){
                disparos_enemigos.remove(municion_enemiga);
                if (nave_enemiga.get_tipo().equals("jet")){
                    jugador.restar_hp(0.001);
                }else {
                    jugador.restar_hp(0.003);
                }
            }
            municion_enemiga = municion_enemiga.getNext();
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
            Municion disparo_jugador = new Municion(jugador.getX()+28, jugador.getY(), 5);
            disparos.addLast(disparo_jugador);

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


    public void keyTyped(KeyEvent e)
    {

    }

    /**
     * Genera naves enemigas aleatoriamente.
     */
    private void generar_naves_enemigas(){

        int x = rd.nextInt(800); //(int)(Math.random()*getWidth());
        int y = rd.nextInt(30);//(int)(Math.random()*(getHeight()-200));

        if(timer_enemigo.isRunning()){
            naves_enemigas.enqueue(ObjetoMovil.generar_nave_aleatoria(x, y));

            System.out.println("SIZE: " + disparos_enemigos.getSize());
        }
    }
    /**
     *Inicia la aparición de naves, iniciando el timer.
     */
    private void iniciar_ataque(){

        if(timer_enemigo == null){

            timer_enemigo = new Timer(ATAQUE_ENEMIGO, new AirWarGUI.TimerHandler());
            timer_enemigo.start();

        }
        else{
            if(!timer_enemigo.isRunning()){
                timer_enemigo.restart();
            }
        }
    }

    /**
     * Crea más naves.
     */
    private class TimerHandler implements  ActionListener {
        public void actionPerformed(ActionEvent actionEvent){
            generar_naves_enemigas();
        }
    }

    /**
     *Ciclo que actualiza la ventana.
     */
    public void run()
    {
        try
        {
            while(true)
            {
                Thread.currentThread().sleep(5);
                repaint();
            }
        }
        catch(Exception e){

        }
    }

    private void cargar_disparos_enemigos(){

        int i = 0;

        while (i < 1000){
            disparos_enemigos.addLast(new Municion(50,50,20));
            i++;
        }
    }

    @SuppressWarnings("unchecked")
    private void cambiar_posicion_disparos_enemigos(int x, int y){

        Node<Municion> municion_enemiga = disparos_enemigos.getHead();

        int cambio = 20;

        while (municion_enemiga.getNext() != null){
            municion_enemiga.getData().setX(x + 30);
            municion_enemiga.getData().setY(y + cambio);

            municion_enemiga = municion_enemiga.getNext();
            cambio += 20;
        }
    }



}
