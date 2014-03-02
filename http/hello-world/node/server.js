
var http = require('http');

http.createServer(function (req, res) {
  res.writeHead(200, {'Content-Type': 'text/plain'});
  res.end('Hello, World!');
}).listen(8300, '127.0.0.1');

console.log('Javascript (Node) - Running on port 8300');

