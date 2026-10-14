import java.util.ArrayList;
import java.util.Iterator;

public class ArvoreBinaria {
    private node raiz;
    private int size;

    public class node{
        public node pai;
        public node filhoE;
        public node filhoD;
        public Object dado;
        public node(Object dado) {
            this.dado = dado;
            this.pai = null;
            this.filhoE = null;
            this.filhoD = null;
        }
    }

    public ArvoreBinaria() {
        raiz = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int height(){
        if (isEmpty()) {
            throw new ArvoreException("Árvore vazia!");
        }
        return height(raiz);
    }

    private int height(node v){
        if (isExternal(v)) {
            return 0;
        }
        int h = 0;
        Iterator<node> it = children(v);
        while (it.hasNext()) {
            h = Math.max(h, height(it.next()));
        }
        return 1 + h;
    }

    public Iterator<Object> elements(){
        ArrayList<Object> lista = new ArrayList<>();
        Iterator<node> it = nos();
        while (it.hasNext()) {
            lista.add(it.next().dado);
        }
        return lista.iterator();
    }

    public Iterator<node> nos(){
        return preOrder();
    }

    public node root(){
        if (isEmpty()) {
            throw new ArvoreException("Árvore vazia!");
        }
        return raiz;
    }

    public node parent(node v){
        if (isRoot(v)) {
            throw new ArvoreException("A raiz não possui pai!");
        }
        return v.pai;
    }

    public Iterator<node> children(node v){
        ArrayList<node> lista = new ArrayList<>();
        if (v.filhoE != null) {
            lista.add(v.filhoE);
        }
        if (v.filhoD != null) {
            lista.add(v.filhoD);
        }
        return lista.iterator();
    }

    public boolean isInternal(node v){
        return v.filhoE != null || v.filhoD != null;
    }

    public boolean isExternal(node v){
        return v.filhoE == null && v.filhoD == null;
    }

    public boolean isRoot(node v){
        return v == raiz;
    }

    public int depth(node v){
        if (isRoot(v)) {
            return 0;
        }
        return 1 + depth(parent(v));
    }

    public Object replace(node v, Object o){
        Object antigo = v.dado;
        v.dado = o;
        return antigo;
    }

    public node leftChild(node v){
        if (!hasLeft(v)) {
            throw new ArvoreException("O nó não possui filho esquerdo!");
        }
        return v.filhoE;
    }

    public node rightChild(node v){
        if (!hasRight(v)) {
            throw new ArvoreException("O nó não possui filho direito!");
        }
        return v.filhoD;
    }

    public boolean hasLeft(node v){
        return v.filhoE != null;
    }

    public boolean hasRight(node v){
        return v.filhoD != null;
    }

    public node insertRoot(Object o){
        if (!isEmpty()) {
            throw new ArvoreException("A árvore já possui raiz!");
        }
        raiz = new node(o);
        size++;
        return raiz;
    }

    public node insertLeft(node v, Object o){
        if (hasLeft(v)) {
            throw new ArvoreException("O nó já possui filho esquerdo!");
        }
        node novo = new node(o);
        novo.pai = v;
        v.filhoE = novo;
        size++;
        return novo;
    }

    public node insertRight(node v, Object o){
        if (hasRight(v)) {
            throw new ArvoreException("O nó já possui filho direito!");
        }
        node novo = new node(o);
        novo.pai = v;
        v.filhoD = novo;
        size++;
        return novo;
    }

    public Object remove(node v){
        if (hasLeft(v) && hasRight(v)) {
            throw new ArvoreException("Nó com dois filhos não pode ser removido!");
        }
        node filho = hasLeft(v) ? v.filhoE : v.filhoD;
        if (filho != null) {
            filho.pai = v.pai;
        }
        if (isRoot(v)) {
            raiz = filho;
        } else if (v == v.pai.filhoE) {
            v.pai.filhoE = filho;
        } else {
            v.pai.filhoD = filho;
        }
        size--;
        return v.dado;
    }

    public Iterator<node> preOrder(){
        ArrayList<node> lista = new ArrayList<>();
        preOrder(raiz, lista);
        return lista.iterator();
    }

    private void preOrder(node v, ArrayList<node> lista){
        if (v == null) {
            return;
        }
        lista.add(v);
        preOrder(v.filhoE, lista);
        preOrder(v.filhoD, lista);
    }

    public Iterator<node> inOrder(){
        ArrayList<node> lista = new ArrayList<>();
        inOrder(raiz, lista);
        return lista.iterator();
    }

    private void inOrder(node v, ArrayList<node> lista){
        if (v == null) {
            return;
        }
        inOrder(v.filhoE, lista);
        lista.add(v);
        inOrder(v.filhoD, lista);
    }

    public Iterator<node> postOrder(){
        ArrayList<node> lista = new ArrayList<>();
        postOrder(raiz, lista);
        return lista.iterator();
    }

    private void postOrder(node v, ArrayList<node> lista){
        if (v == null) {
            return;
        }
        postOrder(v.filhoE, lista);
        postOrder(v.filhoD, lista);
        lista.add(v);
    }

    public class ArvoreException extends RuntimeException {
        public ArvoreException(String mensagem) {
            super(mensagem);
        }
    }
}