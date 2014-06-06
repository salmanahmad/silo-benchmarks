
public class Test {
    public static void main(String[] args) {
        long start = 0;
        String output = "";

        System.out.println();
        System.out.println("Starting Java.");

        start = System.nanoTime();
        for(int i = 0; i < 100000; i++) {
            output = mandelbrot();
        }

        System.out.println("Java Time: " + ((System.nanoTime() - start) / 1000000));
        System.out.println("\n\n");
        System.out.println(output);
    }

    public static String mandelbrot()
    {   
        StringBuilder buffer = new StringBuilder();

        String chars = " .,:;=|iI+hHOE#$-";
    
        double Im, Re, Zr, Zi, Zr2, Zi2;
        int n;

        for(Im=-1.2; Im<=1.2; Im+=.05)
        {   for(Re=-2; Re<=1; Re+=.03)
            {   
                Zr = Re;
                Zi = Im;
                n = 0;
                for(; n<16; n++)
                {   
                    Zr2 = Zr*Zr;
                    Zi2 = Zi*Zi;
                    if(Zr2+Zi2 > 4) break;
                    Zi = 2*Zr*Zi+Im; Zr = Zr2-Zi2+Re;
                }
            
                buffer.append(chars.charAt(n));
            }
            buffer.append("\n");
        }

        return buffer.toString();
    }
    
}
