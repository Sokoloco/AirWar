package com.tec.datos.airwar.estructuras;

public class Queue<Q> extends List {

    private Node head;

    public void enqueue(Q element){
        this.addLast(element);
    }

    public void dequeue(){
        this.removeHead();
    }

    public Node peek(){
        return head;
    }

}
