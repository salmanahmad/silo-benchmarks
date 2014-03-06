
var fs = require('fs')

if(!fs.existsSync("output")) {
    fs.mkdirSync("output")
}

var contents = fs.readFileSync("../lipsum.txt")

for(var i = 0; i < 1000; i++) {
    fs.writeFile("output/lipsum-" + i + ".txt", contents, function(err) {
        if(err) {
            console.log("Error: " + err)
        }
    })
}

