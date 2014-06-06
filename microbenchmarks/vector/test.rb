
def test(s)
  s.to_i
end

puts
puts "Starting Ruby."

start = Time.now
list = []
100000.times do
  1000.times do |i|
    list.push(i)
  end

  1000.times do |i|
    list.pop()
  end
end


puts "Ruby Time: #{(Time.now - start)}"
puts
puts