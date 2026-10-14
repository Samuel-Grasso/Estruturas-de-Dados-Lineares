public class Vectorll {
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

    public Vectorll(){
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

    private Node search(int rank){
        Node aux = head.next;
        for (int i = 0; i < rank; i++) {
            aux = aux.next;
        }
        return aux;
    }

    public void insertAtRank(int rank, Object o){
        if (rank < 0 || rank > size) {
            throw new VetorVazioException("Posição inválida!");
        }

        Node novo = new Node(o);
        Node atual = search(rank);
        Node anterior = atual.prev;

        novo.next = atual;
        novo.prev = anterior;
        anterior.next = novo;
        atual.prev = novo;

        size++;
    }

    public Object replaceAtRank(int rank, Object o){
        if (rank < 0 || rank >= size) {
            throw new VetorVazioException("Posição inválida!");
        }
        Node atual = search(rank);
        Object antigo = atual.dado;
        atual.dado = o;
        return antigo;
    }

    public void removeAtRank(int rank){
        if (rank < 0 || rank >= size) {
            throw new VetorVazioException("Posição inválida!");
        }

        Node atual = search(rank);
        Node anterior = atual.prev;
        Node proximo = atual.next;

        anterior.next = proximo;
        proximo.prev = anterior;

        size--;
    }

    public Object elemAtRank(int rank){
        if (rank < 0 || rank >= size) {
            throw new VetorVazioException("Posição inválida!");
        }
        return search(rank).dado;
    }

    public class VetorVazioException extends RuntimeException {
        public VetorVazioException(String mensagem) {
            super(mensagem);
        }
    }
}