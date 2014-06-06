
def fib(n)
  if n <= 2 then
    return n
  else
    return fib(n-1) + fib(n-2);
  end
end

puts
puts "Starting Ruby."

start = Time.now
list = {}
100000.times do
  fib(20)
end


puts "Ruby Time: #{(Time.now - start)}"
puts
puts