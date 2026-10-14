public class Vector {
    public Object[] array;
    public int size;
    public int head;

    public Vector(){
        array = new Object[1];
        size = 0;
        head = 0;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void insertAtRank(int r, Object o){
        if (r < 0 || r > size) { // checa se o indice passado ta certo
            throw new VetorVazioException("Posição inválida!");
        }
        if (size == array.length) {
            aumentarArray();
        }
        if (r == 0){
            head = (head - 1 + array.length) % array.length;
            array[head] = o; // no array circular move o head um espaço para tras
            size++; // e adiciona o numero no espaço aberto sendo o novo head
            return;
        }
        for (int i = size; i > r; i--) { // puxa do fim do array ate o indice desejado
            int destino = (head + i) % array.length;
            int origem = (head + i - 1) % array.length;
            array[destino] = array[origem]; // e copia o anterior para o proximo
        }  // ate abrir um espaço na posicao desejada
        int indiceFisico = (head + r) % array.length;
        array[indiceFisico] = o; //e ai copia no indice agr cm o espaço livre o objeto desejado
        size++;
    }

    public Object replaceAtRank(int r, Object o){
        if (r < 0 || r >= size) {
            throw new VetorVazioException("Posição inválida!");
        }
        int indiceFisico = (head + r) % array.length;
        Object antigo = array[indiceFisico];
        array[indiceFisico] = o;
        return antigo;
    }

    public void removeAtRank(int r){
        if (r < 0 || r >= size) {
            throw new VetorVazioException("Posição inválida!");
        }
        if (size <= array.length / 3) {
            diminuirArray();
        }
        if (r == 0){
            head = (head + 1) % array.length;
            size--;
            return;
        }
        for (int i = r; i < size - 1; i++) {
            int destino = (head + i) % array.length;
            int origem = (head + i + 1) % array.length;
            array[destino] = array[origem];
        }
        size--;
    }

    public Object elemAtRank(int r){
        if (r < 0 || r >= size) {
            throw new VetorVazioException("Posição inválida!");
        }
        int indiceFisico = (head + r) % array.length;
        return array[indiceFisico];
    }

    private void aumentarArray(){
        Object[] novoArray = new Object[array.length * 2]; //dobra o array
        for (int i = 0; i < size; i++) {
            novoArray[i] = elemAtRank(i); // pega o indice fisico de cada numero
        } // e copia em ordem no novo array
        array = novoArray;
        head = 0; // reseta o head pq o novo array esta ordenado
    }

    private void diminuirArray(){
        Object[] novoArray = new Object[array.length / 2]; //divide o array
        for (int i = 0; i < size; i++) {
            novoArray[i] = elemAtRank(i); // pega o indice fisico de cada numero
        } // e copia em ordem no novo array
        array = novoArray;
        head = 0; // reseta o head pq o novo array esta ordenado
    }

    public class VetorVazioException extends RuntimeException {
        public VetorVazioException(String mensagem) {
            super(mensagem);
        }
    }
}

