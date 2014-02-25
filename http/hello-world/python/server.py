
# http://nichol.as/benchmark-of-python-web-servers

from gevent import wsgi

def application(environ, start_response):
    status = '200 OK'
    output = 'Pong!'
 
    response_headers = [('Content-type', 'text/plain'),
                        ('Content-Length', str(len(output)))]
    start_response(status, response_headers)
    return [output]


wsgi.WSGIServer(('', 8200), application, spawn=None).serve_forever()
