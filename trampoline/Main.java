import java.lang.reflect.Method;

class Main {
    public static Integer foo(int n) {
        if(n == 0) {
            return new Integer(1);
        } else {
            Integer i = foo(n - 1);
            return new Integer(i.intValue() + 1);
        }
    }

    public static Integer bar(int n) {
        return new Integer(n);
    }

    public static void main(String[] args) throws Exception {
        Method method = null;

        if(args.length >= 1 && args[0].equals("foo")) {
            method = Main.class.getMethod("foo", Integer.TYPE);
        } else {
            method = Main.class.getMethod("bar", Integer.TYPE);
        }

        for(int i = 0; i < 1000000000; i++) {
            method.invoke(null, new Integer(2));
        }
    }
}