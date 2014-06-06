
function mandelbrot() {
    var chars = " .,:;=|iI+hHOE#$-";
    var output = ""
    var Im, Re, Zr, Zi, Zr2, Zi2;
    var n;

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
        
            output += chars.charAt(n);
        }
        output += "\n"
    }

    return output;
}

console.log("Starting JS.")

var start = new Date()

var list = {}

for(l = 0; l < 100000; l++) {
    var output = mandelbrot();
}


console.log("Javascript Time: " + ((new Date()).getTime() - start.getTime()))

console.log(output);