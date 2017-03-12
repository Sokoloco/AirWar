package com.tec.datos.airwar.estructuras;

public class Queue<Q> {

    private Node head;

    public void enqueue(Q element){

        Node<Q> current_node = head;

        if (is_empty()){
            head = new Node(element);
        }else {

            while (current_node.getNext() != null){
                current_node = current_node.getNext();
            }

            current_node.setNext(new Node(element));
        }

    }

    public void dequeue(){
        head = head.getNext();
    }

    public Node<Q> get_head(){
        return head;
    }

    public boolean is_empty(){
        return head == null;
    }

}
