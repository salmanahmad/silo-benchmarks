

def mandelbrot()
    chars = " .,:;=|iI+hHOE#$-";
    output = ""

    #var Im, Re, Zr, Zi, Zr2, Zi2;
    #var n;

    im=-1.2
    while im<=1.2
      re=-2
      while re<=1
        zr = re;
        zi = im;
        n = 0;
        while n < 16 do
          zr2 = zr*zr;
          zi2 = zi*zi;
          if(zr2+zi2 > 4) then break end
          zi = 2*zr*zi+im; zr = zr2-zi2+re;
          n += 1
        end

        output += chars[n].to_s;

        re+=0.03
      end
      output += "\n"
      im+=0.05
    end
    
    


    return output;
end

puts
puts "Starting Ruby."

start = Time.now
output = ""
10000.times do
  output = mandelbrot
end


puts "Ruby Time: #{(Time.now - start)}"
puts output
puts
puts