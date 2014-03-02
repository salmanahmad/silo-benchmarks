
# http://nichol.as/benchmark-of-python-web-servers

from gevent import wsgi

def application(environ, start_response):
    status = '200 OK'
    output = 'Hello, World!'

    response_headers = [('Content-type', 'text/plain'),
                        ('Content-Length', str(len(output)))]
    start_response(status, response_headers)
    return [output]


print("Python (GEvent) - Running on port 8200")

wsgi.WSGIServer(('', 8200), application, spawn=None, log=None).serve_forever()
