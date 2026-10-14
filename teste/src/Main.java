public class Main {

    public static void main(String[] args) {
        testLista();
        System.out.println();
        testListall();
    }

    static void testLista() {
        Lista v = new Lista();

        System.out.println(v.isEmpty());
        System.out.println(v.size());

        v.insertFirst("A");
        v.insertLast("B");
        v.insertLast("D");
        v.insertbefore("C", 2);

        System.out.println(v.isEmpty());
        System.out.println(v.size());

        System.out.println(v.first());
        System.out.println(v.last());
        System.out.println(v.before(1));
        System.out.println(v.after(1));

        System.out.println(v.isFirst("A"));
        System.out.println(v.isLast("D"));

        v.replaceElement("X", 1);
        System.out.println(v.before(2));

        v.swapelement(0, 3);
        System.out.println(v.first());
        System.out.println(v.last());

        v.remove(0);
        System.out.println(v.first());
        System.out.println(v.size());

        v.remove(1);
        System.out.println(v.last());
        System.out.println(v.size());
    }

    static void testListall() {
        Listall v = new Listall();

        System.out.println(v.isEmpty());
        System.out.println(v.size());

        v.insertFirst("A");
        v.insertLast("B");
        v.insertLast("D");
        v.insertbefore("C", 2);

        System.out.println(v.isEmpty());
        System.out.println(v.size());

        System.out.println(v.first());
        System.out.println(v.last());
        System.out.println(v.before(1));
        System.out.println(v.after(1));

        System.out.println(v.isFirst("A"));
        System.out.println(v.isLast("D"));

        v.replaceElement("X", 1);
        System.out.println(v.before(2));

        v.swapelement(0, 3);
        System.out.println(v.first());
        System.out.println(v.last());

        v.remove(0);
        System.out.println(v.first());
        System.out.println(v.size());

        v.remove(1);
        System.out.println(v.last());
        System.out.println(v.size());
    }
}

class Lista {
    public Object[] array;
    public int size;
    public int head;

    public Lista(){
        array = new Object[10];
        size = 0;
        head = 0;
    }


    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isFirst(Object o){
        return o == array[head];
    }
    public boolean isLast(Object o){
        int indiceFisico = (head + size - 1) % array.length;
        return o == array[indiceFisico];
    }

    public Object first(){
        return array[head];
    }
    public Object last(){
        int indiceFisico = (head + size - 1) % array.length;
        return array[indiceFisico];
    }

    public Object before(int index){
        if (index <= 0 || index >= size) {
            throw new ListaVaziaException("Posição inválida!");
        }
        int indiceFisico = (head + index - 1) % array.length;
        return array[indiceFisico];
    }
    public Object after(int index){
        if (index < 0 || index >= size - 1) {
            throw new ListaVaziaException("Posição inválida!");
        }
        int indiceFisico = (head + index + 1) % array.length;
        return array[indiceFisico];
    }

    public void replaceElement(Object o, int index){
        if (index < 0 || index >= size) {
            throw new ListaVaziaException("Posição inválida!");
        }
        int indiceFisico = (head + index) % array.length;
        array[indiceFisico] = o;
    }
    public void swapelement(int i, int j){
        int indiceFisicoI = (head + i) % array.length;
        int indiceFisicoJ = (head + j) % array.length;
        Object temp = array[indiceFisicoI];
        array[indiceFisicoI] = array[indiceFisicoJ];
        array[indiceFisicoJ] = temp;
    }

    public void insertbefore(Object o, int index){
        if (index < 0 || index > size) {
            throw new ListaVaziaException("Posição inválida!");
        }
        if (size == array.length) {
            aumentarArray();
        }
        if (index == 0){
            head = (head - 1 + array.length) % array.length;
            array[head] = o;
            size++;
            return;
        }
        for (int i = size; i > index; i--) {
            int destino = (head + i) % array.length;
            int origem = (head + i - 1) % array.length;
            array[destino] = array[origem];
        }
        int indiceFisico = (head + index) % array.length;
        array[indiceFisico] = o;
        size++;
    }

    public void insertAfter(Object o, int index){
        insertbefore(o, index + 1);
    }

    public void insertFirst(Object o){
        insertbefore(o, 0);
    }

    public void insertLast(Object o){
        insertbefore(o, size);
    }

    public void remove(int index){
        if (index < 0 || index >= size) {
            throw new ListaVaziaException("Posição inválida!");
        }
        if (index == 0){
            head = (head + 1) % array.length;
            size--;
            return;
        }
        for (int i = index; i < size - 1; i++) {
            int destino = (head + i) % array.length;
            int origem = (head + i + 1) % array.length;
            array[destino] = array[origem];
        }
        size--;
    }

    public int size(){
        return size;
    }

    private void aumentarArray(){
        Object[] novoArray = new Object[array.length * 2];
        for (int i = 0; i < size; i++) {
            int indiceFisico = (head + i) % array.length;
            novoArray[i] = array[indiceFisico];
        }
        array = novoArray;
        head = 0;
    }

    public class ListaVaziaException extends RuntimeException {
        public ListaVaziaException(String mensagem) {
            super(mensagem);
        }
    }
}

class Listall {
    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Object dado;
        Node next;
        Node prev;
        public Node(Object d){
            this.dado = d;
            this.next = null;
            this.prev = null;
        }
    }

    public Listall(){
        head = new Node(null);
        tail = new Node(null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isFirst(Object o){
        return o == head.next.dado;
    }
    public boolean isLast(Object o){
        return o == tail.prev.dado;
    }

    public Object first(){
        return head.next.dado;
    }
    public Object last(){
        return tail.prev.dado;
    }

    private Node search(int index){
        Node aux = head.next;
        for (int i = 0; i < index; i++) {
            aux = aux.next;
        }
        return aux;
    }

    public Object before(int index){
        if (index <= 0 || index >= size) {
            throw new ListaVaziaException("Posição inválida!");
        }
        return search(index).prev.dado;
    }
    public Object after(int index){
        if (index < 0 || index >= size - 1) {
            throw new ListaVaziaException("Posição inválida!");
        }
        return search(index).next.dado;
    }

    public void replaceElement(Object o, int index){
        if (index < 0 || index >= size) {
            throw new ListaVaziaException("Posição inválida!");
        }
        search(index).dado = o;
    }
    public void swapelement(int i, int j){
        Node noI = search(i);
        Node noJ = search(j);
        Object temp = noI.dado;
        noI.dado = noJ.dado;
        noJ.dado = temp;
    }

    public void insertbefore(Object o, int index){
        if (index < 0 || index > size) {
            throw new ListaVaziaException("Posição inválida!");
        }
        Node novo = new Node(o);
        Node atual = search(index);
        Node anterior = atual.prev;

        novo.next = atual;
        novo.prev = anterior;
        anterior.next = novo;
        atual.prev = novo;

        size++;
    }

    public void insertAfter(Object o, int index){
        insertbefore(o, index + 1);
    }

    public void insertFirst(Object o){
        insertbefore(o, 0);
    }

    public void insertLast(Object o){
        insertbefore(o, size);
    }

    public void remove(int index){
        if (index < 0 || index >= size) {
            throw new ListaVaziaException("Posição inválida!");
        }

        Node atual = search(index);
        Node anterior = atual.prev;
        Node proximo = atual.next;

        anterior.next = proximo;
        proximo.prev = anterior;

        size--;
    }

    public int size(){
        return size;
    }

    public class ListaVaziaException extends RuntimeException {
        public ListaVaziaException(String mensagem) {
            super(mensagem);
        }
    }
}