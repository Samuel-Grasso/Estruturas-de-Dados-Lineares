public class Lista {
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

