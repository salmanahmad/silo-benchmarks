
# http://nichol.as/benchmark-of-python-web-servers

from gevent import wsgi

def application(environ, start_response):
    status = '200 OK'
    output = 'Hello, World!'

    response_headers = [('Connection', 'Keep-Alive'), ('Content-Length', str(len(output)))]
    start_response(status, response_headers)
    return [output]


print("Python (GEvent) - Running on port 8200")

#spawn=None
wsgi.WSGIServer(('127.0.0.1', 8200), application, log=None).serve_forever()
