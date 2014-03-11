
server-gevent:
	python server-gevent.py

server-tornado:
	python server-tornado.py

server-twisted:
	python server-twisted.py

server-gunicorn:
	# Python (Gunicorn) - Running on port 8203
	gunicorn -b :8203 -w 3 app:application

test-server-gevent:
	ab -n 100000 -c 100 127.0.0.1:8200/

test-server-gevent-k:
	ab -k -n 100000 -c 100 127.0.0.1:8200/

test-server-tornado:
	ab -n 100000 -c 100 127.0.0.1:8201/

test-server-tornado-k:
	ab -k -n 100000 -c 100 127.0.0.1:8201/

test-server-twisted:
	ab -n 100000 -c 100 127.0.0.1:8202/

test-server-twisted-k:
	ab -k -n 100000 -c 100 127.0.0.1:8202/

test-server-gunicorn:
	ab -n 100000 -c 100 127.0.0.1:8203/

test-server-gunicorn-k:
	ab -k -n 100000 -c 100 127.0.0.1:8203/
