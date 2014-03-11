
# http://nichol.as/benchmark-of-python-web-servers

from twisted.web.server import Site
from twisted.web.wsgi import WSGIResource
from twisted.internet import reactor

def application(environ, start_response):
    status = '200 OK'
    output = 'Hello, World!'

    response_headers = [('Content-Length', str(len(output)))]
    start_response(status, response_headers)
    return [output]

print("Python (Twisted) - Running on port 8200")
 
resource = WSGIResource(reactor, reactor.getThreadPool(), application)
reactor.listenTCP(8200, Site(resource))
reactor.run()
