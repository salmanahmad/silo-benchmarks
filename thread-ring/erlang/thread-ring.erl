require "fiber"
require "benchmark"

def foo
  while true
    Fiber.yield
  end
end



duration = Benchmark.measure do
  fiber = Fiber.new do
    foo
  end

  i = 0
  while i < 1_000_000 do
    i += 1
    fiber.resume
  end
end

# User, System, User + System, Real
puts duration
