
def test(s)
  s.to_i
end

puts
puts "Starting Ruby."

start = Time.now
list = {}
100000.times do
  1000.times do |i|
    list[i] = ""
    list.delete(i)
  end
end


puts "Ruby Time: #{(Time.now - start)}"
puts
puts