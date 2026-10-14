
public class PilhaListaE {
    private Node head;
    private int size;
    private class Node {
        int dado;
        Node next;
        public Node(int d){
             this.dado = d;
             this.next = null;
        }

    }
    public PilhaListaE(){
        this.head = null;
        this.size = 0;
    }
    public void Push(int dado){
        Node novo = new Node(dado);
        novo.next = head;
        head = novo;
        size++;
    }
    public void Pop(){
        if (isEmpty()) {
            throw new PilhaVaziaException("A pilha esta vazia!");
        }
        Node novohead = head.next;
        head.next = null;
        head = novohead;
        size--;
    }
    public int size(){
        return size;
    }

    public int top(){
        if (isEmpty()) {
            throw new PilhaVaziaException("A pilha esta vazia!");
        }
        return head.dado;
    }
    public boolean isEmpty(){
        return head == null;
    }

    public class PilhaVaziaException extends RuntimeException {
        public PilhaVaziaException(String mensagem) {
            super(mensagem);
        }
    }

}
