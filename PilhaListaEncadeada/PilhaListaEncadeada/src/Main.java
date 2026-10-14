public class Main {
    public static void main(String[] args) {
        PilhaListaE pilha = new PilhaListaE();
        pilha.Push(25);
        System.out.println(pilha.top());
        pilha.Pop();
        pilha.Push(25);
        pilha.Push(15);
        System.out.println(pilha.top());
        System.out.println(pilha.size());
        pilha.Pop();
        System.out.println(pilha.top());
        System.out.println(pilha.size());
    }
}