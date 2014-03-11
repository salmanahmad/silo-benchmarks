
def application(environ, start_response):
    status = '200 OK'
    output = 'Hello, World!'

    response_headers = [('Connection', 'Keep-Alive'), ('Content-Length', str(len(output)))]
    start_response(status, response_headers)
    return [output]