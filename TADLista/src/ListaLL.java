
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
 