package com.tec.datos.airwar.estructuras;


import java.util.NoSuchElementException;

public class Stack<S>{

    private Node head;

    public void push(S element){

        if (is_empty()){
            head = new Node(element);
        }else {
            Node new_node = new Node(element);

            new_node.setNext(head);
            head = new_node;
        }
    }

    public void pop() throws Exception{
       if (is_empty()){
           throw new NoSuchElementException("El nodo que quiere accesar no existe");
       }else {
           head = head.getNext();
       }
    }

    public boolean is_empty(){
        return head == null;
    }

}
