
require 'rack'
require 'puma'
require 'rack/handler/puma.rb'

class HelloWorld
  def call(env)
    [200, {"Content-Type" => "text/plain"}, ["Hello, World!"]]
  end
end

puts "Ruby (Puma) - Running on port 8101"

Rack::Handler::Puma.run HelloWorld.new, :Port => 8101

