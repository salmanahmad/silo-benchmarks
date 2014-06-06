

public class Test {
    public static void main(String[] args) {
        long start = 0;
        int output = 0;

        System.out.println();
        System.out.println("Starting Java.");
        start = System.nanoTime();
        for(long l = 0; l < 100000; l++) {
            for(int i = 0; i < 1000; i++) {
                output = test(new Integer(i).toString());
            }
        }

        System.out.println("Java Time: " + ((System.nanoTime() - start) / 1000000));
        //System.out.println("Java Output: " + output);
        System.out.println("\n\n");
    }

    public static int test(String s) {
        return Integer.valueOf(s);
    }
}