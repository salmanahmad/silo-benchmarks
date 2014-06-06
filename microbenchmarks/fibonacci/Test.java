

public class Test {
    public static void main(String[] args) {
        long start = 0;
        int output = 0;

        System.out.println();
        System.out.println("Starting Java.");

        start = System.nanoTime();

        for(long l = 0; l < 100000; l++) {
            output = fib(20);
        }

        System.out.println("Java Time: " + ((System.nanoTime() - start) / 1000000));
    }

    public static int fib(int n)
    {
        if (n <= 2) 
            return 1;
        else
            return fib(n-1) + fib(n-2);
    }
}