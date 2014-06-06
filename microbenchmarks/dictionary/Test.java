
import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        long start = 0;
        int output = 0;

        System.out.println();
        System.out.println("Starting Java.");

        start = System.nanoTime();
        HashMap list = new HashMap();
        for(long l = 0; l < 1000000; l++) {
            for(int i = 0; i < 1000; i++) {
                list.put(new Integer(i), "");
                list.remove(new Integer(i));
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