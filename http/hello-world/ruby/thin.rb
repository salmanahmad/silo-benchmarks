
require 'rack'
require 'thin'

class HelloWorld
  def call(env)
    [200, {"Content-Type" => "text/plain", "Connection" => "Keep-Alive"}, "Hello, World!"]
  end
end

puts "Ruby (Thin) - Running on port 8100"

Rack::Handler::Thin.run HelloWorld.new, :Port => 8100

