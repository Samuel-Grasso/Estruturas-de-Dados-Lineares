public class Sequencia {
    private Position[] array;
    private int size;
    private int head;

    private class Posicao implements Position {
        Object elemento;
        int indice;

        Posicao(Object elemento, int indice){
            this.elemento = elemento;
            this.indice = indice;
        }

        public Object Element(){
            return elemento;
        }
    }

    public Sequencia(){
        array = new Position[1];
        size = 0;
        head = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    private int indiceFisico(int rank){
        return (head + rank) % array.length;
    }

    public int rankOf(Position p){
        Posicao posicao = (Posicao) p;
        return (posicao.indice - head + array.length) % array.length;
    }

    public Position atRank(int rank){
        if (rank < 0 || rank >= size) {
            throw new SequenciaVaziaException("Posição inválida!");
        }
        return array[indiceFisico(rank)];
    }

    public Object elemAtRank(int rank){
        return atRank(rank).Element();
    }

    public Position first(){
        if (isEmpty()) {
            throw new SequenciaVaziaException("Sequência vazia!");
        }
        return array[head];
    }

    public Position last(){
        if (isEmpty()) {
            throw new SequenciaVaziaException("Sequência vazia!");
        }
        return array[indiceFisico(size - 1)];
    }

    public Position before(Position p){
        int rank = rankOf(p);
        if (rank <= 0) {
            throw new SequenciaVaziaException("Não existe posição anterior!");
        }
        return array[indiceFisico(rank - 1)];
    }

    public Position after(Position p){
        int rank = rankOf(p);
        if (rank >= size - 1) {
            throw new SequenciaVaziaException("Não existe próxima posição!");
        }
        return array[indiceFisico(rank + 1)];
    }

    public Object replaceElement(Position p, Object o){
        Posicao posicao = (Posicao) p;
        Object antigo = posicao.elemento;
        posicao.elemento = o;
        return antigo;
    }

    public void swapElements(Position p, Position q){
        Posicao posicaoP = (Posicao) p;
        Posicao posicaoQ = (Posicao) q;
        Object temp = posicaoP.elemento;
        posicaoP.elemento = posicaoQ.elemento;
        posicaoQ.elemento = temp;
    }

    public Object replaceAtRank(int rank, Object o){
        return replaceElement(atRank(rank), o);
    }

    public Position insertAtRank(int rank, Object o){
        if (rank < 0 || rank > size) {
            throw new SequenciaVaziaException("Posição inválida!");
        }
        if (size == array.length) {
            aumentarArray();
        }
        if (rank == 0){
            head = (head - 1 + array.length) % array.length;
            Posicao novaPosicao = new Posicao(o, head);
            array[head] = novaPosicao;
            size++;
            return novaPosicao;
        }
        for (int i = size; i > rank; i--) {
            int destino = indiceFisico(i);
            int origem = indiceFisico(i - 1);
            Posicao posicao = (Posicao) array[origem];
            posicao.indice = destino;
            array[destino] = posicao;
        }
        int indice = indiceFisico(rank);
        Posicao novaPosicao = new Posicao(o, indice);
        array[indice] = novaPosicao;
        size++;
        return novaPosicao;
    }

    public void removeAtRank(int rank){
        if (rank < 0 || rank >= size) {
            throw new SequenciaVaziaException("Posição inválida!");
        }
        if (size <= array.length / 3) {
            diminuirArray();
        }
        if (rank == 0){
            head = (head + 1) % array.length;
            size--;
            return;
        }
        for (int i = rank; i < size - 1; i++) {
            int destino = indiceFisico(i);
            int origem = indiceFisico(i + 1);
            Posicao posicao = (Posicao) array[origem];
            posicao.indice = destino;
            array[destino] = posicao;
        }
        size--;
    }

    public Position insertBefore(Position p, Object o){
        return insertAtRank(rankOf(p), o);
    }

    public Position insertAfter(Position p, Object o){
        return insertAtRank(rankOf(p) + 1, o);
    }

    public Position insertFirst(Object o){
        return insertAtRank(0, o);
    }

    public Position insertLast(Object o){
        return insertAtRank(size, o);
    }

    public void remove(Position p){
        removeAtRank(rankOf(p));
    }

    private void aumentarArray(){
        Position[] novoArray = new Position[array.length * 2];
        for (int i = 0; i < size; i++) {
            Posicao posicao = (Posicao) array[indiceFisico(i)];
            posicao.indice = i;
            novoArray[i] = posicao;
        }
        array = novoArray;
        head = 0;
    }

    private void diminuirArray(){
        Position[] novoArray = new Position[array.length / 2];
        for (int i = 0; i < size; i++) {
            Posicao posicao = (Posicao) array[indiceFisico(i)];
            posicao.indice = i;
            novoArray[i] = posicao;
        }
        array = novoArray;
        head = 0;
    }

    public class SequenciaVaziaException extends RuntimeException {
        public SequenciaVaziaException(String mensagem) {
            super(mensagem);
        }
    }
}