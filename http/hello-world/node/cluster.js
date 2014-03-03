
var cluster = require('cluster')
var http = require('http')
var os = require('os')



if (cluster.isMaster) {
    console.log('Javascript (Node, Cluster) - Running on port 8301');

    for (i = 0; i < (os.cpus().length * 2); i += 1) {
        cluster.fork();
    }

    cluster.on('death', function (worker) {
        cluster.fork();
    });
} else {
    http.createServer(function (req, res) {
      var message = 'Hello, World!'
      res.writeHead(200, {'Content-Type': 'text/plain', 'Content-Length': message.length});
      res.end(message);
    }).listen(8301, '127.0.0.1');
}

