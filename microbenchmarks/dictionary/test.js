
function test(s) {
    return parseInt(s)
}

console.log("Starting JS.")

var start = new Date()

var list = {}

for(l = 0; l < 100000; l++) {
    for(i = 0; i < 1000; i++) {
        list[i] = ""
    }

    for(i = 0; i < 1000; i++) {
        delete list[i]
    }
}


console.log("Javascript Time: " + ((new Date()).getTime() - start.getTime()))