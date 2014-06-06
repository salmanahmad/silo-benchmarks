
function test(s) {
    return parseInt(s)
}

console.log("Starting JS.")

var start = new Date()

var list = []

for(l = 0; l < 1000000; l++) {
    for(i = 0; i < 1000; i++) {
        list.push(i)
    }

    for(i = 0; i < 1000; i++) {
        list.pop()
    }
}


console.log("Javascript Time: " + ((new Date()).getTime() - start.getTime()))