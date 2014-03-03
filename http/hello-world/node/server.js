
var http = require('http');

http.createServer(function (req, res) {
  var message = 'Hello, World!'
  res.writeHead(200, {'Content-Type': 'text/plain', 'Content-Length': message.length});
  res.end(message);
}).listen(8300, '127.0.0.1');

console.log('Javascript (Node) - Running on port 8300');

