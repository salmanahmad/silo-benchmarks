
server:
	rm ./server
	go build server.go

server-threaded:
	rm ./server-threaded
	go build server-threaded.go

test-server:
	ab -n 100000 -c 100 http://127.0.0.1:8700/

test-server-k:
	ab -k -n 100000 -c 100 http://127.0.0.1:8700/

test-server-threaded:
	ab -n 100000 -c 100 http://127.0.0.1:8701/

test-server-threaded-k:
	ab -k -n 100000 -c 100 http://127.0.0.1:8701/
