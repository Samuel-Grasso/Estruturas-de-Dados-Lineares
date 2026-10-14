import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        ArvoreBinaria t = new ArvoreBinaria();

        System.out.println(t.isEmpty());
        System.out.println(t.size());

        ArvoreBinaria.node soma = t.insertRoot("+");
        ArvoreBinaria.node mult1 = t.insertLeft(soma, "*");
        ArvoreBinaria.node mult2 = t.insertRight(soma, "*");
        ArvoreBinaria.node dois = t.insertLeft(mult1, "2");
        ArvoreBinaria.node menos = t.insertRight(mult1, "-");
        ArvoreBinaria.node a = t.insertLeft(menos, "a");
        t.insertRight(menos, "1");
        t.insertLeft(mult2, "3");
        t.insertRight(mult2, "b");

        System.out.println(t.isEmpty());
        System.out.println(t.size());
        System.out.println(t.height());

        System.out.println(t.root().dado);
        System.out.println(t.parent(menos).dado);
        System.out.println(t.leftChild(mult1).dado);
        System.out.println(t.rightChild(mult1).dado);
        System.out.println(t.hasLeft(dois));
        System.out.println(t.hasRight(menos));

        System.out.println(t.isRoot(soma));
        System.out.println(t.isRoot(menos));
        System.out.println(t.isInternal(menos));
        System.out.println(t.isExternal(a));

        System.out.println(t.depth(soma));
        System.out.println(t.depth(menos));
        System.out.println(t.depth(a));

        imprimirNos(t.children(mult1));
        imprimirNos(t.preOrder());
        imprimirNos(t.inOrder());
        imprimirNos(t.postOrder());
        imprimirNos(t.nos());
        imprimirElementos(t.elements());

        System.out.println(t.replace(a, "k"));
        System.out.println(a.dado);

        System.out.println(t.remove(dois));
        System.out.println(t.size());
        System.out.println(t.hasLeft(mult1));

        System.out.println(t.remove(mult1));
        System.out.println(t.size());
        System.out.println(t.parent(menos).dado);
        System.out.println(t.leftChild(soma).dado);
        System.out.println(t.depth(menos));

        try {
            t.remove(soma);
        } catch (ArvoreBinaria.ArvoreException e) {
            System.out.println(e.getMessage());
        }
    }

    static void imprimirNos(Iterator<ArvoreBinaria.node> it) {
        while (it.hasNext()) {
            System.out.print(it.next().dado + " ");
        }
        System.out.println();
    }

    static void imprimirElementos(Iterator<Object> it) {
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
    }
}