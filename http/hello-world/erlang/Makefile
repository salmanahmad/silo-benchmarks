
hello_world:
	touch hello_world.beam
	rm hello_world.beam
	erl -compile hello_world.erl
	erl -noshell -s inets start -s hello_world start
	#erl -compile server.erl
	#erl -compile ybed_sup.erl
	#erl -compile ybed.erl
	#erl -noshell -s server start
	#erl -noshell -s ybed_sup start_link

hello_world-test:
	ab -k -n 10000 -c 100 http://localhost:8800/erl/hello_world:service

hello_world-test-k:
	ab -k -n 10000 -c 100 http://localhost:8800/erl/hello_world:service