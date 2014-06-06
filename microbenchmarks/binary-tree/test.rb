
class Node
  attr_accessor :value
  attr_accessor :left
  attr_accessor :right
  
  def initialize(value, left, right)
    self.value = value
    self.left = left
    self.right = right
  end
end


puts
puts "Starting Ruby."

start = Time.now
list = {}
100000000.times do
  Node.new("0", Node.new("1", nil, Node.new("3", nil, nil)), Node.new("2", nil, nil))
end


puts "Ruby Time: #{(Time.now - start)}"
puts
puts