
import os
import tornado.httpserver
import tornado.ioloop
import tornado.wsgi
import sys

from app import application

print("Python (Tornado) - Running on port 8201")

container = tornado.wsgi.WSGIContainer(application)
http_server = tornado.httpserver.HTTPServer(container)
http_server.listen(8201)
tornado.ioloop.IOLoop.instance().start()