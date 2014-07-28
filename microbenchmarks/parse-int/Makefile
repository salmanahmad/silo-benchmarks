

all: java, ruby, jruby, js, silo

java:
	javac Test.java; java Test

ruby:
	ruby test.rb

jruby:
	jruby test.rb

js:
	node test.js

silo:
	silo test.silo

erlang:
	erl -compile test.erl
	erl -noshell -s inets start -s test start

clean:
	rm -f *.class
	rm -f *.beam
	rm -f erl_crash.dump