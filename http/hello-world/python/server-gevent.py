
# http://nichol.as/benchmark-of-python-web-servers

from gevent import wsgi
from app import application

print("Python (GEvent) - Running on port 8200")

#spawn=None
wsgi.WSGIServer(('127.0.0.1', 8200), application, log=None).serve_forever()
