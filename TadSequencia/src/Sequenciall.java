public class Sequenciall {
    private Node head;
    private Node tail;
    private int size;

    private class Node implements Position {
        Object elemento;
        Node next;
        Node prev;

        Node(Object elemento){
            this.elemento = elemento;
            this.next = null;
            this.prev = null;
        }

        public Object Element(){
            return elemento;
        }
    }

    public Sequenciall(){
        head = new Node(null);
        tail = new Node(null);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public Position atRank(int rank){
        if (rank < 0 || rank >= size) {
            throw new SequenciaVaziaException("Posição inválida!");
        }
        Node node;
        if (rank <= size / 2) {
            node = head.next;
            for (int i = 0; i < rank; i++) {
                node = node.next;
            }
        } else {
            node = tail.prev;
            for (int i = 0; i < size - rank - 1; i++) {
                node = node.prev;
            }
        }
        return node;
    }

    public int rankOf(Position p){
        Node node = head.next;
        int r = 0;
        while (node != p && node != tail) {
            node = node.next;
            r++;
        }
        return r;
    }

    public Object elemAtRank(int rank){
        return atRank(rank).Element();
    }

    public Position first(){
        if (isEmpty()) {
            throw new SequenciaVaziaException("Sequência vazia!");
        }
        return head.next;
    }

    public Position last(){
        if (isEmpty()) {
            throw new SequenciaVaziaException("Sequência vazia!");
        }
        return tail.prev;
    }

    public Position before(Position p){
        Node node = (Node) p;
        if (node.prev == head) {
            throw new SequenciaVaziaException("Não existe posição anterior!");
        }
        return node.prev;
    }

    public Position after(Position p){
        Node node = (Node) p;
        if (node.next == tail) {
            throw new SequenciaVaziaException("Não existe próxima posição!");
        }
        return node.next;
    }

    public Object replaceElement(Position p, Object o){
        Node node = (Node) p;
        Object antigo = node.elemento;
        node.elemento = o;
        return antigo;
    }

    public void swapElements(Position p, Position q){
        Node nodeP = (Node) p;
        Node nodeQ = (Node) q;
        Object temp = nodeP.elemento;
        nodeP.elemento = nodeQ.elemento;
        nodeQ.elemento = temp;
    }

    public Object replaceAtRank(int rank, Object o){
        return replaceElement(atRank(rank), o);
    }

    public Position insertBefore(Position p, Object o){
        Node atual = (Node) p;
        Node anterior = atual.prev;
        Node novo = new Node(o);

        novo.next = atual;
        novo.prev = anterior;
        anterior.next = novo;
        atual.prev = novo;

        size++;
        return novo;
    }

    public Position insertAfter(Position p, Object o){
        Node atual = (Node) p;
        return insertBefore(atual.next, o);
    }

    public Position insertFirst(Object o){
        return insertBefore(head.next, o);
    }

    public Position insertLast(Object o){
        return insertBefore(tail, o);
    }

    public Position insertAtRank(int rank, Object o){
        if (rank < 0 || rank > size) {
            throw new SequenciaVaziaException("Posição inválida!");
        }
        if (rank == size) {
            return insertLast(o);
        }
        return insertBefore(atRank(rank), o);
    }

    public void remove(Position p){
        Node node = (Node) p;
        Node anterior = node.prev;
        Node proximo = node.next;

        anterior.next = proximo;
        proximo.prev = anterior;

        size--;
    }

    public void removeAtRank(int rank){
        if (rank < 0 || rank >= size) {
            throw new SequenciaVaziaException("Posição inválida!");
        }
        remove(atRank(rank));
    }

    public class SequenciaVaziaException extends RuntimeException {
        public SequenciaVaziaException(String mensagem) {
            super(mensagem);
        }
    }
}