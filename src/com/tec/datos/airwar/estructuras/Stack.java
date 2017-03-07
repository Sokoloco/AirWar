package com.tec.datos.airwar.estructuras;


public class Stack<S> extends List {

    private Node head;

    public void push(S element){
        this.addFirst(element);
    }

    public void pop(){
        this.removeHead();
    }

    public Node peek(){
        return this.head;
    }

}
