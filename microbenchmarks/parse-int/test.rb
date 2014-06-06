
def test(s)
  s.to_i
end

puts
puts "Starting Ruby."

start = Time.now
100000.times do
  1000.times do |i|
    test(i.to_s)
  end
end


puts "Ruby Time: #{(Time.now - start)}"
puts
puts