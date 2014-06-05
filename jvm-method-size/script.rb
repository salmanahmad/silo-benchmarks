

number_of_methods = 0
total_size = 0

for file in ARGV do
  output = `javap -c #{file}`

  current_method = nil
  last_line = nil

  output.each_line do |line|
    if line.match(/^\s\s[^\s]/) then
      current_method = line.strip
      next
    end

    if line.match(/^\s*$|^}.*/) then
      size = last_line.split(":")[0].strip.to_i
      puts "#{current_method}\t\t\t#{size}"

      number_of_methods += 1
      total_size += size
      next
    end

    last_line = line
  end
end

puts "\n\n\nAverage Count: #{total_size / number_of_methods} (#{total_size}, #{number_of_methods})" 