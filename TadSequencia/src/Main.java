public class Main {

    public static void main(String[] args) {
        testSequencia();
        System.out.println();
        testSequenciall();
    }

    static void testSequencia() {
        Sequencia v = new Sequencia();

        System.out.println(v.isEmpty());
        System.out.println(v.size());

        v.insertAtRank(0, "A");
        v.insertAtRank(1, "B");
        v.insertAtRank(2, "D");
        v.insertAtRank(2, "C");

        System.out.println(v.isEmpty());
        System.out.println(v.size());

        System.out.println(v.elemAtRank(0));
        System.out.println(v.elemAtRank(1));
        System.out.println(v.elemAtRank(2));
        System.out.println(v.elemAtRank(3));

        System.out.println(v.replaceAtRank(1, "X"));
        System.out.println(v.elemAtRank(1));

        v.removeAtRank(0);
        System.out.println(v.elemAtRank(0));
        System.out.println(v.size());

        v.removeAtRank(1);
        System.out.println(v.elemAtRank(1));
        System.out.println(v.size());
    }

    static void testSequenciall() {
        Sequenciall v = new Sequenciall();

        System.out.println(v.isEmpty());
        System.out.println(v.size());

        v.insertAtRank(0, "A");
        v.insertAtRank(1, "B");
        v.insertAtRank(2, "D");
        v.insertAtRank(2, "C");

        System.out.println(v.isEmpty());
        System.out.println(v.size());

        System.out.println(v.elemAtRank(0));
        System.out.println(v.elemAtRank(1));
        System.out.println(v.elemAtRank(2));
        System.out.println(v.elemAtRank(3));

        System.out.println(v.replaceAtRank(1, "X"));
        System.out.println(v.elemAtRank(1));

        v.removeAtRank(0);
        System.out.println(v.elemAtRank(0));
        System.out.println(v.size());

        v.removeAtRank(1);
        System.out.println(v.elemAtRank(1));
        System.out.println(v.size());
    }
}