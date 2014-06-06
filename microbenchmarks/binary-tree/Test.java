

public class Test {
    public static class Node {
        String value;
        Node left;
        Node right;
        
        public Node(String value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        long start = 0;
        int output = 0;

        System.out.println();
        System.out.println("Starting Java.");

        start = System.nanoTime();
        for(long l = 0; l < 100000000; l++) {
            new Node("0", new Node("1", null, new Node("3", null, null)), new Node("2", null, null));
        }

        System.out.println("Java Time: " + ((System.nanoTime() - start) / 1000000));
        System.out.println("\n\n");
    }

}