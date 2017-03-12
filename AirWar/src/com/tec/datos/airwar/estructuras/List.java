package com.tec.datos.airwar.estructuras;



public class List<T> {

    private Node head;

    private int size;

    public List(){

        this.head = null;
        this.size = 0;
    }

    /**
     * Revisa si la lista esta vacia.
     * @return si la lista esta vacia.
     */
    public boolean isEmpty(){
        return this.head == null;
    }

    /**
     * Añade un nodo al inicio.
     * @param data dato del nodo.
     */
    public void addFirst(T data){

        Node newNode = new Node(data);

        if(isEmpty()){
            setHead(newNode);
        }else{
            newNode.setNext(getHead());
            setHead(newNode);
        }
        size++;
    }

    /**
     * Añade un nuevo nodo al final de la lista..
     * @param data datos del nodo.
     */
    public void addLast(T data){

        Node newNode = new Node(data);

        if(isEmpty()){
            setHead(newNode);
        }else{
            Node current = this.head;

            while(current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    /**
     * remueve la cabeza
     */
    public void removeHead(){
        setHead(getHead().getNext());
        size--;
    }


    /**
     * Limpia la lista.
     */
    public void clearList(){

        Node current = this.head;

        while (current.getNext() != null){
            current.setData(null);
            current = current.getNext();
        }
    }

    /**
     * imprime la lista.
     */
    public void printList() {
        Node current = this.head;
        while (current != null) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
    }

    public boolean contains(T element){

        Node current = this.head;

        while (current.getNext() != null){
            if(current.getData() == element){
                return true;
            }else{
                current = current.getNext();
            }
        }
        return false;
    }

    public Node getHead() {
        return head;
    }

    public int getSize() {
        return size;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getNode(T data){

        Node current = this.head;

        while (current.getData() != null){

            if(current.getData().equals(data)){
                return current;
            }else {
                current = current.getNext();
            }
        }

        return null;
    }

    public void remove(Node node){
        Node current = head;

        while (current.getData() != null){
            if (current.getNext().getData() == node.getData() || current.getNext().getData().equals(node.getData())){
                current.setNext(current.getNext().getNext());
                return;
            }else {
                current = current.getNext();
            }
        }
    }

}
