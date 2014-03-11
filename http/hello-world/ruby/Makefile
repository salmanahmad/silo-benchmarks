
thin:
	ruby thin.rb

puma:
	ruby puma.rb

test-thin:
	ab -n 5000 -c 100 127.0.0.1:8100/

test-thin-k:
	ab -k -n 5000 -c 100 127.0.0.1:8100/

test-puma:
	ab -n 10000 -c 100 127.0.0.1:8101/

test-puma-k:
	ab -k -n 10000 -c 100 127.0.0.1:8101/
