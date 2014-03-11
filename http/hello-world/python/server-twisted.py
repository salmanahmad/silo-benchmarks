
# http://nichol.as/benchmark-of-python-web-servers

from twisted.web.server import Site
from twisted.web.wsgi import WSGIResource
from twisted.internet import reactor

from app import application

print("Python (Twisted) - Running on port 8202")
 
resource = WSGIResource(reactor, reactor.getThreadPool(), application)
reactor.listenTCP(8202, Site(resource))
reactor.run()
