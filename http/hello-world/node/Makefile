
server:
	node server.js

cluster:
	node cluster.js

test-server:
	ab -n 100000 -c 100 127.0.0.1:8300/

test-server-k:
	ab -k -n 100000 -c 100 127.0.0.1:8300/

test-cluster:
	ab -n 100000 -c 100 127.0.0.1:8301/

test-cluster-k:
	ab -k -n 100000 -c 100 127.0.0.1:8301/

