
function test(s) {
    return parseInt(s)
}

console.log("Starting JS.")

var start = new Date()

for(l = 0; l < 100000; l++) {
    for(i = 0; i < 1000; i++) {
        test("" + i);
    }
}


console.log("Javascript Time: " + ((new Date()).getTime() - start.getTime()))