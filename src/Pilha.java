public class Pilha {
    public int[] pilha;
    public int ponteiroVermelho;
    public int ponteiroPreto;
    public int size;

    public Pilha() {
        pilha = new int[10];
        ponteiroVermelho = -1;
        ponteiroPreto = 10;
        size = 0;
    }

    public boolean isEmptyVermelho() {
        return ponteiroVermelho == -1;
    }
    public boolean isEmptyPreto() {
        return ponteiroPreto == pilha.length;
    }

    public void pushVermelho(int valor){
        if (ponteiroVermelho + 1 == ponteiroPreto) {
            aumentarArray();
        }
        pilha[ponteiroVermelho + 1] = valor;
        ponteiroVermelho++;
        size++;
    }

    public void popVermelho() throws Exception {
        if (isEmptyVermelho()){
            throw new PilhaVaziaException("A pilha esta vazia!");
        }
        ponteiroVermelho--;
        size--;
        if (size <= pilha.length / 3) {
            diminuirArray();
        }
    }

    public int topVermelho() throws Exception {
        if (isEmptyVermelho()){
            throw new PilhaVaziaException("A pilha esta vazia!");
        }
        return pilha[ponteiroVermelho];
    }

    public void pushPreto(int valor){
        if (ponteiroPreto - 1 == ponteiroVermelho) {
            aumentarArray();
        }
        pilha[ponteiroPreto - 1] = valor;
        ponteiroPreto--;
        size++;
    }

    public void popPreto() throws Exception {
        if (isEmptyPreto()){
            throw new PilhaVaziaException("A pilha esta vazia!");
        }
        ponteiroPreto++;
        size--;
        if (size <= pilha.length / 3) {
            diminuirArray();
        }
    }

    public int topPreto() throws Exception {
        if (isEmptyPreto()){
            throw new PilhaVaziaException("A pilha esta vazia!");
        }
        return pilha[ponteiroPreto];
    }

    public int sizeVermelho() {
        return ponteiroVermelho + 1;
    }
    public int sizePreto() {
        return pilha.length - ponteiroPreto;
    }

    public void aumentarArray(){
        int[] novapilha = new int[pilha.length * 2];
        for (int i = 0; i <= ponteiroVermelho; i++) {
            novapilha[i] = pilha[i];
        }
        int sizepreto = pilha.length - ponteiroPreto;
        for (int i = 1; i <= sizepreto; i++) {
            novapilha[novapilha.length - i] = pilha[pilha.length - i];
        }
        ponteiroPreto = novapilha.length - sizepreto;
        pilha = novapilha;

    }

    public void diminuirArray(){
        if (pilha.length <= 10) {
            return;
        }
        int[] novapilha = new int[pilha.length / 2];
        for (int i = 0; i <= ponteiroVermelho; i++) {
            novapilha[i] = pilha[i];
        }
        int sizepreto = pilha.length - ponteiroPreto;
        for (int i = 1; i <= sizepreto; i++) {
            novapilha[novapilha.length - i] = pilha[pilha.length - i];
        }
        ponteiroPreto = novapilha.length - sizepreto;
        pilha = novapilha;
    }

    public class PilhaVaziaException extends RuntimeException {
        public PilhaVaziaException(String mensagem) {
            super(mensagem);
        }
    }

}
