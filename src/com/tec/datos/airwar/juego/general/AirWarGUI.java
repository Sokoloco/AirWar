package com.tec.datos.airwar.juego.general;

import com.tec.datos.airwar.estructuras.*;
import com.tec.datos.airwar.juego.torres.Torre;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

public class AirWarGUI extends Canvas implements KeyListener, Runnable {

    private NaveJugador jugador;
    private Nivel nivel;

    private boolean[] keys;
    private BufferedImage fondo;

    private Graphics g_fondo;

    /**
     * Constructor; instancia todas las estructuras y demás datos del juego.
     */
    public AirWarGUI(Nivel nivel) {

        setBackground(Color.black);

        keys = new boolean[5];

        this.nivel = nivel;

        jugador = new NaveJugador(400, 450);

        jugador.get_municion().enqueue(new Municion(jugador.getX(), jugador.getY(), 5));

        this.addKeyListener(this);
        new Thread(this).start();

        setVisible(true);

    }

    /**
     * Dibuja las estadisticas del jugador.
     *
     * @param g graficos en que se va a dibujar.
     */
    private void dibujar_estadisticas_jugador(Graphics g) {

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("Puntaje: " + jugador.get_puntaje(), 25, 40);


        g.setFont(new Font("Arial", Font.BOLD, 20));
        if (jugador.get_vidas() <= 1) {
            g.setColor(Color.RED);
        } else {
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
     *
     * @param ventana La ventana que es actualizada.
     */
    public void update(Graphics ventana) {
        paint(ventana);
    }

    /**
     * Pinta las imagenes adecuadas, cuando desaparecen de la pantalla se pinta sobre ellas.
     *
     * @param ventana la ventana que se actualiza.
     */
    public void paint(Graphics ventana) {

        Graphics2D twoDGraph = (Graphics2D) ventana;

        if (fondo == null) {
            fondo = (BufferedImage) (createImage(getWidth(), getHeight()));
        }

        //El fondo donde se dibuja.
        g_fondo = fondo.createGraphics();

        g_fondo.setColor(Color.BLUE);
        g_fondo.drawString("AirWar ", 25, 50);
        g_fondo.setColor(Color.BLACK);
        g_fondo.fillRect(0, 0, 800, 600);

        dibujar_estadisticas_jugador(g_fondo);

        verificar_accion();
        dibujar_enemigos(nivel.getNaves_enemigas().get_head());
        dibujar_disparos();
        dibujar_torres(nivel.getTorres().getHead());
        verificar_puntaje();

        jugador.draw(g_fondo);
        twoDGraph.drawImage(fondo, null, 0, 0);
    }

    private void verificar_puntaje() {                       //Cuando se pase de nivel se detiene el timer y se genera al jefe. Cuando el jefe es vencido se reinicia el timer y se continua normal.
        if (jugador.get_puntaje() > 3000 && nivel.numero_nivel == 1) {
            //Aqui debe iniciarse un nuevo nivel
            nivel.numero_nivel++;
        }
    }

    /**
     * Dibuja las distintas naves enemigas
     * @param nave la nave que se dibuja.
     */
    @SuppressWarnings("unchecked")
    private void dibujar_enemigos(Node<ObjetoMovil> nave) {

        try {
            nave.getData().draw(g_fondo);

            if (!nave.getData().get_tipo().equals("kamikaze")) {
                dibujar_disparos_enemigos(nave.getData());

                nave.getData().mover("UP");

                detectar_colision(nave,null);
            } else {
                nave.getData().mover_hacia(jugador.getX(), jugador.getY());
            }

            if (nave.getData().getY() >= 600) {
                nivel.getNaves_enemigas().dequeue();
            }

        } catch (NullPointerException npe) {
            //
        }
    }

    /**
     * Dibuja las distintas torres.
     * @param torre la torre que se va a dibujar.
     */
    public void dibujar_torres(Node<Torre> torre) {

        torre.getData().draw(g_fondo);

        dibujar_disparo_torres(torre.getData());

        torre.getData().mover("UP");

        detectar_colision(null, torre);

        if (torre.getData().getY() >= 600){
            nivel.getTorres().removeHead();
        }
    }

    /**
     * Dibuja los disparos de los enemigos, según el tipo que se le indique.
     * @param nave_enemiga la nave a la que se le dibujan los disparos.
     */
    public void dibujar_disparos_enemigos(ObjetoMovil nave_enemiga) {

        nave_enemiga.get_municion().enqueue(new Municion(nave_enemiga.getX() + 20, nave_enemiga.getY() + 40, 5));

        Node<Municion> municion_enemiga = nave_enemiga.get_municion().get_head();

        while (municion_enemiga != null) {
            municion_enemiga.getData().draw(g_fondo);
            municion_enemiga.getData().mover("UP");

            municion_enemiga = municion_enemiga.getNext();
        }
    }

    /**
     * Dibuja los disparos de las torres, estas balas se dirigen hacia el jugador.
     * @param torre la torre a la que se le dibujan los disparos.
     */
    public void dibujar_disparo_torres(Torre torre) {

        torre.get_municion().enqueue(new Municion(torre.getX() + 20, torre.getY() + 40, 5));

        Node<Municion> municion_torre = torre.get_municion().get_head();

        while (municion_torre != null) {
            municion_torre.getData().draw(g_fondo);
            municion_torre.getData().mover_hacia(jugador.getX(), jugador.getY());

            if (municion_torre.getData().getY() > jugador.getY()) {
                municion_torre.getData().mover_hacia(torre.getX(), torre.getY());
                municion_torre.getData().draw(g_fondo, Color.BLACK);
            }
            municion_torre = municion_torre.getNext();
        }

    }

    /**
     * Detecta la colision entre una bala del jugador y un enemigo (Nave o torre), si colisionan estas se eliminan y se le agrega puntaje al jugador.
     * @param nave Nave con la que colisiona.
     * @param torre Torre con la que colisiona.
     */
    public void detectar_colision(Node<ObjetoMovil> nave, Node<Torre> torre) {

        Node<Municion> municion_jugador = jugador.get_municion().get_head();

        while (municion_jugador.getNext() != null) {
            if (torre == null) {
                if (nave.getData().getX() >= municion_jugador.getData().getX() && nave.getData().getX() <= municion_jugador.getData().getX() + 100 && nave.getData().getY() >= municion_jugador.getData().getY() && nave.getData().getY() <= municion_jugador.getData().getY() + 80) {
                    nivel.getNaves_enemigas().dequeue();
                    jugador.get_municion().dequeue();
                    jugador.sumar_puntaje(nave.getData().get_tipo());
                }
            } else if (nave == null) {
                if (torre.getData().getX() >= municion_jugador.getData().getX() && torre.getData().getX() <= municion_jugador.getData().getX() + 100 && torre.getData().getY() >= municion_jugador.getData().getY() && torre.getData().getY() <= municion_jugador.getData().getY() + 80) {
                    nivel.getTorres().removeHead();
                    jugador.get_municion().dequeue();
                    jugador.sumar_puntaje(torre.getData().get_tipo());
                }
            }
            municion_jugador = municion_jugador.getNext();
        }
    }

    /**
     * Dibuja los disparos del jugador.
     */
    @SuppressWarnings("unchecked")
    private void dibujar_disparos(){

        Node<Municion> municion_jugador = jugador.get_municion().get_head();

        while (municion_jugador.getNext() != null) {

            municion_jugador.getData().draw(g_fondo);
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
            jugador.get_municion().enqueue(disparo_jugador);

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
