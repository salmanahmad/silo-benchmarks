
var fs = require('fs')

if(!fs.existsSync("output")) {
    fs.mkdirSync("output")
}

var contents = fs.readFileSync("../lipsum.txt")

for(var i = 0; i < 10000; i++) {
    fs.writeFileSync("output/lipsum-" + i + ".txt", contents)
}

